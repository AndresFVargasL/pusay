package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegSucursal;


/**
 * Interface for SegSucursalDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegSucursalDAO {
    // property constants
    //public static final Long  SUCCODIGO = "sucCodigo";
    public static final String SUCCODIGO = "sucCodigo";

    //public static final String  SUCCODIGOINTERNO = "sucCodigoInterno";
    public static final String SUCCODIGOINTERNO = "sucCodigoInterno";

    //public static final String  SUCESTADOREGISTRO = "sucEstadoRegistro";
    public static final String SUCESTADOREGISTRO = "sucEstadoRegistro";

    //public static final String  SUCNOMBRE = "sucNombre";
    public static final String SUCNOMBRE = "sucNombre";

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
    * ISegSucursalDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegSucursal entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegSucursal entity);

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
    * ISegSucursalDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegSucursal entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegSucursal entity);

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
    * entity = ISegSucursalDAO.update(entity);
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
    public SegSucursal update(SegSucursal entity);

    public SegSucursal findById(Long id);

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
    *            count of results to return.
    * @return List<SegSucursal> found by query
        */
    public List<SegSucursal> findByProperty(String propertyName, Object value,
        int... rowStartIdxAndCount);

    public List<SegSucursal> findByCriteria(String whereCondition);

    public List<SegSucursal> findBySucCodigo(Object sucCodigo);

    public List<SegSucursal> findBySucCodigo(Object sucCodigo,
        int... rowStartIdxAndCount);

    public List<SegSucursal> findBySucCodigoInterno(Object sucCodigoInterno);

    public List<SegSucursal> findBySucCodigoInterno(Object sucCodigoInterno,
        int... rowStartIdxAndCount);

    public List<SegSucursal> findBySucEstadoRegistro(Object sucEstadoRegistro);

    public List<SegSucursal> findBySucEstadoRegistro(Object sucEstadoRegistro,
        int... rowStartIdxAndCount);

    public List<SegSucursal> findBySucNombre(Object sucNombre);

    public List<SegSucursal> findBySucNombre(Object sucNombre,
        int... rowStartIdxAndCount);

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
    public List<SegSucursal> findAll(int... rowStartIdxAndCount);

    public List<SegSucursal> findPageSegSucursal(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegSucursal();
}
