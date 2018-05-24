package employee.controller;

import java.util.Calendar;






import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import employee.model.*;

public class UpdateDB {
	static MongoCollection<Document>coll;
	public static void updateECount()
	{
		
		coll=ConnectionDB.db.getCollection("MAdmin");
		coll.updateOne(new Document("id", 1),
                new Document("$inc", new Document("ECount", 1))); 
	}
	public static void updatePCount()
	{
		coll=ConnectionDB.db.getCollection("MAdmin");
		coll.updateOne(new Document("id", 1),
                new Document("$inc", new Document("PCount", 1))); 
		
	}
	public static void updateEmpData(Employee emp)
	{
		int salary,salary_r;
		System.out.println("Inside Database "+emp.fname+"  "+emp.salary);
		coll =ConnectionDB.db.getCollection("Registration");
		//coll.updateOne(new Document("_id", new Document("id",emp.id)),
          //      new Document("$set", new Document("fname",emp.fname.toUpperCase())
		MongoCursor<Document>  cursor=coll.find(Filters.eq("_id.id",emp.id.toUpperCase())).iterator();    			
		Document doc =cursor.next();
		salary=doc.getInteger("salary");
		salary_r = doc.getInteger("salary_remaining");
   		int s = salary_r+(emp.salary-salary) ;
		cursor.close();
		
		
		
		
		System.out.println("Inside Database "+emp.id+"  "+emp.salary);
		Document doc1=new Document();
		Document id=new Document();
		try{
			
			id.append("id",emp.id);
			
			doc1.append("_id",id);
			doc1.append("fname",emp.fname.toUpperCase());
			
			doc1.append("middle",emp.middle.toUpperCase());
			doc1.append("surname",emp.surname.toUpperCase());
			doc1.append("gender",emp.gender);
			doc1.append("contactno",emp.mobileno.toUpperCase());
			doc1.append("address", emp.address.toUpperCase());
			doc1.append("salary",emp.salary);
			doc1.append("shift",emp.shift);
			doc1.append("status",emp.status);
			doc1.append("date",emp.date);
			doc1.append("salary_remaining", s);
			coll=ConnectionDB.db.getCollection("Registration");

			System.out.println("Inside Database JSON "+coll+"  "+doc1);
			coll.findOneAndReplace(Filters.eq("_id.id",emp.id),doc1);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		
	}
	public static void updatePayData(Payment p)
	{
		coll =ConnectionDB.db.getCollection("Payment");
		coll.updateOne(new Document("_id", new Document("rid",p.pid)),
                new Document("$set", new Document("fname",p.name.toUpperCase())
            		.append("eid",p.eid.toUpperCase())
            		.append("amount",p.amountpaid )
                	.append("date",p.date)));
	}
	public static void updateSalary(Payment p)
	{
		coll =ConnectionDB.db.getCollection("Registration");
		coll.updateOne(new Document("_id", new Document("id",p.eid)),
                new Document("$inc", new Document("salary_remaining",-p.amountpaid)));
	}
	public static void updateEmpStatus(String id,boolean b)
	{
		coll =ConnectionDB.db.getCollection("Registration");
		coll.updateOne(new Document("_id", new Document("id",id)),
                new Document("$set", new Document("status",b)));
	}
	public static void updatePayment()
	{
		Calendar c =Calendar.getInstance();
		int m=c.get(Calendar.MONTH);
		int y=c.get(Calendar.YEAR);
		int d=c.get(Calendar.DAY_OF_MONTH);
		
	}
}
