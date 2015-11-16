package concurrency.syncassit.cyclicbarrier;

public class Result {
	private int data[];

	public Result(int size) {
		this.data = new int[size];
	}

	public int[] getData() {
		return data;
	}

	public void setData(int i, int counter) {
		data[i] = counter;
	}

}
