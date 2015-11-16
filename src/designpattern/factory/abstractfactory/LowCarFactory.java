package designpattern.factory.abstractfactory;

public class LowCarFactory implements CarFactory {

	@Override
	public Engine createEngine() {
		return new LowEngine();
	}

	@Override
	public Chair createChair() {
		return new LowChair();
	}

}
