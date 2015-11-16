package designpattern.state;

public class Order {
	private State state;

	public void setState(State state) {
		System.out.println("修改状态:从" + this.state + " to " + state);
		this.state = state;
		this.state.handle();
	}

}
