package designpattern.flyweight;

/**
 * 围棋，只有黑色两种棋子，除了放在哪里的位置不一样以外，其他都一样，颜色形状大小属于内部状态，可以共享，位置属于外部状态，不能共享
 * 
 * @author smwang
 * 
 */
public interface ChessFlyWeight {

	String getColor();

	void displayPostion(Coordinate coordinate);
}
