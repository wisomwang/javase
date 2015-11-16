package concurrency.executor.completionservice;

import java.util.concurrent.Callable;

public class ReportGenerator implements Callable<String> {
	private String sender;
	private String title;

	public ReportGenerator(String sender, String title) {
		this.sender = sender;
		this.title = title;
	}

	@Override
	public String call() throws Exception {
		return sender + " " + title;
	}
}
