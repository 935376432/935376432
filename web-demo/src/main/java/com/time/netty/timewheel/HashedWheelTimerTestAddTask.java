package com.time.netty.timewheel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

/**
 * HashedWheelTimer 增加任务测试
 * 当前一个任务执行时间过长的时候，会影响后续任务的到期执行时间的。也就是说其中的任务是串行执行的。所以，要求里面的任务都要短平快。
 *
 */

public class HashedWheelTimerTestAddTask {


    public static void main(String[] argv) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Timer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 5, TimeUnit.SECONDS, 2);


        TimerTask task1 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.err.println("task 1 will run per 5 seconds " + " : " + LocalDateTime.now().format(formatter));
                timer.newTimeout(this, 5, TimeUnit.SECONDS);//结束时候再次注册
            }
        };
        timer.newTimeout(task1, 5, TimeUnit.SECONDS);


        TimerTask task2 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task 2 will run per 10 seconds" + " : " + LocalDateTime.now().format(formatter));
                timer.newTimeout(this, 10, TimeUnit.SECONDS);//结束时候再注册
            }
        };
        timer.newTimeout(task2, 10, TimeUnit.SECONDS);


       //该任务仅仅运行一次
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task 3 run only once ! " + " : " + LocalDateTime.now().format(formatter));
            }
        }, 15, TimeUnit.SECONDS);



    }



    /*结果为:
    task 1 will run per 5 seconds  : 2018-05-15 15:05:40
    task 2 will run per 10 seconds : 2018-05-15 15:05:45
    task 3 run only once !  : 2018-05-15 15:05:50
    task 1 will run per 5 seconds  : 2018-05-15 15:05:50
    task 2 will run per 10 seconds : 2018-05-15 15:06:00
    task 1 will run per 5 seconds  : 2018-05-15 15:06:00
    task 1 will run per 5 seconds  : 2018-05-15 15:06:10
    task 2 will run per 10 seconds : 2018-05-15 15:06:15
    task 1 will run per 5 seconds  : 2018-05-15 15:06:20
    task 2 will run per 10 seconds : 2018-05-15 15:06:30
    task 1 will run per 5 seconds  : 2018-05-15 15:06:30
    task 1 will run per 5 seconds  : 2018-05-15 15:06:40
    task 2 will run per 10 seconds : 2018-05-15 15:06:45
    task 1 will run per 5 seconds  : 2018-05-15 15:06:50
    task 2 will run per 10 seconds : 2018-05-15 15:07:00
    task 1 will run per 5 seconds  : 2018-05-15 15:07:00
    task 1 will run per 5 seconds  : 2018-05-15 15:07:10
    task 2 will run per 10 seconds : 2018-05-15 15:07:15
    task 1 will run per 5 seconds  : 2018-05-15 15:07:20*/


}