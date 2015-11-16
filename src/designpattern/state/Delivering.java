package designpattern.state;

/**
 * 送货中
 * 
 * @author smwang
 * 
 */
public class Delivering implements State {

	@Override
	public void handle() {
		System.out.println("订单处于送货状态");
	}

	@Override
	public String toString() {
		return "送货状态";
	}

}
