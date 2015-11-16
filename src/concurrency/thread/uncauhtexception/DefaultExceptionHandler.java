package concurrency.thread.uncauhtexception;

import java.lang.Thread.UncaughtExceptionHandler;

public class DefaultExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("An exception has been captured by the default exception hanlder");
		System.out.println("Thread: " + t.getId());
		System.out.println("Exception:" + e.getClass().getName() + ":" + e.getMessage());
		
		System.out.println("Stack Track: ");
		e.printStackTrace();
		System.out.println("Thread status: " + t.getState());
	}

}
