package designpattern.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * 客户端类 相当于例子中的笔记本，只有USB接口,但想要调用PS/2接口
 * 
 * @author smwang
 * 
 */
public class Client {
	public static void main(String[] args) {
		Star realStart = new RealStar();
		StarInvocationHandler handler = new StarInvocationHandler(realStart);

		Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] { Star.class },
				handler);

		proxy.signContract();
		proxy.sing();
	}
}
