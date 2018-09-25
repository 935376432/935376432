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
 * �����������
 * @author lhy
 *
 */
public class SchedulerTest {
    public static void main(String[] args) {

        // ͨ��schedulerFactory��ȡһ��������
        SchedulerFactory schedulerfactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            // ͨ��schedulerFactory��ȡһ��������
            scheduler = schedulerfactory.getScheduler();

            // ����jobDetailʵ������Jobʵ����
            // ָ��job�����ƣ�����������ƣ��Լ���job��
            JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "jgroup1").build();

            // ������ȴ�������

            // ʹ��simpleTrigger����
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

            // ʹ��cornTrigger���� ÿ��10��42��
            /*
             * Trigger
             * trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger",
             * "triggerGroup")
             * .withSchedule(CronScheduleBuilder.cronSchedule("0 42 10 * * ? *"
             * )) .startNow().build();
             */

            // ����ҵ�ʹ�����ע�ᵽ���������
            scheduler.scheduleJob(job, simpleTrigger1);

            // ��������
            scheduler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}