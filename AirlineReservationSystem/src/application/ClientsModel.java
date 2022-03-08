package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClientsModel {

	
	static ResultSet rs;
    static DataBase DB = new DataBase();
    
    public static Boolean checkClient(String fName, String lName, String passNb) 
    {
    	String fn,ln,pn;
    	try{
    		rs = DB.SelectFun("Select * from client");
    		while(rs.next()) {
    			fn = rs.getString("first_name");
    			ln = rs.getString("last_name");
    			pn = rs.getString("passport_number");
    			if(fn.equals(fName) && ln.equals(lName) && pn.equals(passNb))
    				return false;
    	   }
	        
	    }
    	catch(Exception e1){ JOptionPane.showMessageDialog(null, e1); }
    	
    	return true;
    }
    
	public static ArrayList<Client> getAllClients() throws SQLException{
		rs = DB.SelectFun("Select * from client");
		ArrayList<Client> listOfClients = new ArrayList<>();
		while(rs.next()) {
        	Client client =new Client(rs.getString("first_name"),rs.getString("last_name"),rs.getString("passport_number"),
        			rs.getString("nationality"), rs.getString("gender"), rs.getString("phone_number"), rs.getString("emailAddress"),
        			rs.getString("frequentFlyerNumber"), rs.getInt("frequent_flyer_points"), rs.getDate("birth_date").toLocalDate());
           listOfClients.add(client);
        }
        return listOfClients;
		
	}
	
	public static void addClient(String fn,String ln,String pn,LocalDate bd,String g,String nat,String mbNB,String emailAd) {
		String Query = "INSERT INTO client (first_name,last_name,birth_date,gender,nationality,passport_number,mobile_phone,email_address)"
    			+ " VALUES ("+fn+","+ln+","+bd+","+g+","+nat+","+pn+","+mbNB+","+emailAd+")";
    		try {
    			DB.InsertFun(Query);
    			} 
    		catch (SQLException e) { e.printStackTrace(); }
	}
	
	
}
