package CRUD.pack.DAO;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_CSV implements PersonDAO
{
	@Override
	public void Create(Person p)
	{
		ArrayList<Person> pp = Read();
		pp.add(p);
		write(pp);
	}

	@Override
	public ArrayList<Person> Read()
	{
		ArrayList<Person> pp = new ArrayList<Person>();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("d:\\Persons.csv"));
			String line = "";
            while ((line = br.readLine()) != null)
            {
                String[] str = line.split(",");
                Person p = new Person();
                p.setId(Integer.parseInt(str[0]));
                p.setFname(str[1]);
                p.setLname(str[2]);
                p.setAge(Integer.parseInt(str[3]));
                pp.add(p);
            }
            br.close();
		}
		catch(Exception ex)
		{
			System.err.println(ex.getMessage());
			System.err.println(ex.getStackTrace());
		}
		
		return pp;
	}

	@Override
	public void Update(Person p)
	{
		ArrayList<Person> pp = Read();

		for (int i = 0; i < pp.size(); i++) 
		{
			if (pp.get(i).getId() == p.getId()) 
			{
				pp.set(i, p);
				break;
			}
		}
		write(pp);
	}

	@Override
	public void Delete(Person p)
	{
		ArrayList<Person> pp = Read();
			for(int i=0; i<pp.size();i++)
			{
				if (pp.get(i).getId()== p.getId())
				{
					pp.remove(i);
					break;
				}
			}

		write(pp);
	}

	private void write(ArrayList<Person> pp)
	{
		try
		{
			FileWriter fw = new FileWriter("D:\\Persons.csv");
			for (Person p:pp)
			{
			
				String s=(+p.getId()+","+p.getFname()+","+p.getLname()+","+p.getAge()+"\r\n");
				fw.write(s);
			}
			
			fw.flush();
			fw.close();
		}
		catch(Exception ex)
		{
			System.err.println(ex.getMessage());
			System.err.println(ex.getStackTrace());
		}
	}
}
