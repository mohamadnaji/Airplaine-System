package dao;

import model.Services;

public interface ISpecialServicesDao extends IDao<Services, Integer> {

	public void updateServices(Services oldServices, Services newServices);
}
