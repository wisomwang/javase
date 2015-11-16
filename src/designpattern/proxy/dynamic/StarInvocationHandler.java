package designpattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StarInvocationHandler implements InvocationHandler {

	private Star realStar;

	public StarInvocationHandler(Star realStar) {
		this.realStar = realStar;
	}

	/**
	 * proxy代理对象，相当于$Proxy0 method代理对象的方法，或者也可以说是真实对象的方法，反正都一样的
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// method.invoke(proxy,
		// args);//会死循环，Proxy0.sing()会调用这里的invoke,然后又回调$Proxy0的sing()

		if ("sing".equals(method.getName())) {
			method.invoke(realStar, args);
			return null;
		}
		System.out.println("经纪人" + method.getName());
		return null;
	}

}
