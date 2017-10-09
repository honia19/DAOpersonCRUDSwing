package CRUD.pack.DAO;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_XML_RA implements PersonDAO
{
	
	//Õ≈ œŒÕ»Ã¿ﬁ œŒ◊≈Ã”, ÕŒ ¬ ›“ŒÃ  À¿——≈ –≈‘À≈ ÿÕ ¬ –»ƒ≈ Õ≈ –¿¡Œ“¿≈“, ’Œ“ﬂ ¬ Œ—“¿À‹Õ€’  À¿——¿’ œŒƒŒ¡Õ€… œŒƒ’Œƒ –¿¡Œ“¿≈“
	//≈—À» ”  Œ√Œ ≈—“‹ »ƒ≈», œŒœ–¿¬‹“≈
	
	File file = new File("D:\\Person.xml");
	
	private void write(ArrayList<Person> pp)
	{
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		str = str.concat("<Persons>\n");
		for (Person p : pp)
		{
			str = str.concat("\t<person>\n");
			Field[] fields = p.getClass().getDeclaredFields();
			for (Field f : fields)
			{
				f.setAccessible(true);
				try
				{
					str = str.concat("\t\t<" + f.getName() + ">" + f.get(p) + "</" + f.getName() + ">\n");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				f.setAccessible(false);
			}
			str = str.concat("\t</person>\n");
		}
		str = str.concat("</Persons>");
		
		try
		{
			FileWriter fw = new FileWriter(file);
			fw.write(str);
			fw.flush();
			fw.close();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}

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
		String string;
		FileInputStream fis = null;
		BufferedReader br = null;
		try
		{
			fis = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(fis));
			while ((string = br.readLine()) != null)
			{
				if (string.endsWith("<person>"))
				{
					Person p = new Person();
					Field[] ff = p.getClass().getDeclaredFields();
					for (Field f : ff)
					{
						try
						{
							f.setAccessible(true);
							string = br.readLine();
							int start = string.indexOf("<Id>");
							int end = string.indexOf("</Id>");
							String Id = string.substring(start + 4, end);
							ff[0].set(p, Integer.parseInt(Id));
							string = br.readLine();
							start = string.indexOf("<FName>");
							end = string.indexOf("</FName>");
							String FName = string.substring(start + 7, end);
							ff[1].set(p, FName);
							string = br.readLine();
							start = string.indexOf("<LName>");
							end = string.indexOf("</LName>");
							String LName = string.substring(start + 7, end);
							ff[2].set(p, LName);
							string = br.readLine();
							start = string.indexOf("<Age>");
							end = string.indexOf("</Age>");
							String Age = string.substring(start + 5, end);
							ff[3].set(p, Integer.parseInt(Age));
							System.out.println(p);
						}
						catch (IllegalArgumentException | IllegalAccessException e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
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
		for (int i = 0; i < pp.size(); i++)
		{
			if (pp.get(i).getId() == p.getId())
			{
				pp.remove(i);
				break;
			}
		}
		write(pp);
	}

}
