package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegUsuario;
import com.vortexbird.seguridad.modelo.dto.SegUsuarioDTO;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegUsuarioLogic {
    public List<SegUsuario> getSegUsuario() throws Exception;

    public void saveSegUsuario(String usuApellidos,
        String usuCodigoInterno, String usuConstrasena, String usuCorreo,
        String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania, String usuLogin, String usuNombres, Date usuUltmimaModificacionPass,
        Long usuCodigo_SegUsuario) throws Exception;

    public void deleteSegUsuario(Long usuCodigo) throws Exception;

    public void updateSegUsuario(String usuApellidos, Long usuCodigo,
        String usuCodigoInterno, String usuConstrasena, String usuCorreo,
        String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania, String usuLogin, String usuNombres, Date usuUltmimaModificacionPass,
        Long usuCodigo_SegUsuario) throws Exception;

    public SegUsuario getSegUsuario(Long usuCodigo) throws Exception;

    public List<SegUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegUsuario> findPageSegUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegUsuario() throws Exception;

	public List<SegUsuarioDTO> getDataSegUsuario() throws Exception;

	public List<SegUsuarioDTO> findUsuariosDTO(List<SegUsuario> losUsuarios) throws Exception;

	public List<SegUsuario> getUsersBySystemsAndCias(String sistema,
			String compania) throws Exception;

	List<SegUsuarioDTO> getUsuariosPorSistemaDTO(String sistema, String compania)
			throws Exception;

	public void saveSegUsuario(String usuApellidos, String usuCodigoInterno,
			String usuConstrasena, String usuCorreo, String usuEstadoRegistro,
			Long usuIntentosFallidos, Long ciaCodigo_SegCompania, String usuLogin, String usuNombres,
			Date usuUltmimaModificacionPass, Long usuSession,
			List<String> rolesAsignados, String compania, String sistema)  throws Exception;

	public void updateSegUsuarioRoles(String usuApellidos, Long usuCodigo,
			String usuCodigoInterno, String usuConstrasena, String usuCorreo,
			String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania,
			String usuLogin, String usuNombres,
			Date usuUltmimaModificacionPass, Long usuSession,
			List<String> rolesAsignados, String compania, String sistema) throws Exception;

	public List<SegUsuario> getUsuariosPorRol(Long codigoRoles)throws Exception;

	public List<SegUsuarioDTO> getUsersAdmin() throws Exception;
	
	public List<SegUsuario> findByCriteriaSegUsuario(String whereCondition);
	
	public SegUsuario consultarUsuarioPorLogin(String login)throws Exception;
}
