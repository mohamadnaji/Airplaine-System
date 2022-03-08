package application;

import java.time.LocalDate;

public class Client {
	String firstName, lastName, passportNumber, ageGroup, phoneNumber, emailAddress;
	LocalDate birthDate;
	
	
	public Client(String firstName, String lastName, String passportNumber, String ageGroup,
			String phoneNumber, String emailAddress, LocalDate birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.passportNumber = passportNumber;
		//this.nationality = nationality;
		this.ageGroup = ageGroup;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.birthDate = birthDate;
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


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}


	public String getAgeGroup() {
		return ageGroup;
	}


	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	
	
}
