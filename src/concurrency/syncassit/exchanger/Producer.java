package concurrency.syncassit.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {

	private List<String> produceBuffer;
	private Exchanger<List<String>> exchanger;

	public Producer(List<String> produceBuffer, Exchanger<List<String>> exchanger) {
		this.produceBuffer = produceBuffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;
		for (int i = 0; i < 1; i++) {
			System.out.println("Producer cycle : " + cycle);

			for (int j = 0; j < 10; j++) {
				String message = "Producer Event " + ((i * 10) + j);
				System.out.println("Producer: " + message);
				produceBuffer.add(message);
			}

			try {
				/** produceBuffer中的元素来自于consumerBuffer,每次为空 */
				produceBuffer = exchanger.exchange(produceBuffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Producer: data size from consumer is " + produceBuffer.size());

			cycle++;
		}
	}

}
