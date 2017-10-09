package CRUD.pack.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_H2 implements PersonDAO 
{
		public  ArrayList<Person> pp = new ArrayList<Person>();
		
		public  ArrayList<Person> Read() 
		{
		ArrayList<Person> pp = new ArrayList<Person>();
		try 
		{
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select *from person");
			while (rs.next()) 
			{
				Person p = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
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
		public  void Delete(Person p) 
		{
			try 
			{
			Class.forName("org.h2.Driver");
			Connection conn=DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			Statement st=conn.createStatement();
			st.executeUpdate("delete from person where ID= " + p.getId());
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public  void Update(Person p)
		{
			try 
			{
			Class.forName("org.h2.Driver");
			Connection conn=DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			Statement st=conn.createStatement();
			st.executeUpdate("update person set FNAME='"+p.getFname()+"', LNAME = '"+p.getLname()+"', AGE = "+p.getAge()+" where ID= " + p.getId());
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public  void Create(Person p) 
		{
			try 
			{
			Class.forName("org.h2.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
	        Statement st=conn.createStatement();
	        st.executeUpdate("insert into person values("+p.getId()+", '"+p.getFname()+"', '"+p.getLname()+"', "+p.getAge()+")");
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
