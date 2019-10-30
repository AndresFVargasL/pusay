package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegSistemaCia;


/**
 * Interface for SegSistemaCiaDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegSistemaCiaDAO {
    // property constants
    //public static final Long  SICCODIGO = "sicCodigo";
    public static final String SICCODIGO = "sicCodigo";

    //public static final String  SICESTADOREGISTRO = "sicEstadoRegistro";
    public static final String SICESTADOREGISTRO = "sicEstadoRegistro";

    /**
    * Perform an initial save of a previously unsaved SegSistemaCia entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegSistemaCiaDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegSistemaCia entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegSistemaCia entity);

    /**
    * Delete a persistent SegSistemaCia entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegSistemaCiaDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegSistemaCia entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegSistemaCia entity);

    /**
    * Persist a previously saved SegSistemaCia entity and return it or a copy of it
    * to the sender. A copy of the SegSistemaCia entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = ISegSistemaCiaDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegSistemaCia entity to update
    * @return SegSistemaCia the persisted SegSistemaCia entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegSistemaCia update(SegSistemaCia entity);

    public SegSistemaCia findById(Long id);

    /**
    * Find all SegSistemaCia entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegSistemaCia property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegSistemaCia> found by query
        */
    public List<SegSistemaCia> findByProperty(String propertyName,
        Object value, int... rowStartIdxAndCount);

    public List<SegSistemaCia> findByCriteria(String whereCondition);

    public List<SegSistemaCia> findBySicCodigo(Object sicCodigo);

    public List<SegSistemaCia> findBySicCodigo(Object sicCodigo,
        int... rowStartIdxAndCount);

    public List<SegSistemaCia> findBySicEstadoRegistro(Object sicEstadoRegistro);

    public List<SegSistemaCia> findBySicEstadoRegistro(
        Object sicEstadoRegistro, int... rowStartIdxAndCount);

    /**
    * Find all SegSistemaCia entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegSistemaCia> all SegSistemaCia entities
    */
    public List<SegSistemaCia> findAll(int... rowStartIdxAndCount);

    public List<SegSistemaCia> findPageSegSistemaCia(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegSistemaCia();

	public List<Object[]> getSegSistemasCiasQuery(String sistema,
			String compania);
}
