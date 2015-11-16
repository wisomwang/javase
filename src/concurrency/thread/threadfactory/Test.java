package concurrency.thread.threadfactory;

public class Test {

	public static void main(String[] args) {
		MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
		Task task = new Task();
		for (int i = 0; i < 10; i++) {
			Thread thread = factory.newThread(task);
			thread.start();
		}
		System.out.println("Factory stats:");
		System.out.println(factory.getStats());
	}

}
