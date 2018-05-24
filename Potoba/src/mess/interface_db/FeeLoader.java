package mess.interface_db;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class FeeLoader {

	public static MongoDatabase db;
	
	public static boolean insertFee()
	{
		MongoCollection<Document> collection=db.getCollection("misc");
		Document doc=new Document();
		Document doc2=new Document();
		Document doc3=new Document();
		Document doc4=new Document();
		try
		{
			doc.append("_id","fee1");
			doc.append("amount", 0);
			collection.insertOne(doc);

			doc2.append("_id","fee2");
			doc2.append("amount", 0);
			collection.insertOne(doc2);
			
			doc3.append("_id", "fee1d");
			doc3.append("amount",0);
			collection.insertOne(doc3);
			
			doc4.append("_id", "fee1t");
			doc4.append("amount",0);
			collection.insertOne(doc4);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static int load1Fee()
	{
		int fee=0;
		MongoCollection<Document> collection=db.getCollection("misc");
		Document doc=new Document();
		try{
			MongoCursor<Document> cursor=collection.find(Filters.eq("_id","fee1")).iterator();
			doc=cursor.next();
			fee=doc.getInteger("amount");
			cursor.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return fee;
	}
	
	public static int load2Fee()
	{
		int fee=0;
		MongoCollection<Document> collection=db.getCollection("misc");
		Document doc=new Document();
		try{
			MongoCursor<Document> cursor=collection.find(Filters.eq("_id","fee2")).iterator();
			doc=cursor.next();
			fee=doc.getInteger("amount");
			cursor.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return fee;
	}
	public static int load1Day()
	{
		int fee=0;
		MongoCollection<Document> collection=db.getCollection("misc");
		Document doc=new Document();
		try{
			MongoCursor<Document> cursor=collection.find(Filters.eq("_id","fee1d")).iterator();
			doc=cursor.next();
			fee=doc.getInteger("amount");
			cursor.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return fee;
	}
	
	public static int load1Time()
	{
		int fee=0;
		MongoCollection<Document> collection=db.getCollection("misc");
		Document doc=new Document();
		try{
			MongoCursor<Document> cursor=collection.find(Filters.eq("_id","fee1t")).iterator();
			doc=cursor.next();
			fee=doc.getInteger("amount");
			cursor.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return fee;
	}
	public static void save1Fee(int fee)
	{
		MongoCollection<Document> collection=db.getCollection("misc");
		try{
			collection.findOneAndReplace(Filters.eq("_id","fee1"),new Document("amount", fee));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void save2Fee(int fee)
	{
		MongoCollection<Document> collection=db.getCollection("misc");
		try{
			collection.findOneAndReplace(Filters.eq("_id","fee2"),new Document("amount", fee));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void save1Day(int fee)
	{
		MongoCollection<Document> collection=db.getCollection("misc");
		try{
			collection.findOneAndReplace(Filters.eq("_id","fee1d"),new Document("amount", fee));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void save1Time(int fee)
	{
		MongoCollection<Document> collection=db.getCollection("misc");
		try{
			collection.findOneAndReplace(Filters.eq("_id","fee1t"),new Document("amount", fee));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String []args)
	{
		MongoClient client=new MongoClient("localhost",27017);
		FeeLoader.db=client.getDatabase("Mess");
	
		FeeLoader.insertFee();
		FeeLoader.save1Fee(1200);
		FeeLoader.save2Fee(2400);
		FeeLoader.save1Day(80);
		FeeLoader.save1Time(50);
		client.close();
	}
	
}
