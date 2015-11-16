package designpattern.composite;

public class Leaf implements Component {
	private String name;

	@Override
	public void operation() {
		System.out.println("component " + name + " is loaded");
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
