package concurrency.thread.interrupt;

public class TestInterrupt4 {
	public static void main(String[] args) {
		MyThread4 myThread = new MyThread4();
		myThread.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		myThread.interrupt();

		System.out.println("main end.");
	}
}

class MyThread4 extends Thread {
	@Override
	public void run() {
		super.run();
		try {
			for (int i = 0; i < 500000; i++) {
				if (Thread.interrupted()) {
					System.out.println("myThread is interrupted.");
					/** 抛出异常，但后面的for循环也会继续执行 */
					throw new InterruptedException();

					/** break也可以从循环跳出，但如果后面还是有循环的话，还是会继续执行 */
					// break;
				}
				System.out.println("i=" + i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("myThread end");
		for (int i = 0; i < 500000; i++) {
			System.out.println("I am still running");
		}
	}
}