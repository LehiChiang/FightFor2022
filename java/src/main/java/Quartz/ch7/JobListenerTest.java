package Quartz.ch7;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

public class JobListenerTest {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail job = JobBuilder.newJob(PrintTimeJob.class)
                                  .withIdentity("timeJob")
                                  .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                                              .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
                                              .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                                              .forJob(job)
                                              .build();

        scheduler.scheduleJob(job, trigger);
        scheduler.getListenerManager().addJobListener(new PrintTimeListener(), KeyMatcher.keyEquals(new JobKey("timeJob")));
//        scheduler.getListenerManager().addJobListener(new PrintTimeListener(), allJobs());
        scheduler.start();
    }
}
