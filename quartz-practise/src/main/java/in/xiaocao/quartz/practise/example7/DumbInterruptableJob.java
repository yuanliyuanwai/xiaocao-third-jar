package in.xiaocao.quartz.practise.example7;

import java.util.Date;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;


public class DumbInterruptableJob implements InterruptableJob {
	
	private boolean _interrupted = false;
	
	private JobKey _jobKey = null;
	
	public DumbInterruptableJob () {
		System.out.println("创建了实例.....");
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		this._jobKey = context.getJobDetail().getKey();
		System.out.println("---" + this._jobKey + " executing at " + new Date());
		try
	    {
	      for (int i = 0; i < 4; i++)
	      {
	        try
	        {
	          Thread.sleep(1000L);
	        }
	        catch (Exception ignore)
	        {
	          ignore.printStackTrace();
	        }
	        if (this._interrupted)
	        {
	          System.out.println("--- " + this._jobKey + "  -- Interrupted... bailing out!"); return;
	        }
	      }
	    }
	    finally
	    {
	      System.out.println("---- " + this._jobKey + " completed at " + new Date());
	    }
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		// TODO Auto-generated method stub
		System.out.println("---" + this._jobKey + "  -- INTERRUPTING --");
	    this._interrupted = true;
	}

}
