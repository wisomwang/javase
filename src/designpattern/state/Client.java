package designpattern.state;

public class Client {

	public static void main(String[] args) {
		Order order = new Order();

		order.setState(new Ordered());
		order.setState(new Payed());
		
		order.setState(new Delivering());
		order.setState(new Deliveryed());
		
		order.setState(new Sign());
	}

}
