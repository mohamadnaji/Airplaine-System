package model;

public class Client {

	int clientID;
	String firstName, lastName, phoneNumber, emailAddress;
	String ageGroup;
	int flag; //flag = 1 --> not deleted, 0 --> deleted.
	
	
	public Client(String firstName, String lastName, String phoneNumber, String emailAddress, String ageGroup,
			int flag) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.ageGroup = ageGroup;
		this.flag = flag;
	}


	public Client(int clientID, String firstName, String lastName, String ageGroup,
			String phoneNumber, String emailAddress, int flag) {
		super();
		this.clientID = clientID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ageGroup = ageGroup;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.flag = flag;
	}


	public Client(int clientID, String firstName, String lastName) {
		super();
		this.clientID = clientID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getClientID() {
		return clientID;
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


	public String getAgeGroup() {
		return ageGroup;
	}


	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}


	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}
