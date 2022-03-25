package daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
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

		Connection conn = db.ConnectDb();
		PreparedStatement ps;
		try {
			db.InsertFun("INSERT INTO flight (flight_id,flight_number,nbr_of_seats,nbr_of_reserved_seats,"
					+ "airline_name,source,destination,arrival_time,departure_time,arrival_date,departure_date,flag) "
					+ "VALUES (" + f.getFlight_id() + ",'" + f.getFlight_number() + "'," + f.getNbr_of_seats() + ","
					+ f.getNbr_of_reserved_seats() + ",'" + f.getAirline_name() + "','" + f.getSource() + "','"
					+ f.getDestination() + "','" + f.getArrival_time() + "','" + f.getDeparture_time() + "',"
					+ f.getArrival_date() + "," + f.getDeparture_date() + "," + f.getFlag() + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer t) {

		Connection conn = db.ConnectDb();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("update Flight set flag = 0 where Flight_id = ? ");
			ps.setInt(1, t);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Flight findById(Integer key) {
		Connection con = DataBase.ConnectDb();
		PreparedStatement ps;
		Flight flight = null;
		try {
			ps = con.prepareStatement("SELECT flight_id,flight_number,nbr_of_seats,nbr_of_reserved_seats,"
					+ "airline_name,source,destination,arrival_time,departure_time,arrival_date,departure_date,flag "
					+ "FROM Flight where Flight_id = ?");

			ps.setInt(1, key);
			ResultSet m_ResultSet = ps.executeQuery();
			while (m_ResultSet.next()) {

				flight = new Flight();
				flight.setFlight_id(m_ResultSet.getInt(1));
				flight.setFlight_number(m_ResultSet.getString(2));
				flight.setNbr_of_seats(m_ResultSet.getInt(3));
				flight.setNbr_of_reserved_seats(m_ResultSet.getInt(4));
				flight.setAirline_name(m_ResultSet.getString(5));
				flight.setSource(m_ResultSet.getString(6));
				flight.setDestination(m_ResultSet.getString(7));
				flight.setArrival_time(m_ResultSet.getString(8));
				flight.setDeparture_time(m_ResultSet.getString(9));
				flight.setArrival_date(
						m_ResultSet.getDate(10).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				flight.setDeparture_date(
						m_ResultSet.getDate(11).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				flight.setFlag(m_ResultSet.getInt(11));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flight;
	}

	@Override
	public List<Flight> findAll() {
		Connection con = DataBase.ConnectDb();
		PreparedStatement ps;
		List<Flight> flights = new ArrayList<>();
		try {
			ps = con.prepareStatement("SELECT flight_id,flight_number,nbr_of_seats,nbr_of_reserved_seats,"
					+ "airline_name,source,destination,arrival_time,departure_time,arrival_date,departure_date,flag "
					+ "FROM Flight");

			ResultSet m_ResultSet = ps.executeQuery();
			while (m_ResultSet.next()) {

				Flight flight = new Flight();

				flight.setFlight_id(m_ResultSet.getInt(1));
				flight.setFlight_number(m_ResultSet.getString(1));
				flight.setNbr_of_seats(m_ResultSet.getInt(1));
				flight.setNbr_of_reserved_seats(m_ResultSet.getInt(1));
				flight.setAirline_name(m_ResultSet.getString(1));
				flight.setSource(m_ResultSet.getString(1));
				flight.setDestination(m_ResultSet.getString(1));
				flight.setArrival_time(m_ResultSet.getString(1));
				flight.setDeparture_time(m_ResultSet.getString(1));
				flight.setArrival_date(
						m_ResultSet.getDate(10).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				flight.setDeparture_date(
						m_ResultSet.getDate(11).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				flight.setFlag(m_ResultSet.getInt(1));
				flights.add(flight);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flights;
	}

	@Override
	public void update(Flight t, Integer flightNumber) {
		String sql = "Update flight set "
				+ "flight_number = " + t.getFlight_number() + ","
				+ "nbr_of_seats = " + t.getNbr_of_seats() + "," + "nbr_of_reserved_seats = " + t.getNbr_of_reserved_seats()
				+ "," + "airline_name = " + t.getAirline_name() + "," + "source = " + t.getSource() + "," + "destination = "
				+ t.getDestination() + "," + "arrival_time = " + t.getArrivalDateString() + "," + "departure_time = "
				+ t.getDepartureDateString() + "," + "arrival_date = " + t.getArrival_date() + "," + "departure_date = "
				+ t.getDeparture_date() + "," + "flag = " + t.getFlag()
				+ " where flight_id = " + flightNumber  ;
		
		try {
			db.InsertFun(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
