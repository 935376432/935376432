package com.test.netty;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.test.netty.ChannelHandlerContextBean.ConnetionState;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;

public class MainSiteServerHandler extends ChannelInboundHandlerAdapter {

    /** 内存维护ChannelHandlerContextBean的map */
    private Map<String,ChannelHandlerContextBean> ctxMap;

    /** 发送或测试 APDU 的超时   ： 认证应答-->认证确认之间的间隔，默认15s，测试帧等待超时 */
    public long T1_TIMEOUT = 15000;

    /** 无数据报文时确认的超时，t2<t1 :  传输时，主站确认的时间，默认10s*/
    public long T2_TIMEOUT = 10000;

    /** 长期空闲状态下发送测试帧的超时   ： 空闲时间超时时间  ，默认20s*/
    public long T3_TIMEOUT = 20000;

    /**
     * 构造函数 内存维护map
     * @param serverCtxMap
     */
    MainSiteServerHandler (Map<String,ChannelHandlerContextBean> serverCtxMap){
        ctxMap = serverCtxMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //如果是认证请求，回复认证请求，认证应答回复时间记录，起个线程开始计时t1时间（15s）
        // TODO

        //如果是认证确认，跟新ChannelHandlerContextBean中认证确认时间
        //TODO

        //如果是STARTDT激活报文  ，更新空闲计时开始时间dataLastTime
        //并发送确认报文
        //TODO

        System.out.println("SimpleServerHandler.channelRead");
        ByteBuf result = (ByteBuf) msg;
        //获取客户端ip
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        ChannelHandlerContextBean ctxBean = ctxMap.get(clientIP);
        if (ctxBean == null) {
            return ;
        }
        ctxBean.setOriByteBuf(result);
        MessageInfo message = new MessageInfo(result);
        if (message == null || message.getMsgType().equals(0)) {
            //记录日志，该报文格式无效
            return ;
        }
        //更新连接状态时间
        ctxBean.setDataLastTime(System.currentTimeMillis());

        //根据连接状态判断是什么操作
        switch (ctxBean.getState()) {
            case NULL:
                // 认证请求，回复认证请求，认证应答回复时间记录，起个线程开始计时t1时间（15s）
                byte[] encoded = authRequest(ctxBean, message, clientIP);
                if (encoded != null) {
                    ctx.write(encoded);
                    ctx.flush();
                    //连接状态更新
                    ctxBean.setState(ConnetionState.AUTH_ANSWER);
                } else {
                    // 断开连接
                    closeChannelHandlerContext(clientIP);
                }
                break;
            case AUTH_ANSWER:
                // 认证确认，确认随机数等
                authConfirm(ctxBean, message, clientIP);
                break;
            case AUTH_CONFIRM:
                // STARTDT激活 ，更新空闲计时开始时间dataLastTime
                byte[] startAnswer = startdtRequest(ctxBean, message, clientIP);
                if (startAnswer != null) {
                    ctx.write(startAnswer);
                    ctx.flush();
                    //连接状态更新
                    ctxBean.setState(ConnetionState.STARTDTCONFIRM);
                    //验证t3超时时间
                    T3Monitor T3Monitor = new T3Monitor(clientIP);
                    Thread thread = new Thread(T3Monitor);
                    thread.setName(clientIP + "_t3_monitor");
                    thread.start();
                } else {
                    // 断开连接
                    closeChannelHandlerContext(clientIP);
                }
                break;
            case STARTDTCONFIRM:
                break;
            case CONNECTION:
                break;
            default :
                //TODO 记录日志，无用的报文格式
                // 断开连接
                closeChannelHandlerContext(clientIP);
                break;
        }


        byte[] result1 = new byte[result.readableBytes()];
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
        result.readBytes(result1);
        String resultStr = new String(result1);
        // 接收并打印客户端的信息
        System.out.println("Client said:" + resultStr);
        // 释放资源
        result.release();

        // 向客户端发送消息
        String response = "hello client!";
        // 在当前场景下，发送的数据必须转换成ByteBuf数组
        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
        encoded.writeBytes(response.getBytes());
        /*ctx.write(encoded);
        ctx.flush();*/
    }

