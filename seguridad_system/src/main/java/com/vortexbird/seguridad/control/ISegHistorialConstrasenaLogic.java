package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegHistorialConstrasena;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegHistorialConstrasenaLogic {
    public List<SegHistorialConstrasena> getSegHistorialConstrasena()
        throws Exception;

    public void saveSegHistorialConstrasena(Long hcoCodigo,
        String hcoConstrasena, String hcoDetalleCambio, Date hcoFechaCambio,
        Long usuCodigo_SegUsuario) throws Exception;

    public void deleteSegHistorialConstrasena(Long hcoCodigo)
        throws Exception;

    public void updateSegHistorialConstrasena(Long hcoCodigo,
        String hcoConstrasena, String hcoDetalleCambio, Date hcoFechaCambio,
        Long usuCodigo_SegUsuario) throws Exception;

    public SegHistorialConstrasena getSegHistorialConstrasena(Long hcoCodigo)
        throws Exception;

    public List<SegHistorialConstrasena> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegHistorialConstrasena> findPageSegHistorialConstrasena(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSegHistorialConstrasena()
        throws Exception;
}
