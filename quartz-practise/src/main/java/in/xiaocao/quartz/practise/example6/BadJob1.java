package in.xiaocao.quartz.practise.example6;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;


@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class BadJob1 implements Job {

	private int calculation;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobKey jobKey = context.getJobDetail().getKey();
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		int denominator = dataMap.getInt("denominator");
		System.out.println("---" + jobKey + " executing at " + new Date() + " with denominator " + denominator);
		try {
			this.calculation = (4815 / denominator);
		} catch (Exception e) {
			System.out.println("--- Error in job!");
			JobExecutionException e2 = new JobExecutionException(e);

			dataMap.put("denominator", "1");
			
			// job会立刻重新执行一次相当于多执行了一次
			// 需要 : 1.e2.setRefireImmediately(true);
			//        2.throw e2;
			e2.setRefireImmediately(true);
			throw e2;
		}
		System.out.println("---" + jobKey + " completed at " + new Date());
	}

}
