package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.IClientDao;
import model.Client;
import pojo.DataBase;

public class ClientDaoImpl implements IClientDao {

	private static ClientDaoImpl clientDao;
	private DataBase db = DataBase.getDataBase();

	public static ClientDaoImpl getclientDaoImpl() {
		if (clientDao == null)
			clientDao = new ClientDaoImpl();
		return clientDao;
	}

	@Override
	public void save(Client t) {
		String fn, ln, a, pn, mbNB, emailAd;
		LocalDate bd;
		fn = t.getFirstName();
		ln = t.getLastName();
		bd = t.getBirthDate();
		a = t.getAgeGroup();
		pn = t.getPassportNumber();
		mbNB = t.getPhoneNumber();
		emailAd = t.getEmailAddress();
		String Query = "INSERT INTO Passenger VALUES ("/* +id"," */ + fn + "," + ln + "," + bd + "," + a + "," + pn
				+ "," + mbNB + "," + emailAd + ")";
		try {
			db.InsertFun(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer t) {

		Connection conn = db.ConnectDb();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("update client set flag = 0 where client_id = ? ");
			ps.setInt(1, t);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Client findById(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findAll() {

		Connection con = DataBase.ConnectDb();
		PreparedStatement ps;
		ArrayList<Client> listOfClients = new ArrayList<>();
		try {
			ps = con.prepareStatement("SELECT * from CLIENT");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Client client = new Client(rs.getString(2), // first name
						rs.getString(3), // last name
						"LH43534535",
						"adult", 
						rs.getString(7), // phone number
						rs.getString(4), // email address
						rs.getDate(5).toLocalDate()); // birth date
				listOfClients.add(client);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfClients;

	}

	@Override
	public void update(Client t, Integer k) {
		// TODO Auto-generated method stub

	}

}
