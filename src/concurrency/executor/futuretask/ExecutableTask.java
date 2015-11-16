package concurrency.executor.futuretask;

import java.util.concurrent.Callable;

public class ExecutableTask implements Callable<String> {

	private String name;

	public ExecutableTask(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String call() throws Exception {
		return "Hello world, i am " + name;
	}

}
