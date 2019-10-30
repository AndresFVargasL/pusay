package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegGrupoOpcion;


/**
 * Interface for SegGrupoOpcionDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegGrupoOpcionDAO {
    // property constants
    //public static final Long  GRUCODIGO = "gruCodigo";
    public static final String GRUCODIGO = "gruCodigo";

    //public static final String  GRUDESCRIPCION = "gruDescripcion";
    public static final String GRUDESCRIPCION = "gruDescripcion";

    //public static final String  GRUESTADOREGISTRO = "gruEstadoRegistro";
    public static final String GRUESTADOREGISTRO = "gruEstadoRegistro";

    //public static final String  GRULLAVEACCESO = "gruLlaveAcceso";
    public static final String GRULLAVEACCESO = "gruLlaveAcceso";

    //public static final String  GRUNOMBRE = "gruNombre";
    public static final String GRUNOMBRE = "gruNombre";

    /**
    * Perform an initial save of a previously unsaved SegGrupoOpcion entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegGrupoOpcionDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegGrupoOpcion entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegGrupoOpcion entity);

    /**
    * Delete a persistent SegGrupoOpcion entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegGrupoOpcionDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegGrupoOpcion entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegGrupoOpcion entity);

    /**
    * Persist a previously saved SegGrupoOpcion entity and return it or a copy of it
    * to the sender. A copy of the SegGrupoOpcion entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = ISegGrupoOpcionDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegGrupoOpcion entity to update
    * @return SegGrupoOpcion the persisted SegGrupoOpcion entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegGrupoOpcion update(SegGrupoOpcion entity);

    public SegGrupoOpcion findById(Long id);

    /**
    * Find all SegGrupoOpcion entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegGrupoOpcion property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegGrupoOpcion> found by query
        */
    public List<SegGrupoOpcion> findByProperty(String propertyName,
        Object value, int... rowStartIdxAndCount);

    public List<SegGrupoOpcion> findByCriteria(String whereCondition);

    public List<SegGrupoOpcion> findByGruCodigo(Object gruCodigo);

    public List<SegGrupoOpcion> findByGruCodigo(Object gruCodigo,
        int... rowStartIdxAndCount);

    public List<SegGrupoOpcion> findByGruDescripcion(Object gruDescripcion);

    public List<SegGrupoOpcion> findByGruDescripcion(Object gruDescripcion,
        int... rowStartIdxAndCount);

    public List<SegGrupoOpcion> findByGruEstadoRegistro(
        Object gruEstadoRegistro);

    public List<SegGrupoOpcion> findByGruEstadoRegistro(
        Object gruEstadoRegistro, int... rowStartIdxAndCount);

    public List<SegGrupoOpcion> findByGruLlaveAcceso(Object gruLlaveAcceso);

    public List<SegGrupoOpcion> findByGruLlaveAcceso(Object gruLlaveAcceso,
        int... rowStartIdxAndCount);

    public List<SegGrupoOpcion> findByGruNombre(Object gruNombre);

    public List<SegGrupoOpcion> findByGruNombre(Object gruNombre,
        int... rowStartIdxAndCount);

    /**
    * Find all SegGrupoOpcion entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegGrupoOpcion> all SegGrupoOpcion entities
    */
    public List<SegGrupoOpcion> findAll(int... rowStartIdxAndCount);

    public List<SegGrupoOpcion> findPageSegGrupoOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegGrupoOpcion();

	public List<Object[]> getSegGrupoOpcionesBySystem(Long usuSession, String sistema);
}
