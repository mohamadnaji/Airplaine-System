package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FlightsModel {
	
	static ResultSet rs;
	 static DataBase DB=new DataBase();
	
	public static ArrayList<Flight> getAllFlights() throws SQLException {
		
		rs = DB.SelectFun("select * from flight");
	
		ArrayList<Flight> flightsList = new ArrayList<>();
		while(rs.next()) {
			Flight flight = new Flight(rs.getInt(1), //flight id
					rs.getString(2), // airline_name
					rs.getInt(3), // capacity
					rs.getInt(4), // nbr_of_seats
					rs.getInt(5), // nbr of reserved seats
					rs.getString(6), // source
					rs.getString(7), //destination
					rs.getString(8), // arrival time
					rs.getString(9), //departure time
					rs.getDate(10).toLocalDate(), // arrival date
					rs.getDate(11).toLocalDate() // departure date
	//				rs.getInt(12)  // flag
					); 
			flightsList.add(flight);
		}
		
		return flightsList;
	}
	
	public static void addFlight(int id, String airline_name, int capacity, int nbr_of_seats, int nbr_of_reserved_seats, String source,
			String destination, String arrival_time, String departure_time, LocalDate arrival_date, LocalDate departure_date) throws SQLException {
		int flag = 1;
		
		try{
			DB.InsertFun("INSERT INTO flight VALUES("+id+",'"+airline_name+"',"+capacity+","+nbr_of_seats+","+nbr_of_reserved_seats+",'"+source+"','"
				+destination+"','"+arrival_time+"','"+departure_time+"','"+arrival_date+"','"+departure_date+"',"+flag+")");
		}
		catch(Exception e1){ 
			JOptionPane.showMessageDialog(null, e1);
		}
	}
	
	public static void deleteFlightByID(int id) throws SQLException {
		try {
			DB.InsertFun("DELETE FROM `flight` WHERE flight_id = " + id + "");
//			DB.InsertFun("UPDATE flight SET flag = 0 WHERE flight_id = " + id + "");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public static void updateFlightByID(int id, String airline_name, int capacity, int nbr_of_seats, int nbr_of_reserved_seats, 
			String source, String destination, String arrival_time, String departure_time, LocalDate arrival_date, LocalDate departure_date) {
		
		try {
			DB.InsertFun("UPDATE flight SET airline_name = '" + airline_name + 
					"',capacity = " + capacity + 
					",nbr_of_seats = " + nbr_of_seats + ",nbr_of_reserved_seats = " + nbr_of_reserved_seats +
					",source ='" + source + "',destination = '" + destination +
					 "',arrival_time = '" + arrival_time + "',departure_time ='" + departure_time + 
					"',arrival_date = '" + arrival_date +
					 "',departure_date = '" + departure_date + 
					"' WHERE flight_id = " + id + "");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
//	public static boolean checkFlightByID(int id ) throws SQLException {
//		try {
//			String sql = "SELECT EXISTS (SELECT * FROM flight where flight_id = " + id +")";
//			rs = DB.SelectFun(sql);
//			while(rs.next()) {
//				if(rs.getInt(1) > 0) {
//					System.out.println("already exist " + id);
//					return true;
//				}
//				System.out.println("not exists ");
//				return false;
//			}
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e);
//		}
//		return false;
//	}
//	
	public static int getMaxID() {
		try {
			rs = DB.SelectFun("SELECT MAX(flight_id) from flight");
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		return 0;
	}
	
}
