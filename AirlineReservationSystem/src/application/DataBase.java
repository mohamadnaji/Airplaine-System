package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;

public class DataBase {
	   ResultSet resultat = null;
	   Statement stmt;
	   Connection conn;
	   
	public DataBase(){
	    
		try{
			Class.forName("com.mysql.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost/ars","root","root");
    	    stmt=con.createStatement();
	         }
	  	catch (Exception ex) {
		  System.out.println("Echec de chargement du driver" );
			   	}
	}
   
    public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/ars","root","root");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
            
            
        }
        
        
        
    }
	public ResultSet SelectFun(String Query) throws SQLException{
	    resultat = stmt.executeQuery(Query);	
	    return resultat;
	}

	public void InsertFun(String Query) throws SQLException{
	    stmt.executeUpdate(Query);
	}
	
}
