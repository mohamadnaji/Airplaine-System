package dao;

import java.util.List;

public interface IDao<T, K> {

	public void save(T t);

	public void delete(T t);

	public T findById(K key);

	public List<T> findAll();
}
