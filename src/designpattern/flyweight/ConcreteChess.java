package designpattern.flyweight;

public class ConcreteChess implements ChessFlyWeight {

	private String color;// 内部属性持有共享状态

	public ConcreteChess(String color) {
		super();
		this.color = color;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public void displayPostion(Coordinate coordinate) {
		System.out.println("棋子在横坐标" + coordinate.getX() + ",纵坐标" + coordinate.getY());
	}

}
