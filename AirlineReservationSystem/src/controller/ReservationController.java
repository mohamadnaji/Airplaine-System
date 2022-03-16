package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import dao.IDao;
import daoimpl.TicketDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import model.Client;
import model.Ticket;
import pojo.DataBase;

public class ReservationController implements Initializable {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private ComboBox<String> flightList;
	private ObservableList<String> flightListData;

	@FXML
	private ComboBox<Client> passengerList;
	private ObservableList<Client> passengerListData;

	@FXML
	private TextField ticketId;

	@FXML
	private TextField numberOfBugs;

	@FXML
	private TextField flightPrice;

	@FXML
	private Button confirmButton;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			loadPassengerList(null);
			loadFlightList(null);
			loadMaxTicketId(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loadPassengerList(ActionEvent event) throws SQLException {
		try {

			passengerListData = FXCollections.observableArrayList();
			passengerListData.clear();
			Connection con = DataBase.ConnectDb();

			Statement m_Statement = con.createStatement();
			String query = "SELECT passenger_id, last_name, first_name FROM PASSENGER";

			ResultSet m_ResultSet = m_Statement.executeQuery(query);
			while (m_ResultSet.next()) {
				Client t = new Client(m_ResultSet.getString(1), m_ResultSet.getString(2), m_ResultSet.getString(3));
				passengerListData.add(t);
				// System.out.println(t);
			}
			m_Statement.close();

			passengerList.setItems(passengerListData);
			passengerList.setConverter(new StringConverter<Client>() {
				@Override
				public String toString(Client object) {
					return object != null ? object.getLastName() + " " + object.getFirstName() : "";
				}

				@Override
				public Client fromString(String string) {
					return passengerList.getItems().stream().filter(ap -> ap.getFirstName().equals(string)).findFirst()
							.orElse(null);
				}
			});

			// print the value
			passengerList.valueProperty().addListener((ov, oldVal, newVal) -> {
				System.out.println(newVal.getFirstName() + " - " + newVal.getLastName());
			});
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void loadFlightList(ActionEvent event) throws SQLException {
		try {

			flightListData = FXCollections.observableArrayList();
			flightListData.clear();
			Connection con = DataBase.ConnectDb();

			Statement m_Statement = con.createStatement();
			String query = "SELECT flight_id FROM flight order by flight_id desc";

			ResultSet m_ResultSet = m_Statement.executeQuery(query);
			while (m_ResultSet.next()) {
				String flightId = m_ResultSet.getString(1);
				flightListData.add(flightId);
				// System.out.println(t);
			}
			m_Statement.close();

			flightList.setItems(flightListData);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void loadMaxTicketId(ActionEvent event) throws SQLException {

		Connection con = DataBase.ConnectDb();

		Statement m_Statement = con.createStatement();
		String query = "SELECT Max(ticket_id) FROM ticket";

		ResultSet m_ResultSet = m_Statement.executeQuery(query);
		while (m_ResultSet.next()) {
			Integer flightId = m_ResultSet.getInt(1) + 1;
			this.ticketId.setText(flightId.toString());
			// System.out.println(t);
		}
		m_Statement.close();
	}

	@FXML
	private void saveNewTicket(ActionEvent event) {
		try {
			if (!ticketId.getText().isEmpty() && !flightPrice.getText().isEmpty() && !flightList.getValue().isEmpty()
					&& passengerList.getValue() != null && !numberOfBugs.getText().isEmpty()) {
				
				Ticket newTicket = new Ticket();
				newTicket.setTicketId(Integer.parseInt(ticketId.getText()));
				newTicket.setFlightId(Integer.parseInt(flightList.getValue()));
				newTicket.setPassengerId(Integer.parseInt(passengerList.getValue().getPassportNumber()));
				newTicket.setFlightPriceId(Integer.parseInt(flightPrice.getText()));
				newTicket.setNumberOfBugs(Integer.parseInt(numberOfBugs.getText()));

				IDao<Ticket, Integer> ticketDao = TicketDaoImpl.getTicketDaoImpl();
				ticketDao.save(newTicket);
				AlertController.alert1("Saved successfully");
			} else {
//				error.setText("Fill The Form and Check Box to Insert !!");
				AlertController.alert("Please Fill The Form to save !!", "Insert Error");
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