    //报文内容总长度         2 字节  网络字节序
    //当前报文内容偏移量  2 字节  网络字节序（从 0x01 开始，0 表示未分帧）
    //报文内容长度             1 字节  当前帧报文内容长度
    //监测装置名称             64 字节  字符串， 如果包含中文则采用 utf8 格式编码
    //监测装置 IP 地址       4 字节  网络字节序
    //随机数 r1          8 字节  由监测装置生成
    //签名结果值                 64 字节 为启动字符（含）到签名结果值之间所有数据的 SM2 签名*/
    /**
     * 认证请求 ，如果是认证请求，回复认证请求，认证应答回复时间记录，起个线程开始计时t1时间（15s）
     * @param ctxBean
     * @param message
     * @return
     */
    public byte[] authRequest(ChannelHandlerContextBean ctxBean, MessageInfo message, String clientIP) {
        if (!message.getMsgType().equals(3) || message.getMark() != 0x91) {
            return null;
        }
        //获取消息体
        ByteBuf buf = message.getBody();
        //报文内容总长度
        byte[] allLength = new byte[2];
        buf.readBytes(allLength);
        //报文内容偏移量
        byte[] offset = new byte[2];
        buf.readBytes(offset);
        //报文内容长度
        buf.readByte();
        //监测装置名称
        byte[] monitorName = new byte[64];
        buf.readBytes(monitorName);
        //监测装置ip
        byte[] monitorIP = new byte[4];
        buf.readBytes(monitorIP);
        //随机数
        byte[] random1 = new byte[8];
        buf.readBytes(random1);
        //签名结果值
        byte[] sign = new byte[64];
        buf.readBytes(sign);
        //验证签名 sign TODO

        //验证t1超时时间
        T1Monitor T1Monitor = new T1Monitor(clientIP);
        Thread thread = new Thread(T1Monitor);
        thread.setName(clientIP + "_t1_monitor");
        thread.start();
        //返回认证应答 TODO
        //报文内容总长度            2 字节  网络字节序
        //当前报文内容偏移量      2 字节  网络字节序（从 0x01 开始，0 表示未分帧）
        // 报文内容长度            1 字节  当前帧报文内容长度
        //主站平台名称             64 字节  字符串， 如果包含中文则采用 utf8 格式编码
        //主站平台 IP 地址        4 字节  网络字节序
        //随机数 r1            8 字节  从认证请求报文中获取
        //随机数 r2            8 字节  由平台生成
        //签名结果值                 64 字节 为启动字符（含）到签名结果值之间所有数 据的 SM2 签名
        ByteBuffer rtnBuf = ByteBuffer.allocate(2 + 2 + 1 + 64 + 4+ 8+ 8 + 64);
        //TODO 生成随机数r2  需要放到ChannelHandlerContextBean中
        return rtnBuf.array();
    }

    //报文内容总长度  2 字节  网络字节序
    //当前报文内容偏移量  2 字节  网络字节序（从 0x01 开始，0 表示未分帧）
    //报文内容长度  1 字节  当前帧报文内容长度
    //报文内容  随机数 r2  8 字节  从认证应答报文中获取
    //签名结果值  64 字节 为启动字符（含）到签名结果值之间所有数 据的 SM2 签名
    /**
     * 认证确认
     * @param ctxBean
     * @param message
     * @return
     */
    public void authConfirm(ChannelHandlerContextBean ctxBean,MessageInfo message,String clientIP) {
        if (!message.getMsgType().equals(3) || message.getMark() != 0x93) {
            //TODO 连接数据不对 断开连接
        }
        //获取消息体
        ByteBuf buf = message.getBody();
        //报文内容总长度
        byte[] allLength = new byte[2];
        buf.readBytes(allLength);
        //报文内容偏移量
        byte[] offset = new byte[2];
        buf.readBytes(offset);
        //报文内容长度
        buf.readByte();
        //随机数r2
        byte[] random2 = new byte[8];
        buf.readBytes(random2);
        //TODO 比较随机数是否匹配
        //签名值
        byte[] sign = new byte[64];
        buf.readBytes(sign);
        //TODO 验证签名

        //跟新连接状态
        ctxBean.setState(ConnetionState.AUTH_CONFIRM);
    }

