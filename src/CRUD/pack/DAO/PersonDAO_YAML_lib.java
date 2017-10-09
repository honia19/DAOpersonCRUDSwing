package CRUD.pack.DAO;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_YAML_lib implements PersonDAO 
{
	
	public void Write(ArrayList<Person> pp)
	{
		try 
		{
			FileWriter fr=new FileWriter("d:\\PersonLib.yml");
			YamlWriter ymr = new YamlWriter(fr);
			ymr.write(pp);
			ymr.close();
		} 
		catch (IOException ex) 
		{
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public void Create(Person p) 
	{
		ArrayList<Person> pp=Read();
		pp.add(p);
		Write(pp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Person> Read() 
	{
		ArrayList<Person> pp = new ArrayList<Person>();
		try 
		{
			FileReader fr = new FileReader("d:\\PersonLib.yml");
			YamlReader ymr = new YamlReader(fr);
			pp=(ArrayList<Person>) ymr.read();
			fr.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
