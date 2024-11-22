package PMSProject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class List_Attendance extends JFrame implements ActionListener
{
	JTable t;
	JButton btn1;
	String x[]={"Employee Id","First Half","Second Half","Date"};
	String y[][]=new String[30][4];
	int i=0, j=0;
	Font f;
	List_Attendance()
	{
		super("List Attendance");
		setLocation(400,200);
		setSize(690,580);
		setResizable(false);
		
		f=new Font("Arial",Font.BOLD,16);
		try {
			ConnectionClass obj=new ConnectionClass();
			String q="select * from attendance";
			ResultSet rest=obj.st.executeQuery(q);
			while (rest.next())
			{
				y[i][j++]=rest.getString("Eid");
				y[i][j++]=rest.getString("first_half");
				y[i][j++]=rest.getString("second_half");
			//	y[i][j++]=rest.getString("date");
				i++;
				j=0;
			}
			t=new JTable(y,x);
			t.setFont(f);
		}
		catch(Exception ex)
		
		{
			ex.printStackTrace();
		}
		JScrollPane js=new JScrollPane(t);
		add(js);
		
		btn1=new JButton("print");
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
		  try
		  {
          t.print();
		  }
		  
		 catch (Exception ex)
		  {
			  ex.printStackTrace();
		  }
	   }
    }
	  
	public static void main(String args[])
	
	 {
		new List_Attendance().setVisible(true);
		
	  }
}
