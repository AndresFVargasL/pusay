package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.modelo.SegUsuario;


/**
 * Interface for SegUsuarioDAO.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public interface ISegUsuarioDAO {
    // property constants
    //public static final String  USUAPELLIDOS = "usuApellidos";
    public static final String USUAPELLIDOS = "usuApellidos";

    //public static final Long  USUCODIGO = "usuCodigo";
    public static final String USUCODIGO = "usuCodigo";

    //public static final String  USUCODIGOINTERNO = "usuCodigoInterno";
    public static final String USUCODIGOINTERNO = "usuCodigoInterno";

    //public static final String  USUCONSTRASENA = "usuConstrasena";
    public static final String USUCONSTRASENA = "usuConstrasena";

    //public static final String  USUCORREO = "usuCorreo";
    public static final String USUCORREO = "usuCorreo";

    //public static final String  USUESTADOREGISTRO = "usuEstadoRegistro";
    public static final String USUESTADOREGISTRO = "usuEstadoRegistro";

    //public static final Long  USUINTENTOSFALLIDOS = "usuIntentosFallidos";
    public static final String USUINTENTOSFALLIDOS = "usuIntentosFallidos";

    //public static final String  USULOGIN = "usuLogin";
    public static final String USULOGIN = "usuLogin";

    //public static final String  USUNOMBRES = "usuNombres";
    public static final String USUNOMBRES = "usuNombres";

    //public static final Date  USUULTMIMAMODIFICACIONPASS = "usuUltmimaModificacionPass";
    public static final String USUULTMIMAMODIFICACIONPASS = "usuUltmimaModificacionPass";

    /**
    * Perform an initial save of a previously unsaved SegUsuario entity. All
    * subsequent persist actions of this entity should use the #update()
    * method. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegUsuarioDAO.save(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegUsuario entity to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SegUsuario entity);

    /**
    * Delete a persistent SegUsuario entity. This operation must be performed
    * within the a database transaction context for the entity's data to be
    * permanently deleted from the persistence store, i.e., database. This
    * method uses the
    * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * ISegUsuarioDAO.delete(entity);
    * EntityManagerHelper.commit();
    * entity = null;
    * </pre>
    *
    * @param entity
    *            SegUsuario entity to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SegUsuario entity);

    /**
    * Persist a previously saved SegUsuario entity and return it or a copy of it
    * to the sender. A copy of the SegUsuario entity parameter is returned when
    * the JPA persistence mechanism has not previously been tracking the
    * updated entity. This operation must be performed within the a database
    * transaction context for the entity's data to be permanently saved to the
    * persistence store, i.e., database. This method uses the
    * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
    * operation.
    *
    * <pre>
    * EntityManagerHelper.beginTransaction();
    * entity = ISegUsuarioDAO.update(entity);
    * EntityManagerHelper.commit();
    * </pre>
    *
    * @param entity
    *            SegUsuario entity to update
    * @return SegUsuario the persisted SegUsuario entity instance, may not be the
    *         same
    * @throws RuntimeException
    *             if the operation fails
    */
    public SegUsuario update(SegUsuario entity);

    public SegUsuario findById(Long id);

    /**
    * Find all SegUsuario entities with a specific property value.
    *
    * @param propertyName
    *            the name of the SegUsuario property to query
    * @param value
    *            the property value to match
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegUsuario> found by query
        */
    public List<SegUsuario> findByProperty(String propertyName, Object value,
        int... rowStartIdxAndCount);

    public List<SegUsuario> findByCriteria(String whereCondition);

    public List<SegUsuario> findByUsuApellidos(Object usuApellidos);

    public List<SegUsuario> findByUsuApellidos(Object usuApellidos,
        int... rowStartIdxAndCount);

    public List<SegUsuario> findByUsuCodigo(Object usuCodigo);

    public List<SegUsuario> findByUsuCodigo(Object usuCodigo,
        int... rowStartIdxAndCount);

    public List<SegUsuario> findByUsuCodigoInterno(Object usuCodigoInterno);

    public List<SegUsuario> findByUsuCodigoInterno(Object usuCodigoInterno,
        int... rowStartIdxAndCount);

    public List<SegUsuario> findByUsuConstrasena(Object usuConstrasena);

    public List<SegUsuario> findByUsuConstrasena(Object usuConstrasena,
        int... rowStartIdxAndCount);

    public List<SegUsuario> findByUsuCorreo(Object usuCorreo);

    public List<SegUsuario> findByUsuCorreo(Object usuCorreo,
        int... rowStartIdxAndCount);

    public List<SegUsuario> findByUsuEstadoRegistro(Object usuEstadoRegistro);

    public List<SegUsuario> findByUsuEstadoRegistro(Object usuEstadoRegistro,
        int... rowStartIdxAndCount);

    public List<SegUsuario> findByUsuIntentosFallidos(
        Object usuIntentosFallidos);

    public List<SegUsuario> findByUsuIntentosFallidos(
        Object usuIntentosFallidos, int... rowStartIdxAndCount);

    public List<SegUsuario> findByUsuLogin(Object usuLogin);

    public List<SegUsuario> findByUsuLogin(Object usuLogin,
        int... rowStartIdxAndCount);

    public List<SegUsuario> findByUsuNombres(Object usuNombres);

    public List<SegUsuario> findByUsuNombres(Object usuNombres,
        int... rowStartIdxAndCount);

    public List<SegUsuario> findByUsuUltmimaModificacionPass(
        Object usuUltmimaModificacionPass);

    public List<SegUsuario> findByUsuUltmimaModificacionPass(
        Object usuUltmimaModificacionPass, int... rowStartIdxAndCount);

    /**
    * Find all SegUsuario entities.
    *
    * @param rowStartIdxAndCount
    *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
    *            row index in the query result-set to begin collecting the
    *            results. rowStartIdxAndCount[1] specifies the the maximum
    *            count of results to return.
    * @return List<SegUsuario> all SegUsuario entities
    */
    public List<SegUsuario> findAll(int... rowStartIdxAndCount);

    public List<SegUsuario> findPageSegUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberSegUsuario();

	public List<Object[]> getSegUsersBySystem(Long usuSession, String sistema);

	public List<Object[]> getSegUsersBySystemAndCias(Long sisCia, String compania);

	public List<Object[]> getSegUsersByRol(Long codigoRoles);

	public List<Object[]> getUserAdmin();
	
	public List<SegUsuario> consultarUsuarioPorLogin(String login);

}
