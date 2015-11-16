package designpattern.flyweight;

import java.util.HashMap;
import java.util.Map;

public class ChessFlyWeightFactory {

	private static Map<String, ChessFlyWeight> chess = new HashMap<>();

	public static ChessFlyWeight getChessFlyWeight(String color) {
		if (chess.get(color) != null) {
			return chess.get(color);
		} else {
			ChessFlyWeight chessFlyWeight = new ConcreteChess(color);
			chess.put(color, chessFlyWeight);
			return chessFlyWeight;
		}
	}
}
