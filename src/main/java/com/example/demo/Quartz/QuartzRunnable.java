package com.example.demo.Quartz;

import com.example.demo.common.SpringBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 描述: 执行方法的函数
 *
 * @Auther: lzx
 * @Date: 2019/9/25 13:21
 */
@Slf4j
public class QuartzRunnable implements Runnable {

    private Object target;
    private Method method;
    private String params;

    public QuartzRunnable(String beanName, String methodName, String params) throws NoSuchMethodException {
        this.target = SpringBeanUtil.getBeanFromSpringByBeanName(beanName);
        this.params = params;

        if(StringUtils.isNotBlank(params)){
            this.method = target.getClass().getDeclaredMethod(methodName,String.class);
        }else{
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try{
            ReflectionUtils.makeAccessible(method);
            if(StringUtils.isNotBlank(params)){
                method.invoke(target,params);
            }else{
                method.invoke(target);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }
}

