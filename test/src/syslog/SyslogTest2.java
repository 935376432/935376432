package syslog;
import java.net.URLDecoder;

import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;

/**
 * SysLog��������
 */
public class SyslogTest2 {

    public static void main(String[] args){
        try {
            //��ȡsyslog�Ĳ����࣬ʹ��udpЭ�顣syslog֧��"udp", "tcp", "unix_syslog", "unix_socket"Э��
            SyslogIF syslog = Syslog.getInstance("udp");
            //����syslog�������˵�ַ
            syslog.getConfig().setHost("192.168.21.198");
            //����syslog���ն˿ڣ�Ĭ��514
            syslog.getConfig().setPort(514);
            //ƴ��syslog��־�������־���Լ�����ģ�ͨ�����Ƕ���ɷ��Ϲ�˾�淶�ĸ�ʽ���У������ѯ������ ����ʱ�䣺2014��8��1��  ������ID:���� �ȡ���Ϣ����һ���ַ�����
            StringBuffer buffer = new StringBuffer();
            /*buffer.append("����ʱ�䣺" + new Date().toString().substring(4, 20) + ";");
            buffer.append("������ID:" + "����" + ";");
            buffer.append("����ʱ��:" + new Date()+ ";");
            buffer.append("��־���:" + "22"+ ";");
            buffer.append("ִ�ж���:" + "����" + ";");
            buffer.append("��ע:" + "��ע");*/
            buffer.append("<2> 2016-12-26 00:38:44 JS_Nanjing_II_DCD_1 DCD 2016-12-26 00:38:44 vead01 10.1.1.1 NARI VEAD 1 8 1 BackDevice HeartBeat Lost");
            /*  ������Ϣ����������2��ʾ��־���� ��ΧΪ0~7�����ֱ��룬��ʾ���¼������س̶ȡ�0��ߣ�7���
             *  syslogΪÿ���¼����輸����ͬ�����ȼ���
                LOG_EMERG�������������Ҫ����֪ͨ������Ա��
                LOG_ALERT��Ӧ�ñ��������������⣬��ϵͳ���ݿⱻ�ƻ���ISP���Ӷ�ʧ��
                LOG_CRIT����Ҫ�������Ӳ�̴��󣬱������Ӷ�ʧ��
                LOG_ERR�����󣬲��Ƿǳ���������һ��ʱ�����޸����ɡ�
                LOG_WARNING��������Ϣ�����Ǵ��󣬱���ϵͳ����ʹ����85%�ȡ�
                LOG_NOTICE�����Ǵ��������Ҳ����Ҫ����������
                LOG_INFO���鱨��Ϣ��������ϵͳ��Ϣ������ɧ�ű��棬�������ݵȣ�����Ҫ������
                LOG_DEBUG��������ϸ�Ŀ����鱨����Ϣ��ͨ��ֻ�ڵ���һ������ʱʹ�á�
             */
            syslog.log(0, URLDecoder.decode(buffer.toString(),"utf-8"));
        } catch (Exception e) {
        }
    }
}