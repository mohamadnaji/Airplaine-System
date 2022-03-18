package daoimpl;

import java.util.List;

import dao.IClientDao;
import model.Client;

public class ClientDaoImpl implements IClientDao {

	
	private static ClientDaoImpl clientDao;
	
	public static ClientDaoImpl getclientDaoImpl() {
		if(clientDao == null)
			clientDao = new ClientDaoImpl();
		return clientDao;
	}
	
	@Override
	public void save(Client t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Client t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Client findById(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
