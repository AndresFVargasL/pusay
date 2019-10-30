package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.vortexbird.seguridad.modelo.SegOpcion;
import com.vortexbird.seguridad.modelo.SegPermiso;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegPermisoLogic {
    public List<SegPermiso> getSegPermiso() throws Exception;

    public void saveSegPermiso(String perEstadoRegistro,
        Long sicCodigo_SegSistemaCia, Long gruCodigo_SegGrupoOpcion,
        Long opcCodigo_SegOpcion, Long rolCodigo_SegRol,
        Long sucCodigo_SegSucursal, Long usuCodigo_SegUsuario,
        Long usuCodigo_SegUsuario0) throws Exception;

    public void deleteSegPermiso(Long perCodigo) throws Exception;

    public void updateSegPermiso(Long perCodigo, String perEstadoRegistro,
        Long ciaCodigo_SegCompania, Long gruCodigo_SegGrupoOpcion,
        Long opcCodigo_SegOpcion, Long rolCodigo_SegRol,
        Long sucCodigo_SegSucursal, Long usuCodigo_SegUsuario,
        Long usuCodigo_SegUsuario0) throws Exception;

    public SegPermiso getSegPermiso(Long perCodigo) throws Exception;

    public List<SegPermiso> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegPermiso> findPageSegPermiso(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegPermiso() throws Exception;

	public void saveOptionsForRolInPermiso(Long rolCodigo, List<SegOpcion> listOpciones, Long codigoCompania, Long sisCodigo, String codigoUsuario) throws Exception;

	public List<SegOpcion> getOpcionesPorRolInPermisos(Long rolCodigo, Long codigoCompania)throws Exception;

	public void updateOptionsForRolInPermiso(Long rolCodigo,
			List<SegOpcion> listOpciones, Long codigoCompania, Long sisCodigo, String codigoUsuario) throws Exception;

	public Set<SegOpcion> findEstructuraArbol(TreeNode rootNoda)throws Exception;

	public List<SegPermiso> getPermisosCriteria(Long rolCodigo,	Long codigoCompania, Long codigoOpcion, String codigoUsario)throws Exception;

	public List<SegOpcion> getOpcionesPorRolInPermisosSisCia(Long codigoRol,
			Long codigoCompania) throws Exception;

	public List<SegPermiso> findPermisosAdmin(Long usuCodigo) throws Exception;

	public List<SegPermiso> findPermisosByUsuAndSisCia(Long usuCodigo,
			String compania, String sistema) throws Exception;

	public List<SegOpcion> consultarOpcionesPorRolUnionUserInPermisos(
			Long codigoRol, Long codigoCompania, String codigoUsario) throws Exception;

	public List<SegOpcion> consultarOpcionesPorUsuarioEnPermisos(
			Long rolCodigo, Long codigoCompania, Long usuSession) throws Exception;
}
