package concurrency.thread.interrupt;

public class TestInterrupt2 {
	public static void main(String[] args) {
		MyThread2 myThread = new MyThread2();
		myThread.start();
		
		try {
			/** 1. 这里sleep,会把执行机会让给myThread */
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/** 3.对myThread进行中断,myThread.run()方法中会捕捉到该中断 */
		myThread.interrupt();
		
		/** 打印出true */
		System.out.println("is stop 1 ? = " + myThread.isInterrupted());
		System.out.println("is stop 2 ? = " + myThread.isInterrupted());
	}
}

class MyThread2 extends Thread {
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 50000; i++) {
			try {
				/** 2. 这里sleep,会把执行机会还给main */
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("i=" + i);
		}
	}
}