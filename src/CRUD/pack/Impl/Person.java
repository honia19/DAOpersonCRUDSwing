package CRUD.pack.Impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "Person")
@Entity
@Table(name = "persontest", schema = "test", catalog = "")
public class Person implements Serializable
{
	private int id;
	private String fname;
	private String lname;
	private int age;
	public int stID;
	public int endID;
	public int stAge;
	public int endAge;

	public Person() 
	{
		
	}
	public Person(int stID, int endID, String fname, String lname, int stAge, int endAge)
	{
		this.stID = stID;
		this.endID = endID;
		this.fname = fname;
		this.lname = lname;
		this.stAge = stAge;
		this.endAge = endAge;
	}

	public Person(int id, String fname, String lname, int age)
	{
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
	}
	public Person(int id)
	{
		this.id = id;
	
	}
	public String getLname()
	{
		return lname;
	}
	public void setLname(String lname)
	{
		this.lname=lname;
	}
	public String getFname()
	{
		return fname;
	}
	public void setFname(String fname)
	{
		this.fname=fname;
	}
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age=age;
	}
	// for RedisDB
	public String toRedisString()
	{
		String result = this.id + "," + this.fname + "," + this.lname + "," + this.age;
		return result;
	}
	public static Person fromRedisString(String record)
	{
		String[] fields = record.split(",");
		Person p = new Person();
		p.id = Integer.parseInt( fields[0] );
		p.fname = fields[1];
		p.lname = fields[2];
		p.age = Integer.parseInt( fields[3] );
		return p;
	}

	@Override
	public String toString()
	{
		return "PersonClass [Stid=" + stID + ",endID="+endID+ ", fname=" + fname + ", lname=" + lname + ", stage=" + stAge +", endage=" + endAge + "]";
	}

}
