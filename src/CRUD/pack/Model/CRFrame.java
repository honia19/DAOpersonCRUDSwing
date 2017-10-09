package CRUD.pack.Model;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class CRFrame extends JFrame
{
	public CRFrame() 
	{
		setLayout(null);
		setBounds(50,50,600,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("CRUD");
		add(new CRPanel());
		setVisible(true);
	}
}
