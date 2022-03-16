package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Flight;

public class ClientsHistory implements Initializable{

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


	@FXML private TableView<Flight> clientFlightsTable;
    @FXML private TableColumn<Flight, String> airlineNameColumn;
    @FXML private TableColumn<Flight, Integer> flightIDColumn;
    @FXML private TableColumn<Flight, LocalDate> dateColumn;
    @FXML private TableColumn<Flight, String> sourceColumn;
    @FXML private TableColumn<Flight, String> destinationColumn;
    
    @FXML private TextField filterTextArea;
    @FXML private RadioButton child;
	@FXML private RadioButton adult;
	
	@FXML private Label firstNameLabel;
	@FXML private Label lastNameLabel;
	@FXML private Label passportNumberLabel;
	@FXML private Label mobilePhoneLabel;
	@FXML private Label emailAddressLabel;

    public ObservableList<Flight> flightsList;
    
	ToggleGroup age;
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
		fillTable();
		

		age = new ToggleGroup();
		child.setToggleGroup(age);
		adult.setToggleGroup(age);

	
		flightIDColumn.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("flight_id"));
		airlineNameColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("airlineline_name"));
		sourceColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("source"));
		destinationColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Flight, LocalDate>("date"));
		
		
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
		 ArrayList<Flight> flightsDemo = new ArrayList<>();
		 /*
			Flight f1 = new Flight(1001, "Air France", "France", "Lebanon", 450, LocalDate.of(2022, 5, 5), "10 pm", "06 pm");
			Flight f2 = new Flight(1002, "Emirates", "France", "Lebanon", 450, LocalDate.of(2022, 5, 5), "10 pm", "06 pm");
			Flight f3 = new Flight(1003, "Middle East", "France", "Lebanon", 450, LocalDate.of(2022, 5, 5), "10 pm", "06 pm");
			flightsDemo.add(f1);
			flightsDemo.add(f2);
			flightsDemo.add(f3);
			*/
			
		 //clientsList = FXCollections.observableArrayList(ClientsModel.getAllClients());
		 flightsList = FXCollections.observableArrayList(flightsDemo);
		 clientFlightsTable.setItems(flightsList);
		 clientFlightsTable.setEditable(true);
		 clientFlightsTable.setFixedCellSize(30.0);

	        FilteredList<Flight> filteredData = new FilteredList<>(flightsList, p -> true);

	        filterTextArea.textProperty().addListener((observable,oldValue, newValue)-> {
	            filteredData.setPredicate(flight -> {
	                // If filter text is empty, display all persons.
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }

	                return flight.getAirline_name().toLowerCase().contains(newValue.toLowerCase()) ||
	                		flight.getSource().toLowerCase().contains(newValue.toLowerCase()) ||
	                		flight.getDestination().toLowerCase().contains(newValue.toLowerCase());
	            });
	        });

	        clientFlightsTable.setItems(filteredData);
	    }
}





