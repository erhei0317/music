package lha.music.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

	T get(Class<T> entityClazz,Serializable id);
	Serializable save(T entity);
	void update(T entity);
	void delete(T entity);
	void delete(Class<T> entityClazz,Serializable id);
	List<T> findAll(Class<T> entityClazz);
	long findCount(Class<T> entityClazz);
}
