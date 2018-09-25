package spring.quartz.test;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 调用任务的类
 * @author lhy
 *
 */
public class SchedulerTest {
    public static void main(String[] args) {

        // 通过schedulerFactory获取一个调度器
        SchedulerFactory schedulerfactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            // 通过schedulerFactory获取一个调度器
            scheduler = schedulerfactory.getScheduler();

            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "jgroup1").build();

            // 定义调度触发规则

            // 使用simpleTrigger规则
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withRepeatCount(8)).startNow().build();

            SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("sim", "simGroup")
                .startAt(new Date())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInMinutes(Integer.valueOf(15))
                    ).build();
            String sDt = "2017-11-12 11:10:00";
            SimpleDateFormat sdf3= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt3 = sdf3.parse(sDt);
            SimpleTrigger simpleTrigger1 = TriggerBuilder.newTrigger()
                .withIdentity("sim1", "simGroup1").startNow().endAt(dt3)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY)
                    ).build();

            // 使用cornTrigger规则 每天10点42分
            /*
             * Trigger
             * trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger",
             * "triggerGroup")
             * .withSchedule(CronScheduleBuilder.cronSchedule("0 42 10 * * ? *"
             * )) .startNow().build();
             */

            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(job, simpleTrigger1);

            // 启动调度
            scheduler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}