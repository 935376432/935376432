/*
 *jiji java
 */
package com.test.netty.HashedWheelTimer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
/**
 * nettyʱ���ֵ�ʵ��-HashedWheelTimer  ��ʹ��ʾ��
    1.����netty����
<dependency>
    <groupId>io.netty</groupId>
    <artifactId>netty-all</artifactId>
    <version>4.1.4.Final</version>
</dependency>
 * @author congzhe
 *
 */
public class HashedWheelTimerTest {

    @Test
    public void test() {
        //fail("Not yet implemented");
        System.out.println("test");
    }

    /*
    HashedWheelTimer(
        ThreadFactory threadFactory, //������Clock�е�updater, ���𴴽�Worker�߳�.
        long tickDuration,           //ʱ��̶�֮���ʱ��(Ĭ��100ms), ͨ�׵�˵, ���Ƕ��tick++һ��.
        TimeUnit unit,               //tickDuration�ĵ�λ.
        int ticksPerWheel            //������Clock�е�wheel�ĳ���(Ĭ��512).
    )
    */

    //@Test
    public void test1() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);
        System.out.println("start:" + LocalDateTime.now().format(formatter));
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("task :" + LocalDateTime.now().format(formatter));
        }, 3, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }

    /**
     * ��ǰһ������ִ��ʱ�������ʱ�򣬻�Ӱ���������ĵ���ִ��ʱ��ġ�Ҳ����˵���е������Ǵ���ִ�еġ����ԣ�Ҫ�����������Ҫ��ƽ�졣
     * @throws Exception �쳣
     */
    //@Test
    public void test2() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);
        System.out.println("start:" + LocalDateTime.now().format(formatter));
        hashedWheelTimer.newTimeout(timeout -> {
            Thread.sleep(3000);
            System.out.println("task1:" + LocalDateTime.now().format(formatter));
        }, 3, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> System.out.println("task2:" + LocalDateTime.now().format(formatter)), 4,
            TimeUnit.SECONDS);
        Thread.sleep(10000);

    }

    //@Test
    public void test3() throws Exception {
        DaemonThreadFactory dt = new DaemonThreadFactory();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(dt,100, TimeUnit.MILLISECONDS,8,true);
        System.out.println("start:" + LocalDateTime.now().format(formatter));
        hashedWheelTimer.newTimeout(timeout -> {
            Thread.sleep(3000);
            System.out.println("task1:" + LocalDateTime.now().format(formatter));
        }, 3, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> System.out.println("task2:" + LocalDateTime.now().format(
                formatter)), 4, TimeUnit.SECONDS);
        Thread.sleep(10000);
    }

    @Test
    public void test4() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DaemonThreadFactory dt = new DaemonThreadFactory();
        Timer timer = new HashedWheelTimer(dt, 100, TimeUnit.MILLISECONDS, 8, true);

        TimerTask task1 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task 1 will run per 5 seconds : " + LocalDateTime.now().format(formatter));
                //timer.newTimeout(this, 5, TimeUnit.SECONDS);// ����ʱ���ٴ�ע��
            }
        };
        timer.newTimeout(task1, 5, TimeUnit.SECONDS);

        TimerTask task2 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task 2 will run per 10 seconds: " + LocalDateTime.now().format(formatter));
                //timer.newTimeout(this, 10, TimeUnit.SECONDS);// ����ʱ����ע��
            }
        };
        timer.newTimeout(task2, 10, TimeUnit.SECONDS);

        // �������������һ��
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task 3 run only once ! : " + LocalDateTime.now().format(formatter));
            }
        }, 15, TimeUnit.SECONDS);

    }

    //@Test
    public void test5() {
        // ����HashedWheelTimer����
        // 1000����������һ��solt,һ����512��solt
        HashedWheelTimer timer = new HashedWheelTimer(5000L, TimeUnit.MILLISECONDS, 512);

        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("---run service-----");
                // ����ִ����ɺ��ٰ��Լ���ӵ�����solt��
                //addTask(timer, this);
            }
        };
        addTask(timer, task);
    }

    public static void addTask(HashedWheelTimer timer, TimerTask task) {
        // ����ʱ����task����ŵ���Ӧ��solt��
        timer.newTimeout(task, 500, TimeUnit.MILLISECONDS);
    }


}



class DaemonThreadFactory implements ThreadFactory {

    private String threadName = "testThread";

    DaemonThreadFactory() {
    }

    DaemonThreadFactory(String name) {
        this.threadName = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName(this.threadName);
        t.setDaemon(true);
        return t;
    }
}