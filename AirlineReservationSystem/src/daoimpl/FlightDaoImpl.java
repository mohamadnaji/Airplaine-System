package daoimpl;

import java.util.List;

import dao.IFlightDao;
import model.Flight;

public class FlightDaoImpl implements IFlightDao {

	private static FlightDaoImpl flightDao;

	public static FlightDaoImpl getFlightDaoImpl() {
		if (flightDao == null)
			flightDao = new FlightDaoImpl();
		return flightDao;
	}

	@Override
	public void save(Flight t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Flight t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Flight findById(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
