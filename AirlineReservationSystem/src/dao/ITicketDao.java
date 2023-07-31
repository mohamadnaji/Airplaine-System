package dao;

import model.Ticket;

public interface ITicketDao extends IDao<Ticket, Integer> {

	public Integer getMaxTicketId();
	
	public void deleteSeat(Integer Id);
}
