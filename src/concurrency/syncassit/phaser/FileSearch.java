package concurrency.syncassit.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable {

	private String initPath;

	private String suffix;

	private Phaser phaser;

	private List<String> results = new ArrayList<>();

	public FileSearch(String initPath, String suffix, Phaser phaser) {
		this.initPath = initPath;
		this.suffix = suffix;
		this.phaser = phaser;
	}

	private void directoryProcess(File file) {
		File[] list = file.listFiles();
		if (null != list) {
			for (File fileTemp : list) {
				if (fileTemp.isDirectory()) {
					directoryProcess(fileTemp);
				} else {
					fileProcess(fileTemp);
				}
			}
		}
	}

	private void fileProcess(File file) {
		if (file.getName().endsWith(suffix)) {
			results.add(file.getAbsolutePath());
		}
	}

	private void filterResults() {
		List<String> filterResult = new ArrayList<>();
		long actualDate = new Date().getTime();
		for (String filePath : results) {
			File file = new File(filePath);
			long fileDate = file.lastModified();

			if ((actualDate - fileDate) < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
				filterResult.add(filePath);
			}
		}

		results = filterResult;
	}

	private boolean checkResults() {
		if (results.isEmpty()) {
			System.out.println(Thread.currentThread().getName() + ": Phase " + phaser.getPhase() + ": 0 results");
			System.out.println(Thread.currentThread().getName() + ": Phase " + phaser.getPhase() + ": end");

			/** 如果结果集为空，通知phaser对象当前线程已经结束这个阶段，并且将不再参与接下来的阶段操作 */
			phaser.arriveAndDeregister();
			return false;
		} else {
			System.out.println(Thread.currentThread().getName() + ": Phase " + phaser.getPhase() + ": "
					+ results.size() + " results");
			System.out.println(Thread.currentThread().getName() + ": Phase " + phaser.getPhase()
					+ ": continue to next pahse work");

			/** 如果结果不为空，通知Phaser对象当前线程已经完成了当前阶段，需要被阻塞直到其他线程也都完成当前阶段 */
			phaser.arriveAndAwaitAdvance();
			return true;
		}
	}

	private void showInfo() {
		for (String filePath : results) {
			System.out.println("Absolute path" + filePath);
		}

		/** 通知Phaser对象当前线程已经完成了当前阶段，需要被阻塞直到其他线程也都完成当前阶段 */
		phaser.arriveAndAwaitAdvance();
	}

	@Override
	public void run() {
		/**
		 * 第一阶段，等待三个线程都到这里，进入run方法，然后在一起往下执行. 在run方法
		 * 的开头调用这个方法可以保障在所有线程创建好之前没有线程开始执行任务，即所有线程都在同一起跑线上
		 */
		phaser.arriveAndAwaitAdvance();
		System.out.println("Thread " + Thread.currentThread().getName() + " Starting.");

		File file = new File(initPath);
		if (file.isDirectory()) {
			directoryProcess(file);
		}

		System.out.println("第二阶段");
		/** 第二阶段， 查找log文件 */
		if (!checkResults()) {
			return;
		}

		filterResults();

		System.out.println("第三阶段");
		/** 第三阶段， 过滤不超过1天的log文件 */
		if (!checkResults()) {
			return;
		}

		/** 第四阶段，显示log文件 */
		System.out.println("第四阶段");
		showInfo();
		System.out.println(Thread.currentThread().getName() + ": Phase " + phaser.getPhase() + ":  Work completed");
		phaser.arriveAndDeregister();
	}
}
