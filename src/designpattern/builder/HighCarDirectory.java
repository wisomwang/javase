package designpattern.builder;

/**
 * 高端汽车装配,client委托汽车装配，装配需要零件，零件由建造者提供
 * 
 * @author smwang
 * 
 */
public class HighCarDirectory implements CarDirectory {

	private CarBuilder builder;

	public HighCarDirectory(CarBuilder builder) {
		this.builder = builder;
	}

	@Override
	public Car directory() {
		HighQualityCar car = new HighQualityCar();
		car.setChair(builder.buildChair());
		car.setEngine(builder.buildEngine());
		return car;
	}

}
