package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegParametro;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegParametroLogic {
    public List<SegParametro> getSegParametro() throws Exception;

    public void saveSegParametro(String parEstadoRegistro,
        String parNombre, String parValorAlfanumerico, Date parValorFecha,
        Double parValorNumerico, Long usuCodigo_SegUsuario)
        throws Exception;

    public void deleteSegParametro(Long parCodigo) throws Exception;

    public void updateSegParametro(Long parCodigo, String parEstadoRegistro,
        String parNombre, String parValorAlfanumerico, Date parValorFecha,
        Double parValorNumerico, Long usuCodigo_SegUsuario)
        throws Exception;

    public SegParametro getSegParametro(Long parCodigo)
        throws Exception;

    public List<SegParametro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegParametro> findPageSegParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegParametro() throws Exception;
}
