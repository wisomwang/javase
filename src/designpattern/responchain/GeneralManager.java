package designpattern.responchain;

public class GeneralManager extends Leader {

	public GeneralManager(String name, Leader superiorLeader) {
		super(name, superiorLeader);
	}

	@Override
	protected void handleRequest(LeaveRequest request) {
		if (request.getLeaveDays() >= 10) {
			System.out.println(name + " approved");
		}

		if (superiorLeader != null) {
			superiorLeader.handleRequest(request);
		}
	}

}
