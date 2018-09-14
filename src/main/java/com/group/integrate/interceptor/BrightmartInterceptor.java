package com.group.integrate.interceptor;

public class BrightmartInterceptor {
    public void before() {
        System.out.println("Brightmart的拦截器类，的before()方法");
    }

    public void after() {
        System.out.println("Brightmart的拦截器类，的after()方法");
    }
}