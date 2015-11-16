package designpattern.composite;

import java.util.LinkedList;
import java.util.List;

public class Composite implements Component {

	private List<Component> children = new LinkedList<>();
	private String name;

	@Override
	public void operation() {
		for (Component component : children) {
			component.operation();
		}
		System.out.println("component " + getName() + " is loaded");
	}

	public void addComponent(Component component) {
		children.add(component);
	}

	public void removeComponent(Component component) {
		children.add(component);
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
