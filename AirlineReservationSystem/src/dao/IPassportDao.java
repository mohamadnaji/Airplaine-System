package dao;

import java.util.List;

import model.Passport;

public interface IPassportDao extends IDao<Passport, Integer>{
	
	List<Integer> findAllPassengerID();
	
}
