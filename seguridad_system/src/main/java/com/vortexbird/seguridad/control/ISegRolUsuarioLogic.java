package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegRolUsuario;
import com.vortexbird.seguridad.modelo.dto.SegRolUsuarioDTO;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegRolUsuarioLogic {
    public List<SegRolUsuario> getSegRolUsuario() throws Exception;

    public void saveSegRolUsuario(String rluEstadoRegistro,
        Long rolCodigo_SegRol, Long usuCodigo_SegUsuario,
        Long usuCodigo_SegUsuario0) throws Exception;

    public void deleteSegRolUsuario(Long rluCodigo) throws Exception;

    public void updateSegRolUsuario(Long rluCodigo, String rluEstadoRegistro,
        Long rolCodigo_SegRol, Long usuCodigo_SegUsuario,
        Long usuCodigo_SegUsuario0) throws Exception;

    public SegRolUsuario getSegRolUsuario(Long rluCodigo)
        throws Exception;

    public List<SegRolUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegRolUsuario> findPageSegRolUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegRolUsuario() throws Exception;

	public List<SegRolUsuarioDTO> getDataSegRolUsuario() throws Exception;

	public List<SegRolUsuarioDTO> findUsuariosDTO(List<SegRolUsuario> losRolUsuarios) throws Exception;

	public List<SegRolUsuarioDTO> getRolesUsuariosBySystem(Long usuSession,
			String sistema) throws Exception;

	public Object[] getRolesSeleccionadosPreviamente(String usuCodigo)throws Exception;

	public SegRolUsuario getRolUsuariosCambiados(Long usuCodigo, String string) throws Exception;

	public Object[] getRolesDeUsuario(Long usuCodigo) throws Exception;
}
