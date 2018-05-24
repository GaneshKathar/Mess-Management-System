package mess.object;

import java.util.Date;

public class Payment {

	public String pid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	public java.util.Date getDop() {
		return dop;
	}
	public void setDop(java.util.Date dop) {
		this.dop = dop;
	}
	public String mid;
	public int amt;
	public java.util.Date dop;
	public Payment()
	{
		pid=mid="";
		amt=0;
		dop=new Date();
	}
	
}
