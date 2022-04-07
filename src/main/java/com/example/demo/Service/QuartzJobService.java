package com.example.demo.Service;

import com.example.demo.bean.QuartzJob;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * 描述:
 *
 * @Auther: lzx
 * @Date: 2019/9/25 10:44
 */
public interface QuartzJobService {

    /**
     * 查询所有任务
     * @return
     * @throws SchedulerException
     */
    List<QuartzJob> findAllJobs() throws SchedulerException;

    /**
     * 添加任务
     * @param quartzJob
     * @return
     */
    QuartzJob addJob(QuartzJob quartzJob);

    /**
     * 删除任务
     * @param quartzJobId
     * @return
     */
    boolean deleteJob(String quartzJobId);
}

