package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegGrupoOpcion;
import com.vortexbird.seguridad.modelo.dto.SegGrupoOpcionDTO;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegGrupoOpcionLogic {
    public List<SegGrupoOpcion> getSegGrupoOpcion() throws Exception;

    public void saveSegGrupoOpcion(Long gruCodigo, String gruDescripcion,
        String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
        Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
        Long usuCodigo_SegUsuario) throws Exception;

    public void deleteSegGrupoOpcion(Long gruCodigo) throws Exception;

    public void updateSegGrupoOpcion(Long gruCodigo, String gruDescripcion,
        String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
        Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
        Long usuCodigo_SegUsuario) throws Exception;

    public SegGrupoOpcion getSegGrupoOpcion(Long gruCodigo)
        throws Exception;

    public List<SegGrupoOpcion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegGrupoOpcion> findPageSegGrupoOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegGrupoOpcion() throws Exception;

	public List<SegGrupoOpcionDTO> findGrupoOpcionDTO(List<SegGrupoOpcion> losGrupoOpcion) throws Exception;

	public List<SegGrupoOpcionDTO> getDataSegGrupoOpcion() throws Exception;

	public List<SegGrupoOpcionDTO> getGrupoOpcionesPorSistema(Long usuSession, String sistema) throws Exception;

	public List<SegGrupoOpcion> getGrupoOpcionesPorSistemaModel(Long usuSession, String sistema) throws Exception;
}
