package concurrency.thread.interrupt;

public class TestInterrupt3 {
	public static void main(String[] args) {
		Thread.currentThread().interrupt();

		/** 判断当前线程是否中断，如果已中断，则清空中断标识位，第二次进来调用就返回false了 */
		System.out.println("is stop 1 ? = " + Thread.interrupted());
		System.out.println("is stop 2 ? = " + Thread.interrupted());
	}
}
