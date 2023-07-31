package model;

public class Services implements Comparable{
	private int servicesId,ticketId,bags;
	private String meal;
	private int s1,s2,s3,s4,s5,s6;
	
	public Services(int services_id, int ticket_id, int bags, String meal, int s1, int s2, int s3, int s4, int s5,
			int s6) {
		super();
		this.servicesId = services_id;
		this.ticketId = ticket_id;
		this.bags = bags;
		this.meal = meal;
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		this.s5 = s5;
		this.s6 = s6;
	}

	public Services(int ticket_id, int bags, String meal, int s1, int s2, int s3, int s4, int s5, int s6) {
		super();
		this.ticketId = ticket_id;
		this.bags = bags;
		this.meal = meal;
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		this.s5 = s5;
		this.s6 = s6;
	}

	public int getServicesId() {
		return servicesId;
	}

	public void setServicesId(int services_id) {
		this.servicesId = services_id;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticket_id) {
		this.ticketId = ticket_id;
	}

	public int getBags() {
		return bags;
	}

	public void setBags(int bags) {
		this.bags = bags;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public int getS1() {
		return s1;
	}

	public void setS1(int s1) {
		this.s1 = s1;
	}

	public int getS2() {
		return s2;
	}

	public void setS2(int s2) {
		this.s2 = s2;
	}

	public int getS3() {
		return s3;
	}

	public void setS3(int s3) {
		this.s3 = s3;
	}

	public int getS4() {
		return s4;
	}

	public void setS4(int s4) {
		this.s4 = s4;
	}

	public int getS5() {
		return s5;
	}

	public void setS5(int s5) {
		this.s5 = s5;
	}

	public int getS6() {
		return s6;
	}

	public void setS6(int s6) {
		this.s6 = s6;
	}

	@Override
	public int compareTo(Object o) {
		Services otherServices = (Services) o;
		if(
				(this.bags == otherServices.bags) 
				&& (this.meal.equals(otherServices.meal)) 
				&& (this.s1 == otherServices.s1)
				&& (this.s2 == otherServices.s2)
				&& (this.s3 == otherServices.s3)
				&& (this.s4 == otherServices.s4)
				&& (this.s5 == otherServices.s5)
				&& (this.s6 == otherServices.s6)
				)
			return 0; //equal
		return -1; //not equal
	}
	
	
}
