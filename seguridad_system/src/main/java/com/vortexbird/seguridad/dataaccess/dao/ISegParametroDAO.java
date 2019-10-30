package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegParametro;


/**
 * Interface for SegParametroDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegParametroDAO {
    // property constants
    //public static final Long  PARCODIGO = "parCodigo";
    public static final String PARCODIGO = "parCodigo";

    //public static final String  PARESTADOREGISTRO = "parEstadoRegistro";
    public static final String PARESTADOREGISTRO = "parEstadoRegistro";

    //public static final String  PARNOMBRE = "parNombre";
    public static final String PARNOMBRE = "parNombre";

    //public static final String  PARVALORALFANUMERICO = "parValorAlfanumerico";
    public static final String PARVALORALFANUMERICO = "parValorAlfanumerico";

    //public static final Date  PARVALORFECHA = "parValorFecha";
    public static final String PARVALORFECHA = "parValorFecha";

    //public static final Double  PARVALORNUMERICO = "parValorNumerico";
    public static final String PARVALORNUMERICO = "parValorNumerico";

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
    * ISegParametroDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegParametro entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegParametro entity);

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
    * ISegParametroDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegParametro entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegParametro entity);

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
    * entity = ISegParametroDAO.update(entity);
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
    public SegParametro update(SegParametro entity);

    public SegParametro findById(Long id);

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
    *            count of results to return.
    * @return List<SegParametro> found by query
        */
    public List<SegParametro> findByProperty(String propertyName, Object value,
        int... rowStartIdxAndCount);

    public List<SegParametro> findByCriteria(String whereCondition);

    public List<SegParametro> findByParCodigo(Object parCodigo);

    public List<SegParametro> findByParCodigo(Object parCodigo,
        int... rowStartIdxAndCount);

    public List<SegParametro> findByParEstadoRegistro(Object parEstadoRegistro);

    public List<SegParametro> findByParEstadoRegistro(
        Object parEstadoRegistro, int... rowStartIdxAndCount);

    public List<SegParametro> findByParNombre(Object parNombre);

    public List<SegParametro> findByParNombre(Object parNombre,
        int... rowStartIdxAndCount);

    public List<SegParametro> findByParValorAlfanumerico(
        Object parValorAlfanumerico);

    public List<SegParametro> findByParValorAlfanumerico(
        Object parValorAlfanumerico, int... rowStartIdxAndCount);

    public List<SegParametro> findByParValorFecha(Object parValorFecha);

    public List<SegParametro> findByParValorFecha(Object parValorFecha,
        int... rowStartIdxAndCount);

    public List<SegParametro> findByParValorNumerico(Object parValorNumerico);

    public List<SegParametro> findByParValorNumerico(Object parValorNumerico,
        int... rowStartIdxAndCount);

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
    public List<SegParametro> findAll(int... rowStartIdxAndCount);

    public List<SegParametro> findPageSegParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegParametro();
}
