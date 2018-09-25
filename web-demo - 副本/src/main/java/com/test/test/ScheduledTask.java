package com.test.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {


    //@Scheduled(cron = "0/1 * *  * * ? ") // 每1秒执行一次
    public void bTask() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()) + "*********B任务每5秒执行一次进入测试");
    }


}