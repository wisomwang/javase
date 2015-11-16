package designpattern.singleton;

/**
 * 懒汉式
 * 
 * @author smwang
 * 
 */
public class SingletonDemo2 {
	private static SingletonDemo2 instance;

	/** 构造器私有化 */
	private SingletonDemo2() {

	}

	/** 获取的时候需要同步，所以并发性能相对不高 */
	public static synchronized SingletonDemo2 getInstance() {
		if (instance == null) {// 其实只需要第一次进来的时候需要同步，一旦对象创建完后，后续的同步都是浪费的
			instance = new SingletonDemo2();
		}
		return instance;
	}
}
