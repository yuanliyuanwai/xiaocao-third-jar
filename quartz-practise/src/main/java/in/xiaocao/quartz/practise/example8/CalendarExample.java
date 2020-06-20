package in.xiaocao.quartz.practise.example8;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import org.quartz.impl.calendar.AnnualCalendar;


public class CalendarExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	    System.out.println("------- Initializing ----------------------");
	    

	    SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();
	    
	    System.out.println("------- Initialization Complete -----------");
	    
	    System.out.println("------- Scheduling Jobs -------------------");
	    

	    AnnualCalendar holidays = new AnnualCalendar();
	    

	    Calendar fourthOfJuly = new GregorianCalendar(2005, 6, 4);
	    holidays.setDayExcluded(fourthOfJuly, true);
	    
	    Calendar halloween = new GregorianCalendar(2005, 9, 31);
	    holidays.setDayExcluded(halloween, true);
	    
	    Calendar christmas = new GregorianCalendar(2005, 11, 25);
	    holidays.setDayExcluded(christmas, true);
	    

	    sched.addCalendar("holidays", holidays, false, false);
	    


	    Date runDate = DateBuilder.dateOf(0, 0, 10, 31, 10);
	    
	    JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();
	    
	    SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runDate).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1).repeatForever()).modifiedByCalendar("holidays").build();
	    


	    Date firstRunTime = sched.scheduleJob(job, trigger);
	    



	    System.out.println(job.getKey() + " will run at: " + firstRunTime + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
	    



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

}
