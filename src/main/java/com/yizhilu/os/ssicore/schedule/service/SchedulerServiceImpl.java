package com.yizhilu.os.ssicore.schedule.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.yizhilu.os.ssicore.schedule.Constant;
/**
 * 定时服务实现类
 * 
 * @author Basil
 * 
 */
public class SchedulerServiceImpl implements SchedulerService{

	/**
	 * 
	 */
	private Scheduler scheduler;
	private JobDetail jobDetail;
	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	@Autowired
	public void setJobDetail(@Qualifier("jobDetail") JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	@Autowired
	public void setScheduler(@Qualifier("quartzScheduler") Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	public void schedule(String cronExpression) {
		schedule("", cronExpression);
	}

	public void schedule(String name, String cronExpression) {
		schedule( name,  cronExpression,Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(String name, String cronExpression,String group) {
		try {
			schedule(name, new CronExpression(cronExpression),group);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public void schedule(CronExpression cronExpression) {
		schedule(null, cronExpression);
	}

	public void schedule(String name, CronExpression cronExpression) {
		schedule( name,  cronExpression,Scheduler.DEFAULT_GROUP) ;
	}
	
	/**
	 * CronExpression name
	 */
	public void schedule(String name, CronExpression cronExpression,String group) {
		if (name == null || name.trim().equals("")) {
			name = UUID.randomUUID().toString();
		}else{
			//在名称后添加UUID，保证名称的唯一性
			name +="&"+UUID.randomUUID().toString();
		}

		try {
			scheduler.addJob(jobDetail, true);

			CronTrigger cronTrigger = new CronTrigger(name, group, jobDetail.getName(),
					Scheduler.DEFAULT_GROUP);
			cronTrigger.setCronExpression(cronExpression);
			scheduler.scheduleJob(cronTrigger);
			scheduler.rescheduleJob(cronTrigger.getName(), cronTrigger.getGroup(), cronTrigger);
			
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	public void schedule(Date startTime) {
		schedule(startTime, Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(Date startTime,String group) {
		schedule(startTime, null,group);
	}

	public void schedule(String name, Date startTime) {
		schedule(name, startTime,Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(String name, Date startTime,String group) {
		schedule(name, startTime, null,group);
	}

	public void schedule(Date startTime, Date endTime) {
		schedule(startTime, endTime, Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(Date startTime, Date endTime,String group) {
		schedule(startTime, endTime, 0,group);
	}

	public void schedule(String name, Date startTime, Date endTime) {
		schedule(name, startTime, endTime,Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(String name, Date startTime, Date endTime,String group) {
		schedule(name, startTime, endTime, 0,group);
	}

	public void schedule(Date startTime, Date endTime, int repeatCount) {
		schedule( startTime, endTime, 0,Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(Date startTime, Date endTime, int repeatCount,String group) {
		schedule(null, startTime, endTime, 0,group);
	}

	public void schedule(String name, Date startTime, Date endTime, int repeatCount) {
		schedule(name, startTime, endTime, 0, Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(String name, Date startTime, Date endTime, int repeatCount,String group) {
		schedule(name, startTime, endTime, 0, 1L,group);
	}

	public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval) {
		schedule(startTime, endTime, repeatCount, repeatInterval,Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval,String group) {
		schedule(null, startTime, endTime, repeatCount, repeatInterval,group);
	}

	public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval) {
		this.schedule( name , startTime,  endTime,  repeatCount,  repeatInterval,  Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval,String group ) {
		if (name == null || name.trim().equals("")) {
			name = UUID.randomUUID().toString();
		}else{
			//在名称后添加UUID，保证名称的唯一性
			name +="&"+UUID.randomUUID().toString();
		}
		
		try {
			scheduler.addJob(jobDetail, true);

			SimpleTrigger SimpleTrigger = new SimpleTrigger(name, group, jobDetail.getName(),
					Scheduler.DEFAULT_GROUP, startTime, endTime, repeatCount, repeatInterval);
			scheduler.scheduleJob(SimpleTrigger);
			scheduler.rescheduleJob(SimpleTrigger.getName(), SimpleTrigger.getGroup(), SimpleTrigger);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void schedule(Map<String,String> map) {
		
		String temp = null;
		//实例化SimpleTrigger
		SimpleTrigger SimpleTrigger = new SimpleTrigger();
		
		//这些值的设置也可以从外面传入，这里采用默放值		
		SimpleTrigger.setJobName(jobDetail.getName());		
		SimpleTrigger.setJobGroup(Scheduler.DEFAULT_GROUP);		
		SimpleTrigger.setRepeatInterval(1000L);
		
		//设置名称
		temp = map.get(Constant.TRIGGERNAME);		
		System.out.println("trigger name is :" + temp);
		if (StringUtils.isEmpty(StringUtils.trim(temp)) ){
			temp = UUID.randomUUID().toString();
		}else{
			//在名称后添加UUID，保证名称的唯一性
			temp +="&"+UUID.randomUUID().toString();
		}
		SimpleTrigger.setName(temp);
		
		//设置Trigger分组
		temp = map.get(Constant.TRIGGERGROUP);
		if(StringUtils.isEmpty(temp)){
			temp = Scheduler.DEFAULT_GROUP;
		}
		SimpleTrigger.setGroup(temp);
		
		//设置开始时间
		temp = map.get(Constant.STARTTIME);
		if(StringUtils.isNotEmpty(temp)){
			SimpleTrigger.setStartTime(this.parseDate(temp));
		}
		
		//设置结束时间
		temp = map.get(Constant.ENDTIME);
		if(StringUtils.isNotEmpty(temp)){
			SimpleTrigger.setEndTime(this.parseDate(temp));
		}
		
		//设置执行次数
		temp = map.get(Constant.REPEATCOUNT);
		if(StringUtils.isNotEmpty(temp) && NumberUtils.toInt(temp) > 0){
			SimpleTrigger.setRepeatCount(NumberUtils.toInt(temp));
		}
		
		//设置执行时间间隔
		temp = map.get(Constant.REPEATINTERVEL);
		if(StringUtils.isNotEmpty(temp) && NumberUtils.toLong(temp) > 0){
			SimpleTrigger.setRepeatInterval(NumberUtils.toLong(temp)*1000);
		}

		try {
			scheduler.addJob(jobDetail, true);
		
			scheduler.scheduleJob(SimpleTrigger);
			scheduler.rescheduleJob(SimpleTrigger.getName(), SimpleTrigger.getGroup(), SimpleTrigger);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void pauseTrigger(String triggerName,String group){		
		try {
			scheduler.pauseTrigger(triggerName, group);//停止触发器
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void resumeTrigger(String triggerName,String group){		
		try {
			scheduler.resumeTrigger(triggerName, group);//重启触发器
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean removeTrigdger(String triggerName,String group){		
		try {
			scheduler.pauseTrigger(triggerName, group);//停止触发器
			return scheduler.unscheduleJob(triggerName, group);//移除触发器
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Date parseDate(String time){
		try {
			return DateUtils.parseDate(time, new String[]{"yyyy-MM-dd HH:mm"});
		} catch (ParseException e) {			
			logger.error("日期格式错误{}，正确格式为：yyyy-MM-dd HH:mm",time);
			throw new RuntimeException(e);
		}
	}

	public List<Map<String, Object>> getQrtzTriggers() {
		return null;
	}
	
	public String[] getTriggerNames(String groupName){
		String []triggerNames = null;
		try {
			triggerNames = scheduler.getTriggerNames(groupName);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return triggerNames;
	}
	
}