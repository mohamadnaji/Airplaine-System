package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller implements Initializable  {
	    
		ResultSet rs;
	    DataBase DB=new DataBase();
	    @FXML
	    private TextField password;
	
	    @FXML
	    private TextField username;

	    @FXML
	    private AnchorPane anchorPane;

	   
	    
	    String p,u;
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	username.setStyle("-fx-prompt-text-fill: black");
	    	password.setStyle("-fx-prompt-text-fill: black");
	       // TODO
	   }
	    void loadWindow(String loc, String title) {
	        try {
	        	URL url = new File(loc).toURI().toURL();
				Parent parent =FXMLLoader.load(url);
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
	    	
	    /*try{
	    	rs=DB.SelectFun("Select * from user");
	    	while(rs.next()) {
	    		u=rs.getString(4);
	    		p=rs.getString(5);
	    	   }
	     	String user=username.getText();
	    	String pass=password.getText();
	    	Alert alert1 = new Alert(AlertType.ERROR);
  	        alert1.setTitle("Error");
  	        alert1.setHeaderText("Error");
	    	alert1.setContentText("!"+u+p);
	      	if(user.equals("") || pass.equals("")) {
	      		 Alert alert = new Alert(AlertType.ERROR);
	      	        alert.setTitle("Error");
	      	        alert.setHeaderText("Error");
	      	        alert.setContentText("Enter your username and password");
	      	        alert.showAndWait();
	  	    }
			if(user.equals(u) && pass.equals(p)) {
				
	   		    loadWindow("src/application/DashBoardAdmin.fxml","Aurora");	
	    		}
	    		else {
	       		Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText("Error");
	            alert.setContentText("username or password invalid!!");
	            alert.showAndWait();
	            }*/
	      	 loadWindow("src/application/DashBoardAdmin.fxml","Aurora");	
	    //	}catch(Exception e1){ JOptionPane.showMessageDialog(null, e1);}
	    }


}
