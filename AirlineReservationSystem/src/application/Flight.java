package application;

import java.time.LocalDate;

public class Flight {
	
	private int flight_id, flight_price;
	private String flight_name, source, destination, arrival_time, departure_time;
	private LocalDate date;
	
	public Flight() {
		
	}
	
	public Flight(int flight_id, String flight_name, String source, String destination, int flight_price, LocalDate date,
			String arrival_time, String departure_time) {
		this.flight_id = flight_id;
		this.flight_price = flight_price;
		this.flight_name = flight_name;
		this.date = date;
		this.source = source;
		this.destination = destination;
		this.arrival_time = arrival_time;
		this.departure_time = departure_time;
	}
	
	public int getFlight_id() {
		return flight_id;
	}
	public int getFlight_price() {
		return flight_price;
	}
	public String getFlight_name() {
		return flight_name;
	}
	public LocalDate getDate() {
		return date;
	}
	public String getSource() {
		return source;
	}
	public String getDestination() {
		return destination;
	}
	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public void setFlight_price(int flight_price) {
		this.flight_price = flight_price;
	}

	public void setFlight_name(String flight_name) {
		this.flight_name = flight_name;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}

	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getArrival_time() {
		return arrival_time;
	}
	public String getDeparture_time() {
		return departure_time;
	}
}
