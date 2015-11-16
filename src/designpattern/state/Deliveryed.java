package designpattern.state;

/**
 * 货已到达未签收状态
 * 
 * @author smwang
 * 
 */
public class Deliveryed implements State {

	@Override
	public void handle() {
		System.out.println("订单处于已送达未签收状态");
	}

	@Override
	public String toString() {
		return "已送达未签收状态";
	}

}
