package CRUD.pack.DAO;


import java.sql.*;
import java.util.ArrayList;

import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

public class PersonDAO_MySql implements PersonDAO

{
		public  ArrayList<Person> pp = new ArrayList<Person>();
	 	static String url = "jdbc:mysql://localhost:3306/";
        static String user = "root";
        static String password = "";
        static String className="com.mysql.jdbc.Driver";
	
   
    public void Delete(Person p) 
    {
    	try 
    	{
		Class.forName(className);
    	Connection con=DriverManager.getConnection(url, user, password);
    	Statement stt=con.createStatement();
    	stt.execute("use test");
    	stt.executeUpdate("delete from persontest where ID= " + p.getId());
    	} 
    	catch (ClassNotFoundException | SQLException e) 
    	{
			
			e.printStackTrace();
		}
    }
    public void Update(Person p) 
    {
    	try 
    	{
			Class.forName(className);
			Connection con = DriverManager.getConnection(url, user, password);
			Statement stt = con.createStatement();
			stt.execute("use test");
			stt.executeUpdate("update persontest set FNAME='"+p.getFname()+"', LNAME = '"+p.getLname()+"', AGE = "+p.getAge()+" where ID= " + p.getId());
    	} 
    	catch (ClassNotFoundException | SQLException e) 
    	{
		
			e.printStackTrace();
		}
    }
    
  
    public  ArrayList<Person> Read()
    {
    	ArrayList<Person> pp=new ArrayList<Person>();
    	try 
    	{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			Statement stt = con.createStatement();
			stt.execute("USE test");
			ResultSet rs = stt.executeQuery("select *from persontest");
	       while(rs.next())
	       {
	    	   Person p=new Person(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
	    	   pp.add(p);
	       }
    	} 
    	catch (ClassNotFoundException | SQLException e) 
    	{
		
			e.printStackTrace();
		}
	       return pp;
    	
    }
    
    public void Create(Person pc) 
    {
    	{
			try 
			{
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url, user, password);
	        Statement st=conn.createStatement();
	        st.execute("USE test");
	        st.executeUpdate("insert into persontest values("+pc.getId()+", '"+pc.getFname()+"', '"+pc.getLname()+"', "+pc.getAge()+")");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}	
    }
}
