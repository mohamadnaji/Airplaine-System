package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

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

public class ReservationController implements Initializable {

	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private ComboBox<String> flightList;
	private ObservableList<String> flightListData;
	
	@FXML
	private ComboBox<Passenger> passengerList;
	private ObservableList<Passenger> passengerListData;

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
				Passenger t = new Passenger(m_ResultSet.getInt(1), m_ResultSet.getInt(2), m_ResultSet.getString(3));
				passengerListData.add(t);
				// System.out.println(t);
			}
			m_Statement.close();
			
			passengerList.setItems(passengerListData);
			passengerList.setConverter(new StringConverter<Passenger>() {
			    @Override
			    public String toString(Passenger object) {
			        return object != null? object.getFirstName() : "";
			    }

			    @Override
			    public Passenger fromString(String string) {
			        return passengerList.getItems().stream().filter(ap -> 
			            ap.getFirstName().equals(string)).findFirst().orElse(null);
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
				// there no need here for preprestatement just practicing to the next one :)
				Connection con = DataBase.ConnectDb();
				PreparedStatement ps = con.prepareStatement("insert into ticket "
						+ "(ticket_id,flight_id,passenger_id,flight_price,nb_of_bags,seat_number, meals,payment_id,flag) "
						+ "values (?,?,?,?,?,0,0,0,0)");
	
				ps.setInt(1, Integer.parseInt(ticketId.getText()));
				ps.setInt(2, Integer.parseInt(flightPrice.getText()));
				ps.setInt(3, Integer.parseInt(flightList.getValue()));
				ps.setInt(4, passengerList.getValue().getPassengerId());
				ps.setInt(5, Integer.parseInt(numberOfBugs.getText()));
				ps.executeUpdate();
				ps.close();
				//System.out.println("i' here ewrfew");
	
				/*
				 * Integer cpid; ps = con.prepareStatement("select max(id) from Company ");
				 * m_ResultSet = ps.executeQuery(); m_ResultSet.next(); cpid =
				 * m_ResultSet.getInt(1);
				 * 
				 * error.setText("Your New inserted Company have ID " + cpid);
				 * AlertController.alert1("Your New inserted Company have ID " + cpid);
				 * m_ResultSet.close(); ps.close();
				 * 
				 * ps =
				 * con.prepareStatement("insert into [CPhaveIT] ([CPID],[ITID]) values (?,?)");
				 * ps.setString(1, cpid.toString()); ps.setString(2, cplocationin.getText());
				 * 
				 * for (InsuranceTypes bean : passengerListData) { if
				 * (bean.getCheckbox().isSelected()) { // System.out.println(bean.getId());
				 * ps.setString(2, bean.getId() + ""); ps.executeUpdate();
				 * 
				 * }
				 * 
				 * } AlertController.alert1("Company Added");
				 */
				ps.close();
			} else {
//				error.setText("Fill The Form and Check Box to Insert !!");
//				AlertController.alert("Please Fill The Form and Check Box to Insert !!", "Insert Error");
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			 e1.printStackTrace();
		}
	}
}
