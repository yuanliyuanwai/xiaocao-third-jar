package in.xiaocao.quartz.practise.test.simple.trigger;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class SimpleTriggerExample {
	
	public static void run() throws Exception {
	    
	    System.out.println("------- Initializing -------------------");
	    

	    SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();
	    
	    System.out.println("------- Initialization Complete --------");
	    
	    System.out.println("------- Scheduling Jobs ----------------");
	    
	    System.out.println("------当前时间：" + new Date() + "-------");


	    Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
	    
	    System.out.println("------startTime：" + startTime + "-------");

	    JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();
	    
	    SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime).build();
	    

	    Date ft = sched.scheduleJob(job, trigger);
	   System.out.println(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
	    


	   job = JobBuilder.newJob(SimpleJob.class).withIdentity("job2", "group1").build();
	    
	    trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startTime).build();
	    
	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
	    
	    job = JobBuilder.newJob(SimpleJob.class).withIdentity("job3", "group1").build();
	    // group1.job3每10s运行一次重复10次,共执行11次
	    trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger3", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(10)).build();
	    

	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
	    
	    // group1.job3对应2个触发器:每10s运行一次重复2次,共执行3次
	    trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger3", "group2").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(2)).forJob(job).build();
	    

	    ft = sched.scheduleJob(trigger);
	    System.out.println(job.getKey() + " will [also] run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
	    



	    job = JobBuilder.newJob(SimpleJob.class).withIdentity("job4", "group1").build();
	    
	    // group1.job4每10s运行一次重复5次,共执行6次
	    trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger4", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(5)).build();
	    

	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
	    


	    job = JobBuilder.newJob(SimpleJob.class).withIdentity("job5", "group1").build();
	    
	    // group1.job5开始于以当前时间为基础, 5分钟之后开始运行
	    trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger5", "group1").startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.MINUTE)).build();
	    

	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
	    


	    job = JobBuilder.newJob(SimpleJob.class).withIdentity("job6", "group1").build();
	    
	    // group1.job6 每40s调度一次，永不停止
	    trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger6", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(40).repeatForever()).build();
	    

	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
	    

	    System.out.println("------- Starting Scheduler ----------------");
	    

	    sched.start();
	    
	    System.out.println("------- Started Scheduler -----------------");
	    


	    job = JobBuilder.newJob(SimpleJob.class).withIdentity("job7", "group1").build();
	    
	    trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger7", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).withRepeatCount(20)).build();
	    

	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
	    

        // storeDurably()：当没有对应触发器时job是否仍然被保存
	    job = JobBuilder.newJob(SimpleJob.class).withIdentity("job8", "group1").storeDurably().build();
	    
	    sched.addJob(job, true);
	    
	    System.out.println("'Manually' triggering job8...");
	    sched.triggerJob(JobKey.jobKey("job8", "group1"));
	    
	    System.out.println("------- Waiting 30 seconds... --------------");
	    try
	    {
	      Thread.sleep(30000L);
	    }
	    catch (Exception e) {}
	    System.out.println("------- Rescheduling... --------------------");
	    trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger7", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).withRepeatCount(20)).build();
	    

	    ft = sched.rescheduleJob(trigger.getKey(), trigger);
	    System.out.println("job7 rescheduled to run at: " + ft);
	    
	    System.out.println("------- Waiting five minutes... ------------");
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
		/*System.out.println(new Date());
		Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
		System.out.println(startTime);*/
		run();
	}

}
