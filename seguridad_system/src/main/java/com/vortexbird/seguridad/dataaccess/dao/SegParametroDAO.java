package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegParametro;


/**
* A data access object (DAO) providing persistence and search support for
* SegParametro entities. Transaction control of the save(), update() and delete()
* operations must be handled externally by senders of these methods or must be
* manually added to each of these methods for data to be persisted to the JPA
* datastore.
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* @see lidis.SegParametro
*
*/
public class SegParametroDAO implements ISegParametroDAO {
    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
    * Perform an initial save of a previously unsaved SegParametro entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegParametroDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegParametro entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegParametro entity) {
        EntityManagerHelper.log("saving SegParametro instance", Level.INFO, null);

        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Delete a persistent SegParametro entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegParametroDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegParametro entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegParametro entity) {
        EntityManagerHelper.log("deleting SegParametro instance", Level.INFO,
            null);

        try {
            entity = getEntityManager()
                         .getReference(SegParametro.class, entity.getParCodigo());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Persist a previously saved SegParametro entity and return it or a copy of it
    * to the sender. A copy of the SegParametro entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = SegParametroDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegParametro entity to update
    * @return SegParametro the persisted SegParametro entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegParametro update(SegParametro entity) {
        EntityManagerHelper.log("updating SegParametro instance", Level.INFO,
            null);

        try {
            SegParametro result = getEntityManager()
                                      .merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);

            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public SegParametro findById(Long id) {
        EntityManagerHelper.log("finding SegParametro instance with id: " + id,
            Level.INFO, null);

        try {
            SegParametro instance = getEntityManager()
                                        .find(SegParametro.class, id);

            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Find all  SegParametro entities with a specific property value.
    *
    * @param propertyName
    *            the metaData.name of the  SegParametro property to query
    * @param value
    *            the property value to match
    * @return List< SegParametro> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegParametro> findByProperty(String propertyName,
        final Object value) {
        EntityManagerHelper.log(
            "finding  SegParametro instance with property: " + propertyName +
            ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from  SegParametro model where model." +
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
    * Find all SegParametro entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegParametro property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            number of results to return.
    * @return List<SegParametro> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegParametro> findByProperty(String propertyName,
        final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding SegParametro instance with property: " +
            propertyName + ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from SegParametro model where model." +
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

    public List<SegParametro> findByParCodigo(Object parCodigo,
        int... rowStartIdxAndCount) {
        return findByProperty(PARCODIGO, parCodigo, rowStartIdxAndCount);
    }

    public List<SegParametro> findByParCodigo(Object parCodigo) {
        return findByProperty(PARCODIGO, parCodigo);
    }

    public List<SegParametro> findByParEstadoRegistro(
        Object parEstadoRegistro, int... rowStartIdxAndCount) {
        return findByProperty(PARESTADOREGISTRO, parEstadoRegistro,
            rowStartIdxAndCount);
    }

    public List<SegParametro> findByParEstadoRegistro(Object parEstadoRegistro) {
        return findByProperty(PARESTADOREGISTRO, parEstadoRegistro);
    }

    public List<SegParametro> findByParNombre(Object parNombre,
        int... rowStartIdxAndCount) {
        return findByProperty(PARNOMBRE, parNombre, rowStartIdxAndCount);
    }

    public List<SegParametro> findByParNombre(Object parNombre) {
        return findByProperty(PARNOMBRE, parNombre);
    }

    public List<SegParametro> findByParValorAlfanumerico(
        Object parValorAlfanumerico, int... rowStartIdxAndCount) {
        return findByProperty(PARVALORALFANUMERICO, parValorAlfanumerico,
            rowStartIdxAndCount);
    }

    public List<SegParametro> findByParValorAlfanumerico(
        Object parValorAlfanumerico) {
        return findByProperty(PARVALORALFANUMERICO, parValorAlfanumerico);
    }

    public List<SegParametro> findByParValorFecha(Object parValorFecha,
        int... rowStartIdxAndCount) {
        return findByProperty(PARVALORFECHA, parValorFecha, rowStartIdxAndCount);
    }

    public List<SegParametro> findByParValorFecha(Object parValorFecha) {
        return findByProperty(PARVALORFECHA, parValorFecha);
    }

    public List<SegParametro> findByParValorNumerico(Object parValorNumerico,
        int... rowStartIdxAndCount) {
        return findByProperty(PARVALORNUMERICO, parValorNumerico,
            rowStartIdxAndCount);
    }

    public List<SegParametro> findByParValorNumerico(Object parValorNumerico) {
        return findByProperty(PARVALORNUMERICO, parValorNumerico);
    }

    /**
    * Find all SegParametro entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegParametro> all SegParametro entities
    */
    @SuppressWarnings("unchecked")
    public List<SegParametro> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all SegParametro instances",
            Level.INFO, null);

        try {
            final String queryString = "select model from SegParametro model";
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

    public List<SegParametro> findByCriteria(String whereCondition) {
        EntityManagerHelper.log("finding SegParametro " + whereCondition,
            Level.INFO, null);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SegParametro model " +
                where;
            Query query = getEntityManager().createQuery(queryString);
            List<SegParametro> entitiesList = query.getResultList();

            return entitiesList;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find By Criteria in SegParametro failed",
                Level.SEVERE, re);
            throw re;
        }
    }

    public List<SegParametro> findPageSegParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        EntityManagerHelper.log("finding SegParametro findPageSegParametro",
            Level.INFO, null);

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SegParametro model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SegParametro model";

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Long findTotalNumberSegParametro() {
        EntityManagerHelper.log("finding SegParametro count", Level.INFO, null);

        try {
            String queryString = "select count(*) from SegParametro model";

            return (Long) getEntityManager().createQuery(queryString)
                              .getSingleResult();
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
