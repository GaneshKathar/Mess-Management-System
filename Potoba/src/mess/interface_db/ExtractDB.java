package mess.interface_db;

import java.util.Calendar;
import java.util.Date;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class ExtractDB {
	public static MongoDatabase db;
	
	public static MongoCursor<Document> getNameMembers(String nm)										//get Members from First-Name
	{
		MongoCollection<Document> col1=db.getCollection("Member");
		MongoCursor<Document> cursor=null;
		try{
			cursor=col1.find(Filters.eq("first-name",nm.toUpperCase())).iterator();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cursor;
	}
	
	public static MongoCursor<Document> getIDMembers(String id)										//get Member for Given MID
	{
		MongoCollection<Document> col1=db.getCollection("Member");
		MongoCursor<Document> cursor=null;
		try{
			cursor=col1.find(Filters.eq("_id.mid",id.toUpperCase())).iterator();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cursor;
	}
	
	public static MongoCursor<Document> getPaidMembers()											// to get all the paid members
	{
		MongoCollection<Document> col1=db.getCollection("Member");
		MongoCursor<Document> cursor=null;
		try{
			cursor=col1.find(Filters.lte("remaining-amt",0)).iterator();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cursor;
	}
	
	public static MongoCursor<Document> getUnpaidMembers()											//to get all unpaid members
	{
		MongoCollection<Document> col1=db.getCollection("Member");
		MongoCursor<Document> cursor=null;
		try{
			cursor=col1.find(Filters.gt("remaining-amt",0)).iterator();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cursor;
	}
	
	public static MongoCursor<Document> getPIDPayment(String id)									//to get Payment slip from pid
	{
		MongoCollection<Document> col1=db.getCollection("Payment");
		MongoCursor<Document> cursor=null;
		try{
			cursor=col1.find(Filters.eq("_id.pid",id.toUpperCase())).iterator();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cursor;
	}
	
	public static MongoCursor<Document> getMIDPayment(String id)									//to get Payment slip from MID
	{
		MongoCollection<Document> col1=db.getCollection("Payment");
		MongoCursor<Document> cursor=null;
		try{
			cursor=col1.find(Filters.eq("mid",id.toUpperCase())).iterator();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cursor;
	}
	
	public static MongoCursor<Document> getDatePayment(Date date)											//to get Payment Slip on a given date
	{
		MongoCollection<Document> col1=db.getCollection("Payment");
		MongoCursor<Document> cursor=null;
		try{
			cursor=col1.find(Filters.and(Filters.gte("date-of-payment",lowerD(date)),Filters.lt("date-of-payment", upperD(date)))).iterator();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cursor;
	}
	
	public static String getName(String id)															//to get First name when MID is passed
	{
		MongoCollection<Document> col1=db.getCollection("Member");
		String name=null;
		try{
			MongoCursor<Document>  cursor=col1.find(Filters.eq("_id.mid",id.toUpperCase())).iterator();
			Document doc =cursor.next();
			name=doc.getString("first-name");
			cursor.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return name;
	}
	
	 public static Date lowerD(Date date) {															//get The Lower Bound of given date
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        return cal.getTime();
	    }

	 public static Date upperD(Date date) {															//get the upper bound of given date
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        int d=cal.get(Calendar.DAY_OF_MONTH)+1;
	        cal.set(Calendar.DAY_OF_MONTH,d);
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        return cal.getTime();
	    }
}
