package in.xiaocao.quartz.practise.example10;

import java.util.Date;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;


public class SimpleJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("Executing job: " + jobKey + " executing at " + new Date() + ", fired by: " + context.getTrigger().getKey());
	    if (context.getMergedJobDataMap().size() > 0)
	    {
	      Set<String> keys = context.getMergedJobDataMap().keySet();
	      for (String key : keys)
	      {
	        String val = context.getMergedJobDataMap().getString(key);
	        System.out.println(" - jobDataMap entry: " + key + " = " + val);
	      }
	    }
	    context.setResult("hello");
		
	}

}
