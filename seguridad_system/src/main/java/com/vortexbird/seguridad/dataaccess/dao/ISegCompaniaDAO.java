package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegCompania;


/**
 * Interface for SegCompaniaDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegCompaniaDAO {
    // property constants
    //public static final Long  CIACODIGO = "ciaCodigo";
    public static final String CIACODIGO = "ciaCodigo";

    //public static final String  CIACODIGOINTERNO = "ciaCodigoInterno";
    public static final String CIACODIGOINTERNO = "ciaCodigoInterno";

    //public static final String  CIAESTADOREGISTRO = "ciaEstadoRegistro";
    public static final String CIAESTADOREGISTRO = "ciaEstadoRegistro";

    //public static final String  CIANOMBRE = "ciaNombre";
    public static final String CIANOMBRE = "ciaNombre";

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
    * ISegCompaniaDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegCompania entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegCompania entity);

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
    * ISegCompaniaDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegCompania entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegCompania entity);

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
    * entity = ISegCompaniaDAO.update(entity);
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
    public SegCompania update(SegCompania entity);

    public SegCompania findById(Long id);

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
    *            count of results to return.
    * @return List<SegCompania> found by query
        */
    public List<SegCompania> findByProperty(String propertyName, Object value,
        int... rowStartIdxAndCount);

    public List<SegCompania> findByCriteria(String whereCondition);

    public List<SegCompania> findByCiaCodigo(Object ciaCodigo);

    public List<SegCompania> findByCiaCodigo(Object ciaCodigo,
        int... rowStartIdxAndCount);

    public List<SegCompania> findByCiaCodigoInterno(Object ciaCodigoInterno);

    public List<SegCompania> findByCiaCodigoInterno(Object ciaCodigoInterno,
        int... rowStartIdxAndCount);

    public List<SegCompania> findByCiaEstadoRegistro(Object ciaEstadoRegistro);

    public List<SegCompania> findByCiaEstadoRegistro(Object ciaEstadoRegistro,
        int... rowStartIdxAndCount);

    public List<SegCompania> findByCiaNombre(Object ciaNombre);

    public List<SegCompania> findByCiaNombre(Object ciaNombre,
        int... rowStartIdxAndCount);

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
    public List<SegCompania> findAll(int... rowStartIdxAndCount);

    public List<SegCompania> findPageSegCompania(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegCompania();

	public List<Object[]> findCiaByUserRol(Long usuSession, long sistema);

	public List<Object[]> findCiaRol(Long sistema);
}
