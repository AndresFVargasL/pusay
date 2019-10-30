package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegHistorialConstrasena;


/**
* A data access object (DAO) providing persistence and search support for
* SegHistorialConstrasena entities. Transaction control of the save(), update() and delete()
* operations must be handled externally by senders of these methods or must be
* manually added to each of these methods for data to be persisted to the JPA
* datastore.
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* @see lidis.SegHistorialConstrasena
*
*/
public class SegHistorialConstrasenaDAO implements ISegHistorialConstrasenaDAO {
    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
    * Perform an initial save of a previously unsaved SegHistorialConstrasena entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegHistorialConstrasenaDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegHistorialConstrasena entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegHistorialConstrasena entity) {
        EntityManagerHelper.log("saving SegHistorialConstrasena instance",
            Level.INFO, null);

        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Delete a persistent SegHistorialConstrasena entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegHistorialConstrasenaDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegHistorialConstrasena entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegHistorialConstrasena entity) {
        EntityManagerHelper.log("deleting SegHistorialConstrasena instance",
            Level.INFO, null);

        try {
            entity = getEntityManager()
                         .getReference(SegHistorialConstrasena.class,
                    entity.getHcoCodigo());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Persist a previously saved SegHistorialConstrasena entity and return it or a copy of it
    * to the sender. A copy of the SegHistorialConstrasena entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = SegHistorialConstrasenaDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegHistorialConstrasena entity to update
    * @return SegHistorialConstrasena the persisted SegHistorialConstrasena entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegHistorialConstrasena update(SegHistorialConstrasena entity) {
        EntityManagerHelper.log("updating SegHistorialConstrasena instance",
            Level.INFO, null);

        try {
            SegHistorialConstrasena result = getEntityManager()
                                                 .merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);

            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public SegHistorialConstrasena findById(Long id) {
        EntityManagerHelper.log(
            "finding SegHistorialConstrasena instance with id: " + id,
            Level.INFO, null);

        try {
            SegHistorialConstrasena instance = getEntityManager()
                                                   .find(SegHistorialConstrasena.class,
                    id);

            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Find all  SegHistorialConstrasena entities with a specific property value.
    *
    * @param propertyName
    *            the metaData.name of the  SegHistorialConstrasena property to query
    * @param value
    *            the property value to match
    * @return List< SegHistorialConstrasena> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegHistorialConstrasena> findByProperty(String propertyName,
        final Object value) {
        EntityManagerHelper.log(
            "finding  SegHistorialConstrasena instance with property: " +
            propertyName + ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from  SegHistorialConstrasena model where model." +
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
    * Find all SegHistorialConstrasena entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegHistorialConstrasena property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            number of results to return.
    * @return List<SegHistorialConstrasena> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegHistorialConstrasena> findByProperty(String propertyName,
        final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log(
            "finding SegHistorialConstrasena instance with property: " +
            propertyName + ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from SegHistorialConstrasena model where model." +
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

    public List<SegHistorialConstrasena> findByHcoCodigo(Object hcoCodigo,
        int... rowStartIdxAndCount) {
        return findByProperty(HCOCODIGO, hcoCodigo, rowStartIdxAndCount);
    }

    public List<SegHistorialConstrasena> findByHcoCodigo(Object hcoCodigo) {
        return findByProperty(HCOCODIGO, hcoCodigo);
    }

    public List<SegHistorialConstrasena> findByHcoConstrasena(
        Object hcoConstrasena, int... rowStartIdxAndCount) {
        return findByProperty(HCOCONSTRASENA, hcoConstrasena,
            rowStartIdxAndCount);
    }

    public List<SegHistorialConstrasena> findByHcoConstrasena(
        Object hcoConstrasena) {
        return findByProperty(HCOCONSTRASENA, hcoConstrasena);
    }

    public List<SegHistorialConstrasena> findByHcoDetalleCambio(
        Object hcoDetalleCambio, int... rowStartIdxAndCount) {
        return findByProperty(HCODETALLECAMBIO, hcoDetalleCambio,
            rowStartIdxAndCount);
    }

    public List<SegHistorialConstrasena> findByHcoDetalleCambio(
        Object hcoDetalleCambio) {
        return findByProperty(HCODETALLECAMBIO, hcoDetalleCambio);
    }

    public List<SegHistorialConstrasena> findByHcoFechaCambio(
        Object hcoFechaCambio, int... rowStartIdxAndCount) {
        return findByProperty(HCOFECHACAMBIO, hcoFechaCambio,
            rowStartIdxAndCount);
    }

    public List<SegHistorialConstrasena> findByHcoFechaCambio(
        Object hcoFechaCambio) {
        return findByProperty(HCOFECHACAMBIO, hcoFechaCambio);
    }

    /**
    * Find all SegHistorialConstrasena entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegHistorialConstrasena> all SegHistorialConstrasena entities
    */
    @SuppressWarnings("unchecked")
    public List<SegHistorialConstrasena> findAll(
        final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all SegHistorialConstrasena instances",
            Level.INFO, null);

        try {
            final String queryString = "select model from SegHistorialConstrasena model";
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

    public List<SegHistorialConstrasena> findByCriteria(String whereCondition) {
        EntityManagerHelper.log("finding SegHistorialConstrasena " +
            whereCondition, Level.INFO, null);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SegHistorialConstrasena model " +
                where;
            Query query = getEntityManager().createQuery(queryString);
            List<SegHistorialConstrasena> entitiesList = query.getResultList();

            return entitiesList;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find By Criteria in SegHistorialConstrasena failed",
                Level.SEVERE, re);
            throw re;
        }
    }

    public List<SegHistorialConstrasena> findPageSegHistorialConstrasena(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) {
        EntityManagerHelper.log("finding SegHistorialConstrasena findPageSegHistorialConstrasena",
            Level.INFO, null);

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SegHistorialConstrasena model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SegHistorialConstrasena model";

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Long findTotalNumberSegHistorialConstrasena() {
        EntityManagerHelper.log("finding SegHistorialConstrasena count",
            Level.INFO, null);

        try {
            String queryString = "select count(*) from SegHistorialConstrasena model";

            return (Long) getEntityManager().createQuery(queryString)
                              .getSingleResult();
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
