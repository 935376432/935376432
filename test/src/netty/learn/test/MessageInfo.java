/*
 *jiji java
 */
package netty.learn.test;

import io.netty.buffer.ByteBuf;

public class MessageInfo {

    /** �����ַ�68H */
    private byte startCharacter;

    /** ������ 1 2 3 4  */
    private byte[] control;

    /** �������� 0 - ���ø�ʽ�� 1 - U, 2 - S, 3 - I */
    private Integer MsgType;

    /** ���ͱ�ʶ */
    private byte mark;

    /** �ɱ�ṹ��  ������ԭ�� ��������ַ�� ��Ϣ���ַ  */
    private byte[] structure;

    /** ��Ϣ�� */
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

    //U��ʽ�������־����һ����λλ��ĵ�һλ���� = 1 ���ҵڶ�λ���� =1,  ��������λλ���һλ���� = 0
    //S��ʽ�������־����һ����λλ��ĵ�һλ���� = 1 ���ҵڶ�λ���� = 0 ,��������λλ���һλ���� = 0
    //I��ʽ�������־����һ����λλ��ĵ�һλ���� = 0 ,                 ��������λλ���һλ���� = 0
    /**
     * ���ݿ������ķ��ر�������
     * @param controls ��������Ϣ
     * @return ��������
     */
    public Integer getMsgType (byte[] controls) {

        if ((control[2] & 0x1)  == 0) {
            switch (control[0] & 0x3) {
                case 3 :
                    //U��ʽ
                    return 1;
                case 1 :
                    //S��ʽ
                    return 2;
                case 0 :
                    //I��ʽ
                    return 3;
                default :
                    //���õı��ĸ�ʽ
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
