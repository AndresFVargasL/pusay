package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegOpcion;
import com.vortexbird.seguridad.modelo.dto.SegOpcionDTO;


/**
* A data access object (DAO) providing persistence and search support for
* SegOpcion entities. Transaction control of the save(), update() and delete()
* operations must be handled externally by senders of these methods or must be
* manually added to each of these methods for data to be persisted to the JPA
* datastore.
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* @see lidis.SegOpcion
*
*/
public class SegOpcionDAO implements ISegOpcionDAO {
    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
    * Perform an initial save of a previously unsaved SegOpcion entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegOpcionDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegOpcion entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegOpcion entity) {
        EntityManagerHelper.log("saving SegOpcion instance", Level.INFO, null);

        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Delete a persistent SegOpcion entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * SegOpcionDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegOpcion entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegOpcion entity) {
        EntityManagerHelper.log("deleting SegOpcion instance", Level.INFO, null);

        try {
            entity = getEntityManager()
                         .getReference(SegOpcion.class, entity.getOpcCodigo());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Persist a previously saved SegOpcion entity and return it or a copy of it
    * to the sender. A copy of the SegOpcion entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = SegOpcionDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegOpcion entity to update
    * @return SegOpcion the persisted SegOpcion entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegOpcion update(SegOpcion entity) {
        EntityManagerHelper.log("updating SegOpcion instance", Level.INFO, null);

        try {
            SegOpcion result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);

            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public SegOpcion findById(Long id) {
        EntityManagerHelper.log("finding SegOpcion instance with id: " + id,
            Level.INFO, null);

        try {
            SegOpcion instance = getEntityManager().find(SegOpcion.class, id);

            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
    * Find all  SegOpcion entities with a specific property value.
    *
    * @param propertyName
    *            the metaData.name of the  SegOpcion property to query
    * @param value
    *            the property value to match
    * @return List< SegOpcion> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegOpcion> findByProperty(String propertyName,
        final Object value) {
        EntityManagerHelper.log("finding  SegOpcion instance with property: " +
            propertyName + ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from  SegOpcion model where model." +
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
    * Find all SegOpcion entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegOpcion property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            number of results to return.
    * @return List<SegOpcion> found by query
        */
    @SuppressWarnings("unchecked")
    public List<SegOpcion> findByProperty(String propertyName,
        final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding SegOpcion instance with property: " +
            propertyName + ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from SegOpcion model where model." +
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

    public List<SegOpcion> findByOpcCodigo(Object opcCodigo,
        int... rowStartIdxAndCount) {
        return findByProperty(OPCCODIGO, opcCodigo, rowStartIdxAndCount);
    }

    public List<SegOpcion> findByOpcCodigo(Object opcCodigo) {
        return findByProperty(OPCCODIGO, opcCodigo);
    }

    public List<SegOpcion> findByOpcDescripcion(Object opcDescripcion,
        int... rowStartIdxAndCount) {
        return findByProperty(OPCDESCRIPCION, opcDescripcion,
            rowStartIdxAndCount);
    }

    public List<SegOpcion> findByOpcDescripcion(Object opcDescripcion) {
        return findByProperty(OPCDESCRIPCION, opcDescripcion);
    }

    public List<SegOpcion> findByOpcEstadoRegistro(Object opcEstadoRegistro,
        int... rowStartIdxAndCount) {
        return findByProperty(OPCESTADOREGISTRO, opcEstadoRegistro,
            rowStartIdxAndCount);
    }

    public List<SegOpcion> findByOpcEstadoRegistro(Object opcEstadoRegistro) {
        return findByProperty(OPCESTADOREGISTRO, opcEstadoRegistro);
    }

    public List<SegOpcion> findByOpcLlaveAcceso(Object opcLlaveAcceso,
        int... rowStartIdxAndCount) {
        return findByProperty(OPCLLAVEACCESO, opcLlaveAcceso,
            rowStartIdxAndCount);
    }

    public List<SegOpcion> findByOpcLlaveAcceso(Object opcLlaveAcceso) {
        return findByProperty(OPCLLAVEACCESO, opcLlaveAcceso);
    }

    public List<SegOpcion> findByOpcNombre(Object opcNombre,
        int... rowStartIdxAndCount) {
        return findByProperty(OPCNOMBRE, opcNombre, rowStartIdxAndCount);
    }

    public List<SegOpcion> findByOpcNombre(Object opcNombre) {
        return findByProperty(OPCNOMBRE, opcNombre);
    }

    /**
    * Find all SegOpcion entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegOpcion> all SegOpcion entities
    */
    @SuppressWarnings("unchecked")
    public List<SegOpcion> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all SegOpcion instances", Level.INFO,
            null);

        try {
            final String queryString = "select model from SegOpcion model";
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

    public List<SegOpcion> findByCriteria(String whereCondition) {
        EntityManagerHelper.log("finding SegOpcion " + whereCondition,
            Level.INFO, null);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SegOpcion model " +
                where;
            Query query = getEntityManager().createQuery(queryString);
            List<SegOpcion> entitiesList = query.getResultList();

            return entitiesList;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find By Criteria in SegOpcion failed",
                Level.SEVERE, re);
            throw re;
        }
    }

    public List<SegOpcion> findPageSegOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        EntityManagerHelper.log("finding SegOpcion findPageSegOpcion",
            Level.INFO, null);

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SegOpcion model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SegOpcion model";

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Long findTotalNumberSegOpcion() {
        EntityManagerHelper.log("finding SegOpcion count", Level.INFO, null);

        try {
            String queryString = "select count(*) from SegOpcion model";

            return (Long) getEntityManager().createQuery(queryString)
                              .getSingleResult();
        } catch (RuntimeException re) {
            throw re;
        }
    }

	@Override
	public List<Object[]> findOpcionesBySystem(Long usuSession, String sistema) {

		try {
			
			String queryString = "SELECT op.opcCodigo , op.opcNombre, op.opcEstadoRegistro, " +
					"op.opcLlaveAcceso, op.opcDescripcion, op.segGrupoOpcion.gruCodigo, " +
					"op.segGrupoOpcion.gruNombre, op.segUsuario.usuCodigo " +
					"FROM SegOpcion op " +
					"join op.segGrupoOpcion grupoOpc " +
					"join grupoOpc.segSistema sist " +
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
            
            List<Object[]> opc =  query.getResultList();
            
			return opc;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}