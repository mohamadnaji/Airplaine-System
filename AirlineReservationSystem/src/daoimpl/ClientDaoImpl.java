package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
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

		// TODO Auto-generated method stub
		
		int flag = 1;
		String Query = "INSERT INTO `ars`.`passenger` (`first_name`, `last_name`, `age_group`, `email`, `phone_number`, `flag`) "
				+ " VALUES ('" +t.getFirstName()+ "','" +t.getLastName()+ "','" +t.getAgeGroup()+ "','" +t.getEmailAddress()+ "','" +t.getPhoneNumber()+ "', '" +flag+ "');";
		try {
			db.InsertFun(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer t) {
		// TODO Auto-generated method stub
		//String Query = "DELETE FROM `ars`.`passenger` WHERE (`passenger_id` = '"+client.getClientID()+"');";
		String Query = "UPDATE `ars`.`passenger` SET `flag` = '0' WHERE (`passenger_id` = '"+ t +"');";

		try {
			db.InsertFun(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Client findById(Integer key) {

		// TODO Auto-generated method stub
		ResultSet rs = null;
		Client client = null;
		try {
			rs = db.SelectFun("Select * from passenger WHERE passenger_id='"+key+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (rs.next()) {
				client = new Client(
						rs.getInt(1), //id
						rs.getString(2), // first name
						rs.getString(3), // last name
						rs.getString(4), //age group
						rs.getString(6), // phone number
						rs.getString(5), // email address
						rs.getInt(7)); // flag
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}


	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try {
			rs = db.SelectFun("Select * from passenger WHERE flag='1'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		ArrayList<Client> listOfClients = new ArrayList<>();
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfClients;
	}

	@Override
	public void update(Client t, Integer k) {
		// TODO Auto-generated method stub
		
	}

}
