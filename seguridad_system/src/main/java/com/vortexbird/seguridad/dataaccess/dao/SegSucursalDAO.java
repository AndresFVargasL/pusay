package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegSucursal;


/**
* A data access object (DAO) providing persistence and search support for
* SegSucursal entities. Transaction control of the save(), update() and delete()
* operations must be handled externally by senders of these methods or must be
* manually added to each of these methods for data to be persisted to the JPA
* datastore.
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* @see lidis.SegSucursal
*
*/
public class SegSucursalDAO implements ISegSucursalDAO {
    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
    * Perform an initial save of a previously unsaved SegSucursal entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegSucursalDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegSucursal entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegSucursal entity) {
        EntityManagerHelper.log("saving SegSucursal instance", Level.INFO, null);

        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Delete a persistent SegSucursal entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegSucursalDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegSucursal entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegSucursal entity) {
        EntityManagerHelper.log("deleting SegSucursal instance", Level.INFO,
            null);

        try {
            entity = getEntityManager()
                         .getReference(SegSucursal.class, entity.getSucCodigo());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Persist a previously saved SegSucursal entity and return it or a copy of it
    * to the sender. A copy of the SegSucursal entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = SegSucursalDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegSucursal entity to update
    * @return SegSucursal the persisted SegSucursal entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegSucursal update(SegSucursal entity) {
        EntityManagerHelper.log("updating SegSucursal instance", Level.INFO,
            null);

        try {
            SegSucursal result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);

            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public SegSucursal findById(Long id) {
        EntityManagerHelper.log("finding SegSucursal instance with id: " + id,
            Level.INFO, null);

        try {
            SegSucursal instance = getEntityManager().find(SegSucursal.class, id);

            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Find all  SegSucursal entities with a specific property value.
    *
    * @param propertyName
    *            the metaData.name of the  SegSucursal property to query
    * @param value
    *            the property value to match
    * @return List< SegSucursal> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegSucursal> findByProperty(String propertyName,
        final Object value) {
        EntityManagerHelper.log("finding  SegSucursal instance with property: " +
            propertyName + ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from  SegSucursal model where model." +
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
    * Find all SegSucursal entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegSucursal property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            number of results to return.
    * @return List<SegSucursal> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegSucursal> findByProperty(String propertyName,
        final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding SegSucursal instance with property: " +
            propertyName + ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from SegSucursal model where model." +
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

    public List<SegSucursal> findBySucCodigo(Object sucCodigo,
        int... rowStartIdxAndCount) {
        return findByProperty(SUCCODIGO, sucCodigo, rowStartIdxAndCount);
    }

    public List<SegSucursal> findBySucCodigo(Object sucCodigo) {
        return findByProperty(SUCCODIGO, sucCodigo);
    }

    public List<SegSucursal> findBySucCodigoInterno(Object sucCodigoInterno,
        int... rowStartIdxAndCount) {
        return findByProperty(SUCCODIGOINTERNO, sucCodigoInterno,
            rowStartIdxAndCount);
    }

    public List<SegSucursal> findBySucCodigoInterno(Object sucCodigoInterno) {
        return findByProperty(SUCCODIGOINTERNO, sucCodigoInterno);
    }

    public List<SegSucursal> findBySucEstadoRegistro(Object sucEstadoRegistro,
        int... rowStartIdxAndCount) {
        return findByProperty(SUCESTADOREGISTRO, sucEstadoRegistro,
            rowStartIdxAndCount);
    }

    public List<SegSucursal> findBySucEstadoRegistro(Object sucEstadoRegistro) {
        return findByProperty(SUCESTADOREGISTRO, sucEstadoRegistro);
    }

    public List<SegSucursal> findBySucNombre(Object sucNombre,
        int... rowStartIdxAndCount) {
        return findByProperty(SUCNOMBRE, sucNombre, rowStartIdxAndCount);
    }

    public List<SegSucursal> findBySucNombre(Object sucNombre) {
        return findByProperty(SUCNOMBRE, sucNombre);
    }

    /**
    * Find all SegSucursal entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegSucursal> all SegSucursal entities
    */
    @SuppressWarnings("unchecked")
    public List<SegSucursal> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all SegSucursal instances",
            Level.INFO, null);

        try {
            final String queryString = "select model from SegSucursal model";
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

    public List<SegSucursal> findByCriteria(String whereCondition) {
        EntityManagerHelper.log("finding SegSucursal " + whereCondition,
            Level.INFO, null);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SegSucursal model " +
                where;
            Query query = getEntityManager().createQuery(queryString);
            List<SegSucursal> entitiesList = query.getResultList();

            return entitiesList;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find By Criteria in SegSucursal failed",
                Level.SEVERE, re);
            throw re;
        }
    }

    public List<SegSucursal> findPageSegSucursal(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        EntityManagerHelper.log("finding SegSucursal findPageSegSucursal",
            Level.INFO, null);

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SegSucursal model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SegSucursal model";

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Long findTotalNumberSegSucursal() {
        EntityManagerHelper.log("finding SegSucursal count", Level.INFO, null);

        try {
            String queryString = "select count(*) from SegSucursal model";

            return (Long) getEntityManager().createQuery(queryString)
                              .getSingleResult();
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    
}
