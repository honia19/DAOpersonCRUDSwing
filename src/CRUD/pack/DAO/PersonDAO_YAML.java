package CRUD.pack.DAO;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.regex.Pattern;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_YAML implements PersonDAO 
{
	public void Write(ArrayList<Person> pp)
	{
		try 
		{
			FileWriter fr=new FileWriter("d:\\Person.yml");
			for (Person p : pp) 
			{
				String str=("-id:"+p.getId()+" "+"fname:"+p.getFname()+" "+"lname:"+p.getLname()+" "+"age:"+p.getAge()+"\r\n");
				fr.write(str);
				fr.flush();
				fr.close();
			}
		} 
		catch (IOException e) 
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
				String line="";
				Pattern pt=Pattern.compile("[\\s:]");
				while((line=br.readLine())!=null)
				{
				String [] str =pt.split(line);
				Person p = new Person();
				p.setId(Integer.parseInt(str[1]));
				p.setFname(str[3]);
				p.setLname(str[5]);
				p.setAge(Integer.parseInt(str[7]));
				pp.add(p);
			}
			br.close();			
		} 
		catch (IOException ex) 
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
