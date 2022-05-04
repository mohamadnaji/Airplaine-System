package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.IDao;
import daoimpl.PassportDaoImpl;
import model.Passport;
import pojo.DataBase;

public class PassportModel {

	static ResultSet rs;
	static DataBase DB = DataBase.getDataBase();
//	
//	public static List<Integer> getAllPassengersIDs() throws SQLException {
//		List<Integer> ids = 
//	}
//	
	// search by the passenger_id
	public static Passport searchPassport(int id)  throws SQLException{
		Passport passport = null;
		IDao<Passport, Integer> passportDao = PassportDaoImpl.getPassportDaoImpl();
		passport = passportDao.findById(id);
		return passport;
	}
	
	public static void addPassport(Passport passport) throws SQLException {
		try {
			IDao<Passport, Integer> passportDao = PassportDaoImpl.getPassportDaoImpl();
			passportDao.save(passport);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}
	
	// update by the passenger_id
	public static void updateFlightByID(Passport passport) {

		try {
			IDao<Passport, Integer> passportDao = PassportDaoImpl.getPassportDaoImpl();
			passportDao.update(passport, passport.getPassenger_id());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}


}
