package designpattern.state;

/**
 * 已付款
 * 
 * @author smwang
 * 
 */
public class Payed implements State {

	@Override
	public void handle() {
		System.out.println("订单处于已付款未发货状态");
	}

	@Override
	public String toString() {
		return "已付款未发货状态";
	}

}
