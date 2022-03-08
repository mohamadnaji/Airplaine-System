package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
//import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class AddFlight implements Initializable{
	  DataBase DB=new DataBase();
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TextField id_textField;
	@FXML
	private TextField airline_name_textField;
	@FXML
	private TextField source_textField;
	@FXML
	private TextField destination_textField;
	@FXML
	private TextField arrival_time_textField;
	@FXML
	private TextField departure_time_textField;
	@FXML
	private TextField price_textField;
	
	@FXML
	private DatePicker arrival_date_textField;
	@FXML
	private DatePicker departure_date_textField;
	
	@FXML
	private TextField nbr_of_seats_TextField;
	@FXML
	private TextField nbr_of_reserved_seats_textField;
	@FXML
	private TextField capacity_textField;
	
	
	@FXML
	private TableView<Flight> flights_tableView;
	@FXML
	private TableColumn<Flight, Integer> id_col;
	@FXML
	private TableColumn<Flight, String> airlineName_col;
	@FXML
	private TableColumn<Flight, String> source_col;
	@FXML
	private TableColumn<Flight, String> destination_col;
	@FXML
	private TableColumn<Flight, String> arrivalTime_col;
	@FXML
	private TableColumn<Flight, String> departureTime_col;
	@FXML
	private TableColumn<Flight, Date> arrivalDate_col;
	@FXML
	private TableColumn<Flight, Date> departureDate_col;
	
	@FXML
	private TableColumn<Flight, Integer> nbrOfSeats_col;
	@FXML
	private TableColumn<Flight, Integer> nbrOfReservedSeats_col;
	@FXML
	private TableColumn<Flight, Integer> capacity_col;

	@FXML
	private Button addButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button clearButton;
	
	PreparedStatement preparedStatement = null;
	ResultSet resultSet;
	Connection connection = DataBase.ConnectDb();
	ObservableList<Flight> flightsList;
	
	String airline_name, source, destination, arrTime, depTime;
	int id, flight_price, capacity,nbr_of_seats, nbr_of_reserved_seats;
	LocalDate arrival_date, departure_date;
	int id_autoincrement = FlightsModel.getMaxID() + 1;
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			fillTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		id_textField.setText(id_autoincrement + "");
		id_col.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("flight_id"));
		airlineName_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("airline_name"));
		source_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("source"));
		destination_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
		arrivalDate_col.setCellValueFactory(new PropertyValueFactory<Flight, Date>("arrival_date"));
		departureDate_col.setCellValueFactory(new PropertyValueFactory<Flight, Date>("departure_date"));
		// HERE WE HAVE A PROBLEM !!!!
