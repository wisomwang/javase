package designpattern.state;

/**
 * 已下单
 * 
 * @author smwang
 * 
 */
public class Ordered implements State {

	@Override
	public void handle() {
		System.out.println("订单处于下单状态");
	}

	@Override
	public String toString() {
		return "已下单";
	}

}
