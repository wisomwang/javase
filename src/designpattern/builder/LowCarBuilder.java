package designpattern.builder;

/**
 * 低端汽车建者商
 * 
 * @author smwang
 * 
 */
public class LowCarBuilder implements CarBuilder {

	@Override
	public Engine buildEngine() {
		return new LowEngine();
	}

	@Override
	public Chair buildChair() {
		return new LowChair();
	}

}
