package in.xiaocao.quartz.practise.example9;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;


public class SimpleJob1 implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		 JobKey jobKey = context.getJobDetail().getKey();
		 System.out.println("SimpleJob1 says: " + jobKey + " executing at " + new Date());
	}

}
