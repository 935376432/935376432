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

    /** ChannelHandlerContext���� */
    private ChannelHandlerContext ctx;

    /** channel ��Ϣ */
    private Channel channel;

    /** �ͻ��˷���ip */
    private String clientIP;

    /** ԭʼByteBuf */
    private ByteBuf oriByteBuf;

    /** ��֤����ʱ�� */
    private Long requestTime;

    /** ��֤ȷ��ʱ�� */
    private Long answerTime;

    /** ���м�ʱ��ʼʱ�� */
    private Long dataLastTime;

    /** ���в��Ա���ʱ�� */
    private Long testDataTime;

    /** �����r1 ��վ����*/
    private byte[] randomR1;

    /** �����r2 ��վ���� */
    private byte[] randomR2;

    /** ����֡����ʱ�䣬���ʱ��Ķ��� */

    /** �Ƿ��Ѿ���֤�������ݴ���״̬��true���ǣ�false���� */
    private Boolean isAuth = false;

    /** ����״̬ */
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
