package Quartz.ch2_3;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TestCalculateJob {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(CalculateJob.class)
                                        .usingJobData("jobCount", 1)
                                        .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                                        .startNow()
                                        .usingJobData("triggerCount", 0)
                                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                                                           .withIntervalInSeconds(1)
                                                                           .repeatForever())
                                        .build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
