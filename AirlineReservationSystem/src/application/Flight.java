package application;

import java.time.LocalDate;

public class Flight {
	
	private int flight_id, nbr_of_seats, nbr_of_reserved_seats, flag; // flag = 1 if the flight is not deleted, 0 if deleted
	private String airline_name, source, destination, arrival_time, departure_time;
	private LocalDate arrival_date, departure_date;
	private String flight_number;
	
	public Flight() {
		
	}

	public Flight(int flight_id, String flight_number, String airline_name, int nbr_of_seats, int nbr_of_reserved_seats, String source,
			String destination, String arrival_time, String departure_time, LocalDate arrival_date,
			LocalDate departure_date) {
		super();
		this.flight_id = flight_id;
		this.flight_number = flight_number;
		this.nbr_of_seats = nbr_of_seats;
		this.nbr_of_reserved_seats = nbr_of_reserved_seats;
		this.airline_name = airline_name;
		this.source = source;
		this.destination = destination;
		this.arrival_time = arrival_time;
		this.departure_time = departure_time;
		this.arrival_date = arrival_date;
		this.departure_date = departure_date;
		this.flag = 1;
	}

	public int getFlight_id() {
		return flight_id;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public int getNbr_of_seats() {
		return nbr_of_seats;
	}

	public void setNbr_of_seats(int nbr_of_seats) {
		this.nbr_of_seats = nbr_of_seats;
	}

	public int getNbr_of_reserved_seats() {
		return nbr_of_reserved_seats;
	}

	public void setNbr_of_reserved_seats(int nbr_of_reserved_seats) {
		this.nbr_of_reserved_seats = nbr_of_reserved_seats;
	}

	public String getAirline_name() {
		return airline_name;
	}

	public void setAirline_name(String airline_name) {
		this.airline_name = airline_name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}

	public String getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}


	public LocalDate getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(LocalDate arrival_date) {
		this.arrival_date = arrival_date;
	}

	public LocalDate getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(LocalDate departure_date) {
		this.departure_date = departure_date;
	}
	
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public String getFlight_number() {
		return flight_number;
	}
	
	public void setFlight_number(String flight_nbr) {
		flight_number = flight_nbr;
	}
	
}