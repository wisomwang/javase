package designpattern.bridge;

public class Desktop implements Computer {

	private Brand brand;

	public Desktop(Brand brand) {
		this.brand = brand;
	}

	@Override
	public void sale() {
		System.out.println("销售" + brand.name() + "品牌的台式机电脑");
	}

}
