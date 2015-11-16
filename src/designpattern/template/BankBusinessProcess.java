package designpattern.template;

public abstract class BankBusinessProcess {
	public void process() {
		takeNumber();
		transact();
		evaluate();
	}

	private void takeNumber() {
		System.out.println("取号");
	}
	
	private void evaluate() {
		System.out.println("请评价服务态度");
	}

	public abstract void transact();

}
