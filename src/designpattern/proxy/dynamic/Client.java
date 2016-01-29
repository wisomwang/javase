package designpattern.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
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
