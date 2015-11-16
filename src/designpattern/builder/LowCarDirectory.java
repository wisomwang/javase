package designpattern.builder;

/**
 * 低端汽车装配
 * 
 * @author smwang
 * 
 */
public class LowCarDirectory implements CarDirectory {

	private CarBuilder builder;

	public LowCarDirectory(CarBuilder builder) {
		this.builder = builder;
	}

	@Override
	public Car directory() {
		LowQualityCar car = new LowQualityCar();
		car.setChair(builder.buildChair());
		car.setEngine(builder.buildEngine());
		return car;
	}

}
