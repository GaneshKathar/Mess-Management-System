package mess.interface_db;

import mess.object.Payment;
import mess.object.Professional;
import mess.object.Student;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class UpdateDB {
	public static MongoDatabase db;
	
	public static void uPay(Payment obj)
	{
		MongoCollection<Document> collection=db.getCollection("Member");
		try{
			
			collection.findOneAndUpdate(Filters.eq("_id.mid", obj.mid.toUpperCase()), new Document("$inc",new Document("remaining-amt",-1*obj.amt)));
		}
		catch(Exception e){
			e.printStackTrace();
		}			
	}
	
	public static void uUnPay(Payment obj)
	{
		MongoCollection<Document> collection=db.getCollection("Member");
		try{
			
			collection.findOneAndUpdate(Filters.eq("_id.mid", obj.mid.toUpperCase()), new Document("$inc",new Document("remaining-amt",obj.amt)));
		}
		catch(Exception e){
			e.printStackTrace();
		}			
	}
	
	public static void monthlyFee()									//to charge the monthly fee to all the Active Member
	{
		MongoCollection<Document> collection=db.getCollection("Member");
		try{
			
		}
		catch(Exception e){
			e.printStackTrace();
		}			
	}
	
	public static boolean uMember(Student obj)
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
			
			doc2.append("attribute1",obj.college);										//College
			doc2.append("attribute2",obj.year);											//Year
			
			doc1.append("misc", doc2);
			MongoCollection<Document> collection=db.getCollection("Member");
			collection.findOneAndReplace(Filters.eq("_id.mid",obj.mid.toUpperCase()),doc1);
		}
		catch(Exception e){

			e.printStackTrace();
			return false;
		}	
		return true;
	}
	public static boolean uMember(Professional obj){
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
			collection.findOneAndReplace(Filters.eq("_id.mid", obj.mid.toUpperCase()), doc1);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
