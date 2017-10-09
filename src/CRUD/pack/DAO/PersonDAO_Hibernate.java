package CRUD.pack.DAO;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;


public class PersonDAO_Hibernate implements PersonDAO 
{
	public SessionFactory sessionFactory;

	public void setup() 
	{
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try 
		{
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} 
		catch (Exception ex) 
		{
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	public void exit() 
	{
		sessionFactory.close();
	}

	@Override
	public void Create(Person p) 
	{
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();
		exit();
	}

	@Override
	public ArrayList<Person> Read() 
	{
		ArrayList<Person> pp=new ArrayList<Person>();
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
        String str="Person";
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from " + str);
        for (Object obj : query.list()) 
        {
        	pp.add((Person) obj);
		}
        exit();
		return pp;
	}

	@Override
	public void Update(Person p) 
	{
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(p);
		session.getTransaction().commit();
		session.close();
		exit();
	}

	@Override
	public void Delete(Person p) 
	{
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		session.close();
		exit();
	}
}
