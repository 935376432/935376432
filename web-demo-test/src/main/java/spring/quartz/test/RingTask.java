package spring.quartz.test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 这是一个打铃的程序，必须隔一段时间打一次
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
        System.out.println("我是打铃程序!" + "我第一次打铃延迟了" + delay + "秒！");
        System.out.println("打铃了!每过" + second + "秒一次");
    }

    //定义好后，下面需要注册调用了，注册调用的方法如下:

    public static void main(String[] args) {
        // 以 java定时器的模式调用
        Timer timer = new Timer();
        timer.schedule(new RingTask(3, 3), // 需要注册的定时类
            3000, // 最开始先延迟3秒的时间
            3000); // 每隔3秒的时间调用一次
    }













}