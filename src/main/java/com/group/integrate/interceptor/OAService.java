package com.group.integrate.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class OAService implements IOAService {

    @Override
    public void onclineOffice() {
        String str = null;
        System.out.println("我们使用OA在线办公系统onclineOffice，工作中（onlineOffice）。。。");
    }
}

//a Brightmart的动态代理类，用于代理某个类。
//b 本示例中我们用来代理【OA办公系统】服务类，目标是将特定的任务绑定到【OA办公系统】服务类之前或之后来执行
//c 我们可以认为在绑定了绑定被代理类和代理处理器（bind()），并显示调用被代理类的方法时候，invoke便被执行。
class BrightmartDynamicProxy implements InvocationHandler {
    private Object object;//被代理的对象

    //Brightmart自定义的拦截器（它是一个普通的类）
    private BrightmartInterceptor interceptor = new BrightmartInterceptor();

    // 动态生成一个代理类对象,并绑定被代理类(object)和代理处理器(this).
    //或者说：bind方法返回一个特定的，具有代理功能的对象，这个对象根据传入的被代理对象的接口而定。
    public Object bind(Object object) {
        this.object = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),

                object.getClass().getInterfaces(), this);
    }

    //method:被调用的方；args:方法的参数；标记为1,2的方法可以根据你业务需要而改动，或只在调用前或调用后执行某种操作
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result = null;
        interceptor.before();//1 调用before方法
        method.invoke(object, args);
        interceptor.after();//2 调用after方法
        return result;
    }

}