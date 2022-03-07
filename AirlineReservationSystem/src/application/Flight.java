package application;

public class Flight {

	private String flightId;
	private Integer numberOfSeats;
	private Integer numberOfReservedSeats;
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public Integer getNumberOfReservedSeats() {
		return numberOfReservedSeats;
	}
	public void setNumberOfReservedSeats(Integer numberOfReservedSeats) {
		this.numberOfReservedSeats = numberOfReservedSeats;
	}
	public Flight(String flightId, Integer numberOfSeats, Integer numberOfReservedSeats) {
		super();
		this.flightId = flightId;
		this.numberOfSeats = numberOfSeats;
		this.numberOfReservedSeats = numberOfReservedSeats;
	}

	
}
