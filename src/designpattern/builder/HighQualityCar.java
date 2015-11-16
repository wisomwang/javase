package designpattern.builder;

public class HighQualityCar implements Car {

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
		System.out.println("高端汽车启动就是快");
	}

	@Override
	public void comfort() {
		System.out.println("高端汽车坐着就是舒服");
	}

}
