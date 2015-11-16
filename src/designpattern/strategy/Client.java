package designpattern.strategy;

public class Client {
	public static void main(String[] args) {
		Context context = new Context(new NewCustomerFewStrategy());
		context.price();

		context.setStrategy(new OldCustomerLargeStrategy());
		context.price();
		
	}
}
