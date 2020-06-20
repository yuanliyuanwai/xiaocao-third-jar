package in.xiaocao.quartz.practise.example9;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;


public class ListenerExample {
	
	public void run() throws Exception {
		System.out.println("-------------Initializing------------");
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		System.out.println("------- Initialization Complete -----------");
		System.out.println("------- Scheduling Jobs -------------------");
		
		JobDetail job = JobBuilder.newJob(SimpleJob1.class).withIdentity("job1").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1").startNow().build();
		
		JobListener listener = new Job1Listener();
		Matcher<JobKey> matcher = KeyMatcher.keyEquals(job.getKey());
	    sched.getListenerManager().addJobListener(listener, matcher);
	    
	    sched.scheduleJob(job, trigger);
	    System.out.println("------- Starting Scheduler ----------------");
	    sched.start();
	    System.out.println("------- Waiting 30 seconds... --------------");
	    try
	    {
	      Thread.sleep(30000L);
	    }
	    catch (Exception e) {}
	    System.out.println("------- Shutting Down ---------------------");
	    sched.shutdown(true);
	    System.out.println("------- Shutdown Complete -----------------");
	    
	    SchedulerMetaData metaData = sched.getMetaData();
	    System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
	}
	
	public static void main(String[] args) throws Exception {
		ListenerExample example = new ListenerExample();
		example.run();
	}

}
