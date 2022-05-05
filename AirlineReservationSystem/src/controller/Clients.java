package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dao.IDao;
import daoimpl.ClientDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.Client;
import model.Flight;

public class Clients implements Initializable {

	/*
	 * @FXML private AnchorPane anchorPane;
	 * 
	 * @FXML private ImageView gear1;
	 * 
	 * @FXML private ImageView gear2;
	 * 
	 * @FXML private ImageView gear3; void rotategears() { RotateTransition rg1=new
	 * RotateTransition(Duration.seconds(5),gear1); rg1.setFromAngle(0);
	 * rg1.setToAngle(360); RotateTransition rg2=new
	 * RotateTransition(Duration.seconds(5),gear2); rg2.setFromAngle(360);
	 * rg2.setToAngle(0); RotateTransition rg3=new
	 * RotateTransition(Duration.seconds(5),gear3); rg3.setFromAngle(0);
	 * rg3.setToAngle(360); ParallelTransition pt=new
	 * ParallelTransition(rg1,rg2,rg3);
	 * pt.setCycleCount(ParallelTransition.INDEFINITE); pt.play(); }
	 */


	@FXML
	private AnchorPane clientHistory;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private RadioButton child;
	@FXML
	private RadioButton adult;
	@FXML
	private TextField mobilePhone;
	@FXML
	private TextField emailAddress;

	@FXML
	private TableView<Client> clientsTable;
	@FXML
	private TableColumn<Client, String> firstNameColumn;
	@FXML
	private TableColumn<Client, String> lastNameColumn;
	@FXML
	private TableColumn<Client, String> ageColumn;
	@FXML
	private TableColumn<Client, String> mobilePhoneColumn;
	// @FXML private TableColumn<Client, String> frequentFlyerNumberColumn;
	// @FXML private TableColumn<Client, Integer> frequentFlyerPointsColumn;
	@FXML
	private TableColumn<Client, String> emailAddressColumn;
	
	
	@FXML
	private TableView<Flight> clientFlightsTable;
	@FXML
	private TableColumn<Flight, String> flightNbColumn;
	@FXML
	private TableColumn<Flight, String> sourceColumn;
	@FXML
	private TableColumn<Flight, String> destinationColumn;
	@FXML
	private TableColumn<Flight, LocalDate> departureDateColumn;
	@FXML
	private TableColumn<Flight, LocalDate> arrivalDateColumn;

	
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label mobilePhoneLabel;
	@FXML
	private Label emailAddressLabel;
	@FXML
	private RadioButton child1;
	@FXML
	private RadioButton adult1;
	@FXML
	private TextField filterTextArea1;
	
	
	@FXML
	private TextField filterTextArea;
	@FXML
	private Button deleteButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button viewFlightsHistoryButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Button addButton;

	public ObservableList<Client> clientsList;
	public ObservableList<Flight> flightsList;

	ToggleGroup ageToggleGroup,ageToggleGroup1;
	String fn, ln, mbNB, emailAd, age;
		
	Client selectedClient;
	
	int updating = 0; // 0 --> not updating(adding), 1 --> updating

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			fillClientsTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ageToggleGroup = new ToggleGroup();
		child.setToggleGroup(ageToggleGroup);
		adult.setToggleGroup(ageToggleGroup);
		
