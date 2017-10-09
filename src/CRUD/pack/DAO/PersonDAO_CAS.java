package CRUD.pack.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

import java.sql.ResultSet;

public class PersonDAO_CAS implements PersonDAO
{
	Connection con=null;

	@Override
	public void Create(Person p) 
	{
		try 
		{
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			con = DriverManager.getConnection("jdbc:cassandra://localhost:9042/Persons");
			Statement st=con.createStatement();
			st.executeUpdate("insert into Persons(id,fname,lname,age) values("+p.getId()+", '"+p.getFname()+"', '"+p.getLname()+"', "+p.getAge()+")");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public ArrayList<Person> Read() 
	{
		ArrayList<Person> pp = new ArrayList<Person>();
		try 
		{
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:7001");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select *from Persons");
			while (rs.next()) 
			{
				Person p =new Person(rs.getInt("id"),rs.getString("fname"),rs.getString("lname"),rs.getInt("age"));
				pp.add(p);
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return pp;
	}

	@Override
	public void Update(Person p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Person p) {
		// TODO Auto-generated method stub
		
	}

}
