package dao;

import java.util.List;

public interface IDao<T, K> {

	public void save(T t);

	public void delete(K t);

	public T findById(K key);

	public List<T> findAll();
	
	public void update(T t, K k);
}
