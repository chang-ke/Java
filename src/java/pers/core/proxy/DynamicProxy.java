package pers.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @description: 动态代理
 * @author: Mr.Dang 315764194@qq.com
 * @Date: 2018-11-04 21:53
 **/


public class DynamicProxy implements InvocationHandler {

    private Object targetObject;

    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Arrays.stream(args).forEach(System.out::println);
        Object ret = null;
        try {
            /*原对象方法调用前处理日志信息*/
            System.out.println("start-->>");

            //调用目标方法
            ret = method.invoke(targetObject, args);
            /*原对象方法调用后处理日志信息*/
            System.out.println("success-->>");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error-->>");
        }
        return ret;
    }


}
