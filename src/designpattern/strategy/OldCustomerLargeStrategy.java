package designpattern.strategy;

/**
 * 老客户，量多
 * 
 * @author smwang
 * 
 */
public class OldCustomerLargeStrategy implements Strategy {

	@Override
	public void price() {
		System.out.println("老客户量多的打8.5折");
	}

}
