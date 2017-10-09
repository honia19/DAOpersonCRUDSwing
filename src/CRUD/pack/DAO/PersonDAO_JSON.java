package CRUD.pack.DAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_JSON implements PersonDAO 
{
	public void Write(ArrayList<Person> pp)//переделать
	{
		try
		{
			FileWriter fw= new FileWriter("D:\\Person.json");
			for (Person p : pp) 
			{
				String s=("{"+"Person"+":"+"["+"{"+"id"+":"+p.getId()+","+"fname"+":"+p.getFname()+","+
				"lname"+":"+p.getLname()+","+"age"+":"+p.getAge()+"}"+"]"+"}"+"\r\n");
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

		try {
			BufferedReader br = new BufferedReader(new FileReader("d:\\Person.json"));
			String line="";
			while((line=br.readLine())!=null)
			{
				String [] str = line.split("[}:,{]");
				Person p = new Person();
				p.setId(Integer.parseInt(str[4]));
				p.setFname(str[6]);
				p.setLname(str[8]);
				p.setAge(Integer.parseInt(str[10]));
				pp.add(p);
			}
			
			br.close();
		} catch (IOException ex) {
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
