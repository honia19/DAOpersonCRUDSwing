package CRUD.pack.ActionAPI;

import CRUD.pack.DAO.PersonDAO_Binary;
import CRUD.pack.DAO.PersonDAO_CSV;
import CRUD.pack.DAO.PersonDAO_CSV_lib;
import CRUD.pack.DAO.PersonDAO_Cassandra;
import CRUD.pack.DAO.PersonDAO_H2;
import CRUD.pack.DAO.PersonDAO_Hibernate;
import CRUD.pack.DAO.PersonDAO_Hyper;
import CRUD.pack.DAO.PersonDAO_JSON;
import CRUD.pack.DAO.PersonDAO_JSON_lib;
import CRUD.pack.DAO.PersonDAO_Jedis;
import CRUD.pack.DAO.PersonDAO_Mock;
import CRUD.pack.DAO.PersonDAO_Mongo;
import CRUD.pack.DAO.PersonDAO_MySql;
import CRUD.pack.DAO.PersonDAO_XML;
import CRUD.pack.DAO.PersonDAO_XML_lib;
import CRUD.pack.DAO.PersonDAO_YAML;
import CRUD.pack.DAO.PersonDAO_YAML_lib;
import CRUD.pack.Impl.PersonDAO;

public class SelectDB 
{
	PersonDAO_CSV csv = null;
	PersonDAO_CSV_lib csvLib = null;
	PersonDAO_H2 h2 = null;
	PersonDAO_JSON json = null;
	PersonDAO_JSON_lib jsonLib = null;
	PersonDAO_MySql mysql = null;
	PersonDAO_XML xml = null;
	PersonDAO_XML_lib xmlLib = null;
	PersonDAO_YAML yaml = null;
	PersonDAO_YAML_lib yamlLib = null;
	PersonDAO_Mock mock = null;
	PersonDAO_Mongo mongo = null;
	PersonDAO_Cassandra cassandra = null;
	PersonDAO_Jedis redis = null;
	PersonDAO_Hyper hyper = null;
	PersonDAO_Hibernate hibernate = null;
	PersonDAO_Binary binary = null;

	public PersonDAO getDB(String db) 
	{
		PersonDAO pd=null;
		switch(db)
		{
		case "CSV": 
			csv=new PersonDAO_CSV();
			pd=csv;
			break;
		case "CSV_LIB": 
			csvLib=new PersonDAO_CSV_lib();
			pd=csvLib;
			break;
		case "JSON": 
			json=new PersonDAO_JSON();
			pd=json;
			break;
		case "JSON_LIB": 
			jsonLib=new PersonDAO_JSON_lib();
			pd=jsonLib;
			break;
		case "MOCK": 
			mock= new PersonDAO_Mock();
			pd=mock;
			break;
		case "H2":
			h2=new PersonDAO_H2();
			pd=h2;
			break;
		case "YAML": 
			yaml=new PersonDAO_YAML();
			pd=yaml;
			break;
		case "YAML_LIB": 
			yamlLib=new PersonDAO_YAML_lib();
			pd=yamlLib;
			break;
		case "MySQL": 
			mysql= new PersonDAO_MySql();
			pd=mysql;
			break;
		case "XML":
			xml=new PersonDAO_XML();
			pd=xml;
			break;
		case "XML_LIB":
			xmlLib=new PersonDAO_XML_lib();
			pd=xmlLib;
			break;	
		case "MONGO": 
			mongo=new PersonDAO_Mongo();
			pd=mongo;
			break;
		case "CASSANDRA":
			cassandra= new PersonDAO_Cassandra();
			pd=cassandra;
			break;
		case "REDIS":
			redis= new PersonDAO_Jedis();
			pd=redis;
			break;
		case "HYPERGRAPH DB":
			hyper= new PersonDAO_Hyper();
			pd=hyper;
			break;
		case "HIBERNATE":
			hibernate = new PersonDAO_Hibernate();
			pd=hibernate;
			break;
		case "BINARY":
			binary = new PersonDAO_Binary();
			pd=binary;
			break;
		}
		return pd;
	}
}
