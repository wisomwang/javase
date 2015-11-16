package designpattern.flyweight;

public class Client {

	public static void main(String[] args) {
		ChessFlyWeight chess1 = ChessFlyWeightFactory.getChessFlyWeight("黑色");
		ChessFlyWeight chess2 = ChessFlyWeightFactory.getChessFlyWeight("黑色");

		System.out.println(chess1);
		System.out.println(chess2);
		
		chess1.displayPostion(new Coordinate(10, 20));
		chess2.displayPostion(new Coordinate(15, 15));
	}

}
