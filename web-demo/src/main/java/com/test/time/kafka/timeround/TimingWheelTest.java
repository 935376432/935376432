/*
 *jiji java
 */
package com.test.time.kafka.timeround;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

public class TimingWheelTest {


    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 5, TimeUnit.SECONDS, 2);

        TimerTask task1 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task 1 will run per 5 seconds : " + LocalDateTime.now().format(formatter));
                timer.newTimeout(this, 5, TimeUnit.SECONDS);// 结束时候再次注册
            }
        };
        timer.newTimeout(task1, 5, TimeUnit.SECONDS);

        TimerTask task2 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task 2 will run per 10 seconds: " + LocalDateTime.now().format(formatter));
                timer.newTimeout(this, 10, TimeUnit.SECONDS);// 结束时候再注册
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

    /*
    task 1 will run per 5 seconds : 2018-06-05 14:34:29
    task 2 will run per 10 seconds: 2018-06-05 14:34:34
    task 3 run only once ! :        2018-06-05 14:34:39
    task 1 will run per 5 seconds : 2018-06-05 14:34:39
    task 2 will run per 10 seconds: 2018-06-05 14:34:49
    task 1 will run per 5 seconds : 2018-06-05 14:34:49
    task 1 will run per 5 seconds : 2018-06-05 14:34:59
    task 2 will run per 10 seconds: 2018-06-05 14:35:04
    task 1 will run per 5 seconds : 2018-06-05 14:35:09
    task 2 will run per 10 seconds: 2018-06-05 14:35:19
    task 1 will run per 5 seconds : 2018-06-05 14:35:19
    task 1 will run per 5 seconds : 2018-06-05 14:35:29
    task 2 will run per 10 seconds: 2018-06-05 14:35:34
    task 1 will run per 5 seconds : 2018-06-05 14:35:39
    task 2 will run per 10 seconds: 2018-06-05 14:35:49
    task 1 will run per 5 seconds : 2018-06-05 14:35:49
    task 1 will run per 5 seconds : 2018-06-05 14:35:59
    */

}
