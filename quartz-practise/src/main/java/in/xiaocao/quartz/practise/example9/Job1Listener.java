package in.xiaocao.quartz.practise.example9;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;


public class Job1Listener implements JobListener {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "job1_to_job2";
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext arg0) {
		// TODO Auto-generated method stub
		System.out.println("Job1Listener says: Job Execution was vetoed.");
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext arg0) {
		// TODO Auto-generated method stub
		System.out.println("Job1Listener says: Job Is about to be executed.");
	}

	@Override
	public void jobWasExecuted(JobExecutionContext inContext,
			JobExecutionException inException) {
		// TODO Auto-generated method stub
		System.out.println("Job1Listener says: Job was executed.");
		JobDetail job2 = JobBuilder.newJob(SimpleJob2.class).withIdentity("job2").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("job2Trigger").startNow().build();
		try{
			inContext.getScheduler().scheduleJob(job2, trigger);
		} catch (SchedulerException e) {
			System.err.println("Unable to schedule job2!");
			e.printStackTrace();
		}
	}

}
