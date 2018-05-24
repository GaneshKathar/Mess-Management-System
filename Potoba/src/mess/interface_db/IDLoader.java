package mess.interface_db;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class IDLoader {

	public static MongoDatabase db;
	
	public static boolean insertID()
	{
		MongoCollection<Document> collection=db.getCollection("misc");
		Document doc=new Document();
		try{
			doc.append("_id","counter");
			doc.append("mid",101);
			doc.append("pid",101);
			collection.insertOne(doc);
		}
		catch(Exception e ){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static void resetID()
	{
		MongoCollection<Document> collection=db.getCollection("misc");
		Document doc=new Document();
		try{
			doc.append("_id","counter");
			doc.append("mid",101);
			doc.append("pid",101);
			collection.findOneAndReplace(Filters.eq("_id","counter"),doc);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static int loadPID()
	{
		int pid=0;
		try{
			MongoCollection<Document> collection=db.getCollection("misc");
			Document doc=new Document();
			MongoCursor<Document> cursor=collection.find(Filters.eq("_id", "counter")).iterator();
			doc=cursor.next();
			pid=doc.getInteger("pid");
			cursor.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return pid;
	}
	
	public static int loadMID()
	{
		int mid=0;
		try{	
			MongoCollection<Document> collection=db.getCollection("misc");
			Document doc=new Document();
			MongoCursor<Document> cursor=collection.find(Filters.eq("_id", "counter")).iterator();
			doc=cursor.next();
			mid=doc.getInteger("mid");
			cursor.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return mid;
	}
	
	public static void incMID()
	{
		MongoCollection<Document> collection=db.getCollection("misc");
		try{
		collection.findOneAndUpdate(Filters.eq("_id", "counter"), new Document("$inc",new Document("mid",1)));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void incPID()
	{
		MongoCollection<Document> collection=db.getCollection("misc");
		try{
			collection.findOneAndUpdate(Filters.eq("_id", "counter"), new Document("$inc",new Document("pid",1)));
		}
		catch(Exception e){
			e.printStackTrace();
		}			
	}
	
	
	public static void main(String []args)
	{
		MongoClient client=new MongoClient("localhost",27017);
		IDLoader.db=client.getDatabase("Mess");
		//IDLoader.insertID();
		IDLoader.resetID();
		//IDLoader.saveMID();
		//IDLoader.savePID();
		System.out.println("PID: "+IDLoader.loadPID()+"\nMID: "+IDLoader.loadMID());		
		client.close();
	}
}
