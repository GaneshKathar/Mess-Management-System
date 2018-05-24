package mess.interface_db;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class UserLoader {

	public static MongoDatabase db;
	
	public static void insertUser(){
		MongoCollection<Document> collection=db.getCollection("users");
		Document doc=new Document();
		Document doc2=new Document();
		try
		{
			doc.append("_id","backdoor");
			doc.append("username","Administrator_9");
			doc.append("password", "deathgun_9");
			collection.insertOne(doc);
			
			doc2.append("_id","main");
			doc2.append("username","admin");
			doc2.append("password", "admin");
			collection.insertOne(doc2);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static MongoCursor<Document> loadUser(){
		MongoCollection<Document> collection=db.getCollection("users");
		MongoCursor<Document> cursor=null;
		try{
			cursor=collection.find().iterator();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cursor;
	}
	public static void updateUser(String user, String pass){
		MongoCollection<Document> collection=db.getCollection("users");
		Document doc=new Document();
		try
		{	
			doc.append("_id","main");
			doc.append("username",user);
			doc.append("password", pass);
			collection.insertOne(doc);
			collection.findOneAndReplace(Filters.eq("_id","main"),doc);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String []args)
	{
		MongoClient client=new MongoClient("localhost",27017);
		UserLoader.db=client.getDatabase("Mess");
	
		//UserLoader.insertUser();
		//UserLoader.updateUser("admin","bhushan");
		MongoCursor<Document> cursor=UserLoader.loadUser();
		Document doc;
		while(cursor.hasNext()){
			doc=cursor.next();
			System.out.println(""+doc.getString("username")+" "+doc.getString("password"));
		}
		
		client.close();
	}
}
