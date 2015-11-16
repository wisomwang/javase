package designpattern.responchain;

public class DepartmentManager extends Leader {

	public DepartmentManager(String name, Leader superiorLeader) {
		super(name, superiorLeader);
	}

	@Override
	protected void handleRequest(LeaveRequest request) {
		if (request.getLeaveDays() >= 3 && request.getLeaveDays() < 10) {
			System.out.println(name + " approved");
		}

		if (superiorLeader != null) {
			superiorLeader.handleRequest(request);
		}
	}

}
