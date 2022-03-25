package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.IDao;
import daoimpl.ClientDaoImpl;
import model.Client;

public class ClientsModel {

	public static Boolean checkClient(String fName, String lName, String passNb) {
		String fn, ln;
		try {
			List<Client> clients = getAllClients();
			for (Client c : clients) {
				fn = c.getFirstName(); // passenger first name
				ln = c.getLastName(); // passenger last name
				// pn = rs.getString("passport_number"); //
				if (fn.equals(fName) && ln.equals(lName) /* && pn.equals(passNb) */)
					return false;
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}

		return true;
	}

	public static List<Client> getAllClients() throws SQLException {
		List<Client> client = null;
		try {
			IDao<Client, Integer> clientDao = ClientDaoImpl.getclientDaoImpl();
			client = clientDao.findAll();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		return client;
	}

	public static void addClient(Client client) {
		try {
			IDao<Client, Integer> clientDao = ClientDaoImpl.getclientDaoImpl();
			clientDao.save(client);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

}
