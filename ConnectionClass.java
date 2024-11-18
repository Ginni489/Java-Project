package PMSProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import datacollection.DataConnection;
public class ConnectionClass {

	Connection con;
	Statement st;
	ResultSet rs;
	public ConnectionClass() throws SQLException
	{
		try {
		       Class.forName("com.mysql.cj.jdbc.Driver"); //registration of driver
		       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll","root","@Ginni552002");
		       st = con.createStatement();
		      if(con.isClosed())
		      {
		    	  System.out.println ("connection closed");
		      }
		
		      else
		{
			System.out.println("connection create");
			
		}
}
	 catch(Exception e)
	 {
		e.printStackTrace();
	 }
   }
    public static void main(String args[])
  {
    	try {
			  new  ConnectionClass();
		    } 
    	catch (SQLException e)
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

