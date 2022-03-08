package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
//import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

//import javax.lang.model.element.ExecutableElement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
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
	private TextField name_textField;
	@FXML
	private TextField source_textField;
	@FXML
	private TextField destination_textField;
	@FXML
	private TextField arrivalTime_textField;
	@FXML
	private TextField departureTime_textField;
	@FXML
	private TextField price_textField;
	@FXML
	private DatePicker date_textField;
	@FXML
	private TableView<Flight> flights_tableView;
	@FXML
	private TableColumn<Flight, Integer> id_col;
	@FXML
	private TableColumn<Flight, String> name_col;
	@FXML
	private TableColumn<Flight, String> source_col;
	@FXML
	private TableColumn<Flight, String> destination_col;
	@FXML
	private TableColumn<Flight, Date> date_col;
	@FXML
	private TableColumn<Flight, String> arrivalTime_col;
	@FXML
	private TableColumn<Flight, String> departureTime_col;
	@FXML
	private TableColumn<Flight, Integer> price_col;
	@FXML
	private Button addButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button deleteButton;
	
	PreparedStatement preparedStatement = null;
	ResultSet resultSet;
	Connection connection = DataBase.ConnectDb();
	ObservableList<Flight> flightsList;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			fillTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id_col.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("flight_id"));
		name_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("flight_name"));
		source_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("source"));
		destination_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
		date_col.setCellValueFactory(new PropertyValueFactory<Flight, Date>("date"));
		arrivalTime_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrival_time"));
		departureTime_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("departure_time"));
		price_col.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("flight_price"));
		System.out.println("6");
		
		
	}

	private ObservableList<Flight> fillTable() throws SQLException {
		ArrayList<Flight> flightsDemo = new ArrayList<>();
		Flight f1 = new Flight(1001, "Air France", "France", "Lebanon", 450, LocalDate.of(2022, 5, 5), "10 pm", "06 pm");
		Flight f2 = new Flight(1002, "Emirates", "France", "Lebanon", 450, LocalDate.of(2022, 5, 5), "10 pm", "06 pm");
		Flight f3 = new Flight(1003, "Middle East", "France", "Lebanon", 450, LocalDate.of(2022, 5, 5), "10 pm", "06 pm");
		flightsDemo.add(f1);
		flightsDemo.add(f2);
		flightsDemo.add(f3);
		
		flightsList = FXCollections.observableArrayList(FlightsModel.getAllFlights());
		//flightsList = FXCollections.observableArrayList(flightsDemo);
		flights_tableView.setItems(flightsList);
		flights_tableView.setEditable(true);
		return flightsList;
	}

	
	private Boolean noEmpltyFields() {
		if (id_textField.getText().isEmpty() || name_textField.getText().isEmpty()
				|| source_textField.getText().trim().isEmpty()
				|| destination_textField.getText().trim().isEmpty()
				|| price_textField.getText().trim().isEmpty()
				|| arrivalTime_textField.getText().isEmpty()
				|| departureTime_textField.getText().isEmpty()) {
			return false;
		}
		return true;
	}
	
	String flight_name, source, dest, arrTime, depTime;
	int id, flight_price;
	LocalDate date;

	// Event Listener on Button.onAction
	@FXML
	public void handleAddButton() throws SQLException  {
		System.out.println("ADD BUTTON");
		
		if(!noEmpltyFields()) {
			Alert failed = new Alert(Alert.AlertType.WARNING);
			failed.setTitle("Missing Fields!");
			failed.setContentText("Please fill all fields.");
			failed.show();
		}
		
		else {
			id = Integer.parseInt(id_textField.getText());
			flight_name = name_textField.getText();
			flight_price = Integer.parseInt(price_textField.getText());
			source = source_textField.getText();
			dest = destination_textField.getText();
			date = date_textField.getValue();
			arrTime = arrivalTime_textField.getText();
			dest = destination_textField.getText();
			 
			        	
			        	
			FlightsModel.addFlight(id, flight_name, source, dest, flight_price, date, arrTime, depTime);
			
			Alert flightAddAlert = new Alert(Alert.AlertType.INFORMATION);
			flightAddAlert.setTitle("Flight Added!");
			flightAddAlert.setContentText("New Flight has been successfly added.");
			flightAddAlert.show();
			fillTable();
		}
		
//		try {
//			
//			String query = "insert into flight value("
//							+id_textField.getText()+",'"
//							+name_textField.getText()+"','"
//							+source_textField.getText()+"',"
//							+destination_textField.getText()+"','"
//							+ arrivalTime_textField.getText() + "','"
//							+ departureTime_textField.getText() + "','"
//							+ date_textField.getValue() + "',"
//							+ price_textField.getText()
//							+")";
//			preparedStatement = connection.prepareStatement(query);
//			resultSet = preparedStatement.executeQuery();
//			showFlights();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void handleDeleteButton() {
		System.out.println("DELETE BUTTON");
//		try {
//			String query = "DELETE FROM flight where id =" + id_textField.getText() + "";
//			preparedStatement = connection.prepareStatement(query);
//			resultSet = preparedStatement.executeQuery();
//		} catch ( Exception e) {
//			e.printStackTrace();
//		}
	
	}
	// Event Listener on Button.onAction
	@FXML
	public void handleUpdateButton() {
		System.out.println("UPDATE BUTTON");
//		try {
//			String query = "update flight set name = ' " + name_textField.getText() + "', source = '" + source_textField.getText() + 
//					"', destination = '" + destination_textField.getText() + ", date = '" + date_textField.getValue() + 
//					"', arrival_time = '" + arrivalTime_textField.getText() + "', departure_time = '" + departureTime_textField.getText() + 
//					"', flight_price = " + price_textField.getText() + ")";
//			preparedStatement = connection.prepareStatement(query);
//			resultSet = preparedStatement.executeQuery();
//			showFlights();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
	}
	
