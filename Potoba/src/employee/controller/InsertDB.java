package employee.controller;

import org.bson.Document;

import employee.controller.ConnectionDB;
import employee.model.*;

import com.mongodb.client.MongoCollection;

public class InsertDB{
	static MongoCollection<Document> coll;
	static public boolean insertEmp(Employee emp)
	{
		try
		{
			coll = (MongoCollection<Document>) ConnectionDB.db.getCollection("Registration");
			Document eid = new Document("id",emp.id.toUpperCase());
			Document doc = new Document("_id",eid)
			.append("fname",emp.fname.toUpperCase())
			.append("middle",emp.middle.toUpperCase())
			.append("surname",emp.surname.toUpperCase())
			.append("gender",emp.gender)
			.append("contactno",emp.mobileno.toUpperCase())
			.append("address", emp.address.toUpperCase())
			.append("salary",emp.salary)
			.append("shift",emp.shift)
			.append("date",emp.date)
			.append("status", emp.status)
			.append("salary_remaining",emp.salary);
			coll.insertOne(doc);
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(""+e);
			return false;
		}
		return true;
			
	}
	static public boolean insertReceipt(Payment p)
	{
		try
		{
			coll = (MongoCollection<Document>) ConnectionDB.db.getCollection("Payment");
			Document eid = new Document("rid",p.pid.toUpperCase());
			Document doc = new Document("_id",eid)
			.append("eid",p.eid.toUpperCase())
			.append("fname",p.name.toUpperCase())
			.append("amount", p.amountpaid)
			.append("date", p.date);
			coll.insertOne(doc);
			UpdateDB.updateSalary(p);
			
		}
		catch(Exception e)
		{
			System.out.println(""+e);
			return false;
		}
		return true;
			
	}
	
	

}
