package designpattern.responchain;

public class LeaveRequest {
	private String employeeName;
	private int leaveDays;

	public LeaveRequest(String employeeName, int leaveDays) {
		super();
		this.employeeName = employeeName;
		this.leaveDays = leaveDays;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public int getLeaveDays() {
		return leaveDays;
	}

}
