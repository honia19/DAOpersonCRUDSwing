package CRUD.pack.DAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_Mongo implements PersonDAO 
{
	MongoCollection<BasicDBObject> person=null;
	@SuppressWarnings("resource")
	public void Start()
	{
		MongoClient mongoCl = new MongoClient("localhost", 27017);
		MongoDatabase data = mongoCl.getDatabase("Person");
		person= data.getCollection("person",BasicDBObject.class);
	}
	
	@Override
	public void Create(Person p) 
	{
		Start();
		BasicDBObject pp = new BasicDBObject("id", p.getId()).append("fname", p.getFname()).append("lname", p.getLname()).append("age",p.getAge());
		person.insertOne(pp);
	}

	@Override
	public ArrayList<Person> Read() 
	{
		ArrayList<Person> pp = new ArrayList<>();
		Start();
		List<BasicDBObject> doc = (List<BasicDBObject>) person.find().into(
			    new ArrayList<BasicDBObject>());
		for (BasicDBObject basic : doc) 
		{
			pp.add(new Person(basic.getInt("id"), basic.getString("fname"), basic.getString("lname"),
					basic.getInt("age")));
		}
		return pp;
	}

	@Override
	public void Update(Person p) 
	{
		Start();
		person.updateMany( new Document("id", new Document("$eq", p.getId()))
		          , new Document("$set", new Document("fname", p.getFname())));
		person.updateMany( new Document("id", new Document("$eq", p.getId()))
		          , new Document("$set", new Document("lname", p.getLname())));
		person.updateMany( new Document("id", new Document("$eq", p.getId()))
		          , new Document("$set", new Document("age", p.getAge())));
	}

	@Override
	public void Delete(Person p) 
	{
		Start();
		person.deleteOne(new Document("id", new Document("$eq",p.getId())));
	}
}
