package com.mohaji.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mohaji.interceptor.LoggerInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor()).excludePathPatterns("/css/**","/images/**","/js/**");
		// 지정된 파일을 제외하고 인터셉트를 한다.
	}
	
}
