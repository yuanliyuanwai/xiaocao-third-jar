package in.xiaocao.quartz.practise.example12;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;


public class SimpleJob implements Job {
	
	public static final String MESSAGE = "msg";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		 JobKey jobKey = context.getJobDetail().getKey();
		 String message = (String)context.getJobDetail().getJobDataMap().get("msg");
		 System.out.println("SimpleJob: " + jobKey + " executing at " + new Date());
		 System.out.println("SimpleJob: msg: " + message);
	}

}
