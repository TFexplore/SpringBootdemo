package com.example.demo.Quartz;

import org.quartz.Scheduler;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 描述: quartz
 *
 * @Auther: lzx
 * @Date: 2019/9/24 13:16
 */
@Configuration
public class QuartzConfig {

    /**
     * 解决Job中注入Spring Bean 为 null 的问题
     */
    @Component("quartzJobFactory")
    public class QuartzJobFactory extends AdaptableJobFactory {
        @Autowired
        private AutowireCapableBeanFactory capableBeanFactory;

        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            Object jobInstance = super.createJobInstance(bundle);
            capableBeanFactory.autowireBean(jobInstance);
            return jobInstance;
        }
    }

    /**
     * 配置quartz
     */
    @Bean("scheduler")
    public Scheduler scheduler(QuartzJobFactory quartzJobFactory)throws Exception{
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(quartzJobFactory);
        factoryBean.afterPropertiesSet();
        Scheduler scheduler = factoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }

}

