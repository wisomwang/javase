package designpattern.memo;

/**
 * 管理备忘录
 * 
 * @author smwang
 * 
 */
public class CareTaker {
	/** 如果要回退多个点，可以换成容器类来保存 */
	private EmpMemo empMemo;

	public EmpMemo getEmpMemo() {
		return empMemo;
	}

	public void setEmpMemo(EmpMemo empMemo) {
		this.empMemo = empMemo;
	}

}
