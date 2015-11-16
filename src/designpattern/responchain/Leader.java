package designpattern.responchain;

public abstract class Leader {
	protected String name;
	protected Leader superiorLeader;

	public Leader(String name, Leader superiorLeader) {
		this.name = name;
		this.superiorLeader = superiorLeader;
	}

	protected abstract void handleRequest(LeaveRequest request);

}
