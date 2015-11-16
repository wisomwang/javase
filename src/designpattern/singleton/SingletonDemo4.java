package designpattern.singleton;

/**
 * 内部静态类
 * 
 * @author smwang
 * 
 */
public class SingletonDemo4 {
	private static class SingletonClassInstance {
		private static SingletonDemo4 instance = new SingletonDemo4();// 线程安全，final可有可无
	}

	/** 构造器私有化 */
	private SingletonDemo4() {

	}

	/** 获取的时候不需要同步，所以性能相对高 */
	public static SingletonDemo4 getInstance() {
		return SingletonClassInstance.instance;// 调用 的时候才会去加载
	}
}
