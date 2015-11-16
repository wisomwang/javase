package concurrency.thread.uncauhtexception;

public class Task implements Runnable {

	@Override
	public void run() {
		Integer.parseInt("A");
		System.out.println("i am here");
	}
	
}
