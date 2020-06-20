package in.xiaocao.quartz.practise.example6;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;


@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class BadJob2 implements Job {

	private int calculation;

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("---" + jobKey + " executing at " + new Date());
		try {
			int zero = 0;
			this.calculation = (4815 / zero);
		} catch (Exception e) {
			System.out.println("--- Error in job!");
			// 抛出异常之后关闭调用
			// 需要 : 1.e2.setUnscheduleAllTriggers(true);
			//       2.throw e2;
			JobExecutionException e2 = new JobExecutionException(e);
			e2.setUnscheduleAllTriggers(true);
			throw e2;
		}
		System.out.println("---" + jobKey + " completed at " + new Date());
	}

}
