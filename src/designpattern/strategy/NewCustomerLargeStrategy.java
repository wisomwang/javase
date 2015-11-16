package designpattern.strategy;

/**
 * 新客户，量多
 * 
 * @author smwang
 * 
 */
public class NewCustomerLargeStrategy implements Strategy {

	@Override
	public void price() {
		System.out.println("新客户量多的打9折");
	}

}
