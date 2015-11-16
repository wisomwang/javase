package designpattern.command;

/**
 * 命令的接收者，执行命令
 * @author smwang
 *
 */
public class Receiver {
	public void action() {
		System.out.println("我是命令的执行者");
	}
}
