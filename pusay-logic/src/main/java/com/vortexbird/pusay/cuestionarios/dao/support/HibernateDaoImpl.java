package com.vortexbird.pusay.cuestionarios.dao.support;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.vortexbird.pusay.cuestionarios.api.dao.support.Dao;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class HibernateDaoImpl<T, PK extends Serializable> implements Dao<T, PK> {

	private Class<T> entityClass;

	@Resource
	private SessionFactory sessionFactory;

	public HibernateDaoImpl() {
		super();
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void save(T instance) {
		getSession().save(instance);
	}

	public void delete(T instance) {
		getSession().delete(instance);
	}

	public void update(T instance) {
		getSession().update(instance);
	}

	public T findById(PK id) {
		return (T) getSession().get(this.entityClass, id);
	}

	public List<T> findByExample(T instance) {
		List results = getSession().createCriteria(entityClass).add(Example.create(instance)).list();
		return results;
	}

	public List<T> findByProperty(String propertyName, Object value) {
		if (value == null) {
			return null;
		}

		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Restrictions.eq(propertyName, value));
		return getByCriteria(criteria);
	}

	private List<T> getByCriteria(DetachedCriteria criteria) {
		if (criteria == null) {
			return null;
		}
		Criteria executeCriteria = criteria.getExecutableCriteria(getSession());
		return executeCriteria.list();
	}

	public List<T> findAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		return getByCriteria(criteria);
	}

	public List<T> findPage(String sortColumnName, boolean sortAscending, int startRow, int maxResults) {

		if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
			try {
				String queryString = "select model from " + entityClass.getName() + " model order by model."
						+ sortColumnName + " " + (sortAscending ? "asc" : "desc");

				return getSession().createQuery(queryString).setFirstResult(startRow).setMaxResults(maxResults).list();
			} catch (RuntimeException re) {
				throw re;
			}
		} else {
			try {
				String queryString = "select model from " + entityClass.getName() + " model";

				return getSession().createQuery(queryString).setFirstResult(startRow).setMaxResults(maxResults).list();
			} catch (RuntimeException re) {
				throw re;
			}
		}
	}

	public Long findTotalNumber() {
		try {
			String queryString = "select count(*) from " + entityClass.getSimpleName() + " model";
			return (Long) getSession().createQuery(queryString).list().get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<T> findByCriteria(String whereCondition) {
		try {
			String where = ((whereCondition == null) || (whereCondition.length() == 0)) ? ""
					: ("where " + whereCondition);
			final String queryString = "select model from " + entityClass.getSimpleName() + " model " + where;

			return getSession().createQuery(queryString).list();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}
}
