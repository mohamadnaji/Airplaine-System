package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.IDao;
import daoimpl.TicketDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Ticket;

public class TicketPopup implements Initializable{
	
	@FXML
	private Button confirmButton;
	
	@FXML
	private TableView<Ticket> ticketTable;
	@FXML
	private TableColumn<Ticket, Integer> ticketIdColumn;
	@FXML
	private TableColumn<Ticket, Integer> flightIdColumn;
	@FXML
	private TableColumn<Ticket, Integer> passengerIdColumn;
	@FXML
	private TableColumn<Ticket, String> seatNumberColumn;
	
	public static Stage stage = null;
	public static int ticketId = 0;
	
	public ObservableList<Ticket> ticketsList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ticketIdColumn.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("ticketId"));
		flightIdColumn.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("flightId"));
		passengerIdColumn.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("passengerId"));
		seatNumberColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("seatNumber"));
		
		ticketTable.setRowFactory(tv -> {
			TableRow<Ticket> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					confirmButton.setDisable(false);
				}
			});
			return row;
		});
		
		try {
			fillTicketTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ticketId = 0;
	}
	
	public void fillTicketTable() throws SQLException {
		//clientsList = FXCollections.observableArrayList(ClientsModel.getAllClients());
		IDao<Ticket, Integer> ticketDao = TicketDaoImpl.getTicketDaoImpl();
		ticketsList = FXCollections.observableArrayList(ticketDao.findAll());
		ticketTable.setItems(ticketsList);
		ticketTable.setEditable(true);
		ticketTable.setFixedCellSize(30.0);
		ticketTable.setItems(ticketsList);
	}
	
	public static int getResult() {
		//return chosen ticket id, 0 ==> no ticket chosen
		return ticketId;
	}
	
	@FXML
	void confirm(ActionEvent event) {
		//get from table the ticketId
		ticketId = ticketTable.getSelectionModel().getSelectedItem().getTicketId();
		closeStage();
	}
	
	public static void setStage(Stage stage1) {
		stage  = stage1;
	}
	
	private void closeStage() {
		if(stage!=null) {
			stage.close();
		}
	}
}
