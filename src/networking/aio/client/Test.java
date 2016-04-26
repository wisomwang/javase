package networking.aio.client;

import java.util.concurrent.Callable;

public class Test {

	public static void main(String[] args) {
//		Thread test = new Thread();
//		test.start();
//
//		System.out.println(Thread.currentThread().getName());

		Thread mythread = new MyThread();
		mythread.start();
		
		Thread mythread11 = new Thread(new MyRunnable());
		mythread11.start();
		
		Callable<V>

	}
}

class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}

class MyRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}