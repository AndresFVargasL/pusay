package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegOpcion;
import com.vortexbird.seguridad.modelo.dto.SegOpcionDTO;


/**
 * Interface for SegOpcionDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegOpcionDAO {
    // property constants
    //public static final Long  OPCCODIGO = "opcCodigo";
    public static final String OPCCODIGO = "opcCodigo";

    //public static final String  OPCDESCRIPCION = "opcDescripcion";
    public static final String OPCDESCRIPCION = "opcDescripcion";

    //public static final String  OPCESTADOREGISTRO = "opcEstadoRegistro";
    public static final String OPCESTADOREGISTRO = "opcEstadoRegistro";

    //public static final String  OPCLLAVEACCESO = "opcLlaveAcceso";
    public static final String OPCLLAVEACCESO = "opcLlaveAcceso";

    //public static final String  OPCNOMBRE = "opcNombre";
    public static final String OPCNOMBRE = "opcNombre";

    /**
    * Perform an initial save of a previously unsaved SegOpcion entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegOpcionDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegOpcion entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegOpcion entity);

    /**
    * Delete a persistent SegOpcion entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegOpcionDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegOpcion entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegOpcion entity);

    /**
    * Persist a previously saved SegOpcion entity and return it or a copy of it
    * to the sender. A copy of the SegOpcion entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = ISegOpcionDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegOpcion entity to update
    * @return SegOpcion the persisted SegOpcion entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegOpcion update(SegOpcion entity);

    public SegOpcion findById(Long id);

    /**
    * Find all SegOpcion entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegOpcion property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegOpcion> found by query
        */
    public List<SegOpcion> findByProperty(String propertyName, Object value,
        int... rowStartIdxAndCount);

    public List<SegOpcion> findByCriteria(String whereCondition);

    public List<SegOpcion> findByOpcCodigo(Object opcCodigo);

    public List<SegOpcion> findByOpcCodigo(Object opcCodigo,
        int... rowStartIdxAndCount);

    public List<SegOpcion> findByOpcDescripcion(Object opcDescripcion);

    public List<SegOpcion> findByOpcDescripcion(Object opcDescripcion,
        int... rowStartIdxAndCount);

    public List<SegOpcion> findByOpcEstadoRegistro(Object opcEstadoRegistro);

    public List<SegOpcion> findByOpcEstadoRegistro(Object opcEstadoRegistro,
        int... rowStartIdxAndCount);

    public List<SegOpcion> findByOpcLlaveAcceso(Object opcLlaveAcceso);

    public List<SegOpcion> findByOpcLlaveAcceso(Object opcLlaveAcceso,
        int... rowStartIdxAndCount);

    public List<SegOpcion> findByOpcNombre(Object opcNombre);

    public List<SegOpcion> findByOpcNombre(Object opcNombre,
        int... rowStartIdxAndCount);

    /**
    * Find all SegOpcion entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegOpcion> all SegOpcion entities
    */
    public List<SegOpcion> findAll(int... rowStartIdxAndCount);

    public List<SegOpcion> findPageSegOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegOpcion();

	public List<Object[]> findOpcionesBySystem(Long usuSession, String sistema);
}
