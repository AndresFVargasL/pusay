package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegHistorialConstrasena;


/**
 * Interface for SegHistorialConstrasenaDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegHistorialConstrasenaDAO {
    // property constants
    //public static final Long  HCOCODIGO = "hcoCodigo";
    public static final String HCOCODIGO = "hcoCodigo";

    //public static final String  HCOCONSTRASENA = "hcoConstrasena";
    public static final String HCOCONSTRASENA = "hcoConstrasena";

    //public static final String  HCODETALLECAMBIO = "hcoDetalleCambio";
    public static final String HCODETALLECAMBIO = "hcoDetalleCambio";

    //public static final Date  HCOFECHACAMBIO = "hcoFechaCambio";
    public static final String HCOFECHACAMBIO = "hcoFechaCambio";

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
    * ISegHistorialConstrasenaDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegHistorialConstrasena entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegHistorialConstrasena entity);

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
    * ISegHistorialConstrasenaDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegHistorialConstrasena entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegHistorialConstrasena entity);

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
    * entity = ISegHistorialConstrasenaDAO.update(entity);
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
    public SegHistorialConstrasena update(SegHistorialConstrasena entity);

    public SegHistorialConstrasena findById(Long id);

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
    *            count of results to return.
    * @return List<SegHistorialConstrasena> found by query
        */
    public List<SegHistorialConstrasena> findByProperty(String propertyName,
        Object value, int... rowStartIdxAndCount);

    public List<SegHistorialConstrasena> findByCriteria(String whereCondition);

    public List<SegHistorialConstrasena> findByHcoCodigo(Object hcoCodigo);

    public List<SegHistorialConstrasena> findByHcoCodigo(Object hcoCodigo,
        int... rowStartIdxAndCount);

    public List<SegHistorialConstrasena> findByHcoConstrasena(
        Object hcoConstrasena);

    public List<SegHistorialConstrasena> findByHcoConstrasena(
        Object hcoConstrasena, int... rowStartIdxAndCount);

    public List<SegHistorialConstrasena> findByHcoDetalleCambio(
        Object hcoDetalleCambio);

    public List<SegHistorialConstrasena> findByHcoDetalleCambio(
        Object hcoDetalleCambio, int... rowStartIdxAndCount);

    public List<SegHistorialConstrasena> findByHcoFechaCambio(
        Object hcoFechaCambio);

    public List<SegHistorialConstrasena> findByHcoFechaCambio(
        Object hcoFechaCambio, int... rowStartIdxAndCount);

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
    public List<SegHistorialConstrasena> findAll(int... rowStartIdxAndCount);

    public List<SegHistorialConstrasena> findPageSegHistorialConstrasena(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults);

    public Long findTotalNumberSegHistorialConstrasena();
}
