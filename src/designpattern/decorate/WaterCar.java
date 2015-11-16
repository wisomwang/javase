package designpattern.decorate;

/**
 * 具体装饰者
 * 
 * @author smwang
 * 
 */
public class WaterCar extends SuperCar {

	public WaterCar(Car car) {
		super(car);
	}

	@Override
	public void run() {
		super.run();
		swim();
	}

	public void swim() {
		System.out.println("这个车还能在水里开");
	}

}
