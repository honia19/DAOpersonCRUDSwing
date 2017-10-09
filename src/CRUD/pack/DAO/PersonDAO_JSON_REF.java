package CRUD.pack.DAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_JSON_REF implements PersonDAO 
{
	public void Write(ArrayList<Person> pp)//переделать
	{
		try
		{
			FileWriter fw= new FileWriter("D:\\Person.json");
			for (Person p : pp) 
			{
				Field [] ff = p.getClass().getDeclaredFields();
				String s1=("{" + "Person" + ":" + "[" + "{" );
				fw.write(s1);
				for (int i = 0; i < ff.length; i++) 
				{
					ff[i].setAccessible(true);
					String s = (ff[i].getName() + ":" + ff[i].get(p) + ",");		
					fw.write(s);
				}	
			String s2 = ("}" + "]" + "}" + "\r\n");
			fw.write(s2);
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
			BufferedReader br = new BufferedReader(new FileReader("d:\\Person.json"));
			String line="";
		
			
			while((line=br.readLine())!=null)
			{			
				String [] str = line.split("[}:,{]");	
				Person p = new Person();
				Field [] ff = p.getClass().getDeclaredFields();
				for (int i = 0; i < ff.length; i++) 
				{
					ff[0].setAccessible(true);
					ff[1].setAccessible(true);
					ff[2].setAccessible(true);
					ff[3].setAccessible(true);
					ff[0].setInt(p, Integer.parseInt(str[4]));
					ff[1].set(p, str[6]);
					ff[2].set(p, str[8]);
					ff[3].setInt(p, Integer.parseInt(str[10]));				
				}
//				p.setId(Integer.parseInt(str[4]));
//				p.setFname(str[6]);
//				p.setLname(str[8]);
//				p.setAge(Integer.parseInt(str[10]));
				pp.add(p);
			}
			
			br.close();
		} catch (IOException | IllegalArgumentException | IllegalAccessException  ex) 
		{
			System.err.println(ex.getMessage());
			System.err.println(ex.getStackTrace());
		}
		
		return pp;
	}

	@Override
	public void Update(Person p) 
	{
		ArrayList<Person> pp= Read();
		for(int i=0; i<pp.size();i++)
		{
			if(pp.get(i).getId()==p.getId())
			{
				pp.set(i, p);
				//break;
			}
		}
		Write(pp);
	}

	@Override
	public void Delete(Person p) 
	{
		ArrayList<Person> pp= Read();
		for(int i=0; i<pp.size();i++)
		{
			if(pp.get(i).getId()==p.getId())
			{
				pp.remove(i);
				break;
			}
		}
		Write(pp);
	}
}
