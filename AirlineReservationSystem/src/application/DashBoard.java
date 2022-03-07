package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class DashBoard implements Initializable {

	@FXML
	private Button theatre;

	@FXML
	private Button settings;

	@FXML
	private Button logout;
	
	@FXML
	private AnchorPane holderPane;

	@FXML
	private Label adminid;

	@FXML
	private Button show;
	AnchorPane mov, theat, showp, homep, settingsp, historyp, resarvationView;

	/**
	 * Initialises the controller class.
	 */

	@Override

	public void initialize(URL url, ResourceBundle rb) {

		try {

			URL url1 = new File("src/application/AddFlight.fxml").toURI().toURL();
			homep = FXMLLoader.load(url1);

			// homep=FXMLLoader.load(getClass().getResource("src/application/Home.fxml"));
			/*
			 * mov=FXMLLoader.load(getClass().getResource("Movies.fxml"));
			 * theat=FXMLLoader.load(getClass().getResource("Theatre.fxml"));
			 * showp=FXMLLoader.load(getClass().getResource("Show.fxml"));
			 * settingsp=FXMLLoader.load(getClass().getResource("Settings.fxml"));
			 * historyp=FXMLLoader.load(getClass().getResource("History.fxml"));
			 */
		} catch (IOException ex) {

			System.out.println(ex);
		}
		setNode(homep);
	}

	private void setNode(Node node) {
		holderPane.getChildren().clear();
		holderPane.getChildren().add((Node) node);

		FadeTransition ft = new FadeTransition(Duration.millis(1500));
		ft.setNode(node);
		ft.setFromValue(0.1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}

	private void closeStage() {
		((Stage) adminid.getScene().getWindow()).close();
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
	void goToMovies(ActionEvent e) {
		setNode(mov);

	}

	@FXML
	void goToHome(ActionEvent event) {
		setNode(homep);
	}

	@FXML
	void goToTheatre(ActionEvent event) {
		setNode(theat);
	}

	@FXML
	private void goToShow(ActionEvent event) {
		setNode(showp);
	}

	@FXML
	private void showSettings(ActionEvent event) {
		setNode(settingsp);
	}

	@FXML
	private void goToLogin(ActionEvent event) {
		closeStage();
		loadWindow("Adminlogin.fxml", "Aurora");
	}

	@FXML
	private void goToHistory(ActionEvent event) {
		setNode(historyp);
	}

	@FXML
	private void goToReservation(ActionEvent event) {

		try {
			URL url1 = new File("src/application/ReservationView.fxml").toURI().toURL();
			resarvationView = FXMLLoader.load(url1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setNode(resarvationView);
	}
}
