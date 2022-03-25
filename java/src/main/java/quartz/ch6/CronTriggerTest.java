package quartz.ch6;

import quartz.ch5.UserInfoJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerTest {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail job = JobBuilder.newJob(UserInfoJob.class)
                                  .usingJobData("username", "Chestnut")
                                  .storeDurably(true)
                                  .build();

        CronTrigger trigger = TriggerBuilder.newTrigger()
                                            .withIdentity("cronTrigger1")
                                            .withSchedule(CronScheduleBuilder.cronSchedule("0 7 18 * * ?"))
                                            .forJob(job)
                                            .build();
        scheduler.addJob(job, true);
        scheduler.scheduleJob(trigger);
        scheduler.start();
    }
}
