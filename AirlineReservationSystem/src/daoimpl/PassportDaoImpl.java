package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.IPassportDao;
import model.Passport;
import pojo.DataBase;

public class PassportDaoImpl implements IPassportDao {

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
					+ " place_of_birth, profession, issue_date, expiry_date, date_of_birth,"
					+ "type, issuing_state_code, gender, nationality) " + "VALUES(" + t.getPassenger_id() + ",'"
					+ t.getPassport_number() + "','" + t.getFather_name() + "','" + t.getMother_name() + "','"
					+ t.getPlace_of_birth() + "','" + t.getProfession() + "','" + t.getIssue_date() + "','"
					+ t.getExpiry_date() + "','" + t.getDate_of_birth() + "','" + t.getType() + "','"
					+ t.getIssuing_state_code() + "','" + t.getGender() + "','" + t.getNationality() + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(String t) {
		try {
			db.InsertFun("DELETE FROM `passport` WHERE passport_number = " + t + "");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	@Override
	public Passport findById(String key) { // by passenger_id
		Passport passport = null;
		ResultSet rs = null;
		try {
//			rs = db.SelectFun("SELECT * FROM passport WHERE passport_number='" + key + "'");
			rs = db.SelectFun("SELECT `passport`.`passport_number`,\r\n" + "    `passport`.`father_name`,\r\n"
					+ "    `passport`.`mother_name`,\r\n" + "    `passport`.`place_of_birth`,\r\n"
					+ "    `passport`.`profession`,\r\n" + "    `passport`.`issue_date`,\r\n"
					+ "    `passport`.`expiry_date`,\r\n" + "    `passport`.`type`,\r\n"
					+ "    `passport`.`issuing_state_code`,\r\n" + "    `passport`.`passenger_id`,\r\n"
					+ "    `passport`.`nationality`,\r\n" + "    `passport`.`gender`,\r\n"
					+ "    `passport`.`date_of_birth`\r\n" + "FROM `ars`.`passport` where `passport`.`passport_number` = '" + key + "';");
			while (rs.next()) {
				passport = new Passport(rs.getInt(10), // passenger_id or key
						rs.getString(1), // passport_number
						rs.getString(2), // father_name
						rs.getString(3), // mother_name
						rs.getString(4), // place of birth
						rs.getString(12), // gender
						rs.getString(11), // nationality
						rs.getDate(13) != null ? rs.getDate(13).toLocalDate() : null, // date_of_birth
						rs.getString(5), // profession
						rs.getDate(6) != null ? rs.getDate(6).toLocalDate() : null, // issue_date
						rs.getDate(7) != null ? rs.getDate(7).toLocalDate() : null, // expiry_date
						rs.getString(8), // type
						rs.getString(9) // issuing_state_code
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
	public void update(Passport t, String k) {
		String sql = "Update passport set passport_number = '" + t.getPassport_number() + "', father_name = '"
				+ t.getFather_name() + "', mother_name = '" + t.getMother_name() + "', 	place_of_birth = '"
				+ t.getPlace_of_birth() + "',profession = '" + t.getProfession() + "',issue_date = '"
				+ t.getIssue_date() + "',expiry_date = '" + t.getExpiry_date() + "', type = '" + t.getType()
				+ "', issuing_state_code = '" + t.getIssuing_state_code() + "', nationality = '" + t.getNationality()
				+ "', gender = '" + t.getGender() + "', date_of_birth = '" + t.getDate_of_birth()
				+ "' where passport_number = '" + k + "'";
		try {
			db.InsertFun(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Integer> findAllPassengerID() {
		ResultSet rs = null;
		List<Integer> ids = null;
		try {
			rs = db.SelectFun("select passenger_id from passenger");
			ids = new ArrayList<>();
			while (rs.next()) {
				// Integer id = new Integer(rs.getInt(1));
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
