package in.xiaocao.quartz.practise.test.cron.trigger;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;


public class SimpleJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// jobKey = groupName.jobName
		// the groupName is DEFAULT if not set
		// the jobName is uuid if not set
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("SimpleJob says :" + jobKey + "executing at " + new Date());
	}
	
	

}
