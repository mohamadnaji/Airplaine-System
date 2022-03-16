package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Seat;
import application.SeatFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import pojo.DataBase;

public class BookSeat implements Initializable {
	ResultSet rs, rs1, rs2, rs3;
	DataBase DB = DataBase.getDataBase();
	String TypeS;
	int price;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TextField FlightId;
	@FXML
	private Button b1, b2, b3, b4, b5;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	void back(ActionEvent event) {
		closeStage();
	}

	private void closeStage() {

	}

	@FXML
	void shape(MouseEvent event) {
		Button btn = (Button) event.getSource();
		Color color = (Color) btn.getBackground().getFills().get(0).getFill();
		String col = color.toString();

		if (col.equals("0xfc941cff"))
			TypeS = "FirstClass";
		else if (col.equals("0x2d6eb4ff"))
			TypeS = "Business";
		else if (col.equals("0x85d1aeff"))
			TypeS = "Economy";
		Seat seat = SeatFactory.createSeat(TypeS);
		TypeS = seat.getSeatType();
		btn.setStyle("-fx-background-color: #c5291b; -fx-background-radius: 100; ");
		try {
			if (TypeS.equals("FirstClass")) {
				rs3 = DB.SelectFun("select FirstClass from seats where flight_id='" + FlightId.getText() + "'");
				rs3.next();
				price = rs3.getInt(1);
			} else if (TypeS.equals("Business")) {
				rs3 = DB.SelectFun("select Business from seats where flight_id='" + FlightId.getText() + "'");
				rs3.next();
				price = rs3.getInt(1);
			} else if (TypeS.equals("Economy")) {
				rs3 = DB.SelectFun("select Economy from seats where flight_id='" + FlightId.getText() + "'");
				rs3.next();
				price = rs3.getInt(1);
			}
			// price
			Date D = new Date();
			/*
			 * DB.InsertFun("Update ticket VALUES(idT.nextval,'"+btn.getText()+"','"+price+
			 * "','"+ D+"','"+FlightId.getText()+"','"+TypeS+"','"+rs2.getString(1)+"')");
			 */
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Seat Added");
			alert.setHeaderText("Seats");
			alert.setContentText("Your Seat is reserved successfuly");
			alert.showAndWait();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	@FXML
	private void setSeats(ActionEvent e) {

	}

}
