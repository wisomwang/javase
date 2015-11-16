package designpattern.composite;

public class Client {
	public static void main(String[] args) {
		Component leaf1 = new Leaf();
		leaf1.setName("leaf1");
		leaf1.operation();
		
		Component leaf2 = new Leaf();
		leaf2.setName("leaf2");
		leaf2.operation();
		
		Composite composite1 = new Composite();
		composite1.setName("composite1");
		composite1.addComponent(leaf1);
		composite1.addComponent(leaf2);
		
		Composite compositeFather = new Composite();
		compositeFather.setName("composite father");
		compositeFather.addComponent(composite1);
		compositeFather.operation();
		
	}
}
