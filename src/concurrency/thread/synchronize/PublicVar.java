package concurrency.thread.synchronize;

public class PublicVar {
	private String username = "A";

	private String password = "AA";

	synchronized public void setValue(String username, String password) {
		this.username = username;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.password = password;

		System.out.println("setValue method thread name=" + Thread.currentThread().getName() + " username=" + username
				+ " password=" + password);
	}

	public  void getValue() {
		System.out.println("getValue method thread name=" + Thread.currentThread().getName() + " username=" + username
				+ " password=" + password);
	}
	
	public static void main(String[] args) {
		PublicVar var = new PublicVar();
		/** getValue()不加synchronized的话，则可以随时调用，如果在调用setValue()方法的过程中调用getValue()方法的话，则会出现脏读 
		 * 加了synchronized的话，getValue()方法不能随时调用，要么在setValue()方法之前调用，要么之后调用
		 * */
		ThreadA thread = new ThreadA(var);
		thread.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		var.getValue();
	}
}

class ThreadA extends Thread {
	PublicVar publicVar;

	public ThreadA(PublicVar var) {
		this.publicVar = var;
	}

	@Override
	public void run() {
		publicVar.setValue("B", "BB");
	}
}