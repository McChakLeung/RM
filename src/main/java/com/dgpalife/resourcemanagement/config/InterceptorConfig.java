package com.dgpalife.resourcemanagement.config;

import com.dgpalife.resourcemanagement.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry){
        //对来自 /rm/** 链接的请求进行拦截，对登录请求/rm/login不进行拦截
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/rm/**").excludePathPatterns("/rm/login");

    }
}
