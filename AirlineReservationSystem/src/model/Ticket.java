package model;

public class Ticket {

	private Integer ticketId;
	private Integer ticketNumber;
	private Integer flightId;
	private Integer passengerId;
	private Integer flightPriceId;
	private Integer NumberOfBugs;
	private String meal;
	private Integer seatNumber;
	private Integer paymentId;

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public Integer getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(Integer ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public Integer getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}

	public Integer getFlightPriceId() {
		return flightPriceId;
	}

	public void setFlightPriceId(Integer flightPriceId) {
		this.flightPriceId = flightPriceId;
	}

	public Integer getNumberOfBugs() {
		return NumberOfBugs;
	}

	public void setNumberOfBugs(Integer numberOfBugs) {
		NumberOfBugs = numberOfBugs;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Ticket(Integer ticketId, Integer ticketNumber, Integer flightId, Integer passengerId, Integer flightPriceId,
			Integer numberOfBugs, String meal, Integer seatNumber, Integer paymentId) {
		super();
		this.ticketId = ticketId;
		this.ticketNumber = ticketNumber;
		this.flightId = flightId;
		this.passengerId = passengerId;
		this.flightPriceId = flightPriceId;
		NumberOfBugs = numberOfBugs;
		this.meal = meal;
		this.seatNumber = seatNumber;
		this.paymentId = paymentId;
	}

	public Ticket() {
	}
}
