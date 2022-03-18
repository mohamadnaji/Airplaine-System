package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Client;
import pojo.DataBase;

public class ClientsModel {

	static ResultSet rs;
	static DataBase DB = DataBase.getDataBase();

	public static Boolean checkClient(String fName, String lName, String passNb) {
		String fn, ln, pn;
		try {
			rs = DB.SelectFun("Select * from client");
			while (rs.next()) {
				fn = rs.getString(2); // passenger first name
				ln = rs.getString(3); // passenger last name
				// pn = rs.getString("passport_number"); //
				if (fn.equals(fName) && ln.equals(lName) /* && pn.equals(passNb) */)
					return false;
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}

		return true;
	}

	public static ArrayList<Client> getAllClients() throws SQLException {
		rs = DB.SelectFun("Select * from client");
		ArrayList<Client> listOfClients = new ArrayList<>();
		while (rs.next()) {
			Client client = new Client(rs.getString(2), // first name
					rs.getString(3), // last name
					"LH43534535",
					/* rs.getString("passport_number"), */
					/* rs.getString("ageGroup"), */
					"adult", rs.getString(7), // phone number
					rs.getString(4), // email address
					rs.getDate(5).toLocalDate()); // birth date
			listOfClients.add(client);
		}
		return listOfClients;

	}

	public static void addClient(String fn, String ln, String pn, LocalDate bd, String a, String mbNB, String emailAd) {
		String Query = "INSERT INTO Passenger VALUES ("/* +id"," */ + fn + "," + ln + "," + bd + "," + a + "," + pn
				+ "," + mbNB + "," + emailAd + ")";
		try {
			DB.InsertFun(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
