package designpattern.proxy.staticproxy;

public class Agentor implements Star {

	private RealStar realStar;

	public Agentor(RealStar realStar) {
		this.realStar = realStar;
	}

	@Override
	public void singContract() {
		System.out.println("由经纪人负责签合同");
	}

	@Override
	public void sing() {
		realStar.sing();
	}

}
