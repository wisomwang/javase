package concurrency.thread.uncauhtexception;


public class Test {

	public static void main(String[] args) {
//		testDefault();
//		testCustom();
		testThreadGroup();
	}

	private static void testDefault() {
		Task task = new Task();
		Thread thread = new Thread(task);
		Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler());
		thread.start();
	}
	
	private static void testCustom() {
		Task task = new Task();
		Thread thread = new Thread(task);
		/** 自定义处理运行时异常 */
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();
	}
	
	static void testThreadGroup()
	{
		Task task = new Task();
		MyThreadGroup group = new MyThreadGroup("testgroup");
		Thread thread = new Thread(group, task);
		thread.start();
	}

}
