package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegOpcion;
import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.SegOpcionDTO;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegOpcionLogic {
    public List<SegOpcion> getSegOpcion() throws Exception;

    public void saveSegOpcion(Long opcCodigo, String opcDescripcion,
        String opcEstadoRegistro, String opcLlaveAcceso, String opcNombre,
        Long gruCodigo_SegGrupoOpcion, Long usuCodigo_SegUsuario)
        throws Exception;

    public void deleteSegOpcion(Long opcCodigo) throws Exception;

    public void updateSegOpcion(Long opcCodigo, String opcDescripcion,
        String opcEstadoRegistro, String opcLlaveAcceso, String opcNombre,
        Long gruCodigo_SegGrupoOpcion, Long usuCodigo_SegUsuario)
        throws Exception;

    public SegOpcion getSegOpcion(Long opcCodigo) throws Exception;

    public List<SegOpcion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegOpcion> findPageSegOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegOpcion() throws Exception;

	public List<SegOpcionDTO> findSegOpcionDTO(List<SegOpcion> losOpcion) throws Exception;

	public List<SegOpcionDTO> getDataSegOpcion() throws Exception;

	public List<SegOpcionDTO> getOpcionesPorSistema(Long usuSession, String sistema) throws Exception;
	
	public List<GrupoDTO> getOpciones(String login) throws Exception;
}
