package mess.interface_db;	

import mess.object.Payment;
import mess.object.Professional;
import mess.object.Student;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class InsertDB {

	public static MongoDatabase db;
	
	public static boolean iMember(Student obj)
	{
		Document doc1=new Document();
		Document doc2=new Document();
		Document id=new Document();
		try{
			id.append("mid",obj.mid.toUpperCase());
			
			doc1.append("_id",id);
			doc1.append("first-name",obj.fname.toUpperCase());
			doc1.append("parent-name",obj.pname.toUpperCase());
			doc1.append("last-name",obj.lname.toUpperCase());
			doc1.append("Date-of-joining",obj.doj);
			doc1.append("contact",obj.contact.toUpperCase());
			doc1.append("gender",obj.gender);
			doc1.append("absentee",obj.absentee);
			doc1.append("remaining-amt",obj.ramt);
			doc1.append("status",obj.status);
			doc1.append("meals", obj.meals);
			
			doc2.append("attribute1",obj.college);													//college
			doc2.append("attribute2",obj.year);														//year
			
			doc1.append("misc",doc2);
			
			MongoCollection<Document> collection=db.getCollection("Member");
			collection.insertOne(doc1);
			
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	
	public static boolean iMember(Professional obj)
	{
		Document doc1=new Document();
		Document doc2=new Document();
		Document id=new Document();
		try{
			id.append("mid",obj.mid);
			
			doc1.append("_id",id);
			doc1.append("first-name",obj.fname.toUpperCase());
			doc1.append("parent-name",obj.pname.toUpperCase());
			doc1.append("last-name",obj.lname.toUpperCase());
			doc1.append("Date-of-joining",obj.doj);
			doc1.append("contact",obj.contact.toUpperCase());
			doc1.append("gender",obj.gender);
			doc1.append("absentee",obj.absentee);
			doc1.append("remaining-amt",obj.ramt);
			doc1.append("status",obj.status);
			doc1.append("meals", obj.meals);
			
			doc2.append("attribute1",obj.occupation.toUpperCase());										//Occupation
			doc2.append("attribute2",obj.address.toUpperCase());										//Address
			
			doc1.append("misc", doc2);
			MongoCollection<Document> collection=db.getCollection("Member");
			collection.insertOne(doc1);
			
			
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	public static boolean iPayment(Payment obj)
	{
		Document doc=new Document();
		Document id=new Document();
		try{
			id.append("pid",obj.pid.toUpperCase());
			doc.append("_id",id);
			doc.append("mid", obj.mid.toUpperCase());
			doc.append("amount",obj.amt);
			doc.append("date-of-payment",obj.dop);
			MongoCollection<Document> collection=db.getCollection("Payment");
			collection.insertOne(doc);
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	@SuppressWarnings("resource")
	public static void main(String []args)
	{

		MongoClient client=new MongoClient("localhost",27017);
		Student obj=new Student();
		//obj.mid=102;
		obj.fname="Kalyani";
		obj.pname="Avinash";
		obj.lname="Deshmukh";
		obj.contact="7028772225";
		obj.gender=2;
		obj.ramt=2400;
		obj.status=1;
		obj.meals=1;
		obj.college=1;
		obj.year=3;
		
		 InsertDB.db=client.getDatabase("Mess");
		 InsertDB.iMember(obj);
	}
}
