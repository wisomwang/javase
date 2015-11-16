package concurrency.syncassit.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 测试使用Phaser的arrive方法和awaitAdvance方法
 * 
 * @author smwang
 * 
 */
public class TestArriveAndAwaitAdvance {

	public static void main(String[] args) {
		Phaser phaser = new Phaser(1); // 此处可使用CountDownLatch(1)
		for (int i = 0; i < 3; i++) {
			new MyThread((char) (97 + i), phaser).start();
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("before arrive, phase " + phaser.getPhase());

		/** 在调用这一句之前，，phaser处于阶段0, 执行完这句后，pahser处于阶段1， 这样下面 run中的等待会结束，执行打印代码 */
		phaser.arrive(); // 此处可使用latch.countDown()
	}
}

class MyThread extends Thread {
	private char c;
	private Phaser phaser;

	public MyThread(char c, Phaser phaser) {
		this.c = c;
		this.phaser = phaser;
	}

	@Override
	public void run() {
		System.out.println("current phase " + phaser.getPhase());

		/**
		 * 当phaser当前所处的阶段与传入的参数值不等时，该方法直接返回然后继续执行下面的代码， 相等时， 则在这里等待，
		 * 直到phaser处于下一阶段
		 */
		phaser.awaitAdvance(phaser.getPhase()); // 此处可使用latch.await()
		for (int i = 0; i < 2; i++) {
			System.out.println(c + " ");
		}
	}
}
