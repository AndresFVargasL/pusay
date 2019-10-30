package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegAuditoria;


/**
 * Interface for SegAuditoriaDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegAuditoriaDAO {
    // property constants
    //public static final Long  AUTCODIGO = "autCodigo";
    public static final String AUTCODIGO = "autCodigo";

    //public static final String  CAMPO = "campo";
    public static final String CAMPO = "campo";

    //public static final Long  CODREGISTRO = "codRegistro";
    public static final String CODREGISTRO = "codRegistro";

    //public static final Date  FECHA = "fecha";
    public static final String FECHA = "fecha";

    //public static final String  TABLA = "tabla";
    public static final String TABLA = "tabla";

    //public static final String  TIPO = "tipo";
    public static final String TIPO = "tipo";

    //public static final String  VALORANTERIOR = "valorAnterior";
    public static final String VALORANTERIOR = "valorAnterior";

    //public static final String  VALORNUEVO = "valorNuevo";
    public static final String VALORNUEVO = "valorNuevo";

    /**
    * Perform an initial save of a previously unsaved SegAuditoria entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegAuditoriaDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegAuditoria entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegAuditoria entity);

    /**
    * Delete a persistent SegAuditoria entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegAuditoriaDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegAuditoria entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegAuditoria entity);

    /**
    * Persist a previously saved SegAuditoria entity and return it or a copy of it
    * to the sender. A copy of the SegAuditoria entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = ISegAuditoriaDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegAuditoria entity to update
    * @return SegAuditoria the persisted SegAuditoria entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegAuditoria update(SegAuditoria entity);

    public SegAuditoria findById(Long id);

    /**
    * Find all SegAuditoria entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegAuditoria property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegAuditoria> found by query
        */
    public List<SegAuditoria> findByProperty(String propertyName, Object value,
        int... rowStartIdxAndCount);

    public List<SegAuditoria> findByCriteria(String whereCondition);

    public List<SegAuditoria> findByAutCodigo(Object autCodigo);

    public List<SegAuditoria> findByAutCodigo(Object autCodigo,
        int... rowStartIdxAndCount);

    public List<SegAuditoria> findByCampo(Object campo);

    public List<SegAuditoria> findByCampo(Object campo,
        int... rowStartIdxAndCount);

    public List<SegAuditoria> findByCodRegistro(Object codRegistro);

    public List<SegAuditoria> findByCodRegistro(Object codRegistro,
        int... rowStartIdxAndCount);

    public List<SegAuditoria> findByFecha(Object fecha);

    public List<SegAuditoria> findByFecha(Object fecha,
        int... rowStartIdxAndCount);

    public List<SegAuditoria> findByTabla(Object tabla);

    public List<SegAuditoria> findByTabla(Object tabla,
        int... rowStartIdxAndCount);

    public List<SegAuditoria> findByTipo(Object tipo);

    public List<SegAuditoria> findByTipo(Object tipo, int... rowStartIdxAndCount);

    public List<SegAuditoria> findByValorAnterior(Object valorAnterior);

    public List<SegAuditoria> findByValorAnterior(Object valorAnterior,
        int... rowStartIdxAndCount);

    public List<SegAuditoria> findByValorNuevo(Object valorNuevo);

    public List<SegAuditoria> findByValorNuevo(Object valorNuevo,
        int... rowStartIdxAndCount);

    /**
    * Find all SegAuditoria entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegAuditoria> all SegAuditoria entities
    */
    public List<SegAuditoria> findAll(int... rowStartIdxAndCount);

    public List<SegAuditoria> findPageSegAuditoria(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegAuditoria();
}
