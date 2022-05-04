package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ITicketDao;
import model.Ticket;
import pojo.DataBase;

public class TicketDaoImpl implements ITicketDao {

	private static TicketDaoImpl ticketDao;

	private DataBase db = DataBase.getDataBase();

	public static TicketDaoImpl getTicketDaoImpl() {
		if (ticketDao == null)
			ticketDao = new TicketDaoImpl();
		return ticketDao;
	}

	@Override
	public void save(Ticket t) {

		Connection conn = db.ConnectDb();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("insert into ticket "
					+ "(ticket_id,flight_id,passenger_id,flight_price,nb_of_bags,seat_number, meals,payment_id,flag,creation_date) "
					+ "values (?,?,?,?,?,0,0,0,1,now())");
			ps.setInt(1, t.getTicketId());
			ps.setInt(2, t.getFlightId());
			ps.setInt(3, t.getPassengerId());
			ps.setInt(4, t.getFlightPriceId());
			ps.setInt(5, t.getNumberOfBugs());
			// TODO save current date
			// I have added the value from the database
			ps.executeUpdate();
			ps.close();
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
			ps = conn.prepareStatement("update ticket set flag = 0 where ticket_id = ? ");
			ps.setInt(1, t);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Ticket findById(Integer key) {
		Connection con = DataBase.ConnectDb();
		PreparedStatement ps;
		Ticket ticket = null;
		try {
			ps = con.prepareStatement("SELECT ticket_Id,flight_Id, "
					+ "passenger_Id,flight_Price,Nb_Of_Bags,meals,seat_Number,payment_Id,creation_Date "
					+ "FROM ticket where ticket_id = ?");

			ps.setInt(1, key);
			ResultSet m_ResultSet = ps.executeQuery();
			while (m_ResultSet.next()) {

				ticket = new Ticket();
				ticket.setTicketId(m_ResultSet.getInt(1));
				ticket.setFlightId(m_ResultSet.getInt(2));
				ticket.setPassengerId(m_ResultSet.getInt(3));
				ticket.setFlightPriceId(m_ResultSet.getInt(4));
				ticket.setNumberOfBugs(m_ResultSet.getInt(5));
				ticket.setMeal(m_ResultSet.getString(6));
				ticket.setSeatNumber(m_ResultSet.getInt(7));
				ticket.setPaymentId(m_ResultSet.getInt(8));
				ticket.setCreationDate(m_ResultSet.getDate(9));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket;
	}

	@Override
	public List<Ticket> findAll() {
		Connection con = DataBase.ConnectDb();
		List<Ticket> tickets = new ArrayList<Ticket>();
		Statement m_Statement;
		try {
			m_Statement = con.createStatement();
			String query = "SELECT ticket_Id,flight_Id,"
					+ "passenger_Id,flight_Price,Nb_Of_Bags,meals,seat_Number,payment_Id,creation_Date "
					+ "FROM ticket where flag = 1";

			ResultSet m_ResultSet = m_Statement.executeQuery(query);
			while (m_ResultSet.next()) {
				Ticket ticket = new Ticket();
				ticket.setTicketId(m_ResultSet.getInt(1));
				ticket.setFlightId(m_ResultSet.getInt(2));
				ticket.setPassengerId(m_ResultSet.getInt(3));
				ticket.setFlightPriceId(m_ResultSet.getInt(4));
				ticket.setNumberOfBugs(m_ResultSet.getInt(5));
				ticket.setMeal(m_ResultSet.getString(6));
				ticket.setSeatNumber(m_ResultSet.getInt(7));
				ticket.setPaymentId(m_ResultSet.getInt(8));
				ticket.setCreationDate(m_ResultSet.getDate(9));
				tickets.add(ticket);
			}
			m_Statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	@Override
	public Integer getMaxTicketId() {
		Connection con = DataBase.ConnectDb();
		Statement m_Statement;
		Integer maxTicketId = 0;
		try {
			m_Statement = con.createStatement();
			String query = "SELECT max(ticket_Id) FROM ticket";

			ResultSet m_ResultSet = m_Statement.executeQuery(query);
			while (m_ResultSet.next()) {
				maxTicketId = m_ResultSet.getInt(1);
			}
			m_Statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxTicketId;
	}

	@Override
	public void update(Ticket t, Integer k) {
		Connection conn = db.ConnectDb();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("update ticket "
					+ "set flight_id = ?, passenger_id = ?, flight_price = ?,nb_of_bags = ? " + "where ticket_id = ?");

			ps.setInt(1, t.getFlightId());
			ps.setInt(2, t.getPassengerId());
			ps.setInt(3, t.getFlightPriceId());
			ps.setInt(4, t.getNumberOfBugs());
			ps.setInt(5, t.getTicketId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
