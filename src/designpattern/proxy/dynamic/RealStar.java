package designpattern.proxy.dynamic;

public class RealStar implements Star {

	@Override
	public void signContract() {

	}

	@Override
	public void sing() {
		System.out.println("由哥星唱歌");
	}

}
