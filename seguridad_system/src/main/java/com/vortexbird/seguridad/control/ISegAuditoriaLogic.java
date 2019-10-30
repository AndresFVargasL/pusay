package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegAuditoria;
import com.vortexbird.seguridad.modelo.dto.SegAuditoriaDTO;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegAuditoriaLogic {
    public List<SegAuditoria> getSegAuditoria() throws Exception;

    public void saveSegAuditoria(Long autCodigo, String campo,
        Long codRegistro, Date fecha, String tabla, String tipo,
        String valorAnterior, String valorNuevo, Long usuCodigo_SegUsuario)
        throws Exception;

    public void deleteSegAuditoria(Long autCodigo) throws Exception;

    public void updateSegAuditoria(Long autCodigo, String campo,
        Long codRegistro, Date fecha, String tabla, String tipo,
        String valorAnterior, String valorNuevo, Long usuCodigo_SegUsuario)
        throws Exception;

    public SegAuditoria getSegAuditoria(Long autCodigo)
        throws Exception;

    public List<SegAuditoria> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegAuditoria> findPageSegAuditoria(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegAuditoria() throws Exception;

	    public List<SegAuditoriaDTO> getDataSegAuditoria()
        throws Exception;
}
