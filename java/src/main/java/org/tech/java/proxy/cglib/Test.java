package org.tech.java.proxy.cglib;

import org.tech.java.proxy.ProxyUtils;

import net.sf.cglib.proxy.Enhancer;

public class Test {

	public static void main(String[] args) {
		/*Programmer progammer = new Programmer();
		Hacker hacker = new Hacker();
		// cglib 中加强器，用来创建动态代理
		Enhancer enhancer = new Enhancer();
		// 设置要创建动态代理的类
		enhancer.setSuperclass(progammer.getClass());
		// 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
		enhancer.setCallback(hacker);
		Programmer proxy = (Programmer) enhancer.create();
		proxy.code();*/
		HackerProxy proxy = new HackerProxy();
		Programmer p = (Programmer)proxy.getProxy(Programmer.class);
		p.code();
		
		ProxyUtils.generateClassFile(proxy.getClass(), "CGlibProxy");

	}
}
