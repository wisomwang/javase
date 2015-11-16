package designpattern.decorate;

/**
 * 具体构件者
 * 
 * @author smwang
 * 
 */
public class CommonCar implements Car {

	@Override
	public void run() {
		System.out.println("一般的车只能在地上开");
	}

}
