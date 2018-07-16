package org.tech.java.proxy.dynamic;

import org.tech.java.proxy.ProxyUtils;

public class DynamicProxyTest {

	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		MyInvocationHandler invocationHandler = new MyInvocationHandler(
				userService);

		UserService proxy = (UserService) invocationHandler.getProxy();
		proxy.add();
		
		// 查看生成的代理class
		ProxyUtils.generateClassFile(proxy.getClass(), "ProxyUserServiceImpl");
	}
}
