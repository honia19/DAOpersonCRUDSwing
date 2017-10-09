package CRUD.pack.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;


public class PersonDAO_VoltDB implements PersonDAO 
{
	Connection con=null;
	Statement stt =null;
	public void start()
	{
		try 
		{
			Class.forName("org.voltdb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:voltdb://192.168.0.120:21212");
			stt = con.createStatement();	
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.getMessage();
			e.printStackTrace();
		}
	}
	@Override
	public void Create(Person p) 
	{
		start();
		try 
		{
			stt.executeUpdate("insert into Person values("+p.getId()+", '"+p.getFname()+"', '"+p.getLname()+"', "+p.getAge()+")");
		} 
		catch (SQLException e) 
		{
			e.getMessage();
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Person> Read() 
	{
		ArrayList<Person> pp = new ArrayList<Person>();
		start();
		try 
		{		
			ResultSet rs = stt.executeQuery("select *from Person");
			while (rs.next()) 
			{
				Person p = new Person(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
				pp.add(p);			
			}
			
		} 
		catch (SQLException e) 
		{
			e.getMessage();
			e.printStackTrace();
		}
		
		return pp;
	}

	@Override
	public void Update(Person p) 
	{
		start();
		try 
		{
			stt.executeUpdate("update Person set fname='"+p.getFname()+"', lname = '"+p.getLname()+"', age = "+p.getAge()+" where id=" + p.getId());
		} 
		catch (SQLException e) 
		{
			e.getMessage();
			e.printStackTrace();
		}

	}

	@Override
	public void Delete(Person p) 
	{
		start();
		try 
		{
			stt.executeUpdate("delete from Person where id="+p.getId());
		} 
		catch (SQLException e) 
		{
			e.getMessage();
			e.printStackTrace();
		}

	}

}
