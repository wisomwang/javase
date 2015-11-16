package designpattern.builder;

/**
 * 汽车制造
 * 
 * @author smwang
 * 
 */
public interface CarBuilder {
	Engine buildEngine();

	Chair buildChair();
}
