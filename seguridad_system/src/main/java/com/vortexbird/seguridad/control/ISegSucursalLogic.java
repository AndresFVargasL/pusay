package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegSucursal;
import com.vortexbird.seguridad.modelo.dto.SegSucursalDTO;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegSucursalLogic {
    public List<SegSucursal> getSegSucursal() throws Exception;

    public void saveSegSucursal(Long sucCodigo, String sucCodigoInterno,
        String sucEstadoRegistro, String sucNombre, Long ciaCodigo_SegCompania,
        Long usuCodigo_SegUsuario) throws Exception;

    public void deleteSegSucursal(Long sucCodigo) throws Exception;

    public void updateSegSucursal(Long sucCodigo, String sucCodigoInterno,
        String sucEstadoRegistro, String sucNombre, Long ciaCodigo_SegCompania,
        Long usuCodigo_SegUsuario) throws Exception;

    public SegSucursal getSegSucursal(Long sucCodigo) throws Exception;

    public List<SegSucursal> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegSucursal> findPageSegSucursal(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegSucursal() throws Exception;

	public List<SegSucursalDTO> findSucursalesDTO(List<SegSucursal> lasSucursales) throws Exception;

	public List<SegSucursalDTO> getDataSegSucursal() throws Exception;
}
