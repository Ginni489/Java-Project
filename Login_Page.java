package PMSProject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.sql.SQLException;
import javax.imageio.ImageIO;
public class Login_Page extends JFrame implements ActionListener
{
JLabel l1, l2, l3, l4;
JButton btn1, btn2;
JTextField tf1;
JPanel p1,p2;
JPasswordField pf;
Font f;

Login_Page()
{
	super("login page");
	setSize(550,250);
	setLocation(500,200);
	setResizable(false);
	
	f=new Font("arial", Font.BOLD, 18);
	l1=new JLabel("Username");
	l2=new JLabel("Password");
	
	l1.setFont(f);
	l1.setBounds(0,0,100,35);
	
	l2.setFont(f); 
	tf1=new JTextField();
	tf1.setFont(f);
	tf1.setBounds(90,90,0,5);
	pf =new JPasswordField();
	pf.setFont(f);
	
	btn1=new JButton("Login");
	btn2=new JButton("Cancel");
	btn1.setBackground(Color.BLACK);
	btn1.setForeground(Color.WHITE);
	btn2.setBackground(Color.BLACK);
	btn2.setForeground(Color.WHITE);
	
	btn1.addActionListener(this);
	btn2.addActionListener(this);
	btn1.setFont(f);
	btn2.setFont(f);
	
	p1=new JPanel();
	p1.setLayout(new GridLayout(3,2,10,10));
	
	p1.add(l1);
	p1.add(tf1);
	p1.add(l2);
	p1.add(pf);
	p1.add(btn1);
	p1.add(btn2);
	
	ImageIcon img=new ImageIcon(ClassLoader.getSystemResource ("PMSProject/icons/login.png"));
	//l1.setIcon(new ImageIcon("icons/login.jpg"));
	Image image=img.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
	ImageIcon img1=new ImageIcon(image);
	l3=new JLabel(img1);
	
	setLayout(new BorderLayout(30,30));
	add(l3,"West");
	add(p1,"Center");
	
	
	
}
public void actionPerformed(ActionEvent e) {

	if(e.getSource()==btn1)
	{
		String u_name=tf1.getText();
		String p_name=pf.getText();
		try 
		{
			  ConnectionClass obj=new ConnectionClass();
			  String q= "select * from login where username='"+u_name+"' and password='"+p_name+"'";
			  ResultSet res=obj.st.executeQuery(q);
			  if(res.next())
			  {
				  new Home_Payroll().setVisible(true);
				   this.setVisible(false);
			  }
			  else
			  {
				  JOptionPane.showMessageDialog(null, "invalid username or password");
				  this.setVisible(false);
			  }}
		 catch(Exception ex)
		{
			 ex.printStackTrace();
		 }
	}
	if(e.getSource()==btn2)	
	{
		JOptionPane.showMessageDialog(null,"Are you sure?");
		setVisible(false);
	}
}
 public static void main (String args[])
 {
	
	try {
		new Login_Page().setVisible(true);
	    } 
	catch (NullPointerException npe)
	{
		// TODO Auto-generated catch block
		npe.printStackTrace();
 }
}
}	

