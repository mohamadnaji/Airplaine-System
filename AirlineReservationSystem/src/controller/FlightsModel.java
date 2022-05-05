package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.IDao;
import dao.IFlightDao;
import daoimpl.FlightDaoImpl;
import model.Flight;
import pojo.DataBase;

public class FlightsModel {

	static ResultSet rs;
	static DataBase DB = DataBase.getDataBase();

	public static List<Flight> getAllFlights() throws SQLException {

		IDao<Flight, Integer> flightDao = FlightDaoImpl.getFlightDaoImpl();

		List<Flight> flights = flightDao.findAll();
		return flights;
	}
	
	public static Flight searchFlight(int id)  throws SQLException{
		Flight flight = null;
		IDao<Flight, Integer> flightDao = FlightDaoImpl.getFlightDaoImpl();
		flight = flightDao.findById(id);
		return flight;
	}

	public static void addFlight(Flight flight) throws SQLException {
		try {
			IDao<Flight, Integer> flightDao = FlightDaoImpl.getFlightDaoImpl();
			flightDao.save(flight);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	public static void deleteFlightByID(int id) throws SQLException {
		try {
			IDao<Flight, Integer> flightDao = FlightDaoImpl.getFlightDaoImpl();
			flightDao.delete(id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public static void updateFlightByID(Flight flight) {

		try {
			IDao<Flight, Integer> flightDao = FlightDaoImpl.getFlightDaoImpl();
			flightDao.update(flight, flight.getFlight_id());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}


	public static int getMaxID() {

		IFlightDao flightDao = FlightDaoImpl.getFlightDaoImpl();
		return flightDao.getMaxFlightId();
	}

}
