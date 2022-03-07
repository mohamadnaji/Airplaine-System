package application;

import java.util.Date;

public class Passenger {
	private Integer ticketId;
	private Integer phoneNumber;
	private Integer passengerId;
	private String nationality;
	private Integer lastName;
	private Integer flag;
	private String firstName; 
	private String email;
	private Date birthDate;
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Integer getLastName() {
		return lastName;
	}
	public void setLastName(Integer lastName) {
		this.lastName = lastName;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Passenger(Integer ticketId, Integer phoneNumber, Integer passengerId, String nationality, Integer lastName,
			Integer flag, String firstName, String email, Date birthDate) {
		super();
		this.ticketId = ticketId;
		this.phoneNumber = phoneNumber;
		this.passengerId = passengerId;
		this.nationality = nationality;
		this.lastName = lastName;
		this.flag = flag;
		this.firstName = firstName;
		this.email = email;
		this.birthDate = birthDate;
	}
	public Passenger(Integer passengerId, Integer lastName, String firstName) {
		super();
		this.passengerId = passengerId;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	
}
