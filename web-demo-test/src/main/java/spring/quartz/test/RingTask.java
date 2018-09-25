package spring.quartz.test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ����һ������ĳ��򣬱����һ��ʱ���һ��
 *
 */
public class RingTask extends TimerTask {
    public RingTask() {
        // TODO Auto-generated constructor stub
    }

    public RingTask(int s, int d) {
        // TODO Auto-generated constructor stub
        this.second = s;
        this.delay = d;
    }

    int second = 1;
    int delay = 1;

    public void setSecond(int second) {
        this.second = second;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("���Ǵ������!" + "�ҵ�һ�δ����ӳ���" + delay + "�룡");
        System.out.println("������!ÿ��" + second + "��һ��");
    }

    //����ú�������Ҫע������ˣ�ע����õķ�������:

    public static void main(String[] args) {
        // �� java��ʱ����ģʽ����
        Timer timer = new Timer();
        timer.schedule(new RingTask(3, 3), // ��Ҫע��Ķ�ʱ��
            3000, // �ʼ���ӳ�3���ʱ��
            3000); // ÿ��3���ʱ�����һ��
    }













}