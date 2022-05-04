package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.IPassportDao;
import model.Passport;
import pojo.DataBase;

public class PassportDaoImpl implements IPassportDao{

	private static PassportDaoImpl passportDao;
	private DataBase db = DataBase.getDataBase();
	
	public static PassportDaoImpl getPassportDaoImpl() {
		if (passportDao == null)
			passportDao = new PassportDaoImpl();
		return passportDao;
	}
	
	@Override
	public void save(Passport t) {
		try {
			db.InsertFun("INSERT INTO passport (passenger_id, passport_number, father_name, mother_name,"
					+ " place_of_birth, profession, issue_date, first_name, last_name, expiry_date, date_of_birth"
					+ "type, issuing_state_code, gender, nationality) "
					+ "VALUES(" + t.getPassenger_id() + ",'" + t.getPassport_number() + "','" + t.getFather_name()
					+ "','" + t.getMother_name() + "','" + t.getPlace_of_birth() + "','"+ t.getProfession() 
					+ "','" + t.getIssue_date() + "','" + t.getFirst_name() + "','" + t.getLast_name() + "','"
					+ t.getExpiry_date() + "','" + t.getDate_of_birth() + "','" + t.getType() + "','" + t.getIssuing_state_code()
					+ "','" + t.getGender() + "','" + t.getNationality() + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Integer t) {
		try {
			db.InsertFun("DELETE FROM `passport` WHERE passenger_id = " + t + "");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}

	@Override
	public Passport findById(Integer key) { // by passenger_id
		Passport passport = null;
		ResultSet rs = null;
		try {
			rs = db.SelectFun("SELECT * FROM passport WHERE passenger_id='" + key + "'");
			
			while(rs.next()) {
				passport = new Passport(rs.getInt(12), //passenger_id or key
						rs.getString(1), // passport_number 
						rs.getString(2), //first_name
						rs.getString(3), //last_name
						rs.getString(4), // father_name
						rs.getString(5),  //mother_name
						rs.getString(6), //place of birth
						rs.getString(14),//gender
						rs.getString(13), // nationality
						rs.getDate(15).toLocalDate(),  //date_of_birth
						rs.getString(7), //profession
						rs.getDate(8).toLocalDate(),//issue_date
						rs.getDate(9).toLocalDate(),//expiry_date
						rs.getString(10), //type
						rs.getString(11) //issuing_state_code
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passport;
	}

	@Override
	public List<Passport> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Passport t, Integer k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> findAllPassengerID() {
		ResultSet rs = null;
		List<Integer> ids = null;
		try {
			rs = db.SelectFun("select passenger_id from passenger");
			ids = new ArrayList<>();
			while(rs.next()) {
				//Integer id = new Integer(rs.getInt(1));
				Integer id = Integer.valueOf(rs.getInt(1));
				ids.add(id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ids);
		return ids;
	}

}