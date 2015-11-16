package designpattern.decorate;

public class Client {
	public static void main(String[] args) {
		Car car = new CommonCar();
		car.run();

		SuperCar superCar = new SuperCar(car);
		superCar.run();

		superCar = new SuperCar(new FlyCar(car));
		superCar.run();

		superCar = new SuperCar(new FlyCar(new WaterCar(car)));
		superCar.run();

		/** 这就是装饰模式， FileInputStream是真实的对象，其他都是装饰对象 */
		// BufferedReader reader = new BufferedReader(new InputStreamReader(new
		// FileInputStream("")));
	}
}
