package CRUD.pack.DAO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.extended.EncodedByteArrayConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_XML_lib implements PersonDAO 
{

	String nameXmlFile = "persons";

	public void Write(ArrayList<Person> pp)
	{
		try 
		{
			XStream xStream = new XStream(new DomDriver());
			xStream.alias(nameXmlFile, List.class);
			xStream.processAnnotations(Person.class);
			String xml = xStream.toXML(pp);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("d:\\Person.xml")));
			bufferedWriter.write(xml);
			bufferedWriter.close();
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
		ArrayList<Person> pp = Read();
		pp.add(p);
		Write(pp);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public ArrayList<Person> Read()
	{
		ArrayList<Person> pp = new ArrayList<Person>();
		XStream xStream = new XStream(new DomDriver());
		xStream.alias("persons", List.class);
		xStream.alias("Person", Person.class);
		xStream.registerConverter((Converter) new EncodedByteArrayConverter());
		return pp = (ArrayList<Person>) xStream.fromXML(new File("d:\\Person.xml"));
	}

	@Override
	public void Update(Person p) 
	{
		ArrayList<Person>pp=Read();
		for(int i=0; i<pp.size(); i++)
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
		ArrayList<Person>pp=Read();
		for(int i=0; i<pp.size(); i++)
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
