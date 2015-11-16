package concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TestInvokeAny {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		UserValidator ldapUser = new UserValidator("ldap");
		UserValidator dbUser = new UserValidator("db");

		String user = "test";
		String password = "test";

		TaskValidator ldap = new TaskValidator(ldapUser, user, password);
		TaskValidator db = new TaskValidator(dbUser, user, password);

		List<TaskValidator> tasks = new ArrayList<>();
		tasks.add(ldap);
		tasks.add(db);

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

		/** 抛出异常不算 */
		String name = executor.invokeAny(tasks);
		System.out.println("name=" + name);

		executor.shutdown();

		System.out.println("Main: End of the execution");
	}

}

class TaskValidator implements Callable<String> {
	private UserValidator validator;
	private String user;
	private String password;

	public TaskValidator(UserValidator validator, String user, String password) {
		this.validator = validator;
		this.user = user;
		this.password = password;
	}

	@Override
	public String call() throws Exception {
		if (!validator.validate(user, password)) {
			System.out.println("the user info is not found");

			/** 要抛出异常 */
			throw new Exception("Erorr validating user");
		}
		System.out.println("the user info has been found");
		return validator.getName();
	}
}

class UserValidator {
	private String name;

	public UserValidator(String name) {
		this.name = name;
	}

	public boolean validate(String user, String password) {
		return new Random().nextBoolean();
	}

	public String getName() {
		return name;
	}
}
