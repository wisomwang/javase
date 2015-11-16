package designpattern.responchain;

public class Client {
	public static void main(String[] args) {
		Leader generalManager = new GeneralManager("changming", null);
		Leader departmentManager = new DepartmentManager("songwei", generalManager);
		Leader scrumMaster = new ScrumMaster("cheng", departmentManager);
		
		LeaveRequest request1 = new LeaveRequest("Alexander", 2);
		scrumMaster.handleRequest(request1);
		
		LeaveRequest request2 = new LeaveRequest("Alexander", 7);
		scrumMaster.handleRequest(request2);
		
		LeaveRequest request3 = new LeaveRequest("Alexander", 15);
		scrumMaster.handleRequest(request3);
	}
}
