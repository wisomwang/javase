package designpattern.command;

public class Client {

	public static void main(String[] args) {
		Command command = new ConcreteCommand(new Receiver());
		Invoker invoker = new Invoker(command);
		invoker.call();
		
//		jdk代码中Executor就类似于这里的Receiver，只不过是个接口，具体由字类执行,Command对应Runnable接口
//		void execute(Runnable command);
	}

}
