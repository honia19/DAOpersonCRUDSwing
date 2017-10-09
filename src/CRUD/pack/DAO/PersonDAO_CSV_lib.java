package CRUD.pack.DAO;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import com.opencsv.CSVReader;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_CSV_lib implements PersonDAO
{
	private void Write(ArrayList<Person> pp)
	{
		try 
		{
			FileWriter fw = new FileWriter("d:\\PersonLib.csv");
			for (Person p : pp) 
			{
				String s = (+p.getId() + "," + p.getFname() + "," + p.getLname() + "," + p.getAge() + "\r\n");
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
		
		try
		{			
			 CSVReader reader = new CSVReader(new FileReader("d:\\PersonLib.csv"));
		     String [] nextLine;
		     while ((nextLine = reader.readNext()) != null) 
		     {
                Person p = new Person();
                p.setId(Integer.parseInt(nextLine[0]));
                p.setFname(nextLine[1]);
                p.setLname(nextLine[2]);
                p.setAge(Integer.parseInt(nextLine[3]));
                pp.add(p);
		     }                   
            reader.close();
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
				//break;
			}
		}
		Write(pp);
	}

	@Override
	public void Delete(Person p)
	{
		ArrayList<Person> pp = Read();

			for(int i=0; i< pp.size();i++)
			{
				if (pp.get(i).getId()== p.getId())
				{
				pp.remove(i);
				break;
				}
			}

		Write(pp);
	}

	
}
