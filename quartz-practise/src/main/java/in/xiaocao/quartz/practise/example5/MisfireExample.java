package in.xiaocao.quartz.practise.example5;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class MisfireExample {
	
	public void run() throws Exception {
	    
	    System.out.println("------- Initializing -------------------");
	    SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();
	    System.out.println("------- Initialization Complete -----------");
	    System.out.println("------- Scheduling Jobs -----------");
	    Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
	    JobDetail job = JobBuilder.newJob(StatefulDumbJob.class).withIdentity("statefulJob1", "group1").usingJobData("ExecutionDelay", Long.valueOf(10000L)).build();
	    

	    SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).withRepeatCount(3)).build();
	    Date ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
	    

	    job = JobBuilder.newJob(StatefulDumbJob.class).withIdentity("statefulJob2", "group1").usingJobData("ExecutionDelay", Long.valueOf(10000L)).build();
	    trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever().withMisfireHandlingInstructionNowWithExistingCount()).build();
	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
	    System.out.println("------- Starting Scheduler ----------------");
	    sched.start();
	    System.out.println("------- Started Scheduler -----------------");
	    try
	    {
	      Thread.sleep(600000L);
	    }
	    catch (Exception e) {}
	    System.out.println("------- Shutting Down ---------------------");
	    sched.shutdown(true);
	    System.out.println("------- Shutdown Complete -----------------");
	    
	    SchedulerMetaData metaData = sched.getMetaData();
	    System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
	}
	
	public static void main(String[] args) throws Exception {
		MisfireExample example = new MisfireExample();
		example.run();
	}

}
