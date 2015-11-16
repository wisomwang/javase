package designpattern.singleton;

/**
 * 饿汉式
 * 
 * @author smwang
 * 
 */
public class SingletonDemo1 {
	/** static的，类加载的时候就会初始化变量（没有延时加载的优势），类加载的时候是天然的线程安全的，不涉及线程同步 安全问题，肯定是安全的 */
	private static SingletonDemo1 instance = new SingletonDemo1();

	/** 构造器私有化 */
	private SingletonDemo1() {

	}

	/** 获取的时候不需要同步，所以性能高 */
	public static SingletonDemo1 getInstance() {
		return instance;
	}
}
