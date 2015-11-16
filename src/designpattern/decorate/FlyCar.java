package designpattern.decorate;

/**
 * 具体装饰者
 * 
 * @author smwang
 * 
 */
public class FlyCar extends SuperCar {

	public FlyCar(Car car) {
		super(car);
	}

	@Override
	public void run() {
		super.run();
		fly();
	}

	public void fly() {
		System.out.println("这个车还会飞");
	}

}
