package employee.model;

import java.util.Date;

public class Payment {
	public String eid;
	public String pid;
	public String name;
	public Date date;
	public int amountpaid;
	public Payment()
	{
		eid="";
		pid="";
		name="";
		date=new Date();
		amountpaid=0;
	}

}