//	public void Random() {
//		Random random = new Random();
//		id_textField.setText(""+random.nextInt(1000+1));
//	}
//	
//	public ObservableList<Flight> getFlightsList() {
//		
//		Connection connection = DataBase.ConnectDb();
//		
//		ObservableList<Flight> flightsList = FXCollections.observableArrayList();
//		String sql = "SELECT * FROM flight";
//		try {
////			preparedStatement = connection.prepareStatement(sql);
////			st = connection.createStatement();
////			rs = st.executeQuery(sql);
//			preparedStatement = connection.prepareStatement(sql);
//			resultSet = preparedStatement.executeQuery();
//			System.out.println("1");
////			resultSet = preparedStatement.executeQuery();
//			System.out.println("2");
//			while(resultSet.next()) {
////				flight = new Flight(resultSet.getInt("flight_id"), 
////						resultSet.getString("flight_name"), 
////						resultSet.getString("flight_price"),
////						resultSet.getDate("date"),
////						resultSet.getString("source"),
////						resultSet.getString("destination"),
////						resultSet.getString("arrival_time"),
////						resultSet.getString(("departure_time")));
//				Flight flight = new Flight();
//				flight.setFlight_id(resultSet.getInt("flight_id"));
//				flight.setFlight_name(resultSet.getString("flight_name"));
//				flight.setDate(resultSet.getDate("date"));
//				flight.setSource(resultSet.getString("source"));
//				flight.setDestination(resultSet.getString("destination"));
//				flight.setFlight_price(resultSet.getInt("flight_price"));
//				System.out.println("33");
////				flights_tableView.setItems(flightsList);
//			
//				flightsList.add(flight);
//				System.out.println("3");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("4");
//		return flightsList;
//	}
//	
	
	
//	public void showFlights () throws SQLException {
//		flightsList = fillTable();
//		System.out.println("5");
//		id_col.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("flight_id"));
//		name_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("flight_name"));
//		source_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("source"));
//		destination_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
//		date_col.setCellValueFactory(new PropertyValueFactory<Flight, Date>("date"));
//		arrivalTime_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrival_time"));
//		departureTime_col.setCellValueFactory(new PropertyValueFactory<Flight, String>("departure_time"));
//		price_col.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("flight_price"));
//		System.out.println("6");
//		flights_tableView.setItems(flightsList);
//		System.out.println("7");
//	}
	

}
