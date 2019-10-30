package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegRol;
import com.vortexbird.seguridad.modelo.dto.SegRolDTO;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegRolLogic {
    public List<SegRol> getSegRol() throws Exception;

    public void saveSegRol(String rolDescripcion,
        Long rolDiasCaducidadPwd, String rolEstadoRegistro, String rolNombre,
        Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
        throws Exception;

    public void deleteSegRol(Long rolCodigo) throws Exception;

    public void updateSegRol(Long rolCodigo, String rolDescripcion,
        Long rolDiasCaducidadPwd, String rolEstadoRegistro, String rolNombre,
        Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
        throws Exception;

    public SegRol getSegRol(Long rolCodigo) throws Exception;

    public List<SegRol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegRol> findPageSegRol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegRol() throws Exception;

	 public List<SegRolDTO> getDataSegRol() throws Exception;

	public List<SegRolDTO> findRolesDTO(List<SegRol> losRoles) throws Exception;

	public List<SegRolDTO> getRolesBySystem(Long usuSession, String sistema) throws Exception;

	public List<SegRol> getRolesBySystemModel(Long usuSession, String sistema) throws Exception;

	public List<SegRol> getRolesBySystemModelNoAdmin(String sistema) throws Exception;

	public List<SegRol> findAdministradores() throws Exception;
}
