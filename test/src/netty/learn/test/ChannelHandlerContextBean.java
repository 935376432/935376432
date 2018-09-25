/*
 *jiji java
 */
package netty.learn.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class ChannelHandlerContextBean {

    enum ConnetionState {
        NULL, AUTH_REQUEST, AUTH_ANSWER, AUTH_CONFIRM, STARTDTREQUEST, STARTDTCONFIRM, CONNECTION
    }

    /** ChannelHandlerContext对象 */
    private ChannelHandlerContext ctx;

    /** channel 信息 */
    private Channel channel;

    /** 客户端访问ip */
    private String clientIP;

    /** 原始ByteBuf */
    private ByteBuf oriByteBuf;

    /** 认证请求时间 */
    private Long requestTime;

    /** 认证确认时间 */
    private Long answerTime;

    /** 空闲计时开始时间 */
    private Long dataLastTime;

    /** 空闲测试报文时间 */
    private Long testDataTime;

    /** 随机数r1 厂站生成*/
    private byte[] randomR1;

    /** 随机数r2 主站生成 */
    private byte[] randomR2;

    /** 测试帧发送时间，存放时间的队列 */

    /** 是否已经认证，是数据传输状态。true：是，false：否 */
    private Boolean isAuth = false;

    /** 连接状态 */
    private ConnetionState state = ConnetionState.NULL;


    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public ByteBuf getOriByteBuf() {
        return oriByteBuf;
    }

    public void setOriByteBuf(ByteBuf oriByteBuf) {
        this.oriByteBuf = oriByteBuf;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    public Long getDataLastTime() {
        return dataLastTime;
    }

    public void setDataLastTime(Long dataLastTime) {
        this.dataLastTime = dataLastTime;
    }

    public Long getTestDataTime() {
        return testDataTime;
    }

    public void setTestDataTime(Long testDataTime) {
        this.testDataTime = testDataTime;
    }

    public byte[] getRandomR1() {
        return randomR1;
    }

    public void setRandomR1(byte[] randomR1) {
        this.randomR1 = randomR1;
    }

    public byte[] getRandomR2() {
        return randomR2;
    }

    public void setRandomR2(byte[] randomR2) {
        this.randomR2 = randomR2;
    }

    public Boolean getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Boolean isAuth) {
        this.isAuth = isAuth;
    }

    public ConnetionState getState() {
        return state;
    }

    public void setState(ConnetionState state) {
        this.state = state;
    }




}
