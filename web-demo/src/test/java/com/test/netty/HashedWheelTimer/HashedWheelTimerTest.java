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
 * netty时间轮的实现-HashedWheelTimer  简单使用示例
    1.引入netty依赖
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
        ThreadFactory threadFactory, //类似于Clock中的updater, 负责创建Worker线程.
        long tickDuration,           //时间刻度之间的时长(默认100ms), 通俗的说, 就是多久tick++一次.
        TimeUnit unit,               //tickDuration的单位.
        int ticksPerWheel            //类似于Clock中的wheel的长度(默认512).
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
     * 当前一个任务执行时间过长的时候，会影响后续任务的到期执行时间的。也就是说其中的任务是串行执行的。所以，要求里面的任务都要短平快。
     * @throws Exception 异常
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
                //timer.newTimeout(this, 5, TimeUnit.SECONDS);// 结束时候再次注册
            }
        };
        timer.newTimeout(task1, 5, TimeUnit.SECONDS);

        TimerTask task2 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task 2 will run per 10 seconds: " + LocalDateTime.now().format(formatter));
                //timer.newTimeout(this, 10, TimeUnit.SECONDS);// 结束时候再注册
            }
        };
        timer.newTimeout(task2, 10, TimeUnit.SECONDS);

        // 该任务仅仅运行一次
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task 3 run only once ! : " + LocalDateTime.now().format(formatter));
            }
        }, 15, TimeUnit.SECONDS);

    }

    //@Test
    public void test5() {
        // 定义HashedWheelTimer对象
        // 1000毫秒跳到下一个solt,一共有512个solt
        HashedWheelTimer timer = new HashedWheelTimer(5000L, TimeUnit.MILLISECONDS, 512);

        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("---run service-----");
                // 任务执行完成后再把自己添加到任务solt上
                //addTask(timer, this);
            }
        };
        addTask(timer, task);
    }

    public static void addTask(HashedWheelTimer timer, TimerTask task) {
        // 根据时长把task任务放到响应的solt上
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