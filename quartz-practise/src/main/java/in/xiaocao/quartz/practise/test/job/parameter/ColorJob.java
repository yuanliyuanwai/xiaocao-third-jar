package in.xiaocao.quartz.practise.test.job.parameter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;


@PersistJobDataAfterExecution // 持久化JobDataMap
@DisallowConcurrentExecution // 保证多个任务间不会同时执行.所以在多任务执行时最好加上
public class ColorJob implements Job {
	
	public static final String FAVORITE_COLOR = "favorite color";
	
	public static final String EXECUTION_COUNT = "count";
	
	private int _counter = 1;
	  
	  
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		 // job 的名字  
        String jobName = context.getJobDetail().getKey().getName();  
        // 任务执行的时间  
        SimpleDateFormat dateFormat = new SimpleDateFormat(  
                "yyyy 年 MM 月 dd 日  HH 时 mm 分 ss 秒");  
        String jobRunTime = dateFormat.format(Calendar.getInstance().getTime());  
  
        // 获取 JobDataMap , 并从中取出参数   
        JobDataMap data = context.getJobDetail().getJobDataMap();  
        String favoriteColor = data.getString(FAVORITE_COLOR);  
        int count = data.getInt(EXECUTION_COUNT);  
        System.out  
                .println("ColorJob: " + jobName + " 在 " + jobRunTime + " 执行了 ...  \n"  
                        + "      喜欢的颜色是：  " + favoriteColor + "\n"  
                        + "      执行次数统计(from job jobDataMap)： " + count + "\n"  
                        + "      执行次数统计( from job 类的成员变 量 ): "  
                        + _counter+ " \n ");  
        // 每次+1 并放回Map 中  
        count++;  
        data.put(EXECUTION_COUNT, count);  
        // 成员变量的增加没有意义，每次实例化对象的时候会 同时初始化该变量  
        _counter++;
		
	}

}
