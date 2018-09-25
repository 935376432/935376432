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

    /** �ڴ�ά��ChannelHandlerContextBean��map */
    private Map<String,ChannelHandlerContextBean> ctxMap;

    /** ���ͻ���� APDU �ĳ�ʱ   �� ��֤Ӧ��-->��֤ȷ��֮��ļ����Ĭ��15s������֡�ȴ���ʱ */
    public long T1_TIMEOUT = 15000;

    /** �����ݱ���ʱȷ�ϵĳ�ʱ��t2<t1 :  ����ʱ����վȷ�ϵ�ʱ�䣬Ĭ��10s*/
    public long T2_TIMEOUT = 10000;

    /** ���ڿ���״̬�·��Ͳ���֡�ĳ�ʱ   �� ����ʱ�䳬ʱʱ��  ��Ĭ��20s*/
    public long T3_TIMEOUT = 20000;

    /**
     * ���캯�� �ڴ�ά��map
     * @param serverCtxMap
     */
    MainSiteServerHandler (Map<String,ChannelHandlerContextBean> serverCtxMap){
        ctxMap = serverCtxMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //�������֤���󣬻ظ���֤������֤Ӧ��ظ�ʱ���¼������߳̿�ʼ��ʱt1ʱ�䣨15s��
        // TODO

        //�������֤ȷ�ϣ�����ChannelHandlerContextBean����֤ȷ��ʱ��
        //TODO

        //�����STARTDT�����  �����¿��м�ʱ��ʼʱ��dataLastTime
        //������ȷ�ϱ���
        //TODO

        System.out.println("SimpleServerHandler.channelRead");
        ByteBuf result = (ByteBuf) msg;
        //��ȡ�ͻ���ip
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        ChannelHandlerContextBean ctxBean = ctxMap.get(clientIP);
        if (ctxBean == null) {
            return ;
        }
        ctxBean.setOriByteBuf(result);
        MessageInfo message = new MessageInfo(result);
        if (message == null || message.getMsgType().equals(0)) {
            //��¼��־���ñ��ĸ�ʽ��Ч
            return ;
        }
        //��������״̬ʱ��
        ctxBean.setDataLastTime(System.currentTimeMillis());

        //��������״̬�ж���ʲô����
        switch (ctxBean.getState()) {
            case NULL:
                // ��֤���󣬻ظ���֤������֤Ӧ��ظ�ʱ���¼������߳̿�ʼ��ʱt1ʱ�䣨15s��
                byte[] encoded = authRequest(ctxBean, message, clientIP);
                if (encoded != null) {
                    ctx.write(encoded);
                    ctx.flush();
                    //����״̬����
                    ctxBean.setState(ConnetionState.AUTH_ANSWER);
                } else {
                    // �Ͽ�����
                    closeChannelHandlerContext(clientIP);
                }
                break;
            case AUTH_ANSWER:
                // ��֤ȷ�ϣ�ȷ���������
                authConfirm(ctxBean, message, clientIP);
                break;
            case AUTH_CONFIRM:
                // STARTDT���� �����¿��м�ʱ��ʼʱ��dataLastTime
                byte[] startAnswer = startdtRequest(ctxBean, message, clientIP);
                if (startAnswer != null) {
                    ctx.write(startAnswer);
                    ctx.flush();
                    //����״̬����
                    ctxBean.setState(ConnetionState.STARTDTCONFIRM);
                    //��֤t3��ʱʱ��
                    T3Monitor T3Monitor = new T3Monitor(clientIP);
                    Thread thread = new Thread(T3Monitor);
                    thread.setName(clientIP + "_t3_monitor");
                    thread.start();
                } else {
                    // �Ͽ�����
                    closeChannelHandlerContext(clientIP);
                }
                break;
            case STARTDTCONFIRM:
                break;
            case CONNECTION:
                break;
            default :
                //TODO ��¼��־�����õı��ĸ�ʽ
                // �Ͽ�����
                closeChannelHandlerContext(clientIP);
                break;
        }


        byte[] result1 = new byte[result.readableBytes()];
        // msg�д洢����ByteBuf���͵����ݣ������ݶ�ȡ��byte[]��
        result.readBytes(result1);
        String resultStr = new String(result1);
        // ���ղ���ӡ�ͻ��˵���Ϣ
        System.out.println("Client said:" + resultStr);
        // �ͷ���Դ
        result.release();

        // ��ͻ��˷�����Ϣ
        String response = "hello client!";
        // �ڵ�ǰ�����£����͵����ݱ���ת����ByteBuf����
        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
        encoded.writeBytes(response.getBytes());
        /*ctx.write(encoded);
        ctx.flush();*/
    }

    //���������ܳ���         2 �ֽ�  �����ֽ���
    //��ǰ��������ƫ����  2 �ֽ�  �����ֽ��򣨴� 0x01 ��ʼ��0 ��ʾδ��֡��
    //�������ݳ���             1 �ֽ�  ��ǰ֡�������ݳ���
    //���װ������             64 �ֽ�  �ַ����� ���������������� utf8 ��ʽ����
    //���װ�� IP ��ַ       4 �ֽ�  �����ֽ���
    //����� r1          8 �ֽ�  �ɼ��װ������
    //ǩ�����ֵ                 64 �ֽ� Ϊ�����ַ���������ǩ�����ֵ֮���������ݵ� SM2 ǩ��*/
    /**
     * ��֤���� ���������֤���󣬻ظ���֤������֤Ӧ��ظ�ʱ���¼������߳̿�ʼ��ʱt1ʱ�䣨15s��
     * @param ctxBean
     * @param message
     * @return
     */
    public byte[] authRequest(ChannelHandlerContextBean ctxBean, MessageInfo message, String clientIP) {
        if (!message.getMsgType().equals(3) || message.getMark() != 0x91) {
            return null;
        }
        //��ȡ��Ϣ��
        ByteBuf buf = message.getBody();
        //���������ܳ���
        byte[] allLength = new byte[2];
        buf.readBytes(allLength);
        //��������ƫ����
        byte[] offset = new byte[2];
        buf.readBytes(offset);
        //�������ݳ���
        buf.readByte();
        //���װ������
        byte[] monitorName = new byte[64];
        buf.readBytes(monitorName);
        //���װ��ip
        byte[] monitorIP = new byte[4];
        buf.readBytes(monitorIP);
        //�����
        byte[] random1 = new byte[8];
        buf.readBytes(random1);
        //ǩ�����ֵ
        byte[] sign = new byte[64];
        buf.readBytes(sign);
        //��֤ǩ�� sign TODO

        //��֤t1��ʱʱ��
        T1Monitor T1Monitor = new T1Monitor(clientIP);
        Thread thread = new Thread(T1Monitor);
        thread.setName(clientIP + "_t1_monitor");
        thread.start();
        //������֤Ӧ�� TODO
        //���������ܳ���            2 �ֽ�  �����ֽ���
        //��ǰ��������ƫ����      2 �ֽ�  �����ֽ��򣨴� 0x01 ��ʼ��0 ��ʾδ��֡��
        // �������ݳ���            1 �ֽ�  ��ǰ֡�������ݳ���
        //��վƽ̨����             64 �ֽ�  �ַ����� ���������������� utf8 ��ʽ����
        //��վƽ̨ IP ��ַ        4 �ֽ�  �����ֽ���
        //����� r1            8 �ֽ�  ����֤�������л�ȡ
        //����� r2            8 �ֽ�  ��ƽ̨����
        //ǩ�����ֵ                 64 �ֽ� Ϊ�����ַ���������ǩ�����ֵ֮�������� �ݵ� SM2 ǩ��
        ByteBuffer rtnBuf = ByteBuffer.allocate(2 + 2 + 1 + 64 + 4+ 8+ 8 + 64);
        //TODO ���������r2  ��Ҫ�ŵ�ChannelHandlerContextBean��
        return rtnBuf.array();
    }

    //���������ܳ���  2 �ֽ�  �����ֽ���
    //��ǰ��������ƫ����  2 �ֽ�  �����ֽ��򣨴� 0x01 ��ʼ��0 ��ʾδ��֡��
    //�������ݳ���  1 �ֽ�  ��ǰ֡�������ݳ���
    //��������  ����� r2  8 �ֽ�  ����֤Ӧ�����л�ȡ
    //ǩ�����ֵ  64 �ֽ� Ϊ�����ַ���������ǩ�����ֵ֮�������� �ݵ� SM2 ǩ��
    /**
     * ��֤ȷ��
     * @param ctxBean
     * @param message
     * @return
     */
    public void authConfirm(ChannelHandlerContextBean ctxBean,MessageInfo message,String clientIP) {
        if (!message.getMsgType().equals(3) || message.getMark() != 0x93) {
            //TODO �������ݲ��� �Ͽ�����
        }
        //��ȡ��Ϣ��
        ByteBuf buf = message.getBody();
        //���������ܳ���
        byte[] allLength = new byte[2];
        buf.readBytes(allLength);
        //��������ƫ����
        byte[] offset = new byte[2];
        buf.readBytes(offset);
        //�������ݳ���
        buf.readByte();
        //�����r2
        byte[] random2 = new byte[8];
        buf.readBytes(random2);
        //TODO �Ƚ�������Ƿ�ƥ��
        //ǩ��ֵ
        byte[] sign = new byte[64];
        buf.readBytes(sign);
        //TODO ��֤ǩ��

        //��������״̬
        ctxBean.setState(ConnetionState.AUTH_CONFIRM);
    }

    /**
     * STARTDT����
     * @param ctxBean
     * @param message
     * @return
     */
    public byte[] startdtRequest(ChannelHandlerContextBean ctxBean,MessageInfo message,String clientIP) {
        if (!message.getMsgType().equals(1) || message.getMark() != 0x93) {
            //TODO �������ݲ��� �Ͽ�����
        }
        //����U��STARTDT����ȷ��
        byte[] rtnStartdt = buildUMsg();
        return rtnStartdt;
    }

    /**
     * ���ӳɹ�֮��ı���
     * @param ctxBean
     * @param message
     * @return
     */
    public byte[] connectionRequest(ChannelHandlerContextBean ctxBean,MessageInfo message,String clientIP) {
        if (!(message.getMsgType().equals(3) || message.getMsgType().equals(1))) {
            //TODO �������ݲ��� �Ͽ�����
        }
        if (message.getMsgType().equals(3)) {
            //�����U�ͱ��ģ������ǲ���֡�ظ����ģ�����֡ʱ�����
            ctxBean.setTestDataTime(null);
        }
        //TODO ���͵����ݽ��б������xxx
        //����S�ͱ��Ľ���ȷ��
        byte[] rtnStartdt = buildUMsg();
        return rtnStartdt;
    }

    /**
     * U�ͱ���
     * @return ����
     */
    public byte[] buildUMsg() {
        ByteBuffer rtnBuf = ByteBuffer.allocate(6);
        //�����ַ�68H
        rtnBuf.put((byte) 0x68);
        //APDU����
        rtnBuf.put((byte) 0x4);
        //�ĸ�������
        rtnBuf.put((byte) 0x1);
        rtnBuf.put((byte) 0x0);
        rtnBuf.put((byte) 0x0);
        rtnBuf.put((byte) 0x0);
        return rtnBuf.array();
    }

    /**
     * S�ͱ���
     * @return ����
     */
    public byte[] buildSMsg() {
        ByteBuffer rtnBuf = ByteBuffer.allocate(6);
        //�����ַ�68H
        rtnBuf.put((byte) 0x68);
        //APDU����
        rtnBuf.put((byte) 0x4);
        //�ĸ�������
        rtnBuf.put((byte) 0x11);
        rtnBuf.put((byte) 0x0);
        rtnBuf.put((byte) 0x0);
        rtnBuf.put((byte) 0x0);
        return rtnBuf.array();
    }

    /**
     * �ر�����
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
     * ���ӿ�ʼ
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
        System.out.println("SimpleServerHandler.channelActive");
        //��ȡip
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        //�жϸ�ip�Ƿ��Ѿ����������������ip�Ѿ������ϣ���Ͽ�����
        if (ctxMap.containsKey(clientIP)) {
            ctx.close();
        }
        ChannelHandlerContextBean ctxBean = new ChannelHandlerContextBean();
        ctxBean.setClientIP(clientIP);
        ctxBean.setChannel(ctx.channel());
        ctxBean.setCtx(ctx);
        ctxMap.put(clientIP, ctxBean);
    }

    // �������֤���󣬻ظ���֤������֤Ӧ��ظ�ʱ���¼������߳̿�ʼ��ʱt1ʱ�䣨15s��
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
                        // T1��ʱ���Ͽ����ӣ�
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
     * t3 �����࣬���ڿ���״̬�·��Ͳ���֡�ĳ�ʱ   �� ����ʱ�䳬ʱʱ��  ��Ĭ��20s
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
                    //���������ǲ��ǿ���20s,�������20s���Ͳ��Ա���
                    checkDateTimeT3(clientIP);
                }
            }, 0, 1000);
        }

        /**
         * ���������ǲ��ǿ���20s,�������20s���Ͳ��Ա���
         * @param clientIP �ͻ���ip
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
                    //���Ͳ��Ա���
                    ctx.write(uMsg);
                    ctx.flush();
                    //���÷��Ͳ��Ա���ʱ��
                    ctxBean.setTestDataTime(System.currentTimeMillis());
                }
                if (testDataTime != null && ((time - testDataTime)/1000 > 15 )){
                    //����֡���ĵȴ���ʱ������15s,�Ͽ�����
                    closeChannelHandlerContext(clientIP);
                }

            }
        }

    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // �������쳣�͹ر�����
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
     * U��ʽ�������־����һ����λλ��ĵ�һλ���� = 1 ���ҵڶ�λ���� =1,  ��������λλ���һλ���� = 0
     * S��ʽ�������־����һ����λλ��ĵ�һλ���� = 1 ���ҵڶ�λ���� = 0 ,��������λλ���һλ���� = 0
     * I��ʽ�������־����һ����λλ��ĵ�һλ���� = 0 ,                 ��������λλ���һλ���� = 0
     * @param b1 ������1
     * @param b3 ������1
     * @return ��������
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

    /*//�����ַ�
    result.readByte();
    //APDU����
    result.readByte();
    byte[] control = new byte[4];
    result.readBytes(control);
    //U��ʽ�������־����һ����λλ��ĵ�һλ���� = 1 ���ҵڶ�λ���� =1,  ��������λλ���һλ���� = 0
    //S��ʽ�������־����һ����λλ��ĵ�һλ���� = 1 ���ҵڶ�λ���� = 0 ,��������λλ���һλ���� = 0
    //I��ʽ�������־����һ����λλ��ĵ�һλ���� = 0 ,                 ��������λλ���һλ���� = 0
    if (control[3] == 0) {
        switch (control[0] & 0x3) {
            case 3 :
                //U��ʽ
                break;
            case 1 :
                //S��ʽ
                break;
            case 0 :
                //I��ʽ
                break;
            default :
                //TODO ��¼��־�����õı��ĸ�ʽ
                break;
        }
    }*/

}