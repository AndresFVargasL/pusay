package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegPermiso;


/**
 * Interface for SegPermisoDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegPermisoDAO {
    // property constants
    //public static final Long  PERCODIGO = "perCodigo";
    public static final String PERCODIGO = "perCodigo";

    //public static final String  PERESTADOREGISTRO = "perEstadoRegistro";
    public static final String PERESTADOREGISTRO = "perEstadoRegistro";

    /**
    * Perform an initial save of a previously unsaved SegPermiso entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegPermisoDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegPermiso entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegPermiso entity);

    /**
    * Delete a persistent SegPermiso entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegPermisoDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegPermiso entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegPermiso entity);

    /**
    * Persist a previously saved SegPermiso entity and return it or a copy of it
    * to the sender. A copy of the SegPermiso entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = ISegPermisoDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegPermiso entity to update
    * @return SegPermiso the persisted SegPermiso entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegPermiso update(SegPermiso entity);

    public SegPermiso findById(Long id);

    /**
    * Find all SegPermiso entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegPermiso property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegPermiso> found by query
        */
    public List<SegPermiso> findByProperty(String propertyName, Object value,
        int... rowStartIdxAndCount);

    public List<SegPermiso> findByCriteria(String whereCondition);

    public List<SegPermiso> findByPerCodigo(Object perCodigo);

    public List<SegPermiso> findByPerCodigo(Object perCodigo,
        int... rowStartIdxAndCount);

    public List<SegPermiso> findByPerEstadoRegistro(Object perEstadoRegistro);

    public List<SegPermiso> findByPerEstadoRegistro(Object perEstadoRegistro,
        int... rowStartIdxAndCount);

    /**
    * Find all SegPermiso entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegPermiso> all SegPermiso entities
    */
    public List<SegPermiso> findAll(int... rowStartIdxAndCount);

    public List<SegPermiso> findPageSegPermiso(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegPermiso();

	public List<Object[]> getPermisosAdmin(Long usuCodigo);

	public List<Object[]> getPermisosByUserAndSisCia(Long usuCodigo,
			Long sisCiaUnico);

	public List<SegPermiso> findPermisosRolUnionUser(Long codigoRol,
			Long sicCodigo, String codigoUsuario);

	public List<SegPermiso> findPermisosRolUnionUserOption(Long rolCodigo,
			Long sisCiaUnico, String codigoUsuario, Long codigoOpcion);
}
