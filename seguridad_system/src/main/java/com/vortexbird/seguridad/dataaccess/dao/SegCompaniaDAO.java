package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegCompania;


/**
* A data access object (DAO) providing persistence and search support for
* SegCompania entities. Transaction control of the save(), update() and delete()
* operations must be handled externally by senders of these methods or must be
* manually added to each of these methods for data to be persisted to the JPA
* datastore.
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* @see lidis.SegCompania
*
*/
public class SegCompaniaDAO implements ISegCompaniaDAO {
    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
    * Perform an initial save of a previously unsaved SegCompania entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegCompaniaDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegCompania entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegCompania entity) {
        EntityManagerHelper.log("saving SegCompania instance", Level.INFO, null);

        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Delete a persistent SegCompania entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegCompaniaDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegCompania entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegCompania entity) {
        EntityManagerHelper.log("deleting SegCompania instance", Level.INFO,
            null);

        try {
            entity = getEntityManager()
                         .getReference(SegCompania.class, entity.getCiaCodigo());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Persist a previously saved SegCompania entity and return it or a copy of it
    * to the sender. A copy of the SegCompania entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = SegCompaniaDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegCompania entity to update
    * @return SegCompania the persisted SegCompania entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegCompania update(SegCompania entity) {
        EntityManagerHelper.log("updating SegCompania instance", Level.INFO,
            null);

        try {
            SegCompania result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);

            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public SegCompania findById(Long id) {
        EntityManagerHelper.log("finding SegCompania instance with id: " + id,
            Level.INFO, null);

        try {
            SegCompania instance = getEntityManager().find(SegCompania.class, id);

            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Find all  SegCompania entities with a specific property value.
    *
    * @param propertyName
    *            the metaData.name of the  SegCompania property to query
    * @param value
    *            the property value to match
    * @return List< SegCompania> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegCompania> findByProperty(String propertyName,
        final Object value) {
        EntityManagerHelper.log("finding  SegCompania instance with property: " +
            propertyName + ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from  SegCompania model where model." +
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
    * Find all SegCompania entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegCompania property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            number of results to return.
    * @return List<SegCompania> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegCompania> findByProperty(String propertyName,
        final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding SegCompania instance with property: " +
            propertyName + ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from SegCompania model where model." +
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

    public List<SegCompania> findByCiaCodigo(Object ciaCodigo,
        int... rowStartIdxAndCount) {
        return findByProperty(CIACODIGO, ciaCodigo, rowStartIdxAndCount);
    }

    public List<SegCompania> findByCiaCodigo(Object ciaCodigo) {
        return findByProperty(CIACODIGO, ciaCodigo);
    }

    public List<SegCompania> findByCiaCodigoInterno(Object ciaCodigoInterno,
        int... rowStartIdxAndCount) {
        return findByProperty(CIACODIGOINTERNO, ciaCodigoInterno,
            rowStartIdxAndCount);
    }

    public List<SegCompania> findByCiaCodigoInterno(Object ciaCodigoInterno) {
        return findByProperty(CIACODIGOINTERNO, ciaCodigoInterno);
    }

    public List<SegCompania> findByCiaEstadoRegistro(Object ciaEstadoRegistro,
        int... rowStartIdxAndCount) {
        return findByProperty(CIAESTADOREGISTRO, ciaEstadoRegistro,
            rowStartIdxAndCount);
    }

    public List<SegCompania> findByCiaEstadoRegistro(Object ciaEstadoRegistro) {
        return findByProperty(CIAESTADOREGISTRO, ciaEstadoRegistro);
    }

    public List<SegCompania> findByCiaNombre(Object ciaNombre,
        int... rowStartIdxAndCount) {
        return findByProperty(CIANOMBRE, ciaNombre, rowStartIdxAndCount);
    }

    public List<SegCompania> findByCiaNombre(Object ciaNombre) {
        return findByProperty(CIANOMBRE, ciaNombre);
    }

    /**
    * Find all SegCompania entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegCompania> all SegCompania entities
    */
    @SuppressWarnings("unchecked")
    public List<SegCompania> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all SegCompania instances",
            Level.INFO, null);

        try {
            final String queryString = "select model from SegCompania model";
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

    public List<SegCompania> findByCriteria(String whereCondition) {
        EntityManagerHelper.log("finding SegCompania " + whereCondition,
            Level.INFO, null);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SegCompania model " +
                where;
            Query query = getEntityManager().createQuery(queryString);
            List<SegCompania> entitiesList = query.getResultList();

            return entitiesList;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find By Criteria in SegCompania failed",
                Level.SEVERE, re);
            throw re;
        }
    }

    public List<SegCompania> findPageSegCompania(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        EntityManagerHelper.log("finding SegCompania findPageSegCompania",
            Level.INFO, null);

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SegCompania model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SegCompania model";

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Long findTotalNumberSegCompania() {
        EntityManagerHelper.log("finding SegCompania count", Level.INFO, null);

        try {
            String queryString = "select count(*) from SegCompania model";

            return (Long) getEntityManager().createQuery(queryString)
                              .getSingleResult();
        } catch (RuntimeException re) {
            throw re;
        }
    }

	@Override
	public List<Object[]> findCiaByUserRol(Long usuSession, long sistema) {
		try {
			String queryString = "SELECT DISTINCT compania.ciaCodigo, compania.ciaNombre " +
					"FROM SegSistemaCia sistemaCia, SegCompania compania, SegSistema sistema, " +
					"SegPermiso permiso, SegUsuario usuario, SegRol rol " +
					"WHERE sistemaCia.segCompania.ciaCodigo = compania.ciaCodigo " +
					"AND sistemaCia.segSistema.sisCodigo = sistema.sisCodigo " +
					"AND sistemaCia.sicCodigo = permiso.segSistemaCia.sicCodigo " +
					"AND permiso.segUsuarioByUsuCodigo.usuCodigo = usuario.usuCodigo " +
					"AND rol.segSistema.sisCodigo = sistema.sisCodigo " +
					"AND rol.rolNombre LIKE 'ADMIN%' " +
					"AND sistema.sisCodigo =:sistema " +
					"AND usuario.usuCodigo =:usuSession";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("usuSession", usuSession);
			query.setParameter("sistema", sistema);

			List<Object[]> codigoCiasByUsuarioRol =  query.getResultList();

			return codigoCiasByUsuarioRol;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> findCiaRol(Long sistema) {
		
		try {
			String queryString = "SELECT compania.ciaCodigo, compania.ciaNombre " +
					"FROM SegSistemaCia sistemaCia, SegCompania compania, SegSistema sistema " +
					"WHERE sistemaCia.segCompania.ciaCodigo = compania.ciaCodigo " +
					"AND sistemaCia.segSistema.sisCodigo = sistema.sisCodigo " +
					"AND sistema.sisCodigo =:sistema";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("sistema", sistema);

			List<Object[]> codigoCiasRol =  query.getResultList();

			return codigoCiasRol;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}