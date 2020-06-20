package in.xiaocao.quartz.practise.example11;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;


public class SimpleJob implements Job {

	public static final String DELAY_TIME = "delay time";

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("Executing job: " + jobKey + " executing at " + new Date());
		long delayTime = context.getJobDetail().getJobDataMap().getLong(DELAY_TIME);
		try {
			Thread.sleep(delayTime);
		} catch (Exception e) {
		}
		System.out.println("Finished Executing job: " + jobKey + " at " + new Date());
	}
}
