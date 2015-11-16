package concurrency.executor.completionservice;

import java.util.concurrent.CompletionService;

public class ReportRequest implements Runnable {
	private String name;

	private CompletionService<String> service;

	public ReportRequest(String name, CompletionService<String> service) {
		this.name = name;
		this.service = service;
	}

	@Override
	public void run() {
		ReportGenerator generator = new ReportGenerator(name, "Report");

		/** 递交 callable任务，由service中的执行器执行该callable */
		service.submit(generator);
	}

}
