package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegGrupoOpcion;


/**
* A data access object (DAO) providing persistence and search support for
* SegGrupoOpcion entities. Transaction control of the save(), update() and delete()
* operations must be handled externally by senders of these methods or must be
* manually added to each of these methods for data to be persisted to the JPA
* datastore.
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* @see lidis.SegGrupoOpcion
*
*/
public class SegGrupoOpcionDAO implements ISegGrupoOpcionDAO {
    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
    * Perform an initial save of a previously unsaved SegGrupoOpcion entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegGrupoOpcionDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegGrupoOpcion entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegGrupoOpcion entity) {
        EntityManagerHelper.log("saving SegGrupoOpcion instance", Level.INFO,
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
    * Delete a persistent SegGrupoOpcion entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegGrupoOpcionDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegGrupoOpcion entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegGrupoOpcion entity) {
        EntityManagerHelper.log("deleting SegGrupoOpcion instance", Level.INFO,
            null);

        try {
            entity = getEntityManager()
                         .getReference(SegGrupoOpcion.class,
                    entity.getGruCodigo());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Persist a previously saved SegGrupoOpcion entity and return it or a copy of it
    * to the sender. A copy of the SegGrupoOpcion entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = SegGrupoOpcionDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegGrupoOpcion entity to update
    * @return SegGrupoOpcion the persisted SegGrupoOpcion entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegGrupoOpcion update(SegGrupoOpcion entity) {
        EntityManagerHelper.log("updating SegGrupoOpcion instance", Level.INFO,
            null);

        try {
            SegGrupoOpcion result = getEntityManager()
                                        .merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);

            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public SegGrupoOpcion findById(Long id) {
        EntityManagerHelper.log("finding SegGrupoOpcion instance with id: " +
            id, Level.INFO, null);

        try {
            SegGrupoOpcion instance = getEntityManager()
                                          .find(SegGrupoOpcion.class, id);

            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Find all  SegGrupoOpcion entities with a specific property value.
    *
    * @param propertyName
    *            the metaData.name of the  SegGrupoOpcion property to query
    * @param value
    *            the property value to match
    * @return List< SegGrupoOpcion> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegGrupoOpcion> findByProperty(String propertyName,
        final Object value) {
        EntityManagerHelper.log(
            "finding  SegGrupoOpcion instance with property: " + propertyName +
            ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from  SegGrupoOpcion model where model." +
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
    * Find all SegGrupoOpcion entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegGrupoOpcion property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            number of results to return.
    * @return List<SegGrupoOpcion> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegGrupoOpcion> findByProperty(String propertyName,
        final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log(
            "finding SegGrupoOpcion instance with property: " + propertyName +
            ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from SegGrupoOpcion model where model." +
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

    public List<SegGrupoOpcion> findByGruCodigo(Object gruCodigo,
        int... rowStartIdxAndCount) {
        return findByProperty(GRUCODIGO, gruCodigo, rowStartIdxAndCount);
    }

    public List<SegGrupoOpcion> findByGruCodigo(Object gruCodigo) {
        return findByProperty(GRUCODIGO, gruCodigo);
    }

    public List<SegGrupoOpcion> findByGruDescripcion(Object gruDescripcion,
        int... rowStartIdxAndCount) {
        return findByProperty(GRUDESCRIPCION, gruDescripcion,
            rowStartIdxAndCount);
    }

    public List<SegGrupoOpcion> findByGruDescripcion(Object gruDescripcion) {
        return findByProperty(GRUDESCRIPCION, gruDescripcion);
    }

    public List<SegGrupoOpcion> findByGruEstadoRegistro(
        Object gruEstadoRegistro, int... rowStartIdxAndCount) {
        return findByProperty(GRUESTADOREGISTRO, gruEstadoRegistro,
            rowStartIdxAndCount);
    }

    public List<SegGrupoOpcion> findByGruEstadoRegistro(
        Object gruEstadoRegistro) {
        return findByProperty(GRUESTADOREGISTRO, gruEstadoRegistro);
    }

    public List<SegGrupoOpcion> findByGruLlaveAcceso(Object gruLlaveAcceso,
        int... rowStartIdxAndCount) {
        return findByProperty(GRULLAVEACCESO, gruLlaveAcceso,
            rowStartIdxAndCount);
    }

    public List<SegGrupoOpcion> findByGruLlaveAcceso(Object gruLlaveAcceso) {
        return findByProperty(GRULLAVEACCESO, gruLlaveAcceso);
    }

    public List<SegGrupoOpcion> findByGruNombre(Object gruNombre,
        int... rowStartIdxAndCount) {
        return findByProperty(GRUNOMBRE, gruNombre, rowStartIdxAndCount);
    }

    public List<SegGrupoOpcion> findByGruNombre(Object gruNombre) {
        return findByProperty(GRUNOMBRE, gruNombre);
    }

    /**
    * Find all SegGrupoOpcion entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegGrupoOpcion> all SegGrupoOpcion entities
    */
    @SuppressWarnings("unchecked")
    public List<SegGrupoOpcion> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all SegGrupoOpcion instances",
            Level.INFO, null);

        try {
            final String queryString = "select model from SegGrupoOpcion model";
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

    public List<SegGrupoOpcion> findByCriteria(String whereCondition) {
        EntityManagerHelper.log("finding SegGrupoOpcion " + whereCondition,
            Level.INFO, null);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SegGrupoOpcion model " +
                where;
            Query query = getEntityManager().createQuery(queryString);
            List<SegGrupoOpcion> entitiesList = query.getResultList();

            return entitiesList;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find By Criteria in SegGrupoOpcion failed",
                Level.SEVERE, re);
            throw re;
        }
    }

    public List<SegGrupoOpcion> findPageSegGrupoOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        EntityManagerHelper.log("finding SegGrupoOpcion findPageSegGrupoOpcion",
            Level.INFO, null);

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SegGrupoOpcion model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SegGrupoOpcion model";

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Long findTotalNumberSegGrupoOpcion() {
        EntityManagerHelper.log("finding SegGrupoOpcion count", Level.INFO, null);

        try {
            String queryString = "select count(*) from SegGrupoOpcion model";

            return (Long) getEntityManager().createQuery(queryString)
                              .getSingleResult();
        } catch (RuntimeException re) {
            throw re;
        }
    }

	@Override
	public List<Object[]> getSegGrupoOpcionesBySystem(Long usuSession, String sistema) {
		try {
			
			String queryString = "SELECT grupos.gruCodigo, grupos.gruNombre, grupos.gruEstadoRegistro, grupos.gruLlaveAcceso, grupos.gruDescripcion, " +
					"grupos.segGrupoOpcion.gruCodigo, sist.sisCodigo, sist.sisNombre " +
					"FROM SegGrupoOpcion grupos " +
					"join grupos.segSistema sist " +
					"WHERE sist.sisCodigo in " +
					"(SELECT rol.segSistema.sisCodigo " +
					"FROM SegRolUsuario rolUsu " +
					"join rolUsu.segRol rol " +
					"WHERE rolUsu.segUsuarioBySegUsuarioUsuCodigo.usuCodigo =:usuSession " +
					"AND rol.rolNombre LIKE 'ADMIN%') " +
					"AND sist.sisCodigo =:sistema";
			
			Query query = getEntityManager().createQuery(queryString);
            query.setParameter("usuSession", usuSession);
            query.setParameter("sistema", Long.parseLong(sistema.toString()));
            
            List<Object[]> gruposOpc =  query.getResultList();
            
			return gruposOpc;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
