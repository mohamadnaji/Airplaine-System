package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import daoimpl.PassportDaoImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Client;
import pojo.DataBase;

public class PassportDetails implements Initializable {
	
	DataBase DB = DataBase.getDataBase();
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private TextField passport_number_textField; 
	
	@FXML
	private TextField firstName_textField; 
	
	@FXML
	private TextField lastName_textField; 
	
	@FXML
	private TextField fatherName_textField;
	
	@FXML
	private TextField profession_textField; 
	
	@FXML
	private TextField motherName_textField; 
	
	@FXML
	private TextField placeOfBirth_textField; 
	
	@FXML
	private TextField nationality_textField; 
	
	@FXML
	private TextField issuingCode_textField; 
	
	@FXML
	private TextField typeOfPassport_textField;
	
	@FXML
	private DatePicker dateOfIssue_textField;
	
	@FXML
	private DatePicker expiryDate_textField;
	
	@FXML
	private DatePicker dateOfBirth_datePicker;
	
	@FXML
	private TextField gender_textField; 
	
	@FXML
	private Button saveButton;
	
	@FXML
	private Button updateButton;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private RadioButton male_radioButton;
	
	@FXML
	private RadioButton female_radioButton;
	
	@FXML
	private ComboBox<Integer> passenger_ids;
	

//	public List<Integer> findAllPassengerID() {
//		ResultSet rs = null;
//		List<Integer> ids = null;
//		DataBase db = DataBase.getDataBase();
//		try {
//			rs = db.SelectFun("select passenger_id from passenger");
//			ids = new ArrayList<>();
//			while(rs.next()) {
//				Integer id = Integer.valueOf(rs.getInt(1));
//				ids.add(id);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		System.out.println(ids);
//		return ids;
//	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//passenger_ids = new ComboBox<Integer>((ObservableList<Integer>) this.findAllPassengerID());
		
	}
	
	
	
	
	@FXML
	public void handleSearchButton(ActionEvent event) throws SQLException {
		
	}
	
	@FXML
	public void handleSaveButton(ActionEvent event) throws SQLException {
		
	}
	
	@FXML
	public void handleUpdateButton(ActionEvent event) throws SQLException {
		
	}
 	
//	private Boolean noEmpltyFields() {
//		//System.out.println("CHECK IF WE HAVE EMPTY FIELDS");
//		if (passport_number_textField.getText().isEmpty() || firstName_textField.getText().trim().isEmpty()
//				|| lastName_textField.getText().trim().isEmpty() || fatherName_textField.getText().trim().isEmpty()
//				|| profession_textField.getText().trim().isEmpty()
//				|| motherName_textField.getText().trim().isEmpty() 
//				|| nationality_textField.getText().trim().isEmpty()
//				|| issuingCode_textField.getText().isEmpty() || placeOfBirth_textField.getText().isEmpty()
////				|| arrival_date_textField.getValue() 
////				|| departure_date_textField.getValue() 
//		) {
////			System.out.println("EMPTY FIELDS");
//			return false;
//		}
////		System.out.println("NO EMPTY FIELDS");
//		return true;
//	}
//	
	

	

}
