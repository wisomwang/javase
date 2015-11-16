package concurrency.thread.uncauhtexception;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("An exception has been captured");
		System.out.println("Thread: " + t.getId());
		System.out.println("Exception:" + e.getClass().getName() + ":" + e.getMessage());
		
		System.out.println("Stack Track: ");
		e.printStackTrace();
		System.out.println("Thread status: " + t.getState());
	}

}
