package com.dgpalife.resourcemanagement.config;

import com.dgpalife.resourcemanagement.interceptor.AuthInterceptor;
import com.dgpalife.resourcemanagement.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class MywebConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/toLogin");
        excludePath.add("/preLogin");
        excludePath.add("/doLogin/**");
        excludePath.add("/logout");
        excludePath.add("/toReg");
        excludePath.add("/doReg");
        excludePath.add("/static/**");

        //对来自 /rm/** 链接的请求进行拦截，对例外的登录请求不进行拦截
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePath);
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePath);
        //super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        //registry.addViewController("/index.html").setViewName("login");
    }

}
