package dao;

import model.Flight;

public interface IFlightDao extends IDao<Flight, Integer> {

	Integer getMaxFlightId();
}




