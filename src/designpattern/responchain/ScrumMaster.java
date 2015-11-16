package designpattern.responchain;

public class ScrumMaster extends Leader {

	public ScrumMaster(String name, Leader superiorLeader) {
		super(name, superiorLeader);
	}

	@Override
	protected void handleRequest(LeaveRequest request) {
		if (request.getLeaveDays() < 3) {
			System.out.println(name + " approved");
		}

		if (superiorLeader != null) {
			superiorLeader.handleRequest(request);
		}
	}

}
