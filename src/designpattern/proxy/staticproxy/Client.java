package designpattern.proxy.staticproxy;


/**
 * 客户端类 相当于例子中的笔记本，只有USB接口,但想要调用PS/2接口
 * 
 * @author smwang
 * 
 */
public class Client {
	public static void main(String[] args) {
		Star star = new Agentor(new RealStar());
		star.singContract();
		star.sing();
	}
}
