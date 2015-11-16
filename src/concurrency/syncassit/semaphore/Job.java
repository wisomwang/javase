package concurrency.syncassit.semaphore;

public class Job implements Runnable {

	private PrintQueue printQueue;

	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}

	@Override
	public void run() {
		System.out.println("Going to print a job " + Thread.currentThread().getName());
		printQueue.print();
	}

}
