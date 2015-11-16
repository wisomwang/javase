package concurrency.syncassit.phaser;

import java.util.concurrent.Phaser;

public class Test {
	public static void main(String[] args) {
		Phaser phaser = new Phaser(3);

		/**
		 * 确保Runnable对象，这里是FileSearch对象的创建的顺序和下面对应Thread对象的顺序一致，
		 * 这里是documents,apps,system,下面对应docuemntsThread,appsThread, systemThread
		 */
		FileSearch documents = new FileSearch("C:\\temp", "log", phaser);
		FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
		FileSearch system = new FileSearch("C:\\Windows", "log", phaser);

		Thread documentsThread = new Thread(documents, "Documents");
		documentsThread.start();

		Thread appsThread = new Thread(apps, "Apps");
		appsThread.start();

		Thread systemThread = new Thread(system, "System");
		systemThread.start();

		/** 动态增加一个，下面就可以再新增一个线程 */
		// phaser.register();
		//
		// FileSearch system1 = new FileSearch("C:\\Windows", "log", phaser);
		// Thread systemThread1 = new Thread(system1, "System1");
		// systemThread1.start();

		/** 这段代码是为了让main线程等待这三个线程结束后再执行最后一句话，没有这段代码，主线程会先执行完最后一句话 */
		try {
			documentsThread.join();
			appsThread.join();
			systemThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Phaser terminated: " + phaser.isTerminated());

	}
}
