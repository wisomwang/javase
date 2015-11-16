package designpattern.memo;

public class Client {
	public static void main(String[] args) {
		CareTaker taker = new CareTaker();
		
		Employee employee = new Employee(35, 10000, "Alexander");
		System.out.println("第一次创建对象：" + employee);

		EmpMemo memo = employee.toMemo();
		taker.setEmpMemo(memo);
		
		employee.setSalary(20000);
		System.out.println("工作表现好，加薪了,观察一个月，如果表现不好，恢复到原来薪资：" + employee);

		employee.recover(taker.getEmpMemo());
		System.out.println("经过一个月的观察，表现达不到预期：" + employee);
	}
}
