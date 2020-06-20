package in.xiaocao.quartz.practise.test.cron.trigger;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


/**
 * CronExpression表达式
 * @author zhengchong.wan
 * Seconds	 	0-59	 	, - * /
   Minutes	 	0-59	 	, - * /
   Hours	 	0-23	 	, - * /
   Day-of-month	 	1-31	 	, - * ? / L W
   Month	 	1-12 or JAN-DEC	 	, - * /
   Day-of-Week	 	1-7 or SUN-SAT	 	, - * ? / L #
   Year (Optional)	 	empty, 1970-2199	 	, - * /
   总结：
  1.'*'代表 '任何值'
  2.'?'也代表 '任何值', 但只用于 Day-of-month 和 Day-of-Week, 当其中一个设置了条件时,另外一个就要用'?' 来表示 '任何值'
  3.'-'用来表示范围, 比如 Day-of-month 5-10 代表 5号到10号
  4.','用来添加附加的参数,比如 Hours 4,14 代表 上午4点和下午两点
  5.'/'用来代表增量以用来循环,比如 Hours 2/6 代表 2点,8点,下午2点,下午8点
  6.'L'用于 Day-of-month 和 Day-of-Week, 代表最后1天, 在Day-of-month中只用'L'就可以,在Day-of-week中用 'nL' 来代表是最后一个星期n
  7.'W'用于 Day-of-month, 代表最近工作日,比如 '15W', 如果15号那天是周六则取14号周五,如果是周日则取16号周一,其他情况不变
  8.'LW' 代表当月最后一个工作日
  9.'#'用于 Day-of-Week, 'n#m'用于代表当月第m个 "周n"(根据n在week中的定义确定
  例子：
  1."0 0 12 * * ?"	  	每天中午12点触发
  2."0 15 10 ? * *"	  	每天上午10:15触发
  3."0 15 10 * * ?"	  	每天上午10:15触发
  4."0 15 10 * * ? *"	  	每天上午10:15触发
  5."0 15 10 * * ? 2005"	  	2005年的每天上午10:15 触发
  6."0 * 14 * * ?"	  	在每天下午2点到下午2:59期间的每1分钟触发
  7."0 0/5 14 * * ?"	  	在每天下午2点到下午2:55期间的每5分钟触发
  8."0 0/5 14,18 * * ?"	  	在每天下午2点到2:55期间和下午6点到6:55期间的每 5分钟触发
  9."0 0-5 14 * * ?"	  	在每天下午2点到下午2:05期间的每1分钟触发
  10."0 10,44 14 ? 3 WED"	  	每年三月的星期三的下午2:10和2:44触发
  11."0 15 10 ? * MON-FRI"	  	周一至周五的上午10:15触发
  12."0 15 10 15 * ?"	  	每月15日上午10:15触发
  13."0 15 10 L * ?"	  	每月最后一日的上午10:15触发
  14."0 15 10 ? * 6L"	  	每月的最后一个星期五上午10:15触发
  15."0 15 10 ? * 6L 2002-2005"	  	2002年至2005年的每月的最后一个星期五上午 10:15触发
  16."0 15 10 ? * 6#3"	  	每月的第三个星期五上午10:15触发
  
 */
public class CronTriggerExample {
	
	public void run() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		
		CronTrigger trigger = null;
		Date ft = null;
		System.out.println("当前时间：" + new Date());
		
		
		JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();
		// 每20s运行一次
		// 0/20 从0开始每次增加20如:0, 20, 40, 0或者5/15则是 5, 20, 35, and 50
		/*trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?")).build();
		ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());*/
	    
		// 偶数分钟的15s运行
	    /*job = JobBuilder.newJob(SimpleJob.class).withIdentity("job2", "group1").build();
	    trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").withSchedule(CronScheduleBuilder.cronSchedule("15 0/2 * * * ?")).build();
	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());*/
	    
		
		// 8点到17点的偶数分钟0秒执行
		/*job = JobBuilder.newJob(SimpleJob.class).withIdentity("job3", "group1").build();
		trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger3", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?")).build();
		ft = sched.scheduleJob(job, trigger);
		System.out.println(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());*/
		
		job = JobBuilder.newJob(SimpleJob.class).withIdentity("job4", "group1").build();
		//trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger4", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0/3 15-23 * * ?")).build();
		trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger4", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * 8-16 * * ?")).build();
		ft = sched.scheduleJob(job, trigger);
		System.out.println(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
		
		/*job = JobBuilder.newJob(SimpleJob.class).withIdentity("job5", "group1").build();
	    trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger5", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0 10am 1,15 * ?")).build();
	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
		
	    job = JobBuilder.newJob(SimpleJob.class).withIdentity("job6", "group1").build();
	    trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger6", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * MON-FRI")).build();
	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

	    job = JobBuilder.newJob(SimpleJob.class).withIdentity("job7", "group1").build();
	    trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger7", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * SAT,SUN")).build();
	    ft = sched.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());*/
	    
	    
	    sched.start();
	    
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CronTriggerExample example = new CronTriggerExample();
		example.run();
	}

}
