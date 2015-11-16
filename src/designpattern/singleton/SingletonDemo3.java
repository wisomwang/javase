package designpattern.singleton;

/**
 * 双查检查模式，不推荐使用
 * 
 * @author smwang
 * 
 */
public class SingletonDemo3 {
	private static SingletonDemo3 instance;

	/** 构造器私有化 */
	private SingletonDemo3() {

	}

	/**
	 * 线程 1 进入 getInstance() 方法。 由于 instance 为 null ，线程 1 在 //1 处进入 synchronized
	 * 块。 线程 1 被线程 2 预占。 线程 2 进入 getInstance() 方法。 由于 instance 仍旧为 null ，线程 2
	 * 试图获取 //1 处的锁。然而，由于线程 1 持有该锁，线程 2 在 //1 处阻塞。 线程 2 被线程 1 预占。 线程 1 执行，由于在
	 * //2 处实例仍旧为 null ，线程 1 还创建一个 Singleton 对象并将其引用赋值给instance 。 线程 1 退出
	 * synchronized 块并从 getInstance() 方法返回实例。 线程 1 被线程 2 预占。 线程 2 获取 //1 处的锁并检查
	 * instance 是否为 null 。 由于 instance 是非 null 的，并没有创建第二个 Singleton 对象，由线程 1
	 * 创建的对象被返回。
	 * 双重检查锁定背后的理论是完美的。不幸地是，现实完全不同。双重检查锁定的问题是：并不能保证它会在单处理器或多处理器计算机上顺利运行。
	 * 
	 * 双重检查锁定失败的问题并不归咎于 JVM 中的实现 bug，而是归咎于 Java
	 * 平台内存模型。内存模型允许所谓的“无序写入”，这也是这些习语失败的一个主要原因。
	 * 
	 * 无序写入
	 * 
	 * 为解释该问题，需要重新考察代码 中的 //3 行。此行代码创建了一个 Singleton 对象并初始化变量instance
	 * 来引用此对象。这行代码的问题是：在 Singleton 构造函数体执行之前，变量 instance 可能成为非 null 的。
	 * 
	 * 什么？这一说法可能让您始料未及，但事实确实如此。在解释这个现象如何发生前，请先暂时接受这一事实，我们先来考察一下双重检查锁定是如何被破坏的。
	 * 假设代码执行以下事件序列：
	 * 
	 * 线程 1 进入 getInstance() 方法。 由于 instance 为 null ，线程 1 在 //1 处进入 synchronized
	 * 块。 线程 1 前进到 //3 处，但在构造函数执行之前 ，使实例成为非 null 。 线程 1 被线程 2 预占。 线程 2 检查实例是否为
	 * null 。因为实例不为 null，线程 2 将 instance 引用返回给一个构造完整但部分初始化了的 Singleton 对象。 线程 2
	 * 被线程 1 预占。 线程 1 通过运行 Singleton 对象的构造函数并将引用返回给它，来完成对该对象的初始化。 此事件序列发生在线程 2
	 * 返回一个尚未执行构造函数的对象的时候。
	 * 
	 * 换句话，返回的实例可能是一个没有初始化的对象 伪代码
	 * 
	 * mem = allocate(); //Allocate memory for Singleton object. instance = mem;
	 * //Note that instance is now non-null, but //has not been initialized.
	 * ctorSingleton(instance); //Invoke constructor for Singleton passing
	 * //instance.
	 * 
	 * */
	public static SingletonDemo3 getInstance() {
		if (instance == null) {
			synchronized (SingletonDemo3.class) { // 1
				if (instance == null) // 2
					instance = new SingletonDemo3(); // 3
			}
		}
		return instance;
	}

	/**
	 * 解决上面的无序写入的情况
	 * 
	 * 此代码试图避免无序写入问题。它试图通过引入局部变量 inst 和第二个 synchronized 块来解决这一问题。该理论实现如下：
	 * 
	 * 线程 1 进入 getInstance() 方法。 由于 instance 为 null ，线程 1 在 //1 处进入第一个
	 * synchronized 块。 局部变量 inst 获取 instance 的值，该值在 //2 处为 null 。 由于 inst 为 null
	 * ，线程 1 在 //3 处进入第二个 synchronized 块。 线程 1 然后开始执行 //4 处的代码，同时使 inst 为非 null
	 * ，但在 Singleton 的构造函数执行前。（这就是我们刚才看到的无序写入问题。） 线程 1 被线程 2 预占。 线程 2 进入
	 * getInstance() 方法。 由于 instance 为 null ，线程 2 试图在 //1 处进入第一个 synchronized
	 * 块。由于线程 1 目前持有此锁，线程 2 被阻断。 线程 1 然后完成 //4 处的执行。 线程 1 然后将一个构造完整的 Singleton
	 * 对象在 //5 处赋值给变量 instance ，并退出这两个synchronized 块。 线程 1 返回 instance 。 然后执行线程
	 * 2 并在 //2 处将 instance 赋值给 inst 。 线程 2 发现 instance 为非 null ，将其返回。 这里的关键行是
	 * //5。此行应该确保 instance 只为 null 或引用一个构造完整的 Singleton
	 * 对象。该问题发生在理论和实际彼此背道而驰的情况下。
	 * 
	 * 由于当前内存模型的定义，清单 7 中的代码无效。Java 语言规范（Java Language Specification，JLS）要求不能将
	 * synchronized 块中的代码移出来。但是，并没有说不能将 synchronized 块外面的代码移入synchronized 块中。
	 * 
	 * @return
	 */
	public static SingletonDemo3 getInstance1() {
		if (instance == null) {
			synchronized (SingletonDemo3.class) { // 1
				SingletonDemo3 inst = instance; // 2
				if (inst == null) {
					synchronized (SingletonDemo3.class) { // 3
						inst = new SingletonDemo3(); // 4
					}
					instance = inst; // 5
				}
			}
		}
		return instance;
	}

	/**
	 * 上面getInstance1（）方法会被编译器优化成如下代码，会同样遇到getInstance()遇到的问题
	 * 
	 * @return
	 */
	public static SingletonDemo3 getInstance2() {
		if (instance == null) {
			synchronized (SingletonDemo3.class) { // 1
				SingletonDemo3 inst = instance; // 2
				if (inst == null) {
					synchronized (SingletonDemo3.class) { // 3
						// inst = new Singleton(); //4
						instance = new SingletonDemo3();
					}
					// instance = inst; //5
				}
			}
		}
		return instance;
	}
}
