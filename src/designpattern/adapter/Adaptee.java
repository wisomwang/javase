package designpattern.adapter;

/**
 * 被适配的对象
 * 相当于盒子中的PS/2 键盘
 * @author smwang
 * 
 */
public class Adaptee {
	public void ps2Request()
	{
		System.out.println("可以完成客户请求的重要的功能");
	}
}
