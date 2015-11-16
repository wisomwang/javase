package designpattern.builder;

/**
 * 高端汽车建者商
 * 
 * @author smwang
 * 
 */
public class HighCarBuilder implements CarBuilder {

	@Override
	public Engine buildEngine() {
		return new HighEngine();
	}

	@Override
	public Chair buildChair() {
		return new HighChair();
	}

}
