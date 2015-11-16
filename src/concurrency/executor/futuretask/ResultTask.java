package concurrency.executor.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ResultTask extends FutureTask<String> {
	private String name;

	public String getName() {
		return name;
	}

	public ResultTask(Callable<String> callable) {
		super(callable);
		this.name = ((ExecutableTask) callable).getName();
	}

	/**
	 * 当task的状态变为isDone时（当task正常结束或者调用cancel时），该方法会被调用，子类可以覆写 该 方法来自定义操作
	 */
	@Override
	protected void done() {
		if (isCancelled()) {
			System.out.println(name + " has been canceled");
		} else {
			System.out.println(name + " has been finished");
		}
	}

}