    /**
     * STARTDT激活
     * @param ctxBean
     * @param message
     * @return
     */
    public byte[] startdtRequest(ChannelHandlerContextBean ctxBean,MessageInfo message,String clientIP) {
        if (!message.getMsgType().equals(1) || message.getMark() != 0x93) {
            //TODO 连接数据不对 断开连接
        }
        //返回U型STARTDT激活确认
        byte[] rtnStartdt = buildUMsg();
        return rtnStartdt;
    }

    /**
     * 连接成功之后的报文
     * @param ctxBean
     * @param message
     * @return
     */
    public byte[] connectionRequest(ChannelHandlerContextBean ctxBean,MessageInfo message,String clientIP) {
        if (!(message.getMsgType().equals(3) || message.getMsgType().equals(1))) {
            //TODO 连接数据不对 断开连接
        }
        if (message.getMsgType().equals(3)) {
            //如果是U型报文，代表是测试帧回复报文，测试帧时间清空
            ctxBean.setTestDataTime(null);
        }
        //TODO 发送的数据进行保存或者xxx
        //返回S型报文进行确认
        byte[] rtnStartdt = buildUMsg();
        return rtnStartdt;
    }

    /**
     * U型报文
     * @return 报文
     */
    public byte[] buildUMsg() {
        ByteBuffer rtnBuf = ByteBuffer.allocate(6);
        //启动字符68H
        rtnBuf.put((byte) 0x68);
        //APDU长度
        rtnBuf.put((byte) 0x4);
        //四个控制域
        rtnBuf.put((byte) 0x1);
        rtnBuf.put((byte) 0x0);
        rtnBuf.put((byte) 0x0);
        rtnBuf.put((byte) 0x0);
        return rtnBuf.array();
    }

    /**
     * S型报文
     * @return 报文
     */
    public byte[] buildSMsg() {
        ByteBuffer rtnBuf = ByteBuffer.allocate(6);
        //启动字符68H
        rtnBuf.put((byte) 0x68);
        //APDU长度
        rtnBuf.put((byte) 0x4);
        //四个控制域
        rtnBuf.put((byte) 0x11);
        rtnBuf.put((byte) 0x0);
        rtnBuf.put((byte) 0x0);
        rtnBuf.put((byte) 0x0);
        return rtnBuf.array();
    }

    /**
     * 关闭连接
     * @param clientIP
     */
    public void closeChannelHandlerContext(String clientIP) {
        if (ctxMap.containsKey(clientIP)) {
            ChannelHandlerContextBean ctxBean = ctxMap.get("ctx");
            ChannelHandlerContext ctx = ctxBean.getCtx();
            ctx.close();
            ctxMap.remove(clientIP);
        }
    }


    /**
     * 连接开始
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
        System.out.println("SimpleServerHandler.channelActive");
        //获取ip
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        //判断该ip是否已经连接上来，如果该ip已经连接上，则断开连接
        if (ctxMap.containsKey(clientIP)) {
            ctx.close();
        }
        ChannelHandlerContextBean ctxBean = new ChannelHandlerContextBean();
        ctxBean.setClientIP(clientIP);
        ctxBean.setChannel(ctx.channel());
        ctxBean.setCtx(ctx);
        ctxMap.put(clientIP, ctxBean);
    }

    // 如果是认证请求，回复认证请求，认证应答回复时间记录，起个线程开始计时t1时间（15s）
    class T1Monitor implements Runnable {
        String clientIP = "";

        T1Monitor(String clientIP) {
            this.clientIP = clientIP;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(T1_TIMEOUT);
                if (ctxMap.containsKey(clientIP)) {
                    ChannelHandlerContextBean ctxBean = ctxMap.get("ctx");
                    if (ConnetionState.NULL.equals(ctxBean.getState())
                        || ConnetionState.AUTH_REQUEST.equals(ctxBean.getState())
                        || ConnetionState.AUTH_ANSWER.equals(ctxBean.getState())) {
                        // T1超时，断开连接，
                        closeChannelHandlerContext(clientIP);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }
        }
    }

    /**
     * t3 测试类，长期空闲状态下发送测试帧的超时   ： 空闲时间超时时间  ，默认20s
     *
     */
    class T3Monitor implements Runnable {
        String clientIP = "";

