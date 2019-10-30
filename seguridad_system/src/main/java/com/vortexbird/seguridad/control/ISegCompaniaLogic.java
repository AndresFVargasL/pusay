package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.seguridad.modelo.SegCompania;
import com.vortexbird.seguridad.modelo.dto.SegCompaniaDTO;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface ISegCompaniaLogic {
    public List<SegCompania> getSegCompania() throws Exception;

    public void saveSegCompania(Long ciaCodigo, String ciaCodigoInterno,
        String ciaEstadoRegistro, String ciaNombre, Long usuCodigo_SegUsuario)
        throws Exception;

    public void deleteSegCompania(Long ciaCodigo) throws Exception;

    public void updateSegCompania(Long ciaCodigo, String ciaCodigoInterno,
        String ciaEstadoRegistro, String ciaNombre, Long usuCodigo_SegUsuario)
        throws Exception;

    public SegCompania getSegCompania(Long ciaCodigo) throws Exception;

    public List<SegCompania> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegCompania> findPageSegCompania(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegCompania() throws Exception;

	public List<SegCompaniaDTO> findCompaniasDTO(List<SegCompania> lasCompanias)throws Exception;

	public List<SegCompaniaDTO> getSegCompaniaDTO() throws Exception;

	public List<SegCompania> getCompaniaDeUsuarioPorRol(Long usuSession, long sistema) throws Exception;

	public List<SegCompaniaDTO> getCompaniasBySystemDTO(Long usuLogin, long sistema) throws Exception;

	public List<SegCompania> getCompaniasPorRol(Long codigoRol) throws Exception;

}
