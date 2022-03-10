package application;

import java.time.LocalDate;

public class Client {
	String firstName, lastName, passportNumber, nationality, gender,phoneNumber, emailAddress,frequentFlyerNumber;
	int frequentFlyerPoints;
	LocalDate birthDate;
	
	
	public Client(String firstName, String lastName, String passportNumber, String nationality, String gender,
			String phoneNumber, String emailAddress, String frequentFlyerNumber, int frequentFlyerPoints,
			LocalDate birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.passportNumber = passportNumber;
		this.nationality = nationality;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.frequentFlyerNumber = frequentFlyerNumber;
		this.frequentFlyerPoints = frequentFlyerPoints;
		this.birthDate = birthDate;
	}


	public Client(String firstName, String lastName, String passportNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.passportNumber = passportNumber;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassportNumber() {
		return passportNumber;
	}


	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public String getFrequentFlyerNumber() {
		return frequentFlyerNumber;
	}


	public void setFrequentFlyerNumber(String frequentFlyerNumber) {
		this.frequentFlyerNumber = frequentFlyerNumber;
	}


	public int getFrequentFlyerPoints() {
		return frequentFlyerPoints;
	}


	public void setFrequentFlyerPoints(int frequentFlyerPoints) {
		this.frequentFlyerPoints = frequentFlyerPoints;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	
}
