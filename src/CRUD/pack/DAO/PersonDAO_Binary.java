package CRUD.pack.DAO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;


public class PersonDAO_Binary implements PersonDAO 
{

	public void Write(ArrayList<Person> pp)
	{
		try 
		{
			FileOutputStream output = new FileOutputStream("D:\\binary.data");
			ObjectOutputStream obj = new ObjectOutputStream(output);
			obj.writeObject(pp);
			obj.close();
			output.close();
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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Person> Read() 
	{
		ArrayList<Person> pp = new ArrayList<Person>();
		try 
		{
			FileInputStream input = new FileInputStream("D:\\binary.data");
			ObjectInputStream obj = new ObjectInputStream(input);
			pp = (ArrayList<Person>) obj.readObject();
			obj.close();
			input.close();
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
