package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegRolUsuario;


/**
 * Interface for SegRolUsuarioDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegRolUsuarioDAO {
    // property constants
    //public static final Long  RLUCODIGO = "rluCodigo";
    public static final String RLUCODIGO = "rluCodigo";

    //public static final String  RLUESTADOREGISTRO = "rluEstadoRegistro";
    public static final String RLUESTADOREGISTRO = "rluEstadoRegistro";

    /**
    * Perform an initial save of a previously unsaved SegRolUsuario entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegRolUsuarioDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegRolUsuario entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegRolUsuario entity);

    /**
    * Delete a persistent SegRolUsuario entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegRolUsuarioDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegRolUsuario entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegRolUsuario entity);

    /**
    * Persist a previously saved SegRolUsuario entity and return it or a copy of it
    * to the sender. A copy of the SegRolUsuario entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = ISegRolUsuarioDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegRolUsuario entity to update
    * @return SegRolUsuario the persisted SegRolUsuario entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegRolUsuario update(SegRolUsuario entity);

    public SegRolUsuario findById(Long id);

    /**
    * Find all SegRolUsuario entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegRolUsuario property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegRolUsuario> found by query
        */
    public List<SegRolUsuario> findByProperty(String propertyName,
        Object value, int... rowStartIdxAndCount);

    public List<SegRolUsuario> findByCriteria(String whereCondition);

    public List<SegRolUsuario> findByRluCodigo(Object rluCodigo);

    public List<SegRolUsuario> findByRluCodigo(Object rluCodigo,
        int... rowStartIdxAndCount);

    public List<SegRolUsuario> findByRluEstadoRegistro(Object rluEstadoRegistro);

    public List<SegRolUsuario> findByRluEstadoRegistro(
        Object rluEstadoRegistro, int... rowStartIdxAndCount);

    /**
    * Find all SegRolUsuario entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegRolUsuario> all SegRolUsuario entities
    */
    public List<SegRolUsuario> findAll(int... rowStartIdxAndCount);

    public List<SegRolUsuario> findPageSegRolUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegRolUsuario();

	public List<Object[]> getSegRolesUsuariosBySystem(Long usuSession,
			String sistema);

	public List<Object[]> getSegRolesPreviamenteSeleccionados(String usuCodigo);

	public List<Object[]> getSegRolesData(Long usuCodigo, String codigoRol);

	public List<Object[]> getSegRolesUsers(Long usuCodigo);
}
