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
public class ActUpd extends JDialog
{
	JTextField textID;
	JTextField textFn;
	JTextField textLn;
	JTextField textAge;

	public ActUpd() 
	{
		setLayout(null);
		setTitle("Update Info");
		setBounds(600, 230, 600, 400);
		getContentPane().setBackground(Color.MAGENTA);
		
		JButton ok = new JButton("OK");
		JButton exit = new JButton("CANCEL");
		
		JLabel jl = new JLabel("ID");
		JLabel jl2 = new JLabel("FNAME");
		JLabel jl3 = new JLabel("LNAME");	
		JLabel jl4 = new JLabel("AGE");
		
		textID=new JTextField();
		textFn=new JTextField();	
		textLn=new JTextField();
		textAge=new JTextField();
		
		ok.setBounds(20, 300, 70, 30);
		exit.setBounds(480, 300 , 80, 30);
		
		textID.setBounds(70, 150, 100, 20);
		textFn.setBounds(70, 180, 100, 20);
		textLn.setBounds(70, 210, 100, 20);
		textAge.setBounds(70, 240, 100, 20);
		
		jl.setBounds(45,133,50,50);
		jl2.setBounds(30,165,50,50);
		jl3.setBounds(29,193,50,50);
		jl4.setBounds(33,225,50,50);
		
		add(ok);
		add(exit);
		
		add(textID);
		add(textFn);
		add(textLn);
		add(textAge);
		
		add(jl);
		add(jl2);
		add(jl3);
		add(jl4);
		
		exit.addActionListener(new ExitClass());
		ok.addActionListener(new OKClass());
		
		setModal(true);
	}
	
	public Person getPersonDialogWindow()
	{
			
			int id=Integer.parseInt(textID.getText());
			String fname=textFn.getText().toString();
			String lname=textLn.getText().toString();
			int age=Integer.parseInt(textAge.getText());
			return new Person(id,fname,lname,age);
	}
	class ExitClass implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			dispose();
		}
		
	}
	class OKClass implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			dispose();		
		}
	}
}
