package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.ClientsModel;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

public class Clients implements Initializable{

    /*@FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView gear1;

    @FXML
    private ImageView gear2;

    @FXML
    private ImageView gear3;
    void rotategears()
    {
       RotateTransition rg1=new RotateTransition(Duration.seconds(5),gear1);
        rg1.setFromAngle(0);
        rg1.setToAngle(360);
        RotateTransition rg2=new RotateTransition(Duration.seconds(5),gear2);
        rg2.setFromAngle(360);
        rg2.setToAngle(0);
        RotateTransition rg3=new RotateTransition(Duration.seconds(5),gear3);
        rg3.setFromAngle(0);
        rg3.setToAngle(360);
        ParallelTransition pt=new ParallelTransition(rg1,rg2,rg3);
        pt.setCycleCount(ParallelTransition.INDEFINITE);
        pt.play();
    }*/
    
	@FXML private TextField firstName;
	@FXML private TextField lastName;
	@FXML private RadioButton male;
	@FXML private RadioButton female;
	@FXML private ChoiceBox nationality;
	@FXML private DatePicker birthDate;
	@FXML private TextField passportNumber;
	@FXML private TextField mobilePhone;
	@FXML private TextField emailAddress;

	@FXML private TableView<Client> clientsTable;
    @FXML private TableColumn<Client, String> firstNameColumn;
    @FXML private TableColumn<Client, String> lastNameColumn;
    @FXML private TableColumn<Client, String> passportNumberColumn;
    @FXML private TableColumn<Client, String> nationalityColumn;
    @FXML private TableColumn<Client, LocalDate> birthDateColumn;
    @FXML private TableColumn<Client, String> genderColumn;
    @FXML private TableColumn<Client, String> mobilePhoneColumn;
    @FXML private TableColumn<Client, String> frequentFlyerNumberColumn;
    @FXML private TableColumn<Client, Integer> frequentFlyerPointsColumn;
    @FXML private TableColumn<Client, String> emailAddressColumn;
    
    @FXML private TextField filterTextArea;
    @FXML private Button deleteButton;
    @FXML private Button updateButton;
    @FXML private Button viewFlightsHistoryButton;

    public ObservableList<Client> clientsList;
    
	ToggleGroup gender;
	String fn,ln,pn,mbNB,emailAd,ad,g,nat;
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
		fillTable();
		

		gender = new ToggleGroup();
		male.setToggleGroup(gender);
		female.setToggleGroup(gender);
		
		String[] countryCodes = java.util.Locale.getISOCountries();
		  
		for (String countryCode : countryCodes) {
		    Locale locale = new Locale("", countryCode);
		    String name = locale.getDisplayCountry();
		    if(name == "Israel") continue;
		    nationality.getItems().add(name);
		}
		
		
		
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
		passportNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("passportNumber"));
		nationalityColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("nationality"));
		birthDateColumn.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("birthDate"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("gender"));
		mobilePhoneColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("phoneNumber"));
		frequentFlyerNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("frequentFlyerNumber"));
		frequentFlyerPointsColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("frequentFlyerPoints"));
		emailAddressColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("emailAddress"));
		
		/*
		clientsTable.setRowFactory( tv -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    deleteButton.setDisable(false);
                }
            });
            return row ;
        });
		*/
		
        //rotategears();
    }    
    
	
	 public void fillTable() {
		 ArrayList<Client> clientsDemo = new ArrayList<>();
			Client c1 = new Client("Fatima","Kaouk","LH84739278","Lebanon","Female","76345787","fatimakaouk@gmail.com","54745689",1500,LocalDate.of(2000, 6, 9));
			Client c2 = new Client("Mariam","Hussein","LH84739278","Lebanon","Female","76345787","fatimakaouk@gmail.com","54745689",1500,LocalDate.of(2000, 6, 9));
			Client c3 = new Client("Nour","Ali","LH84739278","Lebanon","Female","76345787","fatimakaouk@gmail.com","54745689",1500,LocalDate.of(2000, 6, 9));
			clientsDemo.add(c1);clientsDemo.add(c2);clientsDemo.add(c3);
			
		 //clientsList = FXCollections.observableArrayList(ClientsModel.getAllClients());
		 clientsList = FXCollections.observableArrayList(clientsDemo);
		 clientsTable.setItems(clientsList);
		 clientsTable.setEditable(true);
		 clientsTable.setFixedCellSize(30.0);

	        FilteredList<Client> filteredData = new FilteredList<>(clientsList, p -> true);

	        filterTextArea.textProperty().addListener((observable,oldValue, newValue)-> {
	            filteredData.setPredicate(client -> {
	                // If filter text is empty, display all persons.
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }

	                return client.getFirstName().toLowerCase().contains(newValue.toLowerCase()) ||
	                		client.getLastName().toLowerCase().contains(newValue.toLowerCase());
	            });
	        });

	        clientsTable.setItems(filteredData);
	    }
	
	 
	private Boolean noEmptyFields() {
		if( firstName.getText().trim().isEmpty() || lastName.getText().trim().isEmpty() ||
    			passportNumber.getText().trim().isEmpty() || mobilePhone.getText().trim().isEmpty() ||
    			emailAddress.getText().trim().isEmpty() || (gender.getSelectedToggle()==null) ||
    			nationality.getSelectionModel().isEmpty() || birthDate.getValue()==null )
			return false;
		return true;
	}
	
    @FXML
    void addClient(ActionEvent event) {
    	
    	if( !noEmptyFields() ) {
    		Alert failed = new Alert(Alert.AlertType.WARNING);
    		failed.setTitle("Missing Fields");
    		failed.setContentText("Please fill all fields.");
    		failed.show();
    	}
    	
    	else {
    		
    		
    		fn = firstName.getText().trim();
    		ln = lastName.getText().trim();
    		pn = passportNumber.getText().trim();
    		mbNB = mobilePhone.getText().trim();
    		emailAd = emailAddress.getText().trim();
    		nat = nationality.getSelectionModel().toString();
    		LocalDate bd = birthDate.getValue();
    		g = ((RadioButton)gender.getSelectedToggle()).getText();
    		
    		if(!ClientsModel.checkClient(fn,ln,pn)) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText("Error");
    			alert.setContentText("Client already added.");
    			alert.showAndWait();
    			return;
    		}
    		
    		ClientsModel.addClient(fn,ln,pn,bd,g,nat,mbNB,emailAd);
    		
    		Alert clientAdded = new Alert(Alert.AlertType.INFORMATION);
    		clientAdded.setTitle("Client Added");
    		clientAdded.setContentText("New client has been successfly added.");
    		clientAdded.show();
    		fillTable();
    	}
    }

}





