package com.group.integrate.config;

import com.group.integrate.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//这里可以添加多个拦截器
		registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}