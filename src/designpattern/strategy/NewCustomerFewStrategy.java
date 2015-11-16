package designpattern.strategy;

/**
 * 新客户，量少
 * 
 * @author smwang
 * 
 */
public class NewCustomerFewStrategy implements Strategy {

	@Override
	public void price() {
		System.out.println("新客户量少的不打折");
	}

}
