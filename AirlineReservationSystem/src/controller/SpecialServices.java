package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.IDao;
import daoimpl.TicketDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import model.Services;
import model.Ticket;

public class SpecialServices implements Initializable{
	
	@FXML
	private TextField ticketID;
	
	@FXML
	private Slider slider;
	
	@FXML
	private RadioButton rb1;
	@FXML
	private RadioButton rb2;
	@FXML
	private RadioButton rb3;
	@FXML
	private RadioButton rb4;
	@FXML
	private RadioButton rb5;
	
	@FXML
	private CheckBox cb1;
	@FXML
	private CheckBox cb2;
	@FXML
	private CheckBox cb3;
	@FXML
	private CheckBox cb4;
	@FXML
	private CheckBox cb5;
	@FXML
	private CheckBox cb6;
	
	@FXML
	private Button loadButton;
	@FXML
	private Button confirmButton;
	
	@FXML
	private Label bagLabel;
	@FXML
	private Label mealLabel;
	@FXML
	private Label servicesLabel;
	
	@FXML
	private Separator bagSeparator;
	@FXML
	private Separator mealSeparator;
	@FXML
	private Separator servicesSeparator;
	
	@FXML
	private Pane bagPane;
	@FXML
	private Pane mealPane;
	@FXML
	private Pane servicesPane;
	
	private ToggleGroup meal;
	private String meal1 = "Children's Meal";
	private String meal2 = "Vegetarian Meal - Vegan";
	private String meal3 = "Diabetic Meal";
	private String meal4 = "Gluten-Free Meal";
	private String meal5 = "Non-Lactose Meal";
	
	private int ticketId;
	private Ticket ticket;
	private Services service;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hideOptions();
		meal = new ToggleGroup();
		rb1.setToggleGroup(meal);
		rb2.setToggleGroup(meal);
		rb3.setToggleGroup(meal);
		rb4.setToggleGroup(meal);
	}
	
	
	@FXML
	void findTicket(ActionEvent event) throws SQLException {
		ticketId = Integer.parseInt(ticketID.getText().toString());
		IDao<Ticket, Integer> ticketDao = TicketDaoImpl.getTicketDaoImpl();
		ticket = ticketDao.findById(ticketId);
		if(ticket==null) {
			AlertController.alert("Ticket not found", null);
			return;
		}
		
		service = SpecialServicesModel.getTicketServices(ticket);
		
		if( service == null ) {
			service = new Services(ticketId, 0, "", 0,0,0,0,0,0 );
			SpecialServicesModel.addServices(service);
		}
		
		
		showOptions();
		fillOptions(service);
	}
	
	@FXML
	void saveServices(ActionEvent event) throws SQLException {
		Services s;
		String meals;
		
		if((RadioButton)meal.getSelectedToggle()==null)
			meals = "";
		else 
			meals = ((RadioButton)meal.getSelectedToggle()).getText().toString();
		
		int s1=0,s2=0,s3=0,s4=0,s5=0,s6=0;
		if(cb1.isSelected())
			s1=1;
		if(cb2.isSelected())
			s2=1;
		if(cb3.isSelected())
			s3=1;
		if(cb4.isSelected())
			s4=1;
		if(cb5.isSelected())
			s5=1;
		if(cb6.isSelected())
			s6=1;
		s = new Services(
				service.getTicketId(),
				(int) slider.getValue(),
				meals,
				s1,s2,s3,s4,s5,s6
				);
		SpecialServicesModel.updateServices(service,s);
		
		AlertController.alert1("Changes applied");
		hideOptions();
	}
	
	
	private void fillOptions(Services s) {
		slider.adjustValue(s.getBags()*1.0);
		
		if(s.getMeal()!=null) {
			String meal = s.getMeal();
			if(meal.equals(meal1))
				rb1.setSelected(true);
			if(meal.equals(meal2))
				rb2.setSelected(true);
			if(meal.equals(meal3))
				rb3.setSelected(true);
			if(meal.equals(meal4))
				rb4.setSelected(true);
			if(meal.equals(meal5))
				rb5.setSelected(true);
		}
		
		if(s.getS1()==1)
			cb1.setSelected(true);
		if(s.getS2()==1)
			cb2.setSelected(true);
		if(s.getS3()==1)
			cb3.setSelected(true);
		if(s.getS4()==1)
			cb4.setSelected(true);
		if(s.getS5()==1)
			cb5.setSelected(true);
		if(s.getS6()==1)
			cb6.setSelected(true);
	}
	
	private void showOptions() {
		bagLabel.setVisible(true);
		mealLabel.setVisible(true);
		servicesLabel.setVisible(true);
		
		bagSeparator.setVisible(true);
		mealSeparator.setVisible(true);
		servicesSeparator.setVisible(true);

		bagPane.setVisible(true);
		mealPane.setVisible(true);
		servicesPane.setVisible(true);
	}
	
	private void hideOptions() {
		bagLabel.setVisible(false);
		mealLabel.setVisible(false);
		servicesLabel.setVisible(false);
		
		bagSeparator.setVisible(false);
		mealSeparator.setVisible(false);
		servicesSeparator.setVisible(false);

		bagPane.setVisible(false);
		mealPane.setVisible(false);
		servicesPane.setVisible(false);
	}
}
