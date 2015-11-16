package concurrency.executor.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) {

		ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
		CompletionService<String> service = new ExecutorCompletionService<String>(executor);

		ReportRequest faceRequest = new ReportRequest("face", service);
		ReportRequest onlineRequest = new ReportRequest("online", service);

		Thread faceThread = new Thread(faceRequest);
		Thread onlineThread = new Thread(onlineRequest);

		ReportProcessor processor = new ReportProcessor(service, false);
		Thread processorThread = new Thread(processor);

		faceThread.start();
		onlineThread.start();
		processorThread.start();

		try {
			faceThread.join();
			onlineThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/** 执行器要记得关闭 */
		/**
		 * 以前递交的task进行有序关闭，不再接受新的task,这个方法不会等待已经递交的task完成任务，意思是说已经递交的
		 * task可能没有完成，就有可能被关闭
		 */
		executor.shutdown();

		try {
			/** 等待10秒钟后关闭 */
			System.out.println(executor.awaitTermination(10, TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		processor.setEnd(true);
		System.out.println("Main: ends");
	}
}
