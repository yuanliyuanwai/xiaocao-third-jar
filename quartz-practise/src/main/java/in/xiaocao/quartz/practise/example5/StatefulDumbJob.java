package in.xiaocao.quartz.practise.example5;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;


@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class StatefulDumbJob implements Job {
	
	public static final String NUM_EXECUTIONS = "NumExecutions";
	
	public static final String EXECUTION_DELAY = "ExecutionDelay";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.err.println("---" + context.getJobDetail().getKey() + " executing.[" + new Date() + "]");
		JobDataMap map = context.getJobDetail().getJobDataMap();
		int executeCount = 0;
	    if (map.containsKey("NumExecutions")) {
	      executeCount = map.getInt("NumExecutions");
	      System.out.println(context.getJobDetail().getKey() + " : the value of the executeCount is " + executeCount);
	    }
	    executeCount++;
	    map.put("NumExecutions", executeCount);
	    long delay = 5000L;
	    if (map.containsKey("ExecutionDelay")) {
	      delay = map.getLong("ExecutionDelay");
	      System.out.println(context.getJobDetail().getKey() + " : the value of the delay is " + delay);
	    }
	    try
	    {
	      Thread.sleep(delay);
	    }
	    catch (Exception ignore) {}
	    System.err.println("  -" + context.getJobDetail().getKey() + " complete (" + executeCount + ") at" + new Date());
	}

}
