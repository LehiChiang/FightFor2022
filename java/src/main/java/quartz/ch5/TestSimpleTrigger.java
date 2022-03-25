package quartz.ch5;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


public class TestSimpleTrigger {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(UserInfoJob.class)
                                        .withIdentity("jobDetail1")
                                        .usingJobData("username", "Lehi Chiang")
                                        .storeDurably() // 在没有trigger的时候，job也能等待被scheduler调度，不销毁。
                                        .build();

        // 立刻开始，每秒执行一次，5秒后结束
        Trigger trigger1 = TriggerBuilder.newTrigger()
                                         .withIdentity("trigger 1")
                                         .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                                                                       .withIntervalInSeconds(1)
                                                                                       .repeatForever())
                                         .endAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
                                         .forJob("jobDetail1")
                                         .build();

        // 6秒之后开始， 执行到固定时间，每隔两秒执行一次
        SimpleTrigger trigger2 = TriggerBuilder.newTrigger()
                                               .withIdentity("trigger 2")
                                               .startAt(DateBuilder.futureDate(6, DateBuilder.IntervalUnit.SECOND))
                                               .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                                                                  .withIntervalInSeconds(2)
                                                                                  .repeatForever())
                                               .endAt(DateBuilder.dateOf(23, 57, 30))
                                               .forJob("jobDetail1")
                                               .build();

        scheduler.addJob(jobDetail, true);
        scheduler.scheduleJob(trigger1);
        scheduler.scheduleJob(trigger2);
        scheduler.start();
    }
}
