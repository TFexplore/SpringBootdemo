package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class MyConfig implements WebMvcConfigurer {

    // 配置首页视图控制器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/common/index");
        registry.addViewController("/index").setViewName("/common/index");
        registry.addViewController("/user/suc").setViewName("/user/suc");
        registry.addViewController("/admin/vip").setViewName("/admin/vip");
        registry.addViewController("/user/user").setViewName("/user/user");
    }
}
