package in.xiaocao.quartz.practise.example10;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;


public class PlugInExample {

	public void run() throws Exception {

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		System.out.println("------- Initialization Complete -----------");

		System.out.println("------- (Not Scheduling any Jobs - relying on XML definitions --");

		System.out.println("------- Starting Scheduler ----------------");

		sched.start();

		System.out.println("------- Started Scheduler -----------------");

		System.out.println("------- Waiting five minutes... -----------");
		try {
			Thread.sleep(300000L);
		} catch (Exception e) {
		}
		System.out.println("------- Shutting Down ---------------------");
		sched.shutdown(true);
		System.out.println("------- Shutdown Complete -----------------");

		SchedulerMetaData metaData = sched.getMetaData();
		System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
	}

	public static void main(String[] args) throws Exception {
		PlugInExample example = new PlugInExample();
		example.run();
	}

}
