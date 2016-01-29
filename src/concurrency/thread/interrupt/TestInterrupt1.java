package concurrency.thread.interrupt;

public class TestInterrupt1 {
	public static void main(String[] args) {
		MyThread1 myThread = new MyThread1();
		myThread.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/** 停止myThread代表的线程对象，即设置该线程对象的中断标志位, myThread线程执行完后才会执行这里，所以该中断调用无效 
		 * 如果myThread线程执行过程当中返回这里，则下面的输出会是true
		 * */
		myThread.interrupt();

		/** 测试myThread线程是否被中断，这里返回false */
		System.out.println("is stop 1 ? = " + myThread.isInterrupted());
		System.out.println("is stop 2 ? = " + myThread.isInterrupted());
		
		/** 调用main线程的interrupt() */
		Thread.currentThread().interrupt();
		
		/** 返回true */
		System.out.println("is stop 3 ? = " + Thread.currentThread().isInterrupted());
		System.out.println("is stop 4 ? = " + Thread.currentThread().isInterrupted());
	}
}

class MyThread1 extends Thread {
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 50000; i++) {
			System.out.println("i=" + i);
		}
	}
}