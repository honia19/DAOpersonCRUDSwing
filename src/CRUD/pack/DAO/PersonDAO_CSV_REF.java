package CRUD.pack.DAO;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_CSV_REF implements PersonDAO
{
	@Override
	public void Create(Person p)
	{
		ArrayList<Person> pp = Read();
		pp.add(p);
		Write(pp);
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
                Field [] ff = p.getClass().getDeclaredFields();
                for (int i = 0; i < ff.length; i++) 
                {
                	ff[0].setAccessible(true);
					ff[1].setAccessible(true);
					ff[2].setAccessible(true);
					ff[3].setAccessible(true);
					ff[0].setInt(p, Integer.parseInt(str[0]));
					ff[1].set(p, str[1]);
					ff[2].set(p, str[2]);
					ff[3].setInt(p, Integer.parseInt(str[3]));	
                }
//                p.setId(Integer.parseInt(str[0]));
//                p.setFname(str[1]);
//                p.setLname(str[2]);
//                p.setAge(Integer.parseInt(str[3]));
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
		Write(pp);
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

		Write(pp);
	}

	private void Write(ArrayList<Person> pp)
	{
		try
		{
			FileWriter fw = new FileWriter("D:\\Persons.csv");
			for (Person p : pp)
			{
				Field [] ff = p.getClass().getDeclaredFields();
				
				for (int i = 0; i < ff.length; i++) 
				{
					ff[i].setAccessible(true);
					String s=(ff[i].get(p)+",");
					fw.write(s);
				}
				String s2 = ("\r\n");
				fw.write(s2);
			}
			
			fw.flush();
			fw.close();
		}
		catch(Exception ex)
		{
			//System.err.println(ex.getMessage());
			System.err.println(ex.getStackTrace());
		}
	}
}
