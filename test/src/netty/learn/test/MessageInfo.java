/*
 *jiji java
 */
package netty.learn.test;

import io.netty.buffer.ByteBuf;

public class MessageInfo {

    /** 启动字符68H */
    private byte startCharacter;

    /** 控制域 1 2 3 4  */
    private byte[] control;

    /** 报文类型 0 - 无用格式， 1 - U, 2 - S, 3 - I */
    private Integer MsgType;

    /** 类型标识 */
    private byte mark;

    /** 可变结构词  ，传输原因 ，公共地址， 信息体地址  */
    private byte[] structure;

    /** 消息体 */
    private ByteBuf body;

    public MessageInfo(ByteBuf buf) {
        this.startCharacter = buf.readByte();
        byte[] controls =  new byte[4];
        buf.readBytes(controls);
        this.control = controls;
        MsgType = getMsgType(controls);
        this.mark = buf.readByte();
        byte[] structures =  new byte[4];
        buf.readBytes(structures);
        this.structure = structures;
        this.body = buf;
    }

    //U格式控制域标志：第一个八位位组的第一位比特 = 1 并且第二位比特 =1,  第三个八位位组第一位比特 = 0
    //S格式控制域标志：第一个八位位组的第一位比特 = 1 并且第二位比特 = 0 ,第三个八位位组第一位比特 = 0
    //I格式控制域标志：第一个八位位组的第一位比特 = 0 ,                 第三个八位位组第一位比特 = 0
    /**
     * 根据控制域报文返回报文类型
     * @param controls 控制域信息
     * @return 报文类型
     */
    public Integer getMsgType (byte[] controls) {

        if ((control[2] & 0x1)  == 0) {
            switch (control[0] & 0x3) {
                case 3 :
                    //U格式
                    return 1;
                case 1 :
                    //S格式
                    return 2;
                case 0 :
                    //I格式
                    return 3;
                default :
                    //无用的报文格式
                    return 0;
            }
        } else {
            return 0;
        }
    }

    public byte getStartCharacter() {
        return startCharacter;
    }

    public void setStartCharacter(byte startCharacter) {
        this.startCharacter = startCharacter;
    }

    public byte[] getControl() {
        return control;
    }

    public void setControl(byte[] control) {
        this.control = control;
    }

    public Integer getMsgType() {
        return MsgType;
    }

    public void setMsgType(Integer msgType) {
        MsgType = msgType;
    }

    public byte getMark() {
        return mark;
    }

    public void setMark(byte mark) {
        this.mark = mark;
    }

    public byte[] getStructure() {
        return structure;
    }

    public void setStructure(byte[] structure) {
        this.structure = structure;
    }

    public ByteBuf getBody() {
        return body;
    }

    public void setBody(ByteBuf body) {
        this.body = body;
    }



}
