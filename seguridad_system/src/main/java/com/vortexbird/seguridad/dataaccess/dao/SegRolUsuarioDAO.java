package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegRolUsuario;


/**
 * A data access object (DAO) providing persistence and search support for
 * SegRolUsuario entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * @see lidis.SegRolUsuario
 *
 */
public class SegRolUsuarioDAO implements ISegRolUsuarioDAO {
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved SegRolUsuario entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SegRolUsuarioDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            SegRolUsuario entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SegRolUsuario entity) {
		EntityManagerHelper.log("saving SegRolUsuario instance", Level.INFO,
				null);

		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent SegRolUsuario entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SegRolUsuarioDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 *
	 * @param entity
	 *            SegRolUsuario entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SegRolUsuario entity) {
		EntityManagerHelper.log("deleting SegRolUsuario instance", Level.INFO,
				null);

		try {
			entity = getEntityManager()
					.getReference(SegRolUsuario.class,
							entity.getRluCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved SegRolUsuario entity and return it or a copy of it
	 * to the sender. A copy of the SegRolUsuario entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = SegRolUsuarioDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            SegRolUsuario entity to update
	 * @return SegRolUsuario the persisted SegRolUsuario entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SegRolUsuario update(SegRolUsuario entity) {
		EntityManagerHelper.log("updating SegRolUsuario instance", Level.INFO,
				null);

		try {
			SegRolUsuario result = getEntityManager()
					.merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);

			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public SegRolUsuario findById(Long id) {
		EntityManagerHelper.log("finding SegRolUsuario instance with id: " +
				id, Level.INFO, null);

		try {
			SegRolUsuario instance = getEntityManager()
					.find(SegRolUsuario.class, id);

			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all  SegRolUsuario entities with a specific property value.
	 *
	 * @param propertyName
	 *            the metaData.name of the  SegRolUsuario property to query
	 * @param value
	 *            the property value to match
	 * @return List< SegRolUsuario> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SegRolUsuario> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding  SegRolUsuario instance with property: " + propertyName +
				", value: " + value, Level.INFO, null);

		try {
			final String queryString = "select model from  SegRolUsuario model where model." +
					propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property metaData.name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all SegRolUsuario entities with a specific property value.
	 *
	 * @param propertyName
	 *            the name of the SegRolUsuario property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<SegRolUsuario> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SegRolUsuario> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding SegRolUsuario instance with property: " + propertyName +
				", value: " + value, Level.INFO, null);

		try {
			final String queryString = "select model from SegRolUsuario model where model." +
					propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);

			if ((rowStartIdxAndCount != null) &&
					(rowStartIdxAndCount.length > 0)) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);

				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);

					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<SegRolUsuario> findByRluCodigo(Object rluCodigo,
			int... rowStartIdxAndCount) {
		return findByProperty(RLUCODIGO, rluCodigo, rowStartIdxAndCount);
	}

	public List<SegRolUsuario> findByRluCodigo(Object rluCodigo) {
		return findByProperty(RLUCODIGO, rluCodigo);
	}

	public List<SegRolUsuario> findByRluEstadoRegistro(
			Object rluEstadoRegistro, int... rowStartIdxAndCount) {
		return findByProperty(RLUESTADOREGISTRO, rluEstadoRegistro,
				rowStartIdxAndCount);
	}

	public List<SegRolUsuario> findByRluEstadoRegistro(Object rluEstadoRegistro) {
		return findByProperty(RLUESTADOREGISTRO, rluEstadoRegistro);
	}

	/**
	 * Find all SegRolUsuario entities.
	 *
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SegRolUsuario> all SegRolUsuario entities
	 */
	@SuppressWarnings("unchecked")
	public List<SegRolUsuario> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all SegRolUsuario instances",
				Level.INFO, null);

		try {
			final String queryString = "select model from SegRolUsuario model";
			Query query = getEntityManager().createQuery(queryString);

			if ((rowStartIdxAndCount != null) &&
					(rowStartIdxAndCount.length > 0)) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);

				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);

					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<SegRolUsuario> findByCriteria(String whereCondition) {
		EntityManagerHelper.log("finding SegRolUsuario " + whereCondition,
				Level.INFO, null);

		try {
			String where = ((whereCondition == null) ||
					(whereCondition.length() == 0)) ? "" : ("where " +
							whereCondition);
			final String queryString = "select model from SegRolUsuario model " +
					where;
			Query query = getEntityManager().createQuery(queryString);
			List<SegRolUsuario> entitiesList = query.getResultList();

			return entitiesList;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find By Criteria in SegRolUsuario failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<SegRolUsuario> findPageSegRolUsuario(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults) {
		EntityManagerHelper.log("finding SegRolUsuario findPageSegRolUsuario",
				Level.INFO, null);

		if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
			try {
				String queryString = "select model from SegRolUsuario model order by model." +
						sortColumnName + " " + (sortAscending ? "asc" : "desc");

				return getEntityManager().createQuery(queryString)
						.setFirstResult(startRow).setMaxResults(maxResults)
						.getResultList();
			} catch (RuntimeException re) {
				throw re;
			}
		} else {
			try {
				String queryString = "select model from SegRolUsuario model";

				return getEntityManager().createQuery(queryString)
						.setFirstResult(startRow).setMaxResults(maxResults)
						.getResultList();
			} catch (RuntimeException re) {
				throw re;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Long findTotalNumberSegRolUsuario() {
		EntityManagerHelper.log("finding SegRolUsuario count", Level.INFO, null);

		try {
			String queryString = "select count(*) from SegRolUsuario model";

			return (Long) getEntityManager().createQuery(queryString)
					.getSingleResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<Object[]> getSegRolesUsuariosBySystem(Long usuSession,String sistema) {

		try {
			String queryString = "SELECT DISTINCT rolUsuario.rluCodigo, rolUsuario.rluEstadoRegistro, roles.rolCodigo, usuario.usuCodigo " +
					"FROM SegRol roles, SegRolUsuario rolUsuario, SegUsuario usuario, SegSistema sistema " +
					"WHERE usuario.usuCodigo = rolUsuario.segUsuarioBySegUsuarioUsuCodigo.usuCodigo " +
					"AND rolUsuario.segRol.rolCodigo = roles.rolCodigo " +
					"AND sistema.sisCodigo = roles.segSistema.sisCodigo " +
					"AND sistema.sisCodigo in " +
					"(SELECT rol.segSistema.sisCodigo " +
					"FROM SegRolUsuario rolUsu " +
					"join rolUsu.segRol rol " +
					"WHERE rolUsu.segUsuarioBySegUsuarioUsuCodigo.usuCodigo =:usuSession " +
					"AND rol.rolNombre LIKE 'ADMIN%') " +
					"AND sistema.sisCodigo =:sistema";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("usuSession", usuSession);
			query.setParameter("sistema", Long.parseLong(sistema.toString()));

			List<Object[]> rolesUsuario =  query.getResultList();

			return rolesUsuario;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getSegRolesPreviamenteSeleccionados(String usuCodigo) {

		try {
//			String queryString = "SELECT rol.rolCodigo " +
//					"FROM SegRolUsuario rolUsuario, SegUsuario usuario, SegRol rol, SegSistema sistema " +
//					"WHERE usuario.usuCodigo = rolUsuario.segUsuarioBySegUsuarioUsuCodigo.usuCodigo " +
//					"AND rolUsuario.segRol.rolCodigo = rol.rolCodigo " +
//					"AND rol.segSistema.sisCodigo = sistema.sisCodigo " +
//					"AND usuario.usuCodigo =:usuCodigo " +
//					"AND sistema.sisCodigo =:sistema";
			
			String queryString = "SELECT rol.rolCodigo " +
			"FROM SegRolUsuario rolUsuario, SegUsuario usuario, SegRol rol, SegSistema sistema " +
			"WHERE usuario.usuCodigo = rolUsuario.segUsuarioBySegUsuarioUsuCodigo.usuCodigo " +
			"AND rolUsuario.segRol.rolCodigo = rol.rolCodigo " +
			"AND rol.segSistema.sisCodigo = sistema.sisCodigo " +
			"AND usuario.usuCodigo =:usuCodigo";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("usuCodigo", Long.parseLong(usuCodigo.toString()));
//			query.setParameter("sistema", Long.parseLong(sistema.toString()));

			List<Object[]> rolesUsuarioPreviamenteSeleccionados =  query.getResultList();

			return rolesUsuarioPreviamenteSeleccionados;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getSegRolesData(Long usuCodigo, String codigoRol) {
		
		try {
		
			String queryString = "SELECT rolUsuario.rluCodigo " +
					"FROM SegRolUsuario rolUsuario " +
					"WHERE rolUsuario.segRol.rolCodigo =:codigoRol " +
					"AND rolUsuario.segUsuarioBySegUsuarioUsuCodigo.usuCodigo =:usuCodigo";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("usuCodigo", Long.parseLong(usuCodigo.toString()));
			query.setParameter("codigoRol", Long.parseLong(codigoRol.toString()));

			List<Object[]> rolesData =  query.getResultList();

			return rolesData;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getSegRolesUsers(Long usuCodigo) {
		try {
			
			String queryString = "SELECT rolUsuario.rluCodigo " +
					"FROM SegRolUsuario rolUsuario " +
					"WHERE rolUsuario.segUsuarioBySegUsuarioUsuCodigo.usuCodigo =:usuCodigo";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("usuCodigo", Long.parseLong(usuCodigo.toString()));

			List<Object[]> rolesUsers =  query.getResultList();

			return rolesUsers;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
