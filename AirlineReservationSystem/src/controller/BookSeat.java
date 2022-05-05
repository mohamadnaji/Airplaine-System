package controller;

import javafx.fxml.Initializable;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.DataBase;
import application.Seat;
import application.SeatFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class BookSeat implements Initializable{
		ResultSet rs,rs1,rs2,rs3;
		DataBase DB=new DataBase();
		String TypeS,seat_number;
		int price;
		int nbBags=2;
		HashMap<String, Button> SeatsIDA = new HashMap<>();
		HashMap<String, Button> SeatsIDB = new HashMap<>();
		HashMap<String, Button> SeatsIDC = new HashMap<>();
		HashMap<String, Button> SeatsIDD = new HashMap<>();
		HashMap<String, Button> SeatsIDE = new HashMap<>();
		HashMap<String, Button> SeatsIDF = new HashMap<>();
		//HashMap<String, Button> ReservedSeats = new HashMap<>();
		
		String stringReservedSeats;
		List<String>  ReservedSeats=new ArrayList<>();
		List<String>  RedSeats=new ArrayList<>();
		@FXML
		private AnchorPane anchorPane;
	    @FXML
	    private TextField FlightId;
	    @FXML
	    private TextField passengerId;
		@FXML
	    private Button F8,F72,F7,F37,F36,F35,F34,F32,F31,F30,F3,F27,F26,F25,F24,F23,F22,F21,F20,F2,F15,F14,F13,F12,F11,F10,F1;
		@FXML
	    private Button E8,E72,E7,E37,E36,E35,E34,E32,E31,E30,E3,E27,E26,E25,E24,E23,E22,E21,E20,E2,E15,E14,E13,E12,E11,E10,E1;
		@FXML
	    private Button D8,D72,D7,D37,D36,D35,D34,D32,D31,D30,D3,D27,D26,D25,D24,D23,D22,D21,D20,D2,D15,D14,D13,D12,D11,D10,D1;
		@FXML
	    private Button C8,C72,C7,C37,C36,C35,C34,C32,C31,C30,C3,C27,C26,C25,C24,C23,C22,C21,C20,C2,C15,C14,C13,C12,C11,C10,C1;
		@FXML
	    private Button B8,B72,B7,B37,B36,B35,B34,B32,B31,B30,B3,B27,B26,B25,B24,B23,B22,B21,B20,B2,B15,B14,B13,B12,B11,B10,B1;
		@FXML
	    private Button A8,A72,A7,A37,A36,A35,A34,A32,A31,A30,A3,A27,A26,A25,A24,A23,A22,A21,A20,A2,A15,A14,A13,A12,A11,A10,A1;
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	  
	   }
	  /*  public Node lookup(final String selector) {
	    	  return node().lookup(selector);
	    	}*/
	    @FXML
	    void back(ActionEvent event) {
	    	closeStage();
	    }
	    private void closeStage() {
	       
	    }
	    @FXML
	    void test(Event event) {
	   
	    	/*	SeatsIDF.entrySet().forEach(entry -> {
	    		    System.out.println(entry.getKey() + " " + entry.getValue());
	    		});
	    		SeatsIDA.entrySet().forEach(entry -> {
	    		    System.out.println(entry.getKey() + " " + entry.getValue());
	    		});
	    		SeatsIDB.entrySet().forEach(entry -> {
	    		    System.out.println(entry.getKey() + " " + entry.getValue());
	    		});
	    		SeatsIDC.entrySet().forEach(entry -> {
	    		    System.out.println(entry.getKey() + " " + entry.getValue());
	    		});
	    		SeatsIDD.entrySet().forEach(entry -> {
	    		    System.out.println(entry.getKey() + " " + entry.getValue());
	    		});
	    		SeatsIDE.entrySet().forEach(entry -> {
	    		    System.out.println(entry.getKey() + " " + entry.getValue());
	    		});
	  	    	
		   Button A= SeatsIDA.get("A1");
		   A.setStyle("-fx-background-color:#ff0000;");
	   
   	
		  Button B= SeatsIDB.get("B1");
		  B.setStyle("-fx-background-color:#ff0000;");
		  
		  Button C= SeatsIDC.get("C7");
		  C.setStyle("-fx-background-color:#ff0000;");
		  
		  Button D= SeatsIDD.get("D7");
		  D.setStyle("-fx-background-color:#ff0000;");
		  
		  
		  Button E= SeatsIDE.get("E1");
		  E.setStyle("-fx-background-color:#ff0000;");
			    	
		  Button F= SeatsIDF.get("F1");*/
		  F1.setStyle("-fx-background-color:#ff0000;");
	    }
	   
	    @FXML
	    public void shape(ActionEvent event) {
	    	
	    	Button btn = (Button) event.getSource();
	    	Color color = (Color)btn.getBackground().getFills().get(0).getFill();
	    	String col=color.toString();
	    	String idBtn = btn.getId();
	    	
	    	
	    	if(col.equals("0xfc9a29ff")) TypeS="FirstClass";
	    	else if(col.equals("0x2d6eb4ff")) TypeS="Business";
	    	else if(col.equals("0x85d1aeff")) TypeS="Economy";
	    	Seat seat = SeatFactory.createSeat(TypeS);
	    	TypeS= seat.getSeatType();
	    	btn.setStyle("-fx-background-color: #c5291b;");
	     	try{
	    		if(TypeS.equals("FirstClass")) {
	    		rs3=DB.SelectFun("select first_class_price,seat_number from seat where flight_id='"+FlightId.getText()+"'");
	    		rs3.next();
	   
	    		}
				else if(TypeS.equals("Business")) {
				rs3=DB.SelectFun("select economic_class_price,seat_number from seat where flight_id='"+FlightId.getText()+"'");
		    	rs3.next();
	    	
	    		}
				else if(TypeS.equals("Economy")) {
				rs3=DB.SelectFun("select business_class_price,seat_number from seat where flight_id='"+FlightId.getText()+"'");
			    rs3.next();
	  
	    		}
	    		price=rs3.getInt(1);
	    		seat_number=rs3.getString(2);
	    		 Date D=new Date();
		        if(seat_number!= "null") {
		        	
		        	if(seat_number.contains(",")) {
		        		List<String>list=Arrays.asList(seat_number.split(","));
		        	  ReservedSeats = new ArrayList<>(list);
		        	}
		        	else ReservedSeats.add(seat_number);
		        
		        }
		     /*   for (String a : ReservedSeats)
		            System.out.println(a);*/
		        boolean btnIdReserved = ReservedSeats.contains(idBtn);
		        System.out.println("flag="+btnIdReserved);
		        if(btnIdReserved) {
		        	Alert alert = new Alert(AlertType.ERROR);
		            alert.setTitle("Error");
		            alert.setHeaderText("Ooops!!!");
		            alert.setContentText("Seat Already Reserved !!!");
		            alert.showAndWait();
		        	
		        }
		        else {
	    			ReservedSeats.add(idBtn);
	    	//	SeatsIDE.put(idBtn, btn);
	    		//System.out.println("ReservedSeats2="+ReservedSeats.toArray());
	    		stringReservedSeats=String.join(",", ReservedSeats);
	    		//System.out.println("stringReservedSeats="+stringReservedSeats.toString());
	    		//lzm a3ml update tickets
	    		/*DB.InsertFun("INSERT INTO tickets VALUES('"+FlightId.getText()+"','"+PassengerId.getText()+"','"+"','"+price+"','"+ 
	    				nbBags+"','"+idBtn +"','"+TypeS+"','"+""+"','"+1+"')");*/
	    		/*String Str="UPDATE seat SET seat_number = '" + stringReservedSeats +
	    				                    "' WHERE passenger_id = " + passengerId.getText() + "";
	    		System.out.println("hay str"+Str);*/
	    		DB.InsertFun("UPDATE seat SET seat_number = '" + stringReservedSeats +
							"' WHERE flight_id = " + FlightId.getText() + "");
	    		DB.InsertFun("UPDATE ticket SET seat_number = '" + idBtn +
						"' WHERE passenger_id = " + passengerId.getText() + "");
	    
	        	Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Seat Added");
	            alert.setHeaderText("Seats");
	            alert.setContentText("Your Seat is reserved successfuly");
	            alert.showAndWait();
		        }
	        	}catch(Exception e1){ JOptionPane.showMessageDialog(null, e1);}
	    
        }
	    
	    
	    @FXML
	    public void setSeats(Event e)
	    {
	    	Stage stage = (Stage) anchorPane.getScene().getWindow();
    		Scene scene = stage.getScene(); 
    		for(int i=0;i<38;i++) {
    		
    		String idA="A"+String.valueOf(i);
    		//System.out.println("idA="+idA);
    		String idofbtnA= "#A"+String.valueOf(i);
    		//System.out.println("idofbtnA="+idofbtnA);
    		Button btnA= (Button) scene.lookup(idofbtnA);
    		if(btnA != null)
    		SeatsIDA.put(idA, btnA);
    		
    		String idB="B"+String.valueOf(i);
    		String idofbtnB= "#B"+String.valueOf(i);
    		Button btnB= (Button) scene.lookup(idofbtnB);
    		if(btnB != null)
    		SeatsIDB.put(idB, btnB);
    		
    		String idC="C"+String.valueOf(i);
    		//System.out.println("idC="+idC);
    		String idofbtnC= "#C"+String.valueOf(i);
    		//System.out.println("idofbtnC="+idofbtnC);
    		Button btnC= (Button) scene.lookup(idofbtnC);
    		if(btnC != null)
    		SeatsIDC.put(idC, btnC);
    		 
    		
    		
    		String idD="D"+String.valueOf(i);
    		String idofbtnD= "#D"+String.valueOf(i);
    		Button btnD= (Button) scene.lookup(idofbtnD);
    		if(btnD != null)
    		SeatsIDD.put(idD, btnD);
    		
    		String idE="E"+String.valueOf(i);
    		String idofbtnE= "#E"+String.valueOf(i);
    		Button btnE= (Button) scene.lookup(idofbtnE);
    		if(btnE != null)
    		SeatsIDE.put(idE, btnE);
    		
    		String idF="F"+String.valueOf(i);
    		String idofbtnF= "#F"+String.valueOf(i);
    		Button btnF= (Button) scene.lookup(idofbtnF);
    		if(btnF != null)
    		SeatsIDF.put(idF, btnF);
    		}
    		
	    	String ID=FlightId.getText();
	    	try{
	    		rs=DB.SelectFun("select seat_number from seat where flight_id='"+FlightId.getText()+"'");
	    		rs.next();
	    		seat_number=rs.getString(1);
	    		
	    		if(seat_number.contains(",")) {
	        		List<String>list=Arrays.asList(seat_number.split(","));
	        		RedSeats = new ArrayList<>(list);
	        	}
	    		
	        	}catch(Exception e1){ JOptionPane.showMessageDialog(null, e1);}
		    	for(int i=0;i<RedSeats.size();i++) { 
		    		Button b = null;
		    		 System.out.println("seatttID="+RedSeats.get(i));
		    		 //System.out.println("jhj="+ SeatsIDC.get("C11"));
		    		 if(SeatsIDA.get(RedSeats.get(i)) != null)
		    		       b= SeatsIDA.get(RedSeats.get(i));
		    		 else if(SeatsIDB.get(RedSeats.get(i)) != null)
		    		       b= SeatsIDB.get(RedSeats.get(i));
		    		else 
		    		 if(SeatsIDC.get(RedSeats.get(i)) != null) 
		    		       b= SeatsIDC.get(RedSeats.get(i));
		    		 
		    		
		    		 else if(SeatsIDD.get(RedSeats.get(i)) != null)
		    		       b= SeatsIDD.get(RedSeats.get(i));
		    		 else if(SeatsIDE.get(RedSeats.get(i)) != null)
		    		       b= SeatsIDE.get(RedSeats.get(i));
		    		 else if(SeatsIDF.get(RedSeats.get(i)) != null)
		    		       b= SeatsIDF.get(RedSeats.get(i));
		    		 if(b != null)
		    		b.setStyle("-fx-background-color: #c5291b; -fx-background-radius: 100; ");
		    		}
	    
}
}
	

