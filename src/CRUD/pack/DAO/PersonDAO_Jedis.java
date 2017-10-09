package CRUD.pack.DAO;
import java.util.ArrayList;
import java.util.List;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;
import redis.clients.jedis.Jedis;

public class PersonDAO_Jedis implements PersonDAO 
{
	Jedis je=null;
	public void connect()
	{
		je = new Jedis("localhost");
	}

	@Override
	public void Create(Person p) 
	{
		connect();
		je.lpush("persons",p.toRedisString());
		je.close();
	}

	@Override
	public ArrayList<Person> Read() 
	{
		ArrayList<Person> pp = new ArrayList<Person>();
		try
		{
			connect();
			List<String> list = je.lrange("persons", 0, je.llen("persons"));
			for (String record : list)
			{
				pp.add(Person.fromRedisString(record));
			}
			je.close();
		}
		catch(Exception ex)
		{
			System.err.println(ex.getMessage());
		}
		return pp;
	}

	@Override
	public void Update(Person p) 
	{
		try
		{
			connect();
			ArrayList<Person> pp = Read();
			for(int i=0; i<je.llen("persons");i++)
			{
				if(pp.get(i).getId()==p.getId())
				{
					je.lset("persons", i, p.toRedisString());
				}
			}
			je.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void Delete(Person p) 
	{
		try
		{
			connect();
			ArrayList<Person> pp = Read();
			for (Person person : pp)
				if (p.getId() == person.getId())
				{
					je.lrem("persons", -1, person.toRedisString());
					break;
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
