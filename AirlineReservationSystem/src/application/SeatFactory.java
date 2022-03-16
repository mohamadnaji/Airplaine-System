package application;

import pojo.Business;
import pojo.Economy;
import pojo.FirstClass;

public class SeatFactory {

	public SeatFactory() {
		// TODO Auto-generated constructor stub
	}

	public static Seat createSeat(String seat) {
		if (seat.equalsIgnoreCase("FirstClass")) {
			return new FirstClass();
		} else if (seat.equalsIgnoreCase("Economy")) {
			return new Economy();
		} else if (seat.equalsIgnoreCase("Business")) {
			return new Business();
		}
		throw new IllegalArgumentException("No such seat");
	}

}
