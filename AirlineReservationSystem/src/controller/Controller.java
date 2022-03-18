package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pojo.DataBase;

public class Controller implements Initializable {

	ResultSet rs;
	DataBase DB = DataBase.getDataBase();
	@FXML
	private PasswordField password;

	@FXML
	private TextField pass_text;

	@FXML
	private TextField username;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private CheckBox pass_toggle;

	// hiddenPassword

	String p, u;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		username.setStyle("-fx-prompt-text-fill: black");
		password.setStyle("-fx-prompt-text-fill: black");
		System.out.println("1");
		pass_text.setVisible(false);
		// TODO
	}

	void loadWindow(String loc, String title) {
		try {
			URL url = new File(loc).toURI().toURL();
			Parent parent = FXMLLoader.load(url);
			// Parent parent = FXMLLoader.load(getClass().getResource(loc));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(title);
			stage.setScene(new Scene(parent));
			stage.setResizable(true);
			stage.show();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	@FXML
	void LoginMember(ActionEvent event) {
		try {
			rs = DB.SelectFun("Select * from user");
			while (rs.next()) {
				u = rs.getString(1);
				p = rs.getString(2);
			}
			String user = username.getText();
			String pass;
			if (pass_toggle.isSelected() == true) {
				pass = pass_text.getText();
			} else {
				pass = password.getText();
			}

			Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setTitle("Error");
			alert1.setHeaderText("Error");
			alert1.setContentText("!" + u + p);
			if (user.equals("") || pass.equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error");
				alert.setContentText("Enter your username and password");
				alert.showAndWait();
			}
			if (user.equals(u) && pass.equals(p)) {

				loadWindow("src/view/DashBoardAdmin.fxml", "Aurora");
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error");
				alert.setContentText("username or password invalid!!");
				alert.showAndWait();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	String password_text;

	@FXML
	void showPassword(ActionEvent event) {
		if (pass_toggle.isSelected()) {
			password.setVisible(false);
			pass_text.setVisible(true);
			pass_text.setText(password.getText());
			password.setText(pass_text.getText());
		} else {
			pass_text.setVisible(false);
			password.setVisible(true);
			password.setText(pass_text.getText());
		}

		System.out.println("Show password done!");
	}

}
