package designpattern.adapter;

/**
 * 对象适配器，相当于usb和ps/2的转换器，不够灵活，不能再继承其他的了，本身就是被适配者
 * 
 * @author smwang
 * 
 */
public class ObjectAdapter implements Target {

	private Adaptee adaptee;

	public ObjectAdapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void handleUsbRequest() {
		adaptee.ps2Request();
	}

}
