package CRUD.pack.DAO;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Pattern;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_YAML_REF implements PersonDAO 
{
	public void Write(ArrayList<Person> pp)
	{
		try 
		{
			FileWriter fr=new FileWriter("d:\\Person.yml");
			for (Person p : pp) 
			{
				Field [] ff = p.getClass().getDeclaredFields();
				String s=("-");
				fr.write(s);
				for (int i = 0; i < ff.length; i++) 
				{
					ff[i].setAccessible(true);
					String str = (ff[i].getName()+":"+ff[i].get(p)+" ");	
					fr.write(str);
				}
				String s2=("\r\n");
				fr.write(s2);			
				fr.flush();			
			}	
			fr.close();
		} 
		catch (IOException | IllegalArgumentException | IllegalAccessException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void Create(Person p) 
	{
		ArrayList<Person> pp=Read();
		pp.add(p);
		Write(pp);

	}

	@Override
	public ArrayList<Person> Read() 
	{
		ArrayList<Person> pp = new ArrayList<Person>();
		try 
		{
				BufferedReader br = new BufferedReader(new FileReader("d:\\Person.yml"));
				String str1="";
				Pattern pt=Pattern.compile("[\\s:]");
				while((str1=br.readLine())!=null)
				{
					String [] str =pt.split(str1);
					Person p = new Person();
					Field [] ff = p.getClass().getDeclaredFields();
					for (int i = 0; i < ff.length; i++) 
					{
						ff[0].setAccessible(true);
						ff[1].setAccessible(true);
						ff[2].setAccessible(true);
						ff[3].setAccessible(true);
						ff[0].setInt(p, Integer.parseInt(str[1]));
						ff[1].set(p, str[3]);
						ff[2].set(p, str[5]);
						ff[3].setInt(p, Integer.parseInt(str[7]));				
					}
				pp.add(p);
			}
			br.close();			
		} 
		catch (IOException | IllegalArgumentException | IllegalAccessException ex) 
		{
			System.err.println(ex.getMessage());
			System.err.println(ex.getStackTrace());
		}
		return pp;
	}

	@Override
	public void Update(Person p) 
	{
		ArrayList<Person> pp=Read();
		for (int i = 0; i < pp.size(); i++) 
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
		ArrayList<Person> pp=Read();
		for (int i = 0; i < pp.size(); i++) 
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
