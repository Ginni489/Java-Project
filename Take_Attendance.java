package PMSProject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Take_Attendance extends JFrame implements ActionListener
{
	private static final boolean True = false;
	JLabel l1, l2, l3, l4;
	JButton btn1;
	Choice ch1,ch2,ch3;
	JPanel p1;
	Font f;
	Take_Attendance()
	{
		super("Take Attendance");
		setLocation(100,100);
		setSize(590,480);
		setResizable(false);
		
		f=new Font("Arial",Font.BOLD,18);
		l1=new JLabel("First Half");
		l2=new JLabel("Second Half");
		l3=new JLabel("Employee ID");
		
		
		l1.setFont(f);
		l2.setFont(f);
		l3.setFont(f);
		
	ch1=new Choice();
	ch1.add("Present");
	ch1.add("Absent");
	ch1.setFont(f);
	
	ch2=new Choice();
	ch2.add("Present");
	ch2.add("Absent");
	ch2.setFont(f);
	
	ch3=new Choice();
	try
	{
		ConnectionClass obj=new ConnectionClass();
		String q2="select Eid from new_employee";
		ResultSet rest2=obj.st.executeQuery(q2);
		while (rest2.next())
		{
			ch3.add(rest2.getString("Eid"));
		}
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	ch3.setFont(f);
	
	btn1=new JButton("Submit");
	btn1.setBackground(Color.BLACK);	
	btn1.setForeground(Color.WHITE);
	btn1.addActionListener(this);
	
	btn1.setFont(f);
	p1=new JPanel();
	p1.setLayout(new GridLayout(3,2,10,10));
	p1.add(l3);
	p1.add(ch3);
	p1.add(l1);
	p1.add(ch1);
	p1.add(l2);
	p1.add(ch2);
	
	setLayout(new BorderLayout(30,30));
	add(p1,"Center");
	add(btn1,"South");
 }

	public void actionPerformed(ActionEvent e)
	{
	    if(e.getSource()==btn1)	
	     {
		  try
		  {
		
		    int id=Integer.parseInt(ch3.getSelectedItem());
	        String first_half=ch1.getSelectedItem();
	        String second_half=ch2.getSelectedItem();
	         String dt=new java.util.Date().toString();
		
		
		
			    ConnectionClass obj=new ConnectionClass();
				String q="insert into attendance values('"+id+"','"+first_half+"','"+second_half+"','"+dt+"')";
				 obj.st.executeUpdate(q);
			    JOptionPane.showMessageDialog(null,"Record insert");
				setVisible(false);
				
		}
		  catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
	 }
}

	public static void main(String args[])
	
	 {
		new Take_Attendance().setVisible(true);
		
	  }
}

	
	


