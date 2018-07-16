package org.tech.java.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 实现了方法拦截器接口 
 */
public class HackerProxy implements MethodInterceptor {
	private Enhancer enhancer = new Enhancer();
	
	public Object getProxy(Class clazz) {
		// 设置要创建动态代理的类
		enhancer.setSuperclass(clazz);
		// 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
		enhancer.setCallback(this);
		//通过字节码技术动态创建子类实例 
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("**** I am a hacker,Let's see what the poor programmer is doing Now...");
		proxy.invokeSuper(obj, args);
		System.out.println("****  Oh,what a poor programmer.....");
		return null;
	}

}