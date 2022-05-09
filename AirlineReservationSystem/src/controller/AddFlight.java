package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import model.Flight;
import pojo.DataBase;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;

public class AddFlight implements Initializable {
	DataBase DB = DataBase.getDataBase();
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TextField id_textField;
	@FXML
	private TextField airline_name_textField;
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
	private TextField flight_number_textField;
	@FXML
	private Label flight_number_label;
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
	private TableColumn<Flight, String> flightNumber_col;

	@FXML
	private Button addButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button clearButton;

	@FXML
	private Button hideAllFlights;

	@FXML
	private Button getAllFlights;
	
	@FXML
	private Button searchButton;

	ObservableList<Flight> flightsList;
	
	ObservableList<String> countries1;
	
	@FXML
	private ComboBox<String> countries_comboBox1;
	
	@FXML
	private ComboBox<String> countries_comboBox2;

	String flight_number, airline_name, source, destination, arrTime, depTime;
	int id, flight_price, capacity, nbr_of_seats, nbr_of_reserved_seats;
	LocalDate arrival_date;
	LocalDate departure_date;
	int id_autoincrement = FlightsModel.getMaxID() + 1;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		countries_comboBox1.getItems().addAll(getAllCountries());
		countries_comboBox2.getItems().addAll(getAllCountries());
		try {
			fillTable();
			hideAllFlights.setVisible(false);
			flights_tableView.setVisible(false);
			id_textField.setDisable(true);
			flight_number_textField.setDisable(true);
			deleteButton.setDisable(true);
			updateButton.setDisable(true);
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
		arrivalTime_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrival_time"));
		departureTime_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("departure_time"));
		flightNumber_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("flight_number"));
		nbrOfReservedSeats_col.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("nbr_of_reserved_seats"));
		nbrOfSeats_col.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("nbr_of_seats"));
		
//		flights_tableView.setRowFactory(tv -> {
//			TableRow<Flight> row = new TableRow<>();
//			row.setOnMouseClicked(event -> {
//				if (event.getClickCount() == 1 && (!row.isEmpty())) {
//					updateButton.setDisable(false);
//					deleteButton.setDisable(false);
//				} else {
//					updateButton.setDisable(true);
//					deleteButton.setDisable(true);
//				}
//			});
//			return row;
//		});
	}

	private List<String> getAllCountries() {
		List<String> countries = Arrays.asList(Locale.getISOCountries());	
		List <String> countriesList = new ArrayList<>();
		
		for(String countryCode: countries) {
			Locale obj = new Locale("", countryCode);
//			System.out.println("Country Code = " + obj.getCountry()
//					+ ", Country name = " + obj.getDisplayCountry());
			countriesList.add(obj.getDisplayCountry());
		}
//		System.out.println(countriesList);
		
		return countriesList;
		
	}

	private ObservableList<Flight> fillTable() throws SQLException {
		// ArrayList<Flight> flightsDemo = new ArrayList<>();
//		Flight f1 = new Flight(1001, "Air France", "France", "Lebanon", 450, LocalDate.of(2022, 5, 5), "10 pm", "06 pm");
//		Flight f2 = new Flight(1002, "Emirates", "France", "Lebanon", 450, LocalDate.of(2022, 5, 5), "10 pm", "06 pm");
//		Flight f3 = new Flight(1003, "Middle East", "France", "Lebanon", 450, LocalDate.of(2022, 5, 5), "10 pm", "06 pm");
//		flightsDemo.add(f1);
//		flightsDemo.add(f2);
//		flightsDemo.add(f3);
		flightsList = FXCollections.observableArrayList(FlightsModel.getAllFlights());
		// flightsList = FXCollections.observableArrayList(flightsDemo);
		flights_tableView.setItems(flightsList);
		flights_tableView.setEditable(true);
		return flightsList;
	}

	private Boolean noEmpltyFields() {
		//System.out.println("CHECK IF WE HAVE EMPTY FIELDS");
		if (id_textField.getText().isEmpty() || airline_name_textField.getText().trim().isEmpty()
				|| countries_comboBox1.getValue() == null || countries_comboBox2.getValue() == null
				|| arrival_time_textField.getText().trim().isEmpty()
				|| departure_time_textField.getText().trim().isEmpty() 
				|| nbr_of_seats_TextField.getText().isEmpty() || nbr_of_reserved_seats_textField.getText().isEmpty()
//				|| arrival_date_textField.getValue() 
//				|| departure_date_textField.getValue() 
		) {
//			System.out.println("EMPTY FIELDS");
			return false;
		}
//		System.out.println("NO EMPTY FIELDS");
		return true;
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void handleDeleteButton(ActionEvent event) throws SQLException {

		if (id_textField.getText().isEmpty()) {
			Alert failed = new Alert(Alert.AlertType.WARNING);
			failed.setTitle("Missing Fields!");
			failed.setContentText("Please fill the id.");
			failed.show();
		} else {
			id = Integer.parseInt(id_textField.getText());
			//System.out.println("DELETE flight with ID = " + id);
			FlightsModel.deleteFlightByID(id);
			fillTable();
			//System.out.println("Delete is ok!");
			Alert flightDeleteAlert = new Alert(Alert.AlertType.INFORMATION);
			flightDeleteAlert.setContentText("This flight has been successfly deleted.");
			flightDeleteAlert.show();
			fillTable();
		}
	}
	
	@FXML
	private TextField id_textField2;
	
	@FXML
	public void handleSearchButton(ActionEvent event) throws SQLException {
		if (id_textField2.getText().isEmpty()) {
			Alert failed = new Alert(Alert.AlertType.WARNING);
			failed.setTitle("Missing Fields!");
			failed.setContentText("Please fill the id.");
			failed.show();
		} else {
			deleteButton.setDisable(false);
			updateButton.setDisable(false);
			Flight flight = null;
			id = Integer.parseInt(id_textField2.getText());
			flight = FlightsModel.searchFlight(id);
			if(flight == null) {
				Alert failed = new Alert(Alert.AlertType.WARNING);
				failed.setTitle("Flight ID not Found!");
				failed.setContentText("Please re-enter another id.");
				failed.show();
			} else {
				addButton.setDisable(true);
				//System.out.println("Flight = " + flight);
				id_textField.setText(id_textField2.getText());
				id_textField.setDisable(true);
				flight_number_label.setVisible(true);
				flight_number_textField.setVisible(true);
				flight_number_textField.setText(flight.getFlight_number());
				flight_number_textField.setDisable(true);
				airline_name_textField.setText(flight.getAirline_name());
				countries_comboBox1.setValue(flight.getSource());
				countries_comboBox2.setValue(flight.getDestination());
				nbr_of_seats_TextField.setText("" + flight.getNbr_of_seats());
				nbr_of_reserved_seats_textField.setText("" + flight.getNbr_of_reserved_seats());
				arrival_time_textField.setText(flight.getArrival_time());
				departure_time_textField.setText(flight.getDeparture_time());
				arrival_date_textField.setValue(flight.getArrival_date());
				departure_date_textField.setValue(flight.getDeparture_date());
			}
		}
	}

	// Event Listener on Button.onAction
	@FXML
	public void handleUpdateButton(ActionEvent event) throws SQLException {
		//System.out.println("UPDATE BUTTON");
		if (id_textField.getText().isEmpty()) {
			Alert failed = new Alert(Alert.AlertType.WARNING);
			failed.setTitle("Missing Fields!");
			failed.setContentText("Please fill the id.");
			failed.show();
		}
		Flight flight = new Flight();
		flight.setFlight_id(Integer.parseInt(id_textField.getText()));
		flight.setFlight_number(generateFlightNumber());
		flight.setAirline_name(airline_name_textField.getText());
		flight.setSource(countries_comboBox1.getValue());
		flight.setDestination(countries_comboBox2.getValue());
		flight.setNbr_of_seats(Integer.parseInt(nbr_of_seats_TextField.getText()));
		flight.setNbr_of_reserved_seats(Integer.parseInt(nbr_of_reserved_seats_textField.getText()));
		flight.setDeparture_time(departure_time_textField.getText());
		flight.setArrival_time(arrival_time_textField.getText());
		flight.setArrival_date(arrival_date_textField.getValue());
		flight.setDeparture_date(departure_date_textField.getValue());
		FlightsModel.updateFlightByID(flight);
		fillTable();
		Alert flightUpdateAlert = new Alert(Alert.AlertType.INFORMATION);
		flightUpdateAlert.setContentText("This flight has been successfly updated.");
		flightUpdateAlert.show();
		fillTable();
	}
	
//	 if airline name is "Air Canada" the flight number should be "ACxxx" where xxx is
//	 * a random 3 digit number between 101 and 300
	private String generateFlightNumber() {
		String flight_nbr = "";
		//split the airline name by white space
		String[] strings = (airline_name_textField.getText()).split("\\s+");
		int randomNumber = getRandomInteger(101, 300);
		flight_nbr =  flight_nbr + strings[0].charAt(0) + strings[1].charAt(0) + String.valueOf(randomNumber);
		return flight_nbr;
	} 

	private int getRandomInteger(int max, int min) {
		return ((int) (Math.random() * (max - min))) + min;
	}
	

	// Event Listener on Button.onAction
	@FXML
	public void handleAddButton(ActionEvent event) throws SQLException {
		//System.out.println("ADD BUTTON");

		if (!noEmpltyFields()) {
			System.out.println("WE HAVE EMPTY FIELDS");
			Alert failed = new Alert(Alert.AlertType.WARNING);
			failed.setTitle("Missing Fields!");
			failed.setContentText("Please fill all fields.");
			failed.show();
		}
		//System.out.println("Add new flight : ");
			id = Integer.parseInt(id_textField.getText());
			flight_number = generateFlightNumber();
			airline_name = airline_name_textField.getText();
			source = countries_comboBox1.getValue();
			destination = countries_comboBox2.getValue();
			nbr_of_seats = Integer.parseInt(nbr_of_seats_TextField.getText());
			nbr_of_reserved_seats = Integer.parseInt(nbr_of_reserved_seats_textField.getText());
			depTime = departure_time_textField.getText();
			arrTime = arrival_time_textField.getText();
			arrival_date = arrival_date_textField.getValue();
			departure_date = departure_date_textField.getValue();

			Flight flight = new Flight(id, flight_number, airline_name, nbr_of_seats, nbr_of_reserved_seats, source,
					destination, arrTime, depTime, arrival_date, departure_date);

			FlightsModel.addFlight(flight);

			Alert flightAddAlert = new Alert(Alert.AlertType.INFORMATION);
			flightAddAlert.setTitle("Flight Added!");
			flightAddAlert.setContentText("New Flight has been successfly added.");
			flightAddAlert.show();
			fillTable();
		
	}

	@FXML
	public void handleClearButton(ActionEvent event) throws SQLException {
		//System.out.println("CLEAR BUTTON");
		id_textField.setText(id_autoincrement + "");
		airline_name_textField.clear();
		nbr_of_reserved_seats_textField.clear();
		nbr_of_seats_TextField.clear();
		arrival_time_textField.clear();
		departure_time_textField.clear();
		id_textField2.clear();
		updateButton.setDisable(true);
		deleteButton.setDisable(true);
		countries_comboBox1.getSelectionModel().clearSelection();
		countries_comboBox2.getSelectionModel().clearSelection();
		id_textField.setDisable(true);
		addButton.setDisable(false);
		flight_number_textField.clear();
		departure_date_textField.setValue(null);
		arrival_date_textField.setValue(null);
	}

//	@FXML
//	public void handleTableViewMouseAction(ActionEvent event) {
//		System.out.println("mouse action");
////		Flight flight = flights_tableView.getSelectionModel().getSelectedItem();
////		id_textField.setText("" + flight.getFlight_id());
////		System.out.println("hey");
////		id_textField.setDisable(true);
////		flight_number_label.setVisible(true);
////		flight_number_textField.setVisible(true);
////		flight_number_textField.setText(flight.getFlight_number());
////		System.out.println("hey2");
////		flight_number_textField.setDisable(true);
////		airline_name_textField.setText(flight.getAirline_name());
////		//source_textField.setText(flight.getSource());
////		//destination_textField.setText(flight.getDestination());
////		nbr_of_seats_TextField.setText("" + flight.getNbr_of_seats());
////		nbr_of_reserved_seats_textField.setText("" + flight.getNbr_of_reserved_seats());
////		arrival_time_textField.setText(flight.getArrival_time());
////		System.out.println("hey2");
////		departure_time_textField.setText(flight.getDeparture_time());
////		System.out.println("123");
////		arrival_date_textField.setValue(flight.getArrival_date());
////		departure_date_textField.setValue(flight.getDeparture_date());
//	}
	

	@FXML
	public void getAllFlightsButton(ActionEvent event) {
		flights_tableView.setVisible(true);
		getAllFlights.setVisible(false);
		hideAllFlights.setVisible(true);
	}

	@FXML
	public void HideAllFlightsButton(ActionEvent event) {
		flights_tableView.setVisible(false);
		getAllFlights.setVisible(true);
		hideAllFlights.setVisible(false);
	}
}
