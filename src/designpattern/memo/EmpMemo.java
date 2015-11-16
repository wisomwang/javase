package designpattern.memo;

public class EmpMemo {

	private int age;
	private int salary;
	private String name;

	public EmpMemo(Employee employee) {
		this.age = employee.getAge();
		this.salary = employee.getSalary();
		this.name = employee.getName();
	}

	public int getAge() {
		return age;
	}

	public int getSalary() {
		return salary;
	}

	public String getName() {
		return name;
	}

}
