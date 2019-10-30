package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegCambioPass;


/**
 * Interface for SegCambioPassDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegCambioPassDAO {
    // property constants
    //public static final Long  CAPCODIGO = "capCodigo";
    public static final String CAPCODIGO = "capCodigo";

    //public static final String  CAPESTADO = "capEstado";
    public static final String CAPESTADO = "capEstado";

    //public static final Date  CAPFECHAFIN = "capFechaFin";
    public static final String CAPFECHAFIN = "capFechaFin";

    //public static final Date  CAPFECHAINI = "capFechaIni";
    public static final String CAPFECHAINI = "capFechaIni";

    //public static final String  CAPTOKEN = "capToken";
    public static final String CAPTOKEN = "capToken";

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
    * ISegCambioPassDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegCambioPass entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegCambioPass entity);

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
    * ISegCambioPassDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegCambioPass entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegCambioPass entity);

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
    * entity = ISegCambioPassDAO.update(entity);
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
    public SegCambioPass update(SegCambioPass entity);

    public SegCambioPass findById(Long id);

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
    *            count of results to return.
    * @return List<SegCambioPass> found by query
        */
    public List<SegCambioPass> findByProperty(String propertyName,
        Object value, int... rowStartIdxAndCount);

    public List<SegCambioPass> findByCriteria(String whereCondition);

    public List<SegCambioPass> findByCapCodigo(Object capCodigo);

    public List<SegCambioPass> findByCapCodigo(Object capCodigo,
        int... rowStartIdxAndCount);

    public List<SegCambioPass> findByCapEstado(Object capEstado);

    public List<SegCambioPass> findByCapEstado(Object capEstado,
        int... rowStartIdxAndCount);

    public List<SegCambioPass> findByCapFechaFin(Object capFechaFin);

    public List<SegCambioPass> findByCapFechaFin(Object capFechaFin,
        int... rowStartIdxAndCount);

    public List<SegCambioPass> findByCapFechaIni(Object capFechaIni);

    public List<SegCambioPass> findByCapFechaIni(Object capFechaIni,
        int... rowStartIdxAndCount);

    public List<SegCambioPass> findByCapToken(Object capToken);

    public List<SegCambioPass> findByCapToken(Object capToken,
        int... rowStartIdxAndCount);

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
    public List<SegCambioPass> findAll(int... rowStartIdxAndCount);

    public List<SegCambioPass> findPageSegCambioPass(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegCambioPass();
}
