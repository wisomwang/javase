package designpattern.template;

public class Client {

	public static void main(String[] args) {
		BankBusinessProcess drawMoney = new DrawMoney();
		drawMoney.process();
	}

}