        T3Monitor(String clientIP) {
            this.clientIP = clientIP;
        }

        @Override
        public void run() {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //检查该连接是不是空闲20s,如果空闲20s则发送测试报文
                    checkDateTimeT3(clientIP);
                }
            }, 0, 1000);
        }

        /**
         * 检查该连接是不是空闲20s,如果空闲20s则发送测试报文
         * @param clientIP 客户端ip
         */
        public void checkDateTimeT3(String clientIP) {
            if (ctxMap.containsKey(clientIP)) {
                ChannelHandlerContextBean ctxBean = ctxMap.get("ctx");
                ChannelHandlerContext ctx = ctxBean.getCtx();
                Long lastDataTime = ctxBean.getDataLastTime();
                Long testDataTime = ctxBean.getTestDataTime();
                long time = System.currentTimeMillis();
                if ((time - lastDataTime)/1000 > 20 ) {
                    byte[] uMsg = buildUMsg();
                    //发送测试报文
                    ctx.write(uMsg);
                    ctx.flush();
                    //设置发送测试报文时间
                    ctxBean.setTestDataTime(System.currentTimeMillis());
                }
                if (testDataTime != null && ((time - testDataTime)/1000 > 15 )){
                    //测试帧报文等待超时，超过15s,断开连接
                    closeChannelHandlerContext(clientIP);
                }

            }
        }

    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
        System.out.println("SimpleServerHandler.exceptionCaught");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("SimpleServerHandler.channelReadComplete");
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelRegistered();
        System.out.println("SimpleServerHandler.channelRegistered");
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelUnregistered()} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelUnregistered();
        System.out.println("SimpleServerHandler.channelUnregistered");
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelInactive()} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelInactive();
        System.out.println("SimpleServerHandler.channelInactive");
    }

    /**
     * Calls {@link ChannelHandlerContext#fireUserEventTriggered(Object)} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        ctx.fireUserEventTriggered(evt);
        System.out.println("SimpleServerHandler.userEventTriggered");
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelWritabilityChanged()} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelWritabilityChanged();
        System.out.println("SimpleServerHandler.channelWritabilityChanged");
    }


    /**
     * U格式控制域标志：第一个八位位组的第一位比特 = 1 并且第二位比特 =1,  第三个八位位组第一位比特 = 0
     * S格式控制域标志：第一个八位位组的第一位比特 = 1 并且第二位比特 = 0 ,第三个八位位组第一位比特 = 0
     * I格式控制域标志：第一个八位位组的第一位比特 = 0 ,                 第三个八位位组第一位比特 = 0
     * @param b1 控制域1
     * @param b3 控制域1
     * @return 报文类型
     */
    public static String getMsgType(byte b1,byte b3) {
        int co1 = b1 & 0x3;
        if (co1 == 3 && b3 == 0x0) {
            return "U";
        } else if (co1 == 1 && b3 == 0x0) {
            return "S";
        } else if (co1 == 0 && b3 == 0x0) {
            return "I";
        }
        return null;
    }

    /*//启动字符
    result.readByte();
    //APDU长度
    result.readByte();
    byte[] control = new byte[4];
    result.readBytes(control);
    //U格式控制域标志：第一个八位位组的第一位比特 = 1 并且第二位比特 =1,  第三个八位位组第一位比特 = 0
    //S格式控制域标志：第一个八位位组的第一位比特 = 1 并且第二位比特 = 0 ,第三个八位位组第一位比特 = 0
    //I格式控制域标志：第一个八位位组的第一位比特 = 0 ,                 第三个八位位组第一位比特 = 0
    if (control[3] == 0) {
        switch (control[0] & 0x3) {
            case 3 :
                //U格式
                break;
            case 1 :
                //S格式
                break;
            case 0 :
                //I格式
                break;
            default :
                //TODO 记录日志，无用的报文格式
                break;
        }
    }*/

}