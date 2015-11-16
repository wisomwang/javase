package designpattern.builder;

public class LowQualityCar implements Car {
	private Engine engine;

	private Chair chair;

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Chair getChair() {
		return chair;
	}

	public void setChair(Chair chair) {
		this.chair = chair;
	}

	@Override
	public void start() {
		System.out.println("低端汽车启动就是慢，赶紧赚钱换车吧");
	}

	@Override
	public void comfort() {
		System.out.println("低端汽车坐着就是难受，赶紧赚钱换车吧");
	}
}
