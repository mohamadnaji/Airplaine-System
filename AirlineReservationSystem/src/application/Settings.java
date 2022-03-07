package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXPasswordField;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Settings implements Initializable {
	ResultSet rs;
	   DataBase DB=new DataBase();
    @FXML
    private ImageView img3;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img1;
    @FXML
    private JFXPasswordField new_password;
    @FXML
    private JFXPasswordField current_password;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
    	rotategears();
   }
    void rotategears()
    {
       RotateTransition rg1=new RotateTransition(Duration.seconds(5),img1);
        rg1.setFromAngle(0);
        rg1.setToAngle(360);
        RotateTransition rg2=new RotateTransition(Duration.seconds(5),img2);
        rg2.setFromAngle(360);
        rg2.setToAngle(0);
        RotateTransition rg3=new RotateTransition(Duration.seconds(5),img3);
        rg3.setFromAngle(0);
        rg3.setToAngle(360);
        ParallelTransition pt=new ParallelTransition(rg1,rg2,rg3);
        pt.setCycleCount(ParallelTransition.INDEFINITE);
        pt.play();
    }
    @FXML
    private void confirm(ActionEvent e)
    {
      String npass=new_password.getText();
      String opass=current_password.getText();
  	  try{
    	DB.InsertFun("update admins set pass='"+npass+"' where pass='"+opass+"'");
    	Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Password changed");
        alert.setHeaderText("Settings");
        alert.setContentText("Your password is changed successfuly");
        alert.showAndWait();
    	}catch(Exception e1){ JOptionPane.showMessageDialog(null, e1);}
  	    
    }
}
