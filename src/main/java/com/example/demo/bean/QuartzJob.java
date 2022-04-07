package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 自动任务管理模型
 * @author lzx
 * @date 2019-09-07
 */
@Getter
@Setter
@ApiModel("自动任务模型")
public class QuartzJob implements Serializable {

    @ApiModelProperty(hidden = true)
    public static final String JOB_KEY = "JOB_KEY";

    @ApiModelProperty(value = "任务id",dataType = "Long",example = "201909250001",required = true)
    @NotNull(message = "任务id不能为空")
    private Long id;

    /**
     * 定时器名称
     */
    @ApiModelProperty(value = "定时器名称",dataType = "String",example = "测试任务",required = true)
    @NotBlank(message = "定时器名称不能为空")
    private String jobName;

    /**
     * Bean名称
     */
    @ApiModelProperty(value = "Bean名称",dataType = "String",example = "testTaskJob",required = true)
    @NotBlank(message = "Bean名称不能为空")
    private String beanName;

    /**
     * 方法名称
     */
    @ApiModelProperty(value = "方法名称",dataType = "String",example = "run",required = true)
    @NotBlank(message = "方法名称不能为空")
    private String methodName;

    /**
     * 参数
     */
    @ApiModelProperty(value = "参数",dataType = "String",required = false)
    private String params;

    /**
     * cron表达式
     */
    @ApiModelProperty(value = "cron表达式",dataType = "String",required = true,example = "0/1 * * * * ?")
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态",dataType = "Boolean",required = true,example = "true")
    @NotNull(message = "状态不能为空")
    private Boolean isPause = false;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",dataType = "String",required = false)
    private String remark;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期",dataType = "Timestamp",required = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "GTM+8")
    private Date updateTime;

   	@ApiModelProperty(value = "是否自动需要关闭",dataType = "boolean",required = true)
    @NotNull(message = "是否自动需要关闭不能为空")
    private boolean needClose = false;
}

