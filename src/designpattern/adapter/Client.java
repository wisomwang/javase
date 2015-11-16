package designpattern.adapter;


/**
 * 客户端类 相当于例子中的笔记本，只有USB接口,但想要调用PS/2接口
 * 
 * @author smwang
 * 
 */
public class Client {
	public void test(Target t) {
		t.handleUsbRequest();
	}

	public static void main(String[] args) {
		Client c = new Client();
		Target t1 = new Adapter();
		c.test(t1);

		Adaptee adaptee = new Adaptee();
		Target t2 = new ObjectAdapter(adaptee);
		c.test(t2);
	}
}
