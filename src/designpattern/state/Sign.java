package designpattern.state;

/**
 * 签收状态
 * 
 * @author smwang
 * 
 */
public class Sign implements State {

	@Override
	public void handle() {
		System.out.println("订单处于已签收状态");
	}

	@Override
	public String toString() {
		return "已签收状态";
	}

}
