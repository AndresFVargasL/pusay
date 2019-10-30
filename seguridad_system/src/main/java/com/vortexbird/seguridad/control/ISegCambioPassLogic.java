package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegCambioPass;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegCambioPassLogic {
    public List<SegCambioPass> getSegCambioPass() throws Exception;

    public void saveSegCambioPass(Long capCodigo, String capEstado,
        Date capFechaFin, Date capFechaIni, String capToken,
        Long usuCodigo_SegUsuario) throws Exception;

    public void deleteSegCambioPass(Long capCodigo) throws Exception;

    public void updateSegCambioPass(Long capCodigo, String capEstado,
        Date capFechaFin, Date capFechaIni, String capToken,
        Long usuCodigo_SegUsuario) throws Exception;

    public SegCambioPass getSegCambioPass(Long capCodigo)
        throws Exception;

    public List<SegCambioPass> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegCambioPass> findPageSegCambioPass(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegCambioPass() throws Exception;
}
