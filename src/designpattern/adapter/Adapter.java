package designpattern.adapter;

/**
 * 类适配器，相当于usb和ps/2的转换器，不够灵活，不能再继承其他的了，本身就是被适配者
 * 
 * @author smwang
 * 
 */
public class Adapter extends Adaptee implements Target {

	@Override
	public void handleUsbRequest() {
		super.ps2Request();
	}

}
