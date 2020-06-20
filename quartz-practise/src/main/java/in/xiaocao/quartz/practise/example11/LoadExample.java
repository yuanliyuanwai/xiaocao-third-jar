package in.xiaocao.quartz.practise.example11;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class LoadExample {
	
	private int _numberOfJobs = 500;
	
	public LoadExample(int inNumberOfJobs) {
		this._numberOfJobs = inNumberOfJobs;
	}
	
	public void run() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();
	    System.out.println("------- Initialization Complete -----------");
	    for (int count = 1; count <= this._numberOfJobs; count++)
	    {
	      JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job" + count, "group_1").requestRecovery().build();
	      long timeDelay = (long) (Math.random() * 2500);
	      job.getJobDataMap().put("delay time", timeDelay);
	      Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger_" + count, "group_1").startAt(DateBuilder.futureDate(10000 + count * 100, DateBuilder.IntervalUnit.MILLISECOND)).build();
	      sched.scheduleJob(job, trigger);
	      if (count % 25 == 0) {
	        System.out.println("...scheduled " + count + " jobs");
	      }
	    }
	    
	    System.out.println("------- Starting Scheduler ----------------");
	    sched.start();
	    System.out.println("------- Started Scheduler -----------------");
	    System.out.println("------- Waiting five minutes... -----------");
	    try
	    {
	      Thread.sleep(300000L);
	    }
	    catch (Exception e) {}
	    System.out.println("------- Shutting Down ---------------------");
	    sched.shutdown(true);
	    System.out.println("------- Shutdown Complete -----------------");
	    
	    SchedulerMetaData metaData = sched.getMetaData();
	    System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
	}
	
	public static void main(String[] args) throws Exception {
		int numberOfJobs = 500;
		LoadExample example = new LoadExample(numberOfJobs);
		example.run();
	}

}
