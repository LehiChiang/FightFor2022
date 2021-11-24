package Quartz.ch2_3;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class CalculateJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        int jobCount = (int) context.getJobDetail().getJobDataMap().get("jobCount");
        int triggerCount = (int) context.getTrigger().getJobDataMap().get("triggerCount");
        logger.info(String.valueOf(jobCount) + ',' + String.valueOf(triggerCount));
        context.getJobDetail().getJobDataMap().put("jobCount", jobCount + 1);
        context.getTrigger().getJobDataMap().put("triggerCount", triggerCount + 1);
    }
}
