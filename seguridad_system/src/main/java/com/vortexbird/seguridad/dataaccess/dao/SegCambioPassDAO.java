package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegCambioPass;


/**
* A data access object (DAO) providing persistence and search support for
* SegCambioPass entities. Transaction control of the save(), update() and delete()
* operations must be handled externally by senders of these methods or must be
* manually added to each of these methods for data to be persisted to the JPA
* datastore.
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* @see lidis.SegCambioPass
*
*/
public class SegCambioPassDAO implements ISegCambioPassDAO {
    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
    * Perform an initial save of a previously unsaved SegCambioPass entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegCambioPassDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegCambioPass entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegCambioPass entity) {
        EntityManagerHelper.log("saving SegCambioPass instance", Level.INFO,
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
    * Delete a persistent SegCambioPass entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegCambioPassDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegCambioPass entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegCambioPass entity) {
        EntityManagerHelper.log("deleting SegCambioPass instance", Level.INFO,
            null);

        try {
            entity = getEntityManager()
                         .getReference(SegCambioPass.class,
                    entity.getCapCodigo());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Persist a previously saved SegCambioPass entity and return it or a copy of it
    * to the sender. A copy of the SegCambioPass entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = SegCambioPassDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegCambioPass entity to update
    * @return SegCambioPass the persisted SegCambioPass entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegCambioPass update(SegCambioPass entity) {
        EntityManagerHelper.log("updating SegCambioPass instance", Level.INFO,
            null);

        try {
            SegCambioPass result = getEntityManager()
                                       .merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);

            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public SegCambioPass findById(Long id) {
        EntityManagerHelper.log("finding SegCambioPass instance with id: " +
            id, Level.INFO, null);

        try {
            SegCambioPass instance = getEntityManager()
                                         .find(SegCambioPass.class, id);

            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Find all  SegCambioPass entities with a specific property value.
    *
    * @param propertyName
    *            the metaData.name of the  SegCambioPass property to query
    * @param value
    *            the property value to match
    * @return List< SegCambioPass> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegCambioPass> findByProperty(String propertyName,
        final Object value) {
        EntityManagerHelper.log(
            "finding  SegCambioPass instance with property: " + propertyName +
            ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from  SegCambioPass model where model." +
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
    * Find all SegCambioPass entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegCambioPass property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            number of results to return.
    * @return List<SegCambioPass> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegCambioPass> findByProperty(String propertyName,
        final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log(
            "finding SegCambioPass instance with property: " + propertyName +
            ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from SegCambioPass model where model." +
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

    public List<SegCambioPass> findByCapCodigo(Object capCodigo,
        int... rowStartIdxAndCount) {
        return findByProperty(CAPCODIGO, capCodigo, rowStartIdxAndCount);
    }

    public List<SegCambioPass> findByCapCodigo(Object capCodigo) {
        return findByProperty(CAPCODIGO, capCodigo);
    }

    public List<SegCambioPass> findByCapEstado(Object capEstado,
        int... rowStartIdxAndCount) {
        return findByProperty(CAPESTADO, capEstado, rowStartIdxAndCount);
    }

    public List<SegCambioPass> findByCapEstado(Object capEstado) {
        return findByProperty(CAPESTADO, capEstado);
    }

    public List<SegCambioPass> findByCapFechaFin(Object capFechaFin,
        int... rowStartIdxAndCount) {
        return findByProperty(CAPFECHAFIN, capFechaFin, rowStartIdxAndCount);
    }

    public List<SegCambioPass> findByCapFechaFin(Object capFechaFin) {
        return findByProperty(CAPFECHAFIN, capFechaFin);
    }

    public List<SegCambioPass> findByCapFechaIni(Object capFechaIni,
        int... rowStartIdxAndCount) {
        return findByProperty(CAPFECHAINI, capFechaIni, rowStartIdxAndCount);
    }

    public List<SegCambioPass> findByCapFechaIni(Object capFechaIni) {
        return findByProperty(CAPFECHAINI, capFechaIni);
    }

    public List<SegCambioPass> findByCapToken(Object capToken,
        int... rowStartIdxAndCount) {
        return findByProperty(CAPTOKEN, capToken, rowStartIdxAndCount);
    }

    public List<SegCambioPass> findByCapToken(Object capToken) {
        return findByProperty(CAPTOKEN, capToken);
    }

    /**
    * Find all SegCambioPass entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegCambioPass> all SegCambioPass entities
    */
    @SuppressWarnings("unchecked")
    public List<SegCambioPass> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all SegCambioPass instances",
            Level.INFO, null);

        try {
            final String queryString = "select model from SegCambioPass model";
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

    public List<SegCambioPass> findByCriteria(String whereCondition) {
        EntityManagerHelper.log("finding SegCambioPass " + whereCondition,
            Level.INFO, null);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SegCambioPass model " +
                where;
            Query query = getEntityManager().createQuery(queryString);
            List<SegCambioPass> entitiesList = query.getResultList();

            return entitiesList;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find By Criteria in SegCambioPass failed",
                Level.SEVERE, re);
            throw re;
        }
    }

    public List<SegCambioPass> findPageSegCambioPass(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        EntityManagerHelper.log("finding SegCambioPass findPageSegCambioPass",
            Level.INFO, null);

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SegCambioPass model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SegCambioPass model";

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Long findTotalNumberSegCambioPass() {
        EntityManagerHelper.log("finding SegCambioPass count", Level.INFO, null);

        try {
            String queryString = "select count(*) from SegCambioPass model";

            return (Long) getEntityManager().createQuery(queryString)
                              .getSingleResult();
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
