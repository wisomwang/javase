package designpattern.command;

/**
 * 客户端使用该类调用命令，持有一个命令
 * 
 * @author smwang
 * 
 */
public class Invoker {
	private Command command;

	public Invoker(Command command) {
		this.command = command;
	}

	public void call() {
		command.execute();
	}

}
