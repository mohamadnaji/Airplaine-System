package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.ISpecialServicesDao;
import model.Services;
import pojo.DataBase;

public class SpecialServicesDaoImpl implements ISpecialServicesDao {

	private static SpecialServicesDaoImpl specialServicesDao;
	private DataBase db = DataBase.getDataBase();

	public static SpecialServicesDaoImpl getSpecialServicesDaoImpl() {
		if (specialServicesDao == null)
			specialServicesDao = new SpecialServicesDaoImpl();
		return specialServicesDao;
	}

	@Override
	public void save(Services services) {
		String Query = "INSERT INTO `ars`.`services` (`ticket_id`, `bags`, `meal`, `service1`, `service2`, `service3`, `service4`, `service5`, `service6`) "
				+ " VALUES ('" + services.getTicketId() + "','" + services.getBags() + "','" + services.getMeal()
				+ "','" + services.getS1() + "','" + services.getS2() + "','" + services.getS3() + "','"
				+ services.getS4() + "','" + services.getS5() + "','" + services.getS6() + "');";
		try {
			db.InsertFun(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Services findById(Integer key) {
		Services s = null;
		ResultSet rs = null;
		try {
			rs = db.SelectFun("Select * from services WHERE ticket_id='" + key + "'");
			while (rs.next()) {
				s = new Services(rs.getInt(1), // services_id
						rs.getInt(2), // ticket_id
						rs.getInt(3), // nb of extra bags
						rs.getString(4), // meal
						rs.getInt(5), // service1
						rs.getInt(6), // service2
						rs.getInt(7), // service3
						rs.getInt(8), // service4
						rs.getInt(9), // service5
						rs.getInt(10) // service6
				);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return s;
	}

	@Override
	public List<Services> findAll() {
		return null;
	}

	@Override
	public void update(Services newServices, Integer k) {
	}

	@Override
	public void updateServices(Services oldServices, Services newServices) {

		ResultSet rs = null;
		if (newServices.compareTo(oldServices) == 0)
			return;
		String Query = "";
		try {
			rs = db.SelectFun("Select services_id from services WHERE ticket_id='" + newServices.getTicketId() + "'");

			rs.next();
			int services_id = rs.getInt(1);
			if (newServices.getBags() != oldServices.getBags()) {
				Query = "UPDATE `ars`.`services` SET `bags` = '" + newServices.getBags() + "';";
//		String Query = "UPDATE `ars`.`services` SET `bags` = '"+s.getBags()+"', `meal` = '"+s.getMeal()
//		+"', `service1` = '"+s.getS1()+"',`service2` = ' "+s.getS2()+"', `service3` = '"+s.getS3()+"', `service4` = '" +s.getS4()+ "',`service5` = ' "+s.getS5()+"', `service6` = '"+s.getS6()+
//		"' WHERE (`ticket_id` = '"+s.getTicketId()+"');";
				db.InsertFun(Query);
			}
			if (!newServices.getMeal().equals(oldServices.getMeal())) {
				Query = "UPDATE `ars`.`services` SET `meal` = \"" + newServices.getMeal()
						+ "\" WHERE (`services_id` = '" + services_id + "');";
				db.InsertFun(Query);
			}
			if (newServices.getS1() != oldServices.getS1()) {
				Query = "UPDATE `ars`.`services` SET `service1` = '" + newServices.getS1()
						+ "' WHERE (`services_id` = '" + services_id + "');";
				db.InsertFun(Query);
			}
			if (newServices.getS2() != oldServices.getS2()) {
				Query = "UPDATE `ars`.`services` SET `service2` = '" + newServices.getS2()
						+ "' WHERE (`services_id` = '" + services_id + "');";
				db.InsertFun(Query);
			}
			if (newServices.getS3() != oldServices.getS3()) {
				Query = "UPDATE `ars`.`services` SET `service3` = '" + newServices.getS3()
						+ "' WHERE (`services_id` = '" + services_id + "');";
				db.InsertFun(Query);
			}
			if (newServices.getS4() != oldServices.getS4()) {
				Query = "UPDATE `ars`.`services` SET `service4` = '" + newServices.getS4()
						+ "' WHERE (`services_id` = '" + services_id + "');";
				db.InsertFun(Query);
			}
			if (newServices.getS5() != oldServices.getS5()) {
				Query = "UPDATE `ars`.`services` SET `service5` = '" + newServices.getS5()
						+ "' WHERE (`services_id` = '" + services_id + "');";
				db.InsertFun(Query);
			}
			if (newServices.getS6() != oldServices.getS6()) {
				Query = "UPDATE `ars`.`services` SET `service6` = '" + newServices.getS6()
						+ "' WHERE (`services_id` = '" + services_id + "');";
				db.InsertFun(Query);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
