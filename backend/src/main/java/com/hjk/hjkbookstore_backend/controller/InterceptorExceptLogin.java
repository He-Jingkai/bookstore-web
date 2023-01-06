package com.hjk.hjkbookstore_backend.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

@Configuration
public class InterceptorExceptLogin implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {// 登录校验
        List<String> excludePaths = Collections.singletonList("/checkuser");
        registry.addInterceptor(new Interceptor()).addPathPatterns("/**").excludePathPatterns(excludePaths);
        }
    }

