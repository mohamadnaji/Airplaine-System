package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
					+ "(ticket_id,flight_id,passenger_id,flight_price,nb_of_bags,seat_number, meals,payment_id,flag) "
					+ "values (?,?,?,?,?,0,0,0,0)");
			ps.setInt(1, t.getTicketId());
			ps.setInt(2, t.getFlightId());
			ps.setInt(3, t.getPassengerId());
			ps.setInt(4, t.getFlightPriceId());
			ps.setInt(5, t.getNumberOfBugs());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Ticket t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Ticket findById(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
