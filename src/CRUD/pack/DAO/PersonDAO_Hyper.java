package CRUD.pack.DAO;
import java.util.ArrayList;
import java.util.List;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery.hg;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

import org.hypergraphdb.HyperGraph;



public class PersonDAO_Hyper implements PersonDAO 
{
    public String databaseLocation="D:\\hypergraphdb-1.3\\database\\DAOhp";
    HyperGraph graph = null;
    List<HGHandle> persons=null;

	@Override
	public void Create(Person p)
	{
		HyperGraph graph = new HyperGraph(databaseLocation);
		graph.add(p);
		graph.close();
	}

	@Override
	public ArrayList<Person> Read()
	{
		ArrayList<Person> pp = new ArrayList<Person>();
		HyperGraph graph = new HyperGraph(databaseLocation);
		persons = hg.findAll(graph, hg.typePlus(Person.class));
		for (HGHandle p : persons) 
		{
			pp.add((Person)graph.get(p));
		}
		graph.close();
		return pp;
	}

	@Override
	public void Update(Person p)
	{
		try
		{
			HyperGraph graph = new HyperGraph(databaseLocation);
			List<HGHandle>  persons = hg.findAll(graph, hg.typePlus(Person.class));
			for (HGHandle person : persons)
			{
				Person tmp = (Person)graph.get(person);  

				if(tmp.getId() == p.getId())
				{
					graph.replace(person, p);
					break;
				}
			}
			graph.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void Delete(Person p)
	{
		try
		{
			HyperGraph graph = new HyperGraph(databaseLocation);
			List<HGHandle>  persons = hg.findAll(graph, hg.typePlus(Person.class));
			for (HGHandle person : persons)
			{
				Person tmp=(Person)graph.get(person);   
				
				if(tmp.getId() == p.getId())
				{
					graph.remove(person);
					break;
				}
			}
			graph.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}


