package concurrency.executor.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ReportProcessor implements Runnable {
	private CompletionService<String> service;
	private boolean end;

	public ReportProcessor(CompletionService<String> service, boolean end) {
		this.service = service;
		this.end = end;
	}

	@Override
	public void run() {
		while (!end) {
			try {
				/**
				 * 获取已经完成的future task, 同时remove 这个future task,如果现在还没有完成的future
				 * task，则等待指定时间
				 * 
				 * 通过Future.get本身就可以获取，看不出用这个有多大的好处，可能感觉用这个service显示出分离的效果
				 * 在ReportRequest中递交task,在这里获取task
				 */
				Future<String> result = service.poll(20, TimeUnit.SECONDS);
				if (result != null) {
					String report = result.get();
					System.out.println("ReporteReceiver: Report received " + report);
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		System.out.println("ReporteSender: end ");
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

}
