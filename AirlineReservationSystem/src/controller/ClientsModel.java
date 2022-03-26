package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Client;
import model.Flight;
import pojo.DataBase;

public class ClientsModel {

	static ResultSet rs;
	static DataBase DB = DataBase.getDataBase();

	public static Boolean checkClient(String fName, String lName, String age, String emailAd, String mbNB) {
		String fn, ln, a, ea, mn;
		try {
			rs = DB.SelectFun("Select * from passenger");
			while (rs.next()) {
				fn = rs.getString(2); // passenger first name
				ln = rs.getString(3); // passenger last name
				a = rs.getString(4); // passenger age group
				ea = rs.getString(5); // passenger email address
				mn = rs.getString(6); // passenger phone number
				if (
						fn.equals(fName) 
						&& ln.equals(lName) 
						&& a.equals(age)
						&& ea.equals(emailAd)
						&& mn.equals(mbNB)
						)
					return false;
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}

		return true;
	}

	public static ArrayList<Client> getAllClients() throws SQLException {
		rs = DB.SelectFun("Select * from passenger WHERE flag='1'");
		ArrayList<Client> listOfClients = new ArrayList<>();
		while (rs.next()) {
			Client client = new Client(
					rs.getInt(1), //id
					rs.getString(2), // first name
					rs.getString(3), // last name
					rs.getString(4), //age group
					rs.getString(6), // phone number
					rs.getString(5), // email address
					rs.getInt(7)); // flag
			listOfClients.add(client);
		}
		return listOfClients;

	}

	public static void addClient(String fn, String ln, String age, String emailAd, String pn) {
		int flag = 1;
		String Query = "INSERT INTO `ars`.`passenger` (`first_name`, `last_name`, `age_group`, `email`, `phone_number`, `flag`) "
				+ " VALUES ('" +fn+ "','" +ln+ "','" +age+ "','" +emailAd+ "','" +pn+ "', '" +flag+ "');";
		try {
			DB.InsertFun(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateClient(Client client) {
		String Query = "UPDATE `ars`.`passenger` SET `first_name` = '"+client.getFirstName()+"', `last_name` = '"+client.getLastName()
				+"', `age_group` = '"+client.getAgeGroup()+"',`email` = ' "+client.getEmailAddress()+"', `phone_number` = '"+client.getPhoneNumber()+
				"' WHERE (`passenger_id` = '"+client.getClientID()+"');";
		try {
			DB.InsertFun(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteClient(Client client) {
		//String Query = "DELETE FROM `ars`.`passenger` WHERE (`passenger_id` = '"+client.getClientID()+"');";
		String Query = "UPDATE `ars`.`passenger` SET `flag` = '0' WHERE (`passenger_id` = '"+ client.getClientID() +"');";

		try {
			DB.InsertFun(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static ArrayList<Flight> getClientFlights(Client client) throws SQLException {
		rs = DB.SelectFun("Select flight_id from ticket WHERE passenger_id='"+client.getClientID()+"'");
		ArrayList<Flight> listOfFlights = new ArrayList<>();
		ArrayList<Integer> listOfFlightIDs = new ArrayList<>();
		while (rs.next()) {
			listOfFlightIDs.add(rs.getInt(1));
		}
		for( int i =0;i<listOfFlightIDs.size();i++) {
			rs = DB.SelectFun("Select * from flight WHERE flight_id='"+listOfFlightIDs.get(i)+"'");
			while (rs.next()) {
				Flight flight = new Flight(
						rs.getInt(1), // flight id
						rs.getString(13), //flight number
						rs.getString(2), // airline_name
						rs.getInt(4), // nbr_of_seats
						rs.getInt(5), // nbr of reserved seats
						rs.getString(6), // source
						rs.getString(7), // destination
						rs.getString(8), // arrival time
						rs.getString(9), // departure time
						rs.getDate(10).toLocalDate(), // arrival date
						rs.getDate(11).toLocalDate()); // departure date
				listOfFlights.add(flight);
			}
		}
		return listOfFlights;
	}
}
