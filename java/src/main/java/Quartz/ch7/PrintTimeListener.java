package Quartz.ch7;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class PrintTimeListener implements JobListener {
    @Override
    public String getName() {
        return "[PrintTimeListener]";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println(jobExecutionContext.getJobDetail().getJobClass().getName() + " 即将执行");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println(jobExecutionContext.getJobDetail().getJobClass().getName() + " 执行完毕");
    }
}
