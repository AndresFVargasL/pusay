package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegSistemaCia;
import com.vortexbird.seguridad.modelo.dto.SegSistemaCiaDTO;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegSistemaCiaLogic {
    public List<SegSistemaCia> getSegSistemaCia() throws Exception;

    public void saveSegSistemaCia(Long sicCodigo, String sicEstadoRegistro,
        Long ciaCodigo_SegCompania, Long sisCodigo_SegSistema,
        Long usuCodigo_SegUsuario) throws Exception;

    public void deleteSegSistemaCia(Long sicCodigo) throws Exception;

    public void updateSegSistemaCia(Long sicCodigo, String sicEstadoRegistro,
        Long ciaCodigo_SegCompania, Long sisCodigo_SegSistema,
        Long usuCodigo_SegUsuario) throws Exception;

    public SegSistemaCia getSegSistemaCia(Long sicCodigo)
        throws Exception;

    public List<SegSistemaCia> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegSistemaCia> findPageSegSistemaCia(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegSistemaCia() throws Exception;

	public List<SegSistemaCiaDTO> findSegSistemaCiaDTO(List<SegSistemaCia> losSistemasCia) throws Exception;

	public List<SegSistemaCiaDTO> getDataSegSistemaCia() throws Exception;

	public List<SegSistemaCiaDTO> getSistemasCiaQuery(String sistema,
			String compania) throws Exception;
}
