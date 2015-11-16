package concurrency.executor.rejectedexecutionhandler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedTaskController implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("RejectedTaskController: The task " + r.toString() + " has been rejected.");
		System.out.println("RejectedTaskController: Terminating " + executor.isTerminating());
		System.out.println("RejectedTaskController: Terminated " + executor.isTerminated());

		/** 再次submit还会被拒绝，从而进入死循环 */
//		executor.submit(r);

	}

}
