package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.IFlightDao;
import model.Flight;
import pojo.DataBase;

public class FlightDaoImpl implements IFlightDao {

	private static FlightDaoImpl flightDao;
	private DataBase db = DataBase.getDataBase();

	public static FlightDaoImpl getFlightDaoImpl() {
		if (flightDao == null)
			flightDao = new FlightDaoImpl();
		return flightDao;
	}

	@Override
	public void save(Flight f) {
		try {
			int flag = 1;
			String seat=null;
			db.InsertFun("INSERT INTO flight (flight_id,flight_number,nbr_of_seats,nbr_of_reserved_seats,"
					+ "airline_name,source,destination,arrival_time,departure_time,arrival_date,departure_date,flag) "
					+ "VALUES(" + f.getFlight_id() + ",'" + f.getFlight_number() + "'," + f.getNbr_of_seats() + ","
					+ f.getNbr_of_reserved_seats() + ",'" + f.getAirline_name() + "','" + f.getSource() + "','"
					+ f.getDestination() + "','" + f.getArrival_time() + "','" + f.getDeparture_time() + "','"
					+ f.getArrival_date() + "','" + f.getDeparture_date() + "'," + flag + ")");

			db.InsertFun("INSERT INTO seat (flight_id,seat_number,first_class_price,economic_class_price,business_class_price,flag) "
					+ "VALUES(" + f.getFlight_id() + ",'" + "" + "'," + 100 + ","
					+ 150 + "," + 200 + "," + flag + ")");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	@Override
	public void delete(Integer t) {		
		try {
			db.InsertFun("DELETE FROM `flight` WHERE flight_id = " + t + "");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	@Override
	public Flight findById(Integer key) { // by flight_id
		Flight flight = null;
		ResultSet rs = null;
		try {
			rs = db.SelectFun("SELECT * FROM flight WHERE flight_id='"+key+"'");
			
			while(rs.next()) {
				flight = new Flight(rs.getInt(1), //flight_id // or key
						rs.getString(12), //flight_number 
						rs.getString(2), //airline_name
						rs.getInt(3), //nbr_of_seats
						rs.getInt(4), // nbr_of_reserved_seats	
						rs.getString(5), //source
						rs.getString(6), //destination
						rs.getString(7), //arrival_time
						rs.getString(8), //departure_time
						rs.getDate(9).toLocalDate(), //arrival_date
						rs.getDate(10).toLocalDate() //	departure_date
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flight;
	}

	@Override
	public List<Flight> findAll() {
		ResultSet rs = null;
		try {
			rs = db.SelectFun("select * from flight");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<Flight> flightsList = new ArrayList<>();
		try {
			while(rs.next()) {
				Flight flight = new Flight(rs.getInt(1), //flight id
						rs.getString(12), //flight_number
						rs.getString(2), // airline_name
						rs.getInt(3), // nbr_of_seats
						rs.getInt(4), // nbr of reserved seats
						rs.getString(5), // source
						rs.getString(6), //destination
						rs.getString(7), // arrival time
						rs.getString(8), //departure time
						rs.getDate(9).toLocalDate(), // arrival date
						rs.getDate(10).toLocalDate() // departure date
//				rs.getInt(12)  // flag
						); 
				flightsList.add(flight);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flightsList;
	}

	@Override
	public void update(Flight t, Integer id) {  // update by flight_id
		String sql = "Update flight set "
				+ "flight_number = '" + t.getFlight_number() + "',"
				+ "nbr_of_seats = " + t.getNbr_of_seats() + "," + "nbr_of_reserved_seats = " + t.getNbr_of_reserved_seats()
				+ "," + "airline_name = '" + t.getAirline_name() + "'," + "source = '" + t.getSource() + "'," + "destination = '"
				+ t.getDestination() + "'," + "arrival_time = '" + t.getArrival_time() + "'," + "departure_time = '"
				+ t.getDeparture_time() + "'," + "arrival_date = '" + t.getArrival_date() + "'," + "departure_date = '"
				+ t.getDeparture_date() + "'," + "flag = " + t.getFlag() + " where flight_id = " + id + ""  ;
		
		try {
			db.InsertFun(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	@Override
	public Integer getMaxFlightId() {
		try {
			ResultSet rs;
			rs = db.SelectFun("SELECT MAX(flight_id) from flight");
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
