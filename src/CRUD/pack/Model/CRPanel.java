package CRUD.pack.Model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import CRUD.pack.ActionAPI.ActCreate;
import CRUD.pack.ActionAPI.ActDel;
import CRUD.pack.ActionAPI.ActSearch;
import CRUD.pack.ActionAPI.ActUpd;
import CRUD.pack.Impl.Person;

@SuppressWarnings("serial")
public class CRPanel extends JPanel 
{
	
	JTextField text1;
	JTextField text2;
	JTextField text3;
	JTextField text4;

	CRTableModel crt = new CRTableModel();
	ArrayList<Person> pp = crt.pp;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CRPanel() 
	{
		setLayout(null);
		setBounds(0,0,600, 400);
		
		setBackground(Color.GREEN);
		
		JButton btnCr=new JButton("Create");
		JButton btnUP=new JButton("Update");
		JButton btnDel=new JButton("Delete");
		JButton btnRead=new JButton("Read");
		JButton btnSearch = new JButton("Search");
		
		JComboBox jcb = new JComboBox(crt.achoose.ChooseDB());
		jcb.setBounds(185, 150, 150, 20);
		jcb.addActionListener(crt.achoose);
		add(jcb);
		
		
		add(btnCr);
		add(btnUP);
		add(btnDel);
		add(btnRead);
		add(btnSearch);
		
		btnDel.setBounds(200, 100, 80, 20);
		btnRead.setBounds(285,100,80,20);
		btnUP.setBounds(118, 100, 80, 20);
		btnCr.setBounds(40, 100, 75, 20);
		btnSearch.setBounds(420, 65, 80, 20);
		
		
		JLabel jl = new JLabel("ID");
		JLabel jl2 = new JLabel("FNAME");
		JLabel jl3 = new JLabel("LNAME");	
		JLabel jl4 = new JLabel("AGE");
		
		jl.setBounds(45,133,50,50);
		jl2.setBounds(30,165,50,50);
		jl3.setBounds(29,193,50,50);
		jl4.setBounds(33,225,50,50);
		
		add(jl);
		add(jl2);
		add(jl3);
		add(jl4);
		
		text1=new JTextField();
		text2=new JTextField();	
		text3=new JTextField();
		text4=new JTextField();
		text1.setBounds(70, 150, 100, 20);
		text2.setBounds(70, 180, 100, 20);
		text3.setBounds(70, 210, 100, 20);
		text4.setBounds(70, 240, 100, 20);
		
		add(text1);
		add(text2);
		add(text3);
		add(text4);
		
		JTable jt=new JTable(crt);
		JScrollPane jsc = new JScrollPane(jt);
		jsc.setBounds(370, 90, 200, 250);
		add(jsc);
		
		btnCr.addActionListener(new ActionCreate());
		btnUP.addActionListener(new ActionUpdate());
		btnDel.addActionListener(new ActionDelete());
		btnRead.addActionListener(new ActionRead());
		btnSearch.addActionListener(new ActionSearch());
	}
//	private Person getDeletePerson()
//	{
//			int id=Integer.parseInt(text1.getText());
//			return new Person(id);
//	}
//	private Person getPerson()
//	{
//			int id=Integer.parseInt(text1.getText());
//			String fname=text2.getText().toString();
//			String lname=text3.getText().toString();
//			int age=Integer.parseInt(text4.getText());
//			return new Person(id,fname,lname,age);
//	}
	class ActionSearch implements ActionListener
	{
//&& p.stAge < p.endAge && p.getFname().contains(p.getFname()) && p.getLname().contains(p.getFname())
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		crt.Read();
		ActSearch search = new ActSearch();
		search.show();
		Person p = search.getPersonDialogWindowSearch();
		ArrayList<Person> temp = new ArrayList<>();
		for (Person person : pp) 
		{
			if (person.getId() >= p.stID && person.getId() <= p.endID && person.getAge()>=p.stAge && person.getAge()<=p.endAge && person.getFname().contains(p.getFname()) || person.getLname().contains(p.getFname()))
			{
				temp.add(person);
			}
			crt.setPp(temp);
			crt.fire();
		}
	}
}
	public class ActionCreate implements ActionListener
	{
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			ActCreate ac = new ActCreate();
			ac.show();
			Person p = ac.getPersonDialogWindow();
			crt.Create(p);	
			crt.Read();
		}
	}
	public class ActionRead implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			crt.Read();			
		}
	}
	public class ActionUpdate implements ActionListener
	{	@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			ActUpd ac = new ActUpd();
			ac.show();
			Person p = ac.getPersonDialogWindow();
			crt.Update(p);
			crt.Read();	
		}
	}
	public class ActionDelete implements ActionListener
	{
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			ActDel ac = new ActDel();
			ac.show();
			Person p = ac.getPersonDialogWindow();
			crt.Delete(p);
			crt.Read();	
		}
	}
}
