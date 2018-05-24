package mess.interface_db;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class DeleteDB {

	public  static MongoDatabase db;
	
	public static void dPayment(String id)
	{
		MongoCollection<Document> col1=db.getCollection("Payment");
		try{
			col1.deleteOne(Filters.eq("_id.pid",id.toUpperCase()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void dMemberSlips(String id)
	{
		MongoCollection<Document> col1=db.getCollection("Payment");
		try{
			col1.deleteMany(Filters.eq("mid",id.toUpperCase()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void dMember(String id)
	{
		MongoCollection<Document> col1=db.getCollection("Member");
		try{
			col1.deleteOne(Filters.eq("_id.mid",id.toUpperCase()));	
		}
		catch(Exception e)
		{
			
		}
	}
}
