package designpattern.proxy.staticproxy;

public class RealStar implements Star {

	@Override
	public void singContract() {

	}

	@Override
	public void sing() {
		System.out.println("由哥星唱歌");
	}

}
