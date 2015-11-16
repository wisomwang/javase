package concurrency.executor.rejectedexecutionhandler;

public class Task implements Runnable {

	private String name;

	public Task(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("Task " + name + ": Starting");
		System.out.println("Task " + name + ": Ending");
	}
}
