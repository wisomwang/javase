package designpattern.bridge;

public class Client {
	public static void main(String[] args) {
		Desktop desktop = new Desktop(new LenovoBrand());
		desktop.sale();

		Laptop laptop = new Laptop(new DellBrand());
		laptop.sale();

		/** 增加电脑类型和电脑品牌是独立的，无关的,客户可以随意组合 电脑类型和电脑品牌 */
	}
}
