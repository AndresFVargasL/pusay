package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegSistema;
import com.vortexbird.seguridad.modelo.dto.SegSistemaDTO;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegSistemaLogic {
    public List<SegSistema> getSegSistema() throws Exception;

    public void saveSegSistema(Long sisCodigo, String sisDescripcion,
        String sisEstadoRegistro, String sisNombre, Long usuCodigo_SegUsuario)
        throws Exception;

    public void deleteSegSistema(Long sisCodigo) throws Exception;

    public void updateSegSistema(Long sisCodigo, String sisDescripcion,
        String sisEstadoRegistro, String sisNombre, Long usuCodigo_SegUsuario)
        throws Exception;

    public SegSistema getSegSistema(Long sisCodigo) throws Exception;

    public List<SegSistema> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegSistema> findPageSegSistema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegSistema() throws Exception;

	public List<SegSistemaDTO> findSistemasDTO(List<SegSistema> losSistemas)throws Exception;

	public List<SegSistemaDTO> getDataSegSistema() throws Exception;

	public List<SegSistema> getSistemasDeUsuario(Long usuSession, String sistema) throws Exception;

	public List<SegSistema> getSistemasDeUsuarioPorRol(Long usuSession) throws Exception;
}
