package concurrency.lock.fairlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PricesInfo {
	private Lock lock;
	double price1;
	double price2;

	public PricesInfo(double price1, double price2, boolean fair) {
		this.price1 = price1;
		this.price2 = price2;
		this.lock = new ReentrantLock(fair);
	}

	public double getPrice1() {
		/**
		 * 第一个线程进来获取锁，其他线程处于等待状态，当第一个线程释放锁时，随即又调用lock.lock试图再次获取锁， 但由于采取了公平锁策略，
		 * 他不可能马上再次获取锁，等待中的第二个线程会获取锁，然后是第三个，以此类推
		 * 
		 */
		lock.lock();
		double price = 0;
		try {
			System.out.println("Thread " + Thread.currentThread().getName() + " get the price1");
			Thread.sleep(100);
			price = price1;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		/** 下面这段代码没有的话，采用fair策略和不采用效果是一样的 */
		/**
		 * 这样让我怀疑， 是不是这个公平策略只是针对重新再次试图获取锁的时候才会起作用，一开始初始化所有的线程，然后每个线程按什么顺序被调用
		 * 不是按这个策略来，因为去掉这段代码，然后去掉创建线程的那个等待10ms的代码，会导致线程无序的获取到锁
		 */

		/** 当前线程再次尝试获取锁 */
		lock.lock();

		price = 0;
		try {
			System.out.println("Thread " + Thread.currentThread().getName() + " get the price1");
			Thread.sleep(100);
			price = price1;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return price;
	}
}
