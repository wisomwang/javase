package designpattern.bridge;

public class Laptop implements Computer {

	private Brand brand;

	public Laptop(Brand brand) {
		this.brand = brand;
	}

	@Override
	public void sale() {
		System.out.println("销售" + brand.name() + "品牌的笔记本电脑");
	}

}
