package com.group.integrate.interceptor;//执行自定义的拦截器类

public class TestBrightmartInterceptor {
    public static void main(String[] args) {
        //Brightmart自己实现的拦截器,调用bind方法获取一个代理类，这个代理类调用原有的业务逻辑类（被代理类）的方法。
        BrightmartDynamicProxy handler = new BrightmartDynamicProxy();
        IOAService oaService = new OAService();
        IOAService oaServiceProxy = (IOAService) handler.bind(oaService);//关键代码
        oaServiceProxy.onclineOffice();
    }
}