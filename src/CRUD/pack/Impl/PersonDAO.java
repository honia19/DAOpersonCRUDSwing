package CRUD.pack.Impl;
import java.util.ArrayList;


public interface PersonDAO 
{
	void Create(Person p);
	ArrayList<Person> Read();
	void Update(Person p);
	void Delete(Person p);
}
