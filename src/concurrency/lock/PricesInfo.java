package concurrency.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PricesInfo {
	private ReadWriteLock readWritelock;
	double price1;
	double price2;

	public PricesInfo(double price1, double price2) {
		this.price1 = price1;
		this.price2 = price2;
		this.readWritelock = new ReentrantReadWriteLock();
	}

	public double getPrice1() {
		readWritelock.readLock().lock();
		double price = price1;
		readWritelock.readLock().unlock();
		return price;
	}

	public double getPrice2() {
		/** 读锁 */
		readWritelock.readLock().lock();
		double price = price2;
		readWritelock.readLock().unlock();
		return price;
	}

	public void updatePrice(double price1, double price2) {
		/** 写锁 */
		readWritelock.writeLock().lock();
		this.price1 = price1;
		this.price2 = price2;
		System.out.println("current price1=" + price1 + ",price2=" + price2);
		readWritelock.writeLock().unlock();
	}
}
