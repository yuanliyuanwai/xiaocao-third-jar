package in.xiaocao.quartz.practise.example7;

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



public class InterruptExample {
	
	public void run() throws Exception {
		    
		    System.out.println("------- Initializing ----------------------");
		    SchedulerFactory sf = new StdSchedulerFactory();
		    Scheduler sched = sf.getScheduler();
		    System.out.println("------- Initialization Complete -----------");
		    System.out.println("------- Scheduling Jobs -------------------");
		    System.out.println("--------当前时间：" + new Date());
		    Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
		    
		    JobDetail job = JobBuilder.newJob(DumbInterruptableJob.class).withIdentity("interruptableJob1", "group1").build();
		    SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
		    

		    Date ft = sched.scheduleJob(job, trigger);
		    System.out.println(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
		    
		    sched.start();
		    System.out.println("------- Started Scheduler -----------------");
		    
		    System.out.println("------- Starting loop to interrupt job every 7 seconds ----------");
		    for (int i = 0; i < 50; i++) {
		      try
		      {
		        Thread.sleep(7000L);
		        
		        sched.interrupt(job.getKey());
		      }
		      catch (Exception e) {}
		    }
		    System.out.println("------- Shutting Down ---------------------");
		    
		    sched.shutdown(true);
		    
		    System.out.println("------- Shutdown Complete -----------------");
		    SchedulerMetaData metaData = sched.getMetaData();
		    System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		InterruptExample example = new InterruptExample();
	    example.run();
	}

}
