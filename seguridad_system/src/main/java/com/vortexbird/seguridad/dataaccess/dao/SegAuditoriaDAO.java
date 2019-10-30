package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegAuditoria;


/**
* A data access object (DAO) providing persistence and search support for
* SegAuditoria entities. Transaction control of the save(), update() and delete()
* operations must be handled externally by senders of these methods or must be
* manually added to each of these methods for data to be persisted to the JPA
* datastore.
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* @see lidis.SegAuditoria
*
*/
public class SegAuditoriaDAO implements ISegAuditoriaDAO {
    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
    * Perform an initial save of a previously unsaved SegAuditoria entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegAuditoriaDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegAuditoria entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegAuditoria entity) {
        EntityManagerHelper.log("saving SegAuditoria instance", Level.INFO, null);

        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Delete a persistent SegAuditoria entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegAuditoriaDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegAuditoria entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegAuditoria entity) {
        EntityManagerHelper.log("deleting SegAuditoria instance", Level.INFO,
            null);

        try {
            entity = getEntityManager()
                         .getReference(SegAuditoria.class, entity.getAutCodigo());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Persist a previously saved SegAuditoria entity and return it or a copy of it
    * to the sender. A copy of the SegAuditoria entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = SegAuditoriaDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegAuditoria entity to update
    * @return SegAuditoria the persisted SegAuditoria entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegAuditoria update(SegAuditoria entity) {
        EntityManagerHelper.log("updating SegAuditoria instance", Level.INFO,
            null);

        try {
            SegAuditoria result = getEntityManager()
                                      .merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);

            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public SegAuditoria findById(Long id) {
        EntityManagerHelper.log("finding SegAuditoria instance with id: " + id,
            Level.INFO, null);

        try {
            SegAuditoria instance = getEntityManager()
                                        .find(SegAuditoria.class, id);

            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Find all  SegAuditoria entities with a specific property value.
    *
    * @param propertyName
    *            the metaData.name of the  SegAuditoria property to query
    * @param value
    *            the property value to match
    * @return List< SegAuditoria> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegAuditoria> findByProperty(String propertyName,
        final Object value) {
        EntityManagerHelper.log(
            "finding  SegAuditoria instance with property: " + propertyName +
            ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from  SegAuditoria model where model." +
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
    * Find all SegAuditoria entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegAuditoria property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            number of results to return.
    * @return List<SegAuditoria> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegAuditoria> findByProperty(String propertyName,
        final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding SegAuditoria instance with property: " +
            propertyName + ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from SegAuditoria model where model." +
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

    public List<SegAuditoria> findByAutCodigo(Object autCodigo,
        int... rowStartIdxAndCount) {
        return findByProperty(AUTCODIGO, autCodigo, rowStartIdxAndCount);
    }

    public List<SegAuditoria> findByAutCodigo(Object autCodigo) {
        return findByProperty(AUTCODIGO, autCodigo);
    }

    public List<SegAuditoria> findByCampo(Object campo,
        int... rowStartIdxAndCount) {
        return findByProperty(CAMPO, campo, rowStartIdxAndCount);
    }

    public List<SegAuditoria> findByCampo(Object campo) {
        return findByProperty(CAMPO, campo);
    }

    public List<SegAuditoria> findByCodRegistro(Object codRegistro,
        int... rowStartIdxAndCount) {
        return findByProperty(CODREGISTRO, codRegistro, rowStartIdxAndCount);
    }

    public List<SegAuditoria> findByCodRegistro(Object codRegistro) {
        return findByProperty(CODREGISTRO, codRegistro);
    }

    public List<SegAuditoria> findByFecha(Object fecha,
        int... rowStartIdxAndCount) {
        return findByProperty(FECHA, fecha, rowStartIdxAndCount);
    }

    public List<SegAuditoria> findByFecha(Object fecha) {
        return findByProperty(FECHA, fecha);
    }

    public List<SegAuditoria> findByTabla(Object tabla,
        int... rowStartIdxAndCount) {
        return findByProperty(TABLA, tabla, rowStartIdxAndCount);
    }

    public List<SegAuditoria> findByTabla(Object tabla) {
        return findByProperty(TABLA, tabla);
    }

    public List<SegAuditoria> findByTipo(Object tipo, int... rowStartIdxAndCount) {
        return findByProperty(TIPO, tipo, rowStartIdxAndCount);
    }

    public List<SegAuditoria> findByTipo(Object tipo) {
        return findByProperty(TIPO, tipo);
    }

    public List<SegAuditoria> findByValorAnterior(Object valorAnterior,
        int... rowStartIdxAndCount) {
        return findByProperty(VALORANTERIOR, valorAnterior, rowStartIdxAndCount);
    }

    public List<SegAuditoria> findByValorAnterior(Object valorAnterior) {
        return findByProperty(VALORANTERIOR, valorAnterior);
    }

    public List<SegAuditoria> findByValorNuevo(Object valorNuevo,
        int... rowStartIdxAndCount) {
        return findByProperty(VALORNUEVO, valorNuevo, rowStartIdxAndCount);
    }

    public List<SegAuditoria> findByValorNuevo(Object valorNuevo) {
        return findByProperty(VALORNUEVO, valorNuevo);
    }

    /**
    * Find all SegAuditoria entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegAuditoria> all SegAuditoria entities
    */
    @SuppressWarnings("unchecked")
    public List<SegAuditoria> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all SegAuditoria instances",
            Level.INFO, null);

        try {
            final String queryString = "select model from SegAuditoria model";
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

    public List<SegAuditoria> findByCriteria(String whereCondition) {
        EntityManagerHelper.log("finding SegAuditoria " + whereCondition,
            Level.INFO, null);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SegAuditoria model " +
                where;
            Query query = getEntityManager().createQuery(queryString);
            List<SegAuditoria> entitiesList = query.getResultList();

            return entitiesList;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find By Criteria in SegAuditoria failed",
                Level.SEVERE, re);
            throw re;
        }
    }

    public List<SegAuditoria> findPageSegAuditoria(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        EntityManagerHelper.log("finding SegAuditoria findPageSegAuditoria",
            Level.INFO, null);

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SegAuditoria model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SegAuditoria model";

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Long findTotalNumberSegAuditoria() {
        EntityManagerHelper.log("finding SegAuditoria count", Level.INFO, null);

        try {
            String queryString = "select count(*) from SegAuditoria model";

            return (Long) getEntityManager().createQuery(queryString)
                              .getSingleResult();
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