//		departureDate_col.setCellValueFactory(new PropertyValueFactory<Flight, Date>("departure_date"));
		
		arrivalTime_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrival_time"));
		departureTime_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("departure_time"));
		capacity_col.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("capacity"));
		nbrOfReservedSeats_col.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("nbr_of_reserved_seats"));
		nbrOfSeats_col.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("nbr_of_seats"));				
	}

	private ObservableList<Flight> fillTable() throws SQLException {
		//ArrayList<Flight> flightsDemo = new ArrayList<>();
//		Flight f1 = new Flight(1001, "Air France", "France", "Lebanon", 450, LocalDate.of(2022, 5, 5), "10 pm", "06 pm");
//		Flight f2 = new Flight(1002, "Emirates", "France", "Lebanon", 450, LocalDate.of(2022, 5, 5), "10 pm", "06 pm");
//		Flight f3 = new Flight(1003, "Middle East", "France", "Lebanon", 450, LocalDate.of(2022, 5, 5), "10 pm", "06 pm");
//		flightsDemo.add(f1);
//		flightsDemo.add(f2);
//		flightsDemo.add(f3);
		flightsList = FXCollections.observableArrayList(FlightsModel.getAllFlights());
		//flightsList = FXCollections.observableArrayList(flightsDemo);
		flights_tableView.setItems(flightsList);
		flights_tableView.setEditable(true);
		return flightsList;
	}

	
	private Boolean noEmpltyFields() {
		System.out.println("CHECK IF WE HAVE EMPTY FIELDS");
		if (id_textField.getText().isEmpty() || airline_name_textField.getText().trim().isEmpty()
				|| source_textField.getText().trim().isEmpty()
				|| destination_textField.getText().trim().isEmpty()
				|| arrival_time_textField.getText().trim().isEmpty()
				|| departure_time_textField.getText().trim().isEmpty()
				|| capacity_textField.getText().isEmpty()
				|| nbr_of_seats_TextField.getText().isEmpty()
				|| nbr_of_reserved_seats_textField.getText().isEmpty()
//				|| arrival_date_textField.getValue() 
//				|| departure_date_textField.getValue() 
				) {
			System.out.println("EMPTY FIELDS");
			return false;
		}
		System.out.println("NO EMPTY FIELDS");
		return true;
	}
	
	// Event Listener on Button.onAction
		@FXML
		public void handleDeleteButton() throws SQLException {
			System.out.println("DELETE BUTTON");
			if(id_textField.getText().isEmpty()) {
				Alert failed = new Alert(Alert.AlertType.WARNING);
				failed.setTitle("Missing Fields!");
				failed.setContentText("Please fill the id.");
				failed.show();
			} else {
				id = Integer.parseInt(id_textField.getText());
				System.out.println("DELETE flight with ID = " + id);
				FlightsModel.deleteFlightByID(id);
				fillTable();
				System.out.println("Delete is ok!");
				Alert flightDeleteAlert = new Alert(Alert.AlertType.INFORMATION);
				flightDeleteAlert.setContentText("This flight has been successfly deleted.");
				flightDeleteAlert.show();
				fillTable();
			}
			
		}
	
	// Event Listener on Button.onAction
		@FXML
		public void handleUpdateButton() throws SQLException {
			System.out.println("UPDATE BUTTON");
			if(id_textField.getText().isEmpty()) {
				Alert failed = new Alert(Alert.AlertType.WARNING);
				failed.setTitle("Missing Fields!");
				failed.setContentText("Please fill the id.");
				failed.show();
			}
			id = Integer.parseInt(id_textField.getText());
			airline_name = airline_name_textField.getText();
			source = source_textField.getText();
			destination = destination_textField.getText();
			nbr_of_seats = Integer.parseInt(nbr_of_seats_TextField.getText());
			nbr_of_reserved_seats = Integer.parseInt(nbr_of_reserved_seats_textField.getText());
			capacity = Integer.parseInt(capacity_textField.getText());
			depTime = departure_time_textField.getText();
			arrTime = arrival_time_textField.getText();		
			arrival_date = arrival_date_textField.getValue();
			departure_date = departure_date_textField.getValue();
			FlightsModel.updateFlightByID(id, airline_name, capacity, nbr_of_seats, nbr_of_reserved_seats, source, destination, arrTime, depTime, arrival_date, departure_date);
			fillTable();
			Alert flightUpdateAlert = new Alert(Alert.AlertType.INFORMATION);
			flightUpdateAlert.setContentText("This flight has been successfly updated.");
			flightUpdateAlert.show();
			fillTable();
		}

	// Event Listener on Button.onAction
	@FXML
	public void handleAddButton() throws SQLException  {
		System.out.println("ADD BUTTON");
		
		if(!noEmpltyFields()) {
			System.out.println("WE HAVE EMPTY FIELDS");
			Alert failed = new Alert(Alert.AlertType.WARNING);
			failed.setTitle("Missing Fields!");
			failed.setContentText("Please fill all fields.");
			failed.show();
		}
//		if(FlightsModel.checkFlightByID(id) == true) {
//			Alert failed = new Alert(Alert.AlertType.WARNING);
//			failed.setTitle("Flight already exist!");
//			failed.setContentText("Clear, and re-enter all fields ");
//			failed.show();
//		}
		
		else {
			System.out.println("WE DON'T HAVE EMPTY FIELDS");
			id = Integer.parseInt(id_textField.getText());
			airline_name = airline_name_textField.getText();
			source = source_textField.getText();
			destination = destination_textField.getText();
			nbr_of_seats = Integer.parseInt(nbr_of_seats_TextField.getText());
			nbr_of_reserved_seats = Integer.parseInt(nbr_of_reserved_seats_textField.getText());
			capacity = Integer.parseInt(capacity_textField.getText());
			depTime = departure_time_textField.getText();
			arrTime = arrival_time_textField.getText();		
			arrival_date = arrival_date_textField.getValue();
			departure_date = departure_date_textField.getValue();
			System.out.println("Getting data done");
			FlightsModel.addFlight(id, airline_name, capacity, nbr_of_seats, nbr_of_reserved_seats, 
					source, destination, arrTime, depTime, arrival_date, departure_date);
			System.out.println("ADD IS OK");
			Alert flightAddAlert = new Alert(Alert.AlertType.INFORMATION);
			flightAddAlert.setTitle("Flight Added!");
			flightAddAlert.setContentText("New Flight has been successfly added.");
			flightAddAlert.show();
			fillTable();
		}	
	}
	
	@FXML
	public void handleClearButton() throws SQLException {
		System.out.println("CLEAR BUTTON");
		id_textField.setText(id_autoincrement + "");
		airline_name_textField.clear();
		source_textField.clear();
		destination_textField.clear();
		capacity_textField.clear();
		nbr_of_reserved_seats_textField.clear();
		nbr_of_seats_TextField.clear();
		arrival_time_textField.clear();
		departure_time_textField.clear();
//		arrival_date_textField.setValue(null);
//		departure_date_textField.setValue(null);
	}
	
	@FXML
	public void handleTableViewMouseAction() {
		Flight flight = flights_tableView.getSelectionModel().getSelectedItem();
//		System.out.println("id = " + flight.getFlight_id());
		id_textField.setText("" + flight.getFlight_id());
		airline_name_textField.setText(flight.getAirline_name());
		source_textField.setText(flight.getSource());
		destination_textField.setText(flight.getDestination());
		nbr_of_seats_TextField.setText("" + flight.getNbr_of_seats());
		nbr_of_reserved_seats_textField.setText("" + flight.getNbr_of_reserved_seats());
		capacity_textField.setText("" + flight.getCapacity());
		arrival_time_textField.setText(flight.getArrival_time());
		departure_time_textField.setText(flight.getDeparture_time());
		arrival_date_textField.setValue(flight.getArrival_date());
		departure_date_textField.setValue(flight.getDeparture_date());
	}
	
	
	
	
	
	
	

}
