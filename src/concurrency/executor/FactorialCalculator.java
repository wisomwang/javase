package concurrency.executor;

import java.util.concurrent.Callable;

public class FactorialCalculator implements Callable<Integer> {

	private Integer number;

	public FactorialCalculator(Integer number) {
		this.number = number;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println(Thread.currentThread().getName() + " execute this task " + number);
		if (number == 0 || number == 1) {
			return 1;
		} else {
			int result = 1;
			for (int i = 1; i < number; i++) {
				result = result * i;
			}
			return result;
		}
	}

}
