package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao.IClientDao;
import dao.IDao;
import dao.IFlightDao;
import dao.ITicketDao;
import daoimpl.ClientDaoImpl;
import daoimpl.FlightDaoImpl;
import daoimpl.TicketDaoImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import model.Client;
import model.Flight;
import model.Ticket;

public class ReservationController implements Initializable {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private ComboBox<String> flightList;
	private ObservableList<String> flightListData;

	@FXML
	private ComboBox<Client> passengerList;
	private ObservableList<Client> passengerListData;

	private ObservableList<Ticket> ticketListData;

	@FXML
	private Button confirmButton;

	@FXML
	private Button cancelButton;

	@FXML
	private Button deleteTicketButton;

	@FXML
	private Button updateTicketButton;

	@FXML
	private TextField flightPrice;

	@FXML
	private TextField ticketId;

	@FXML
	private TextField numberOfBugs;

	@FXML
	private TextField filteredTicketId;

	@FXML
	private TableView<Ticket> reservationsTable;

	@FXML
	private TableColumn<Ticket, String> flightColumn;

	@FXML
	private TableColumn<Ticket, String> flightPriceColumn;

	@FXML
	private TableColumn<Ticket, String> numOfBugsColumn;

	@FXML
	private TableColumn<Ticket, String> ticketColumn;

	@FXML
	private TableColumn<Ticket, String> passengerIdColumn;

	Ticket selectedTicket;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			loadPassengerList(null);
			loadFlightList(null);
			loadMaxTicketId(null);

			fillTicketsTable();
			flightColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("flightId"));
			flightPriceColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("flightPriceId"));
			numOfBugsColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("numberOfBugs"));
			ticketColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("ticketId"));
			passengerIdColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("passengerId"));

			updateTicketButton.setDisable(true);
			deleteTicketButton.setDisable(true);
			reservationsTable.setRowFactory(tv -> {
				TableRow<Ticket> row = new TableRow<>();
				row.setOnMouseClicked(event -> {
					if (event.getClickCount() == 1 && (!row.isEmpty())) {
						updateTicketButton.setDisable(false);
						deleteTicketButton.setDisable(false);
					} else {
						updateTicketButton.setDisable(true);
						deleteTicketButton.setDisable(true);
					}
				});
				return row;
			});

			flightPrice.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, 
			        String newValue) {
			        if (newValue != null && !newValue.matches("\\d*")) {
			            flightPrice.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			numberOfBugs.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, 
			        String newValue) {
			        if (newValue != null && !newValue.matches("\\d*")) {
			        	numberOfBugs.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fillTicketsTable() throws SQLException {
		// clientsList =
		// FXCollections.observableArrayList(ClientsModel.getAllClients());
		IDao<Ticket, Integer> ticketDao = TicketDaoImpl.getTicketDaoImpl();
		ticketListData = FXCollections.observableArrayList(ticketDao.findAll());
		reservationsTable.setItems(ticketListData);
		reservationsTable.setEditable(true);
		reservationsTable.setFixedCellSize(30.0);

		FilteredList<Ticket> filteredData = new FilteredList<>(ticketListData, p -> true);

		filteredTicketId.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(ticket -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String ticketValue = ticket.getTicketId().toString();
				return ticketValue.toLowerCase().contains(newValue.toLowerCase());
			});
		});

		reservationsTable.setItems(filteredData);
	}

	public void loadPassengerList(ActionEvent event) throws SQLException {
		try {

			passengerListData = FXCollections.observableArrayList();
			passengerListData.clear();

			IClientDao clientDao = ClientDaoImpl.getclientDaoImpl();

			passengerListData.addAll(clientDao.findAll());

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

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void loadFlightList(ActionEvent event) throws SQLException {
		try {

			flightListData = FXCollections.observableArrayList();
			flightListData.clear();
			IFlightDao flightDao = FlightDaoImpl.getFlightDaoImpl();
			List<Flight> flights = flightDao.findAll();
			for (Flight flight : flights) {
				Integer flightId = flight.getFlight_id();
				flightListData.add(flightId.toString());
				// System.out.println(t);
			}
			flightList.setItems(flightListData);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void loadMaxTicketId(ActionEvent event) throws SQLException {

		ITicketDao ticketDao = TicketDaoImpl.getTicketDaoImpl();
		Integer maxId = ticketDao.getMaxTicketId() + 1;
		this.ticketId.setText(maxId.toString());
	}

	@FXML
	private void deleteTicket(ActionEvent event) {
		selectedTicket = reservationsTable.getSelectionModel().getSelectedItem();
		// ClientsModel.deleteClient(selectedClient);
		ITicketDao ticketDao = TicketDaoImpl.getTicketDaoImpl();
		ticketDao.delete(selectedTicket.getTicketId());
		ticketDao.deleteSeat(selectedTicket.getTicketId());
		try {
			fillTicketsTable();
			reset(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void updateTicket(ActionEvent event) {

		selectedTicket = reservationsTable.getSelectionModel().getSelectedItem();
		IClientDao cliDao = ClientDaoImpl.getclientDaoImpl();
		Client cli = cliDao.findById(selectedTicket.getPassengerId());
		selectedTicket = reservationsTable.getSelectionModel().getSelectedItem();
		flightPrice.setText(selectedTicket.getFlightPriceId().toString());
		ticketId.setText(selectedTicket.getTicketId().toString());
		numberOfBugs.setText(selectedTicket.getNumberOfBugs().toString());
		passengerList.setValue(cli);
		flightList.setValue(selectedTicket.getFlightId().toString());
		updateTicketButton.setDisable(true);

	}

	@FXML
	private void saveNewTicket(ActionEvent event) {
		try {
			if (!ticketId.getText().isEmpty() && flightPrice.getText() != null && !flightPrice.getText().isEmpty()
					&& !flightList.getValue().isEmpty() && passengerList.getValue() != null
					&& numberOfBugs.getText() != null && !numberOfBugs.getText().isEmpty()) {

				Ticket newTicket = new Ticket();
				newTicket.setTicketId(Integer.parseInt(ticketId.getText()));
				newTicket.setFlightId(Integer.parseInt(flightList.getValue()));
				newTicket.setPassengerId(passengerList.getValue().getClientID());
				newTicket.setFlightPriceId(Integer.parseInt(flightPrice.getText()));
				newTicket.setNumberOfBugs(Integer.parseInt(numberOfBugs.getText()));

				IDao<Ticket, Integer> ticketDao = TicketDaoImpl.getTicketDaoImpl();
				Ticket tic = ticketDao.findById(newTicket.getTicketId());
				if (tic != null) {
					ticketDao.update(newTicket, newTicket.getTicketId());
				} else {
					ticketDao.save(newTicket);
				}
				AlertController.alert1("Saved successfully");

				reset(null);
			} else {
//				error.setText("Fill The Form and Check Box to Insert !!");
				AlertController.alert("Please Fill The Form to save", "Mandatory fields");
			}

			fillTicketsTable();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void reset(ActionEvent event) throws SQLException {
		loadPassengerList(null);
		loadFlightList(null);
		loadMaxTicketId(null);
		numberOfBugs.setText(null);
		flightPrice.setText(null);

		updateTicketButton.setDisable(true);
		deleteTicketButton.setDisable(true);
	}
	

}
