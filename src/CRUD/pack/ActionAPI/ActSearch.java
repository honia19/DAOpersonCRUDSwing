package CRUD.pack.ActionAPI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import CRUD.pack.Impl.Person;

@SuppressWarnings("serial")
public class ActSearch extends JDialog
{
	JTextField startID;
	JTextField endID;
	JTextField fname;
	JTextField lname;
	JTextField stAge;
	JTextField endAge;
	
	public ActSearch() 
	{
		setLayout(null);
		setTitle("Search Window");
		getContentPane().setBackground(Color.orange);
		setBounds(600, 230, 600, 400);
		JButton btnSearch = new JButton("SEARCH");
		JButton btnCancel = new JButton("CANCEL");
		
		JLabel jl1 = new JLabel("StartID");
		JLabel jl2 = new JLabel("EndID");
		JLabel jl3 = new JLabel("Fname");
		JLabel jl4 = new JLabel("Lname");
		JLabel jl5 = new JLabel("StartAge");
		JLabel jl6 = new JLabel("EndAge");
		
		startID = new JTextField();
		endID = new JTextField();
		fname = new JTextField();
		lname = new JTextField();
		stAge = new JTextField();
		endAge = new JTextField();
		
		btnSearch.setBounds(260, 300, 90, 30);
		btnCancel.setBounds(480, 300, 90, 30);
		jl1.setBounds(60, 105, 50, 20);
		jl2.setBounds(480, 105, 50, 20);
		jl3.setBounds(220, 135, 60, 30);
		jl4.setBounds(220, 165, 60, 30);
		jl5.setBounds(50, 195, 50, 20);
		jl6.setBounds(480, 195, 50, 20);
		
		
		startID.setBounds(100, 100, 70, 30);
		endID.setBounds(410, 100, 70, 30);
		fname.setBounds(260, 130, 70, 30);
		lname.setBounds(260, 160, 70, 30);
		stAge.setBounds(100, 190, 70, 30);
		endAge.setBounds(410, 190, 70, 30);
	
		
		add(btnSearch);
		add(btnCancel);
		add(startID);
		add(endID);
		add(fname);
		add(lname);
		add(stAge);
		add(endAge);
		
		add(jl1);
		add(jl2);
		add(jl3);
		add(jl4);
		add(jl5);
		add(jl6);
	
		btnSearch.addActionListener(new SearchWindow());
		btnCancel.addActionListener(new CancelWindow());
		setModal(true);
	}
	public Person getPersonDialogWindowSearch()
	{
		int start = Integer.parseInt(startID.getText());
		int end = Integer.parseInt(endID.getText());
		String Fname = fname.getText();
		String Lname = lname.getText();
		int startAge = Integer.parseInt(stAge.getText());
		int eAge = Integer.parseInt(endAge.getText());
		Person p = new Person(start, end, Fname, Lname, startAge, eAge);
		return p;
	}

	class SearchWindow implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			dispose();
		}
	}
	class CancelWindow implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			dispose();
		}
	}
}
