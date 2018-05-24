package employee.controller;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



public class ConnectionDB {
	static MongoClient mongoClient;
	public static MongoDatabase db;
	static MongoCollection<Document>coll;
	public static void getConnected1()
	{		
		try
		{
			mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
			db=mongoClient.getDatabase("Employee");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public static void getClose()
	{		
		try
		{
			mongoClient.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