		ageToggleGroup1 = new ToggleGroup();
		child1.setToggleGroup(ageToggleGroup1);
		adult1.setToggleGroup(ageToggleGroup1);

		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("ageGroup"));
		mobilePhoneColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("phoneNumber"));
		emailAddressColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("emailAddress"));
		
		flightNbColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("flight_number"));
		sourceColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("source"));
		destinationColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
		arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<Flight, LocalDate>("arrival_date"));
		departureDateColumn.setCellValueFactory(new PropertyValueFactory<Flight, LocalDate>("departure_date"));

		clientsTable.setRowFactory(tv -> {
			TableRow<Client> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					viewFlightsHistoryButton.setDisable(false);
					updateButton.setDisable(false);
					deleteButton.setDisable(false);
				}
			});
			return row;
		});

		// rotategears();
	}

	public void fillClientsTable() throws SQLException {
		//clientsList = FXCollections.observableArrayList(ClientsModel.getAllClients());
		IDao<Client, Integer> clientDao = ClientDaoImpl.getclientDaoImpl();
		clientsList = FXCollections.observableArrayList(clientDao.findAll());
		clientsTable.setItems(clientsList);
		clientsTable.setEditable(true);
		clientsTable.setFixedCellSize(30.0);

		FilteredList<Client> filteredData = new FilteredList<>(clientsList, p -> true);

		filterTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(client -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				return client.getFirstName().toLowerCase().contains(newValue.toLowerCase())
						|| client.getLastName().toLowerCase().contains(newValue.toLowerCase());
			});
		});

		clientsTable.setItems(filteredData);
	}
	
	
	public void fillFlightsTable() throws SQLException {
		flightsList = FXCollections.observableArrayList(ClientsModel.getClientFlights(selectedClient));
		clientFlightsTable.setItems(flightsList);
		clientFlightsTable.setEditable(true);
		clientFlightsTable.setFixedCellSize(30.0);

		FilteredList<Flight> filteredData = new FilteredList<>(flightsList, p -> true);

		filterTextArea1.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(flight -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				return flight.getFlight_number().toLowerCase().contains(newValue.toLowerCase())
						|| flight.getSource().toLowerCase().contains(newValue.toLowerCase())
						|| flight.getDestination().toLowerCase().contains(newValue.toLowerCase());
			});
		});

		clientFlightsTable.setItems(filteredData);
	}

	private Boolean noEmptyFields() {
		if (
				firstName.getText().trim().isEmpty() 
				|| lastName.getText().trim().isEmpty()
				|| mobilePhone.getText().trim().isEmpty()
				|| emailAddress.getText().trim().isEmpty()
				)
			return false;
		return true;
	}

	@FXML
	void addClient(ActionEvent event) throws SQLException {
		
		if (!noEmptyFields()) {
			AlertController.alert("Error", "Empty Fields");
			return;
		}
		
		fn = firstName.getText().trim();
		ln = lastName.getText().trim();
		mbNB = mobilePhone.getText().trim();
		emailAd = emailAddress.getText().trim();
		age = ((RadioButton) ageToggleGroup.getSelectedToggle()).getText();
		
		//adding
		if(updating==0) {
			if (!ClientsModel.checkClient(fn, ln, age, emailAd, mbNB)) {
				AlertController.alert("Error", "Empty Fields");
				return;
			}

			
			//ClientsModel.addClient(fn, ln, age, emailAd, mbNB);
			Client newClient = new Client(fn,ln,mbNB,emailAd,age,1);
			IDao<Client, Integer> clientDao = ClientDaoImpl.getclientDaoImpl();
			clientDao.save(newClient);
			AlertController.alert1("New client has been successfly added.");
		}
		
		//updating
		else {
			selectedClient.setFirstName(fn);
			selectedClient.setLastName(ln);
			selectedClient.setAgeGroup(age);
			selectedClient.setEmailAddress(emailAd);
			selectedClient.setPhoneNumber(mbNB);
			ClientsModel.updateClient(selectedClient);
			
			addButton.setText("Add");
			updateButton.setDisable(true);
			deleteButton.setDisable(true);
			viewFlightsHistoryButton.setDisable(true);
			cancelButton.setVisible(false);
		}
		
		
		fillClientsTable();
		reset();
	}
	
	
	@FXML
	void updateClient(ActionEvent event) throws SQLException {
		updating = 1;
		cancelButton.setVisible(true);
		
		selectedClient = clientsTable.getSelectionModel().getSelectedItem();
		firstName.setText(selectedClient.getFirstName());
		lastName.setText(selectedClient.getLastName());
		mobilePhone.setText(selectedClient.getPhoneNumber());
		emailAddress.setText(selectedClient.getEmailAddress());
		if(selectedClient.getAgeGroup().equals("Adult"))
			ageToggleGroup.selectToggle(adult);
		else
			ageToggleGroup.selectToggle(child);
		
		updateButton.setDisable(true);
		addButton.setText("Update");
	}
	
	@FXML
	void deleteClient(ActionEvent event) throws SQLException {
		selectedClient = clientsTable.getSelectionModel().getSelectedItem();
		//ClientsModel.deleteClient(selectedClient);
		IDao<Client, Integer> clientDao = ClientDaoImpl.getclientDaoImpl();
		clientDao.delete(selectedClient.getClientID());
		fillClientsTable();
	}
	
	@FXML
	void cancelUpdate(ActionEvent event) throws SQLException {
		updating = 0;
		
		addButton.setText("Add");
		updateButton.setDisable(true);
		deleteButton.setDisable(true);
		viewFlightsHistoryButton.setDisable(true);
		cancelButton.setVisible(false);
		reset();
	}
	
	@FXML
	void viewFlightsHistory(ActionEvent event) throws SQLException {
		selectedClient = clientsTable.getSelectionModel().getSelectedItem();
		clientHistory.setVisible(true);
		firstNameLabel.setText(selectedClient.getFirstName());
		lastNameLabel.setText(selectedClient.getLastName());
		mobilePhoneLabel.setText(selectedClient.getPhoneNumber());
		emailAddressLabel.setText(selectedClient.getEmailAddress());
		if(selectedClient.getAgeGroup().equals("Adult"))
			adult1.setSelected(true);
		else
			child1.setSelected(true);
		
		fillFlightsTable();
	}
	
	@FXML
    void exitHistory(MouseEvent mouseEvent) {
		clientHistory.setVisible(false);
    }
	
	public void reset() {
		firstName.setText("");
		lastName.setText("");
		mobilePhone.setText("");
		emailAddress.setText("");
		ageToggleGroup.getSelectedToggle().setSelected(false);
	}

}
