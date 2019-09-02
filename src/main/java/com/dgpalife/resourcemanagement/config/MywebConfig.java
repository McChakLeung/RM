package com.dgpalife.resourcemanagement.config;

import com.dgpalife.resourcemanagement.interceptor.CommonInterceptor;
import com.dgpalife.resourcemanagement.listener.SystemLaunchListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Configuration
public class MywebConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry){
        System.out.println("拦截器起作用");
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/toLogin");
        excludePath.add("/preLogin");
        excludePath.add("/doLogin");
        excludePath.add("/logout");
        excludePath.add("/toReg");
        excludePath.add("/doReg");
        excludePath.add("/static/**");

        //对来自 /rm/** 链接的请求进行拦截，对登录请求/rm/login不进行拦截
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePath);
        //super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
