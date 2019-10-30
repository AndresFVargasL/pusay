package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegSistema;


/**
 * Interface for SegSistemaDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegSistemaDAO {
    // property constants
    //public static final Long  SISCODIGO = "sisCodigo";
    public static final String SISCODIGO = "sisCodigo";

    //public static final String  SISDESCRIPCION = "sisDescripcion";
    public static final String SISDESCRIPCION = "sisDescripcion";

    //public static final String  SISESTADOREGISTRO = "sisEstadoRegistro";
    public static final String SISESTADOREGISTRO = "sisEstadoRegistro";

    //public static final String  SISNOMBRE = "sisNombre";
    public static final String SISNOMBRE = "sisNombre";

    /**
    * Perform an initial save of a previously unsaved SegSistema entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegSistemaDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegSistema entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegSistema entity);

    /**
    * Delete a persistent SegSistema entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegSistemaDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegSistema entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegSistema entity);

    /**
    * Persist a previously saved SegSistema entity and return it or a copy of it
    * to the sender. A copy of the SegSistema entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = ISegSistemaDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegSistema entity to update
    * @return SegSistema the persisted SegSistema entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegSistema update(SegSistema entity);

    public SegSistema findById(Long id);

    /**
    * Find all SegSistema entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegSistema property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegSistema> found by query
        */
    public List<SegSistema> findByProperty(String propertyName, Object value,
        int... rowStartIdxAndCount);

    public List<SegSistema> findByCriteria(String whereCondition);

    public List<SegSistema> findBySisCodigo(Object sisCodigo);

    public List<SegSistema> findBySisCodigo(Object sisCodigo,
        int... rowStartIdxAndCount);

    public List<SegSistema> findBySisDescripcion(Object sisDescripcion);

    public List<SegSistema> findBySisDescripcion(Object sisDescripcion,
        int... rowStartIdxAndCount);

    public List<SegSistema> findBySisEstadoRegistro(Object sisEstadoRegistro);

    public List<SegSistema> findBySisEstadoRegistro(Object sisEstadoRegistro,
        int... rowStartIdxAndCount);

    public List<SegSistema> findBySisNombre(Object sisNombre);

    public List<SegSistema> findBySisNombre(Object sisNombre,
        int... rowStartIdxAndCount);

    /**
    * Find all SegSistema entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegSistema> all SegSistema entities
    */
    public List<SegSistema> findAll(int... rowStartIdxAndCount);

    public List<SegSistema> findPageSegSistema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegSistema();

	public List<Object[]> findSystemsByUser(Long usuSession, String sistemaSeleccionado);

	public List<Object[]> findSystemsByUserRol(Long usuSession);
}
