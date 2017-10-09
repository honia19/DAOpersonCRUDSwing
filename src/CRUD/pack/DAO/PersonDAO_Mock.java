package CRUD.pack.DAO;


import java.util.ArrayList;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_Mock implements PersonDAO 
{
	ArrayList<Person>pp=new ArrayList<Person>();
	public PersonDAO_Mock() 
	{
		pp.add(new Person(1, "Anna", "Asmolova", 18));
		pp.add(new Person(2, "Yana", "Asmolova", 15));
		pp.add(new Person(3, "Roman", "Voloshin", 21));
		pp.add(new Person(4, "Yulia", "Hnezdilova", 22));
		pp.add(new Person(5, "Maksim", "Holybiev", 27));
		pp.add(new Person(6, "Vladislav", "Honchenko", 30));
		pp.add(new Person(7, "Alexandr", "Hordiichuk", 32));
		pp.add(new Person(8, "Dima", "Horyainov", 36));
		pp.add(new Person(9, "Bohdan", "Zaviriukha", 39));
		pp.add(new Person(10, "Alexandr", "Izyumchenko", 43));
		pp.add(new Person(11, "Nikolay", "Kolosovsky", 47));
		pp.add(new Person(12, "Vladislav", "Kuts", 53));
		pp.add(new Person(13, "Uyliy", "Malyar", 58));
		pp.add(new Person(14, "Kseniya", "Nehoy", 62));
		pp.add(new Person(15, "Maksim", "Oancha", 65));
		pp.add(new Person(16, "David", "Phapoyan", 73));
		pp.add(new Person(17, "Bohdan", "Petrikovsky", 78));
	}
	
	@Override
	public void Create(Person p)
	{
		pp.add(p);
	}

	@Override
	public ArrayList<Person> Read() 
	{
		return pp;
	}

	@Override
	public void Update(Person p) 
	{
		ArrayList<Person> pp =Read();
		for(int i=0;i<pp.size();i++)
		{
			if(pp.get(i).getId()==p.getId())
			{
				pp.set(i, p);
			}
		}
	}

	@Override
	public void Delete(Person p) 
	{
		ArrayList<Person> pp = Read();
		for(int i=0; i<pp.size();i++)
		{
			if(pp.get(i).getId()==p.getId())
			{
				pp.remove(i);
				break;
			}
		}
	}
}
