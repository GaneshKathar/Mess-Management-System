package mess.object;

import java.util.Calendar;

public class Professional 
{
	public String mid;
	public String fname,pname,lname;
	public java.util.Date doj;	
	public String contact;
	public int gender;
	public int absentee;
	public int ramt;
	public int status;
	public int meals;
	public String occupation;
	public String address;
	
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public java.util.Date getDoj() {
		return doj;
	}

	public void setDoj(java.util.Date doj) {
		this.doj = doj;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAbsentee() {
		return absentee;
	}

	public void setAbsentee(int absentee) {
		this.absentee = absentee;
	}

	public int getRamt() {
		return ramt;
	}

	public void setRamt(int ramt) {
		this.ramt = ramt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getMeals() {
		return meals;
	}

	public void setMeals(int meals) {
		this.meals = meals;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Professional()
	{
		mid="";
		fname=pname=lname=contact="";
		doj=new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		gender=absentee=ramt=meals=0;
		address=occupation="";
		status=1;
	}
}
