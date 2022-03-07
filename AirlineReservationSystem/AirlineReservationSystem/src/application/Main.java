package application;
	
import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
		
	}
	@Override
	public void start(Stage primaryStage) {
		try {
		     
			URL url = new File("src/application/Login.fxml").toURI().toURL();
			Parent fxmlLoader =FXMLLoader.load(url);
			//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
			//fxmlLoader.setRoot(new AnchorPane());
		//	Parent root = fxmlLoader.load();
			
			Scene scene = new Scene(fxmlLoader,800,500);
	    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    primaryStage.setScene(scene);
		    primaryStage.setResizable(true);
			primaryStage.show();
			
	    } catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
