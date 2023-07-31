package model;

import java.util.Date;

public class Ticket {

	private Integer ticketId;
	private Integer ticketNumber;
	private Integer flightId;
	private Integer passengerId;
	private Integer flightPriceId;
	private Integer NumberOfBugs;
	private String meal;
	private String seatNumber;
	private Integer paymentId;
	private Date creationDate;

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

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String string) {
		this.seatNumber = string;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Ticket(Integer ticketId, Integer ticketNumber, Integer flightId, Integer passengerId, Integer flightPriceId,
			Integer numberOfBugs, String meal, String seatNumber, Integer paymentId, Date cre) {
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
		this.creationDate = cre;
	}

	public Ticket() {
		
	}
}
