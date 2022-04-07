package com.example.demo.Quartz;

import com.example.demo.Service.impl.QuartzJobServiceImpl;
import com.example.demo.bean.QuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 描述:
 *
 * @Auther: lzx
 * @Date: 2019/9/25 13:11
 */
@Async
@Slf4j
public class ExecutionJob extends QuartzJobBean {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        QuartzJob quartzJob =  (QuartzJob)context.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        try {
            log.info("任务准备执行，任务名称：{}",quartzJob.getJobName());
            long startTime = System.currentTimeMillis();
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(), quartzJob.getParams());
            Future<?> future = executorService.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            log.info("任务执行完毕，任务名称：{} 总耗时：{} 毫秒",quartzJob.getJobName(),times);
            if(quartzJob.isNeedClose()){
                log.info("执行后需要关闭任务");
                JobKey jobKey = JobKey.jobKey(QuartzJobServiceImpl.JOB_NAME + quartzJob.getId());
                context.getScheduler().pauseJob(jobKey);
                context.getScheduler().deleteJob(jobKey);
                log.info("{}任务关闭",quartzJob.getJobName());
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}

