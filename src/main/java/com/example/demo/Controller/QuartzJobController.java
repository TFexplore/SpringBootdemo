package com.example.demo.Controller;

import com.example.demo.bean.QuartzJob;
import com.example.demo.Service.QuartzJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 描述:
 *
 * @Auther: lzx
 * @Date: 2019/9/25 10:52
 */
@RestController
@RequestMapping("/quartzJob")
@Api(tags = {"定时任务管理"})
public class QuartzJobController {


    private QuartzJobService quartzJobService;

    public QuartzJobController(QuartzJobService quartzJobService) {
        this.quartzJobService = quartzJobService;
    }

    @GetMapping("/fingAllJobs")
    @ApiOperation(value = "获取所有定时任务")
    public List<QuartzJob> fingAllJobs() throws SchedulerException {
        return quartzJobService.findAllJobs();
    }

    @ApiOperation(value = "添加任务")
    @PostMapping("/addJob")
    public QuartzJob addJob(@RequestBody @Valid QuartzJob quartzJob){
        return  quartzJobService.addJob(quartzJob);
    }

    @ApiOperation(value = "删除任务")
    @DeleteMapping("/deleteJob/{quartzJobId}")
    public boolean deleteJob(@PathVariable("quartzJobId") String quartzJobId){
        return quartzJobService.deleteJob(quartzJobId);
    }
}

