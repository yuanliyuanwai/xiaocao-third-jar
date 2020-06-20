package in.xiaocao.quartz.practise.example6;

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


/**
 * job报异常的处理
 * @author Administrator
 *
 */
public class JobExceptionExample {
	
	public void run() throws Exception {
	    System.out.println("------- Initializing ----------------------");
	    SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();
	    System.out.println("------- Initialization Complete ------------");
	    System.out.println("------- Scheduling Jobs -------------------");
	    System.out.println("当前时间：" + new Date());

	    Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
	    JobDetail job = null;
	    SimpleTrigger trigger = null;
	    Date ft = null;
	    
	    /*job = JobBuilder.newJob(BadJob1.class).withIdentity("badJob1", "group1").usingJobData("denominator", "0").build();
	    trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(3)).build();
	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");*/
	    

	    job = JobBuilder.newJob(BadJob2.class).withIdentity("badJob2", "group1").build();
	    trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
	    
	    
	    System.out.println("------- Starting Scheduler ----------------");
	    sched.start();
	    System.out.println("------- Started Scheduler -----------------");
	    try
	    {
	      Thread.sleep(60000L);
	    }
	    catch (Exception e) {}
	    System.out.println("------- Shutting Down ---------------------");
	    sched.shutdown(false);
	    System.out.println("------- Shutdown Complete -----------------");
	    SchedulerMetaData metaData = sched.getMetaData();
	    System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
	}
	
	public static void main(String[] args) throws Exception {
		JobExceptionExample example = new JobExceptionExample();
		example.run();
	}

}
