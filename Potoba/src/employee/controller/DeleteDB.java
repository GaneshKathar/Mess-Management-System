package employee.controller;


import org.bson.Document;

import com.mongodb.client.MongoCollection;

import employee.model.Payment;

public class DeleteDB {
	static MongoCollection<Document> coll;
	public static boolean DeletePay(String id,int amount)
	{
		try
		{
			coll=ConnectionDB.db.getCollection("Payment");
			coll.deleteOne(new Document("_id",new Document("rid",id)));
			Payment p = new Payment();
			p.amountpaid=-amount;
			p.eid=id;
			UpdateDB.updateSalary(p);
			
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	public static boolean DeleteEmp(String id)
	{
		try
		{
			coll=ConnectionDB.db.getCollection("Registration");
			coll.deleteOne(new Document("_id",new Document("id",id)));
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

}
