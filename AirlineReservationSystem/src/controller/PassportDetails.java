package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dao.IDao;
import daoimpl.ClientDaoImpl;
import daoimpl.PassportDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.Client;
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

	@FXML
	private TextField passportNumberSearch;
	
	String father_name;
	String mother_name;
	String place_of_birth;
	LocalDate date_of_birth;
	LocalDate issue_date, expiry_date;
	String type;
	String issuing_state_code;
	String profession;
	String passport_number;
	String nationality;
	String gender;

	// create a toggle group
	ToggleGroup tg = new ToggleGroup();

	public List<Integer> findAllPassengerID() { // DONE
		List<Integer> ids = null;

		IDao<Client, Integer> clientDao = ClientDaoImpl.getclientDaoImpl();
		List<Client> clients = clientDao.findAll();
		ids = new ArrayList<>();
		for (int i = 0; i < clients.size(); i++) {
			ids.add(clients.get(i).getClientID());
		}
		//System.out.println(ids);
		return ids;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		passenger_ids.getItems().addAll(findAllPassengerID());
		reset();
	}

	

	@FXML
	public void handleSearchButton(ActionEvent event) throws SQLException {
		if (passportNumberSearch.getText().isEmpty()) {
			Alert failed = new Alert(Alert.AlertType.WARNING);
			failed.setTitle("Missing Fields!");
			failed.setContentText("Please fill the id.");
			failed.show();
		} else {
			passport_number = passportNumberSearch.getText();
			Passport passport = null;
			IDao<Passport, String> passportDao = PassportDaoImpl.getPassportDaoImpl();
			passport = passportDao.findById(passport_number);
			if (passport != null) {
				passenger_ids.setValue(passport.getPassenger_id());
				passport_number_textField.setText(passport.getPassport_number());
				//System.out.println(passport.getPassport_number());
				fatherName_textField.setText(passport.getFather_name());
				motherName_textField.setText(passport.getMother_name());
				nationality_textField.setText(passport.getNationality());
				profession_textField.setText(passport.getProfession());
				placeOfBirth_textField.setText(passport.getPlace_of_birth());
				dateOfBirth_datePicker.setValue(passport.getDate_of_birth());
				// gender.setText(passport.getMother_name());
				dateOfIssue_textField.setValue(passport.getIssue_date());
				expiryDate_textField.setValue(passport.getExpiry_date());
				typeOfPassport_textField.setText(passport.getType());
				issuingCode_textField.setText(passport.getIssuing_state_code());
				if (passport.getGender().compareToIgnoreCase("m") == 0) {
					male_radioButton.setSelected(true);
				} else {
					female_radioButton.setSelected(true);
				}
				updateButton.setDisable(false);
				saveButton.setDisable(true);
			}

		}
	}

	@FXML
	public void handleSaveButton(ActionEvent event) throws SQLException {

		if (noEmpltyFields()) {
			Passport newPassport = new Passport();
			newPassport.setPassport_number(passport_number_textField.getText());
			newPassport.setFather_name(fatherName_textField.getText());
			newPassport.setMother_name(motherName_textField.getText());
			newPassport.setPlace_of_birth(placeOfBirth_textField.getText());
			newPassport.setDate_of_birth(dateOfBirth_datePicker.getValue());
			newPassport.setIssue_date(dateOfIssue_textField.getValue());
			newPassport.setExpiry_date(expiryDate_textField.getValue());
			newPassport.setType(typeOfPassport_textField.getText());
			newPassport.setIssuing_state_code(issuingCode_textField.getText());
			newPassport.setProfession(profession_textField.getText());
			newPassport.setPassenger_id(passenger_ids.getValue());
			newPassport.setNationality(nationality_textField.getText());
			if(tg.getSelectedToggle().toString().compareToIgnoreCase("male") == 0)
				newPassport.setGender("m");
			else
				newPassport.setGender("f");
			try {
				IDao<Passport, String> passportDao = PassportDaoImpl.getPassportDaoImpl();
				passportDao.save(newPassport);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			AlertController.alert1("Saved successfully");
			reset();
		} else {
			AlertController.alert("Please Fill The Form to save", "Mandatory fields");
		}
	}

	@FXML
	public void handleUpdateButton(ActionEvent event) throws SQLException {

		if (noEmpltyFields()) {

			Passport newPassport = new Passport();
			newPassport.setPassport_number(passport_number_textField.getText());
			newPassport.setFather_name(fatherName_textField.getText());
			newPassport.setMother_name(motherName_textField.getText());
			newPassport.setPlace_of_birth(placeOfBirth_textField.getText());
			newPassport.setDate_of_birth(dateOfBirth_datePicker.getValue());
			newPassport.setIssue_date(dateOfIssue_textField.getValue());
			newPassport.setExpiry_date(expiryDate_textField.getValue());
			newPassport.setType(typeOfPassport_textField.getText());
			newPassport.setIssuing_state_code(issuingCode_textField.getText());
			newPassport.setProfession(profession_textField.getText());
			newPassport.setPassenger_id(passenger_ids.getValue());
			newPassport.setNationality(nationality_textField.getText());
			if(tg.getSelectedToggle().toString().compareToIgnoreCase("male") == 0)
				newPassport.setGender("m");
			else
				newPassport.setGender("f");
			try {
				IDao<Passport, String> passportDao = PassportDaoImpl.getPassportDaoImpl();
				passportDao.update(newPassport, newPassport.getPassport_number());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			AlertController.alert1("Saved successfully");
			reset();
			
		} else {
			AlertController.alert("Please Fill The Form to save", "Mandatory fields");
		}

	}

	private Boolean noEmpltyFields() {
		// System.out.println("CHECK IF WE HAVE EMPTY FIELDS");
		if (passport_number_textField.getText().isEmpty() || fatherName_textField.getText().trim().isEmpty()
				|| profession_textField.getText().trim().isEmpty() || motherName_textField.getText().trim().isEmpty()
				|| nationality_textField.getText().trim().isEmpty() || issuingCode_textField.getText().isEmpty()
				|| placeOfBirth_textField.getText().isEmpty() || passenger_ids.getValue() == null
				|| !tg.getSelectedToggle().isSelected()
				|| dateOfIssue_textField.getValue() == null
				|| expiryDate_textField.getValue() == null
				|| dateOfBirth_datePicker.getValue() == null
		) {
//			System.out.println("EMPTY FIELDS");
			return false;
		}
//		System.out.println("NO EMPTY FIELDS");
		return true;
	}

	public void reset() {
		passport_number_textField.clear();
		fatherName_textField.setText("");
		motherName_textField.setText("");
		nationality_textField.setText("");
		profession_textField.setText("");
		placeOfBirth_textField.setText("");
		dateOfBirth_datePicker.setValue(null);
		// gender.setText(passport.getMother_name());
		dateOfIssue_textField.setValue(null);
		expiryDate_textField.setValue(null);
		typeOfPassport_textField.setText("");
		issuingCode_textField.setText("");
		male_radioButton.setSelected(false);
		female_radioButton.setSelected(false);
		male_radioButton.setToggleGroup(tg);
		female_radioButton.setToggleGroup(tg);

		updateButton.setDisable(true);
		saveButton.setDisable(false);

		//passenger_ids.getItems().addAll(findAllPassengerID());
		passenger_ids.setValue(null);
	}
}
