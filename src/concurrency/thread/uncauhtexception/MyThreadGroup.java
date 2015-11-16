package concurrency.thread.uncauhtexception;

/**
 * 线程组中的未捕获异常处理器
 * 
 * @author smwang
 * 
 */
public class MyThreadGroup extends ThreadGroup {

	public MyThreadGroup(String name) {
		super(name);
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		super.uncaughtException(t, e);
		System.out.println("An exception has been captured by thread group exception handler");
	}

}
