package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegUsuario;


/**
 * A data access object (DAO) providing persistence and search support for
 * SegUsuario entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * @see lidis.SegUsuario
 *
 */
public class SegUsuarioDAO implements ISegUsuarioDAO {
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved SegUsuario entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SegUsuarioDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            SegUsuario entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SegUsuario entity) {
		EntityManagerHelper.log("saving SegUsuario instance", Level.INFO, null);

		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent SegUsuario entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SegUsuarioDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 *
	 * @param entity
	 *            SegUsuario entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SegUsuario entity) {
		EntityManagerHelper.log("deleting SegUsuario instance", Level.INFO, null);

		try {
			entity = getEntityManager()
					.getReference(SegUsuario.class, entity.getUsuCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved SegUsuario entity and return it or a copy of it
	 * to the sender. A copy of the SegUsuario entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = SegUsuarioDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            SegUsuario entity to update
	 * @return SegUsuario the persisted SegUsuario entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SegUsuario update(SegUsuario entity) {
		EntityManagerHelper.log("updating SegUsuario instance", Level.INFO, null);

		try {
			SegUsuario result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);

			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public SegUsuario findById(Long id) {
		EntityManagerHelper.log("finding SegUsuario instance with id: " + id,
				Level.INFO, null);

		try {
			SegUsuario instance = getEntityManager().find(SegUsuario.class, id);

			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all  SegUsuario entities with a specific property value.
	 *
	 * @param propertyName
	 *            the metaData.name of the  SegUsuario property to query
	 * @param value
	 *            the property value to match
	 * @return List< SegUsuario> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SegUsuario> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding  SegUsuario instance with property: " +
				propertyName + ", value: " + value, Level.INFO, null);

		try {
			final String queryString = "select model from  SegUsuario model where model." +
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
	 * Find all SegUsuario entities with a specific property value.
	 *
	 * @param propertyName
	 *            the name of the SegUsuario property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<SegUsuario> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SegUsuario> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding SegUsuario instance with property: " +
				propertyName + ", value: " + value, Level.INFO, null);

		try {
			final String queryString = "select model from SegUsuario model where model." +
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

	public List<SegUsuario> findByUsuApellidos(Object usuApellidos,
			int... rowStartIdxAndCount) {
		return findByProperty(USUAPELLIDOS, usuApellidos, rowStartIdxAndCount);
	}

	public List<SegUsuario> findByUsuApellidos(Object usuApellidos) {
		return findByProperty(USUAPELLIDOS, usuApellidos);
	}

	public List<SegUsuario> findByUsuCodigo(Object usuCodigo,
			int... rowStartIdxAndCount) {
		return findByProperty(USUCODIGO, usuCodigo, rowStartIdxAndCount);
	}

	public List<SegUsuario> findByUsuCodigo(Object usuCodigo) {
		return findByProperty(USUCODIGO, usuCodigo);
	}

	public List<SegUsuario> findByUsuCodigoInterno(Object usuCodigoInterno,
			int... rowStartIdxAndCount) {
		return findByProperty(USUCODIGOINTERNO, usuCodigoInterno,
				rowStartIdxAndCount);
	}

	public List<SegUsuario> findByUsuCodigoInterno(Object usuCodigoInterno) {
		return findByProperty(USUCODIGOINTERNO, usuCodigoInterno);
	}

	public List<SegUsuario> findByUsuConstrasena(Object usuConstrasena,
			int... rowStartIdxAndCount) {
		return findByProperty(USUCONSTRASENA, usuConstrasena,
				rowStartIdxAndCount);
	}

	public List<SegUsuario> findByUsuConstrasena(Object usuConstrasena) {
		return findByProperty(USUCONSTRASENA, usuConstrasena);
	}

	public List<SegUsuario> findByUsuCorreo(Object usuCorreo,
			int... rowStartIdxAndCount) {
		return findByProperty(USUCORREO, usuCorreo, rowStartIdxAndCount);
	}

	public List<SegUsuario> findByUsuCorreo(Object usuCorreo) {
		return findByProperty(USUCORREO, usuCorreo);
	}

	public List<SegUsuario> findByUsuEstadoRegistro(Object usuEstadoRegistro,
			int... rowStartIdxAndCount) {
		return findByProperty(USUESTADOREGISTRO, usuEstadoRegistro,
				rowStartIdxAndCount);
	}

	public List<SegUsuario> findByUsuEstadoRegistro(Object usuEstadoRegistro) {
		return findByProperty(USUESTADOREGISTRO, usuEstadoRegistro);
	}

	public List<SegUsuario> findByUsuIntentosFallidos(
			Object usuIntentosFallidos, int... rowStartIdxAndCount) {
		return findByProperty(USUINTENTOSFALLIDOS, usuIntentosFallidos,
				rowStartIdxAndCount);
	}

	public List<SegUsuario> findByUsuIntentosFallidos(
			Object usuIntentosFallidos) {
		return findByProperty(USUINTENTOSFALLIDOS, usuIntentosFallidos);
	}

	public List<SegUsuario> findByUsuLogin(Object usuLogin,
			int... rowStartIdxAndCount) {
		return findByProperty(USULOGIN, usuLogin, rowStartIdxAndCount);
	}

	public List<SegUsuario> findByUsuLogin(Object usuLogin) {
		return findByProperty(USULOGIN, usuLogin);
	}

	public List<SegUsuario> findByUsuNombres(Object usuNombres,
			int... rowStartIdxAndCount) {
		return findByProperty(USUNOMBRES, usuNombres, rowStartIdxAndCount);
	}

	public List<SegUsuario> findByUsuNombres(Object usuNombres) {
		return findByProperty(USUNOMBRES, usuNombres);
	}

	public List<SegUsuario> findByUsuUltmimaModificacionPass(
			Object usuUltmimaModificacionPass, int... rowStartIdxAndCount) {
		return findByProperty(USUULTMIMAMODIFICACIONPASS,
				usuUltmimaModificacionPass, rowStartIdxAndCount);
	}

	public List<SegUsuario> findByUsuUltmimaModificacionPass(
			Object usuUltmimaModificacionPass) {
		return findByProperty(USUULTMIMAMODIFICACIONPASS,
				usuUltmimaModificacionPass);
	}

	/**
	 * Find all SegUsuario entities.
	 *
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SegUsuario> all SegUsuario entities
	 */
	@SuppressWarnings("unchecked")
	public List<SegUsuario> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all SegUsuario instances", Level.INFO,
				null);

		try {
			final String queryString = "select model from SegUsuario model";
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

	public List<SegUsuario> findByCriteria(String whereCondition) {
		EntityManagerHelper.log("finding SegUsuario " + whereCondition,
				Level.INFO, null);

		try {
			String where = ((whereCondition == null) ||
					(whereCondition.length() == 0)) ? "" : ("where " +
							whereCondition);
			final String queryString = "select model from SegUsuario model " +
					where;
			Query query = getEntityManager().createQuery(queryString);
			List<SegUsuario> entitiesList = query.getResultList();

			return entitiesList;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find By Criteria in SegUsuario failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<SegUsuario> findPageSegUsuario(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults) {
		EntityManagerHelper.log("finding SegUsuario findPageSegUsuario",
				Level.INFO, null);

		if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
			try {
				String queryString = "select model from SegUsuario model order by model." +
						sortColumnName + " " + (sortAscending ? "asc" : "desc");

				return getEntityManager().createQuery(queryString)
						.setFirstResult(startRow).setMaxResults(maxResults)
						.getResultList();
			} catch (RuntimeException re) {
				throw re;
			}
		} else {
			try {
				String queryString = "select model from SegUsuario model";

				return getEntityManager().createQuery(queryString)
						.setFirstResult(startRow).setMaxResults(maxResults)
						.getResultList();
			} catch (RuntimeException re) {
				throw re;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Long findTotalNumberSegUsuario() {
		EntityManagerHelper.log("finding SegUsuario count", Level.INFO, null);

		try {
			String queryString = "select count(*) from SegUsuario model";

			return (Long) getEntityManager().createQuery(queryString)
					.getSingleResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<Object[]> getSegUsersBySystem(Long usuSession, String sistema) {
		try {

			String queryString = "SELECT usuario.usuCodigo, usuario.usuApellidos, usuario.usuCodigoInterno, " +
					"usuario.usuConstrasena, usuario.usuCorreo, usuario.usuEstadoRegistro, usuario.usuIntentosFallidos, " +
					"usuario.usuLogin, usuario.usuNombres, usuario.usuUltmimaModificacionPass " +
					"FROM SegUsuario usuario, SegRolUsuario rolUsuario, SegRol rol, SegSistema sistema " +
					"WHERE usuario.usuCodigo = rolUsuario.segUsuarioBySegUsuarioUsuCodigo.usuCodigo " +
					"AND rolUsuario.segRol.rolCodigo = rol.rolCodigo " +
					"AND rol.segSistema.sisCodigo = sistema.sisCodigo " +
					"AND sistema.sisCodigo =:sistema";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("sistema", Long.parseLong(sistema.toString()));

			List<Object[]> users = query.getResultList();

			return users;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getSegUsersBySystemAndCias(Long sisCia, String compania) {

		try {
//			String queryString = "SELECT DISTINCT usuario.usuCodigo, usuario.usuApellidos, usuario.usuCodigoInterno, " +
//					"usuario.usuConstrasena, usuario.usuCorreo, usuario.usuEstadoRegistro, usuario.usuIntentosFallidos, " +
//					"usuario.usuLogin, usuario.usuNombres, usuario.usuUltmimaModificacionPass " +
//					"FROM SegUsuario usuario, SegRolUsuario rolUsuario, SegRol rol, SegSistema sistema, SegSistemaCia sisCia, " +
//					"SegCompania compania, SegPermiso permiso " +
//					"WHERE usuario.usuCodigo = rolUsuario.segUsuarioBySegUsuarioUsuCodigo.usuCodigo " +
//					"AND rolUsuario.segRol.rolCodigo = rol.rolCodigo " +
//					"AND permiso.segRol.rolCodigo = rol.rolCodigo " +
//					"AND permiso.segCompania.ciaCodigo = compania.ciaCodigo " +
//					"AND sisCia.segCompania.ciaCodigo = compania.ciaCodigo " +
//					"AND sisCia.segSistema.sisCodigo = sistema.sisCodigo " +
//					"AND sistema.sisCodigo =:sistema " +
//					"AND compania.ciaCodigo =:compania";

			String queryString = "SELECT DISTINCT perm.segUsuarioByUsuCodigo.usuCodigo, perm.perEstadoRegistro " +
					"FROM SegPermiso perm, SegUsuario segusu " +
					"WHERE perm.segUsuarioByUsuCodigo.usuCodigo=segusu.usuCodigo and perm.segSistemaCia.sicCodigo=:sisCia and perm.perEstadoRegistro=1 and segusu.usuCompaniaCiaCodigo.ciaCodigo="+compania+"";
			
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("sisCia", sisCia);

			List<Object[]> users = query.getResultList();

			return users;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getSegUsersByRol(Long codigoRoles) {
		try {

			String queryString = "SELECT usuario.usuCodigo, usuario.usuApellidos, usuario.usuCodigoInterno, " +
					"usuario.usuConstrasena, usuario.usuCorreo, usuario.usuEstadoRegistro, usuario.usuIntentosFallidos, " +
					"usuario.usuLogin, usuario.usuNombres, usuario.usuUltmimaModificacionPass " +
					"FROM SegUsuario usuario, SegRolUsuario rolUsuario, SegRol rol " +
					"WHERE usuario.usuCodigo = rolUsuario.segUsuarioBySegUsuarioUsuCodigo.usuCodigo " +
					"AND rolUsuario.segRol.rolCodigo = rol.rolCodigo " +
					"AND rol.rolCodigo =:roles " +
					"AND rol.rolNombre LIKE 'ADMIN%'";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("roles", codigoRoles);

			List<Object[]> users = query.getResultList();

			return users;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getUserAdmin() {
		try {

			String queryString = "SELECT DISTINCT usuario.usuCodigo, usuario.usuApellidos, usuario.usuCodigoInterno, " +
					"usuario.usuConstrasena, usuario.usuCorreo, usuario.usuEstadoRegistro, usuario.usuIntentosFallidos, " +
					"usuario.usuLogin, usuario.usuNombres, usuario.usuUltmimaModificacionPass " +
					"FROM SegUsuario usuario, SegRolUsuario rolUsuario, SegRol rol " +
					"WHERE usuario.usuCodigo = rolUsuario.segUsuarioBySegUsuarioUsuCodigo.usuCodigo " +
					"AND rolUsuario.segRol.rolCodigo = rol.rolCodigo " +
					"AND rol.rolNombre LIKE 'ADMIN%'";

			Query query = getEntityManager().createQuery(queryString);

			List<Object[]> usersAdmin = query.getResultList();

			return usersAdmin;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<SegUsuario> consultarUsuarioPorLogin(String login){

		return findByUsuLogin(login);
	}
}
