package CRUD.pack.DAO;
import java.util.ArrayList;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_Cassandra implements PersonDAO 
{
	private Cluster cluster;
	private Session session;

	public void connect() 
	{
	    cluster = Cluster.builder().addContactPoint("localhost").build();
	    Metadata metadata = cluster.getMetadata();
	    System.out.printf("Cluster: %s%n", metadata.getClusterName());
	    for ( Host host : metadata.getAllHosts() ) 
	    {
	        System.out.printf("Host: %s %n",host.getAddress());
	    }
	    session = cluster.connect();
	}

	@Override
	public void Create(Person p) 
	{
		session.execute("insert into person.persontable(id,fnane,lname,age) values("+p.getId()+", '"+p.getFname()+"', '"+p.getLname()+"', "+p.getAge()+")");
	}

	@Override
	public ArrayList<Person> Read() 
	{
		ArrayList<Person> pp = new ArrayList<Person>();
		connect();
		ResultSet res = session.execute("SELECT * FROM person.persontable");
		for (Row row : res) 
		{
			Person p =new Person(row.getInt("id"),row.getString("fnane"),row.getString("lname"),row.getInt("age"));
			pp.add(p);
		}
		return pp;
	}

	@Override
	public void Update(Person p) 
	{
		session.execute("update person.persontable set fnane='"+p.getFname()+"', lname = '"+p.getLname()+"', age = "+p.getAge()+" where id= " + p.getId());
	}

	@Override
	public void Delete(Person p) 
	{
		session.execute("delete from person.persontable where id="+p.getId());
	}

}
