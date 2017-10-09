package CRUD.pack.DAO;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;
public class PersonDAO_XML implements PersonDAO 
{
	
	private RandomAccessFile raf;


	public PersonDAO_XML() 
	{

	}
	
	public void Write(ArrayList<Person> pp)
	{
		FileWriter fw;
		try {
			fw = new FileWriter("D:\\Person.xml");
			for (Person p : pp) 
		{
			
			String str;

			str="<Person>\n<id>"+p.getId()+"</id>"+'\n'+"<fname>"+p.getFname()+"</fname>"+'\n'+"<lname>"+p.getLname()
						+"</lname>"+'\n'+"<age>"+p.getAge()+"</age>"+'\n'+"</Person>\r\n";
			
			fw.write(str);
			fw.flush();
		}
		
		} catch (IOException e) {
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

	@Override
	public ArrayList<Person> Read() {
		
		ArrayList<Person> pp = new ArrayList<Person>();
		try 
		{
		raf = new RandomAccessFile("d:\\Person.xml","rw");
			
		String str="";
		String id="";
		String fname="";
		String lname="";
		String age="";
		Person p = new Person();
		for(int i=0;i<raf.length();i++)
		{
			char ch= (char)raf.read();
			str+=ch;
			if(ch=='\t' || ch=='\r' || ch=='\n')
			{
				str="";
			}
			else if(str.contains("<id>") && str.contains("/id"))
			{
				id=str.substring(str.indexOf(">")+1, str.lastIndexOf("<"));
			}
			else if(str.contains("<fname>") && str.contains("/fname"))
			{
				fname=str.substring(str.indexOf(">")+1, str.lastIndexOf("<"));
			}
			else if(str.contains("<lname>") && str.contains("/lname"))
			{
				lname=str.substring(str.indexOf(">")+1, str.lastIndexOf("<"));
			}
			else if(str.contains("<age>") && str.contains("/age"))
			{
				age=str.substring(str.indexOf(">")+1, str.lastIndexOf("<"));
			}
			else if(str.contains("</Person>"))
			{
				p.setId(Integer.parseInt(id));
				p.setFname(fname);
				p.setLname(lname);
				p.setAge(Integer.parseInt(age));
				pp.add(p);

			}
			else if(str.contains("<Person>"))
			{
				p=new Person();
			}
		}
		} catch ( NumberFormatException | IOException e) {

			e.printStackTrace();
		}

		
		return pp;

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
	public void Delete(Person p) {
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
