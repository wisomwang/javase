package designpattern.factory.abstractfactory;

public class HighCarFactory implements CarFactory {

	@Override
	public Engine createEngine() {
		return new HighEngine();
	}

	@Override
	public Chair createChair() {
		return new HighChair();
	}

}
