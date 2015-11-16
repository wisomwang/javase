package designpattern.template;

public class DrawMoney extends BankBusinessProcess {

	@Override
	public void transact() {
		System.out.println("处理现金业务，取钱");
	}

}
