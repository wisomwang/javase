package designpattern.memo;

public class Employee {
	private int age;
	private int salary;
	private String name;

	public Employee(int age, int salary, String name) {
		this.age = age;
		this.salary = salary;
		this.name = name;
	}

	EmpMemo toMemo() {
		return new EmpMemo(this);
	}

	void recover(EmpMemo memo) {
		this.age = memo.getAge();
		this.salary = memo.getSalary();
		this.name = memo.getName();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "name is " + name + ", age is " + age + ", salary is " + salary;
	}
}
