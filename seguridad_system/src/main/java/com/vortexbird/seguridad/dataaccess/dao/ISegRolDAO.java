package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegRol;


/**
 * Interface for SegRolDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegRolDAO {
    // property constants
    //public static final Long  ROLCODIGO = "rolCodigo";
    public static final String ROLCODIGO = "rolCodigo";

    //public static final String  ROLDESCRIPCION = "rolDescripcion";
    public static final String ROLDESCRIPCION = "rolDescripcion";

    //public static final Long  ROLDIASCADUCIDADPWD = "rolDiasCaducidadPwd";
    public static final String ROLDIASCADUCIDADPWD = "rolDiasCaducidadPwd";

    //public static final String  ROLESTADOREGISTRO = "rolEstadoRegistro";
    public static final String ROLESTADOREGISTRO = "rolEstadoRegistro";

    //public static final String  ROLNOMBRE = "rolNombre";
    public static final String ROLNOMBRE = "rolNombre";

    /**
    * Perform an initial save of a previously unsaved SegRol entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegRolDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegRol entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegRol entity);

    /**
    * Delete a persistent SegRol entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegRolDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegRol entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegRol entity);

    /**
    * Persist a previously saved SegRol entity and return it or a copy of it
    * to the sender. A copy of the SegRol entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = ISegRolDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegRol entity to update
    * @return SegRol the persisted SegRol entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegRol update(SegRol entity);

    public SegRol findById(Long id);

    /**
    * Find all SegRol entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegRol property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegRol> found by query
        */
    public List<SegRol> findByProperty(String propertyName, Object value,
        int... rowStartIdxAndCount);

    public List<SegRol> findByCriteria(String whereCondition);

    public List<SegRol> findByRolCodigo(Object rolCodigo);

    public List<SegRol> findByRolCodigo(Object rolCodigo,
        int... rowStartIdxAndCount);

    public List<SegRol> findByRolDescripcion(Object rolDescripcion);

    public List<SegRol> findByRolDescripcion(Object rolDescripcion,
        int... rowStartIdxAndCount);

    public List<SegRol> findByRolDiasCaducidadPwd(Object rolDiasCaducidadPwd);

    public List<SegRol> findByRolDiasCaducidadPwd(Object rolDiasCaducidadPwd,
        int... rowStartIdxAndCount);

    public List<SegRol> findByRolEstadoRegistro(Object rolEstadoRegistro);

    public List<SegRol> findByRolEstadoRegistro(Object rolEstadoRegistro,
        int... rowStartIdxAndCount);

    public List<SegRol> findByRolNombre(Object rolNombre);

    public List<SegRol> findByRolNombre(Object rolNombre,
        int... rowStartIdxAndCount);

    /**
    * Find all SegRol entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegRol> all SegRol entities
    */
    public List<SegRol> findAll(int... rowStartIdxAndCount);

    public List<SegRol> findPageSegRol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegRol();

	public List<Object[]> getSegRolesBySystem(Long usuSession, String sistema);

	public List<Object[]> getSegRolesBySystemNoAdmin(String sistema);

	public List<Object[]> getSegRolesAdmin();
}
