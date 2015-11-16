package designpattern.strategy;

/**
 * 老客户，量少
 * 
 * @author smwang
 * 
 */
public class OldCustomerFewStrategy implements Strategy {

	@Override
	public void price() {
		System.out.println("老客户量少的打9.5折");
	}

}
