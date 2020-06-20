package in.xiaocao.quartz.practise.example12;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;


public class RemoteServerExample {
	
	public void run()
		    throws Exception
		  {
		    

		    SchedulerFactory sf = new StdSchedulerFactory();
		    Scheduler sched = sf.getScheduler();
		    
		    System.out.println("------- Initialization Complete -----------");
		    
		    System.out.println("------- (Not Scheduling any Jobs - relying on a remote client to schedule jobs --");
		    
		    System.out.println("------- Starting Scheduler ----------------");
		    

		    sched.start();
		    
		    System.out.println("------- Started Scheduler -----------------");
		    
		    System.out.println("------- Waiting ten minutes... ------------");
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

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RemoteServerExample example = new RemoteServerExample();
	    example.run();
	}

}
