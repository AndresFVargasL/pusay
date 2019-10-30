package com.vortexbird.pusay.cuestionarios.api.dao.support;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Fenix DAO Generico
 * 
 * @param <T>
 * @param <PK>
 */
public interface Dao<T, PK extends Serializable> {

	void save(T instance);

	void delete(T instance);

	void update(T instance);

	T findById(PK id);

	List<T> findByExample(T instance);

	List<T> findByProperty(String propertyName, Object value);

	List<T> findAll();

	List<T> findPage(String sortColumnName, boolean sortAscending,
			int startRow, int maxResults);

	Long findTotalNumber();

	List<T> findByCriteria(String whereCondition);
}
