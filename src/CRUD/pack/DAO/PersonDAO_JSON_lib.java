package CRUD.pack.DAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_JSON_lib implements PersonDAO 
{

	public void Write(ArrayList<Person> pp)
	{
		try
		{
			Gson gs = new Gson();
			String str= gs.toJson(pp);
			FileWriter fw= new FileWriter("D:\\PersonLib.json");
			fw.write(str);
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
			BufferedReader br = new BufferedReader(new FileReader("D:\\PersonLib.json"));
			String line=br.readLine();
			Gson gs = new Gson();
			pp=gs.fromJson(line, new TypeToken<ArrayList<Person>>(){}.getType());
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
