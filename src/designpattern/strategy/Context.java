package designpattern.strategy;

public class Context {
	private Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	void price() {
		System.out.println("开始报价");
		strategy.price();
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
