package com.example.demo.Service.impl;

import com.example.demo.Quartz.ExecutionJob;
import com.example.demo.bean.QuartzJob;
import com.example.demo.Service.QuartzJobService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 描述:
 *
 * @Auther: lzx
 * @Date: 2019/9/25 10:46
 */
@Service
public class QuartzJobServiceImpl implements QuartzJobService {

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    public static final String JOB_NAME = "TASK_";

    @Override
    public List<QuartzJob> findAllJobs() throws SchedulerException {
        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyGroup());
        List<QuartzJob> names = new ArrayList<>();
        for (TriggerKey j : triggerKeys) {
            try {
                QuartzJob o = (QuartzJob) scheduler.getTrigger(j).getJobDataMap().get(QuartzJob.JOB_KEY);
                names.add(o);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
        return names;
    }

    @Override
    public QuartzJob addJob(QuartzJob quartzJob) {
        JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class)
                .withIdentity(JOB_NAME + quartzJob.getId())
                .build();
        Trigger cronTrigger = newTrigger()
                .withIdentity(JOB_NAME + quartzJob.getId())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()))
                .build();
        cronTrigger.getJobDataMap().put(QuartzJob.JOB_KEY,quartzJob);
        ((CronTriggerImpl)cronTrigger).setStartTime(new Date());
        try {
            quartzJob.setIsPause(false);
            scheduler.scheduleJob(jobDetail,cronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return quartzJob;
    }

    @Override
    public boolean deleteJob(String quartzJobId) {
        JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJobId);
        try {
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return true;
    }
}

