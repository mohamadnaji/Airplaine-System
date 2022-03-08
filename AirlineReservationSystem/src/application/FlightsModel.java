package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FlightsModel {
	
	static ResultSet rs;
	 static DataBase DB=new DataBase();
	
	/*    public void loadtabledata()
	    {
	    	try{
	        	ResultSet rs=DB.SelectFun("Select * from Movies");
	        	while(rs.next()) {
	        		int id=rs.getInt(1);
	        		String name=rs.getString(2);
	        		int duree=rs.getInt(3);
	        		String castt=rs.getString(4);
	        		String des=rs.getString(5);
	        		String direct=rs.getString(6);
	        		list.add(new Movie(id,name,direct,castt,des,duree));
	        	   }
	        	
	        	}catch(Exception e1){ JOptionPane.showMessageDialog(null, e1);}
	        table.getItems().setAll(list);
	    }*/
	public static ArrayList<Flight> getAllFlights() throws SQLException {
		
		rs = DB.SelectFun("select * from flight");
	
		ArrayList<Flight> flightsList = new ArrayList<>();
		while(rs.next()) {
			Flight flight = new Flight(rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5),
					rs.getDate(6).toLocalDate(),
					rs.getString(7),
					rs.getString(8));
			flightsList.add(flight);
			System.out.println("ab");
		}
		
		System.out.println("cd");
		return flightsList;
	}
	
	public static void addFlight(int id, String name, String source, String dest, int price,
			LocalDate date, String arrTime, String depTime) {
		id=1;
		try{
		DB.InsertFun("INSERT INTO flight VALUES('"+id+"','"+name+"','"+source+"','"+dest+"','"+ price+"','"+date+"','"+arrTime+"','"+depTime+"')");
		/*String query = "INSERT INTO flight (flight_id,flight_name,source,destination,flight_price,date,arrival_time,departure_time)"
				+ " VALUES (" +id+","+name+","+source+","+dest+","+price+","+date+","+arrTime+","+depTime+")";
		try {
			System.out.println("1");
			db.InsertFun(query);
			System.out.println("2");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		}catch(Exception e1){ JOptionPane.showMessageDialog(null, e1);}
	}
	
	public static void deleteFlight(int id) {
		String query = "DELETE FROM flight where id = " + id + "";
		try {
			DB.SelectFun(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public static void updateFlight(String name, String source, String dest, int price,
//			LocalDate date, String arrTime, String depTime) {
//		String query = "UPDATE flight set name = '" + name + 
//		try {
//			System.out.println("1");
//			db.InsertFun(query);
//			System.out.println("2");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
