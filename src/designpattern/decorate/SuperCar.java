package designpattern.decorate;

/**
 * 装饰者
 * @author smwang
 *
 */
public class SuperCar implements Car {

	private Car car;

	public SuperCar(Car car) {
		super();
		this.car = car;
	}

	@Override
	public void run() {
		car.run();
	}

}
