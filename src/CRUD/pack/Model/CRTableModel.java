package CRUD.pack.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import CRUD.pack.ActionAPI.SelectDB;
import CRUD.pack.DAO.PersonDAO_Mock;
import CRUD.pack.Impl.Person;
import CRUD.pack.Impl.PersonDAO;

@SuppressWarnings("serial")
public class CRTableModel extends AbstractTableModel   
{
	
	ArrayList<Person> pp = new ArrayList<Person>();

	PersonDAO pd = new PersonDAO_Mock();
	SelectDB selectDB = new SelectDB();
	ActionChoose achoose = new ActionChoose();

	
	public void setPp(ArrayList<Person> pp) 
	{
		this.pp = pp;
	}
	public CRTableModel() 
	{
		
		pp = pd.Read();

	}
	class ActionChoose implements ActionListener
	{
		public String [] ChooseDB()
		{
			String[] items = { "MOCK", "CSV_LIB", "JSON", "JSON_LIB", "CSV", "H2", "YAML", "YAML_LIB", "MySQL", "XML",
					"XML_LIB","MONGO","CASSANDRA","REDIS","HYPERGRAPH DB","HIBERNATE","BINARY"};
			return items;
		}

		@SuppressWarnings("rawtypes")
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JComboBox jcb = (JComboBox) e.getSource();
			if (jcb.getSelectedItem().equals("CSV_LIB")) 
			{
				pd = selectDB.getDB("CSV_LIB");
			}
			if (jcb.getSelectedItem().equals("JSON_LIB")) 
			{
				pd = selectDB.getDB("JSON_LIB");
			}
			if (jcb.getSelectedItem().equals("CSV")) 
			{
				pd = selectDB.getDB("CSV");
			}
			if (jcb.getSelectedItem().equals("JSON")) 
			{
				pd = selectDB.getDB("JSON");
			}
			if (jcb.getSelectedItem().equals("MOCK")) 
			{
				pd = selectDB.getDB("MOCK");
			}
			if (jcb.getSelectedItem().equals("H2")) 
			{
				pd = selectDB.getDB("H2");
			}
			if (jcb.getSelectedItem().equals("YAML")) 
			{
				pd = selectDB.getDB("YAML");
			}
			if (jcb.getSelectedItem().equals("YAML_LIB")) 
			{
				pd = selectDB.getDB("YAML_LIB");
			}
			if (jcb.getSelectedItem().equals("MySQL")) 
			{
				pd = selectDB.getDB("MySQL");
			}
			if (jcb.getSelectedItem().equals("XML_LIB")) 
			{
				pd = selectDB.getDB("XML_LIB");
			}
			if (jcb.getSelectedItem().equals("XML")) 
			{
				pd = selectDB.getDB("XML");
			}
			if(jcb.getSelectedItem().equals("MONGO"))
			{
				pd=selectDB.getDB("MONGO");
			}
			if(jcb.getSelectedItem().equals("CASSANDRA"))
			{
				pd=selectDB.getDB("CASSANDRA");
			}
			if(jcb.getSelectedItem().equals("REDIS"))
			{
				pd=selectDB.getDB("REDIS");
			}
			if(jcb.getSelectedItem().equals("HYPERGRAPH DB"))
			{
				pd=selectDB.getDB("HYPERGRAPH DB");
			}
			if(jcb.getSelectedItem().equals("HIBERNATE"))
			{
				pd=selectDB.getDB("HIBERNATE");
			}
			if(jcb.getSelectedItem().equals("BINARY"))
			{
				pd=selectDB.getDB("BINARY");
			}
		}
	}
	
	public void Create(Person p) 
	{
		pd.Create(p);
	}
	public void Update(Person p) 
	{
		pd.Update(p);
	}
	public void Delete(Person p) 
	{
		pd.Delete(p);
	}

	public void Read() 
	{
		pp = pd.Read();
		fireTableDataChanged();
	
	}
	public void fire()
	{
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) 
	{
		String[] str={"ID","Fname","Lname","Age"};
		return str[column];	
	}
	@Override
	public int getColumnCount() 
	{
		return 4;
	}
	@Override
	public int getRowCount() 
	{
		return pp.size();
	}
	
	@Override
	public Object getValueAt(int row, int column) 
	{
		Person p=pp.get(row);
		Object str=null;
		switch(column)
		{
		case 0: str=p.getId();break;
		case 1: str=p.getFname();break;
		case 2: str=p.getLname();break;
		case 3: str=p.getAge();break;
		default: break;
		}
		return str;
	}
}
