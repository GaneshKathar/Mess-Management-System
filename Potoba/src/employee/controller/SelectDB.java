package employee.controller;

import java.util.Date;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

public class SelectDB {
	static MongoCollection<Document> coll;
	static MongoCursor<Document> cursor ;
	static Document obj;
	
	static public int getECount()
	{	
		int eid=0;
		try
		{
			coll =ConnectionDB.db.getCollection("MAdmin");
			cursor=(MongoCursor<Document>) coll.find().iterator();
			obj = cursor.next();
			eid = (Integer)obj.get("ECount");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return eid;
	}
	static public int getPCount()
	{
		int pid=0;
		try
		{
			coll = ConnectionDB.db.getCollection("MAdmin");
			cursor=(MongoCursor<Document>)coll.find().iterator();
			obj = cursor.next();
			pid = (Integer)obj.get("PCount");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return pid;
	}
	static public MongoCursor<Document> getElist()
	{
		
		coll=ConnectionDB.db.getCollection("Registration");
		
		MongoCursor<Document> cursor =coll.find().iterator();
        return cursor;
    }
	static public Document getEmpData(String id)
	{
		coll = ConnectionDB.db.getCollection("Registration");
		Document doc=null;
		MongoCursor<Document> cursor = coll.find().iterator();
		while(cursor.hasNext())
		{
			doc= cursor.next();
			Document doc1 = (Document) doc.get("_id");
			if(id.equals(doc1.get("id")))
				break;
		}
		return doc;
	}
	static public Document getPayData(String  id)
	{
		coll = ConnectionDB.db.getCollection("Payment");
		Document doc=null;
		MongoCursor<Document> cursor = coll.find().iterator();
		while(cursor.hasNext())
		{
			doc= cursor.next();
			Document doc1 = (Document) doc.get("_id");
			if(id.equals(doc1.get("id")))
				break;
		}
		return doc;
	}
	static public MongoCursor<Document> getPayByName(String eid)
	{
		coll = ConnectionDB.db.getCollection("Payment");
		MongoCursor<Document> results = (MongoCursor<Document>) coll.find(new Document("eid", eid)).iterator();
		return results ;
	}
	static public MongoCursor<Document> getPayByRno(String rno)
	{
		coll = ConnectionDB.db.getCollection("Payment");
		MongoCursor<Document> results = (MongoCursor<Document>) coll.find(new Document("_id", new Document("rid",rno))).iterator();
		return results ;
	}
	static public MongoCursor<Document> getPayByDate(Date d1,Date d2)
	{
		coll = ConnectionDB.db.getCollection("Payment");
		MongoCursor<Document> results = (MongoCursor<Document>)coll.find(Filters.and(Filters.gte("date",d1),Filters.lte("date",d2))).iterator();
		return results;
	}
	static public MongoCursor<Document> getPayByMonth(Date d1,Date d2)
	{
		coll = ConnectionDB.db.getCollection("Payment");
		MongoCursor<Document> results = (MongoCursor<Document>)coll.find(Filters.and(Filters.gte("date",d1),Filters.lt("date",d2))).iterator();
		return results;
	}
	
	
}
