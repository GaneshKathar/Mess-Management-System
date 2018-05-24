package employee.model;

import java.util.Date;

public class Employee {
	public String id;
	public String fname;
	public String middle;
	public String surname;
	public String mobileno;
	public String address;
	public int gender;
	public int salary;
	public int shift;
	public Date date;
	public boolean status;
	
	
	//constructor to take Employee data
	
	
	public Employee()
	{
		this.id="";
		this.fname="";
		this.middle="";
		this.surname="";
		this.shift=0;
		this.mobileno="";
		this.address="";
		this.gender=0;
		this.salary=0;
		this.date=new Date();
		this.status=true;
		
	}
	
	

}
