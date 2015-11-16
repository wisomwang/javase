package designpattern.command;

/**
 * 具体的命令,拥有一个接收者
 * 
 * @author smwang
 * 
 */
public class ConcreteCommand implements Command {

	private Receiver receiver;

	public ConcreteCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		System.out.println("我是具体的命令，调用的命令行者");
		receiver.action();
	}

}
