package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Passport;
import pojo.DataBase;

public class PassportDetails implements Initializable {
	
	DataBase DB = DataBase.getDataBase();
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private TextField passport_number_textField; 
	
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
	
	
	

	public List<Integer> findAllPassengerID() { // DONE
		ResultSet rs = null;
		List<Integer> ids = null;
		DataBase db = DataBase.getDataBase();
		try {
			rs = db.SelectFun("select passenger_id from passenger");
			ids = new ArrayList<>();
			while(rs.next()) {
				Integer id = Integer.valueOf(rs.getInt(1));
				ids.add(id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(ids);
		return ids;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		passenger_ids = new ComboBox<Integer>((ObservableList<Integer>) this.findAllPassengerID());
		System.out.println(findAllPassengerID());
		passenger_ids.getItems().addAll(findAllPassengerID());
		
		
	}
	
	
	
	String passport_number;
	String father_name;
	String mother_name;
	String place_of_birth;
	LocalDate date_of_birth;
	LocalDate issue_date, expiry_date;
	String type;
	String issuing_state_code;
	String profession;
	int passenger_id;
	String nationality;
	String gender;
	
	@FXML
	public void handleSearchButton(ActionEvent event) throws SQLException {
		if(passenger_ids.getSelectionModel().isEmpty() == true) {
			Alert failed = new Alert(Alert.AlertType.WARNING);
			failed.setTitle("Missing Fields!");
			failed.setContentText("Please fill the id.");
			failed.show();
		} else {
			Passport passport;
			passenger_id = passenger_ids.getValue();
			passport = PassportModel.searchPassport(passenger_id);
			passport_number_textField.setText(passport.getPassport_number());
			System.out.println(passport.getPassport_number());
			fatherName_textField.setText(passport.getFather_name());
			motherName_textField.setText(passport.getMother_name());
			nationality_textField.setText(passport.getNationality());
			profession_textField.setText(passport.getProfession());
			placeOfBirth_textField.setText(passport.getPlace_of_birth());
			dateOfBirth_datePicker.setValue(passport.getDate_of_birth());
			//gender.setText(passport.getMother_name());
			dateOfIssue_textField.setValue(passport.getIssue_date());
			expiryDate_textField.setValue(passport.getExpiry_date());
			typeOfPassport_textField.setText(passport.getType());
			issuingCode_textField.setText(passport.getIssuing_state_code());
			
		}
	}
	
	@FXML
	public void handleSaveButton(ActionEvent event) throws SQLException {
		
	}
	
	@FXML
	public void handleUpdateButton(ActionEvent event) throws SQLException {
		
	}
 	
	private Boolean noEmpltyFields() {
		//System.out.println("CHECK IF WE HAVE EMPTY FIELDS");
		if (passport_number_textField.getText().isEmpty() || fatherName_textField.getText().trim().isEmpty()
				|| profession_textField.getText().trim().isEmpty()
				|| motherName_textField.getText().trim().isEmpty() 
				|| nationality_textField.getText().trim().isEmpty()
				|| issuingCode_textField.getText().isEmpty() || placeOfBirth_textField.getText().isEmpty()
//				|| arrival_date_textField.getValue() 
//				|| departure_date_textField.getValue() 
		) {
//			System.out.println("EMPTY FIELDS");
			return false;
		}
//		System.out.println("NO EMPTY FIELDS");
		return true;
	}
	
	

	

}
