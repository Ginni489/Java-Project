package PMSProject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class List_Employee extends JFrame implements ActionListener
{
	JTable tb;
	String x[]= {"Eid","name","gender","address","state","city","email","phone"};
	String y[][]=new String[20][8];
	JButton btn1;
	
	int i = 0;
	//int j=0;
	Font f;
	List_Employee()
	{
		super("List Employee");
		setSize(900,400);
		setLocation(100,100);
		setResizable(true);
		f=new Font("Arial",Font.BOLD,16);
		try
		{
			ConnectionClass obj=new ConnectionClass();
			String q="select * from new_employee";
			ResultSet rest=obj.st.executeQuery(q);
			while (rest.next())
			{   
				int j = 0;
				y[i][j++] = rest.getString("Eid");
				y[i][j++] = rest.getString("name");
				y[i][j++] = rest.getString("gender");
		    	y[i][j++] = rest.getString("address");
				y[i][j++] = rest.getString("state");
				y[i][j++] = rest.getString("city");
				y[i][j++] = rest.getString("email");
			    y[i][j++] = rest.getString("phone");
			   
			    i++;
			  j=0;
			
		}
			tb = new JTable(y, x);
			tb.setFont(f);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		JScrollPane js= new JScrollPane(tb);
		add(js);
		btn1=new JButton("print");
		add(btn1,"South");
		btn1.setBackground(Color.BLACK);
		btn1.setForeground(Color.WHITE);
		btn1.addActionListener(this);
		btn1.setFont(f);
		
		add(btn1,"South");
}
	public void actionPerformed(ActionEvent e)
	{
	  if(e.getSource()==btn1)	
	  {
		  try {
			  tb.print();
		  }
	  catch(Exception exx)
		  {
		  exx.printStackTrace();
		  }
	  }
	}

	public static void main(String args[])
	{
		new List_Employee().setVisible(true);
		
	}
}
