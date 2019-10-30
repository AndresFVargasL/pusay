package com.vortexbird.pusay.modelo.control;





import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEridaDTO;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionAsuntoAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionImpactosAmbientalesDTO;
import com.vortexbird.pusay.modelo.dto.PsyTablaEridaDTO;
import com.vortexbird.pusay.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("PsyDetalleEridaLogic")
public class PsyDetalleEridaLogic implements IPsyDetalleEridaLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyDetalleEridaLogic.class);

    /**
     * DAO injected by Spring that manages PsyDetalleErida entities
     *
     */
    @Autowired
    private IPsyDetalleEridaDAO psyDetalleEridaDAO;
    @Autowired
    private IPsyMatrizEridaDAO psyMatrizEridaDAO;
   
    
    @Autowired
    private IPsyAsuntoAmbientalDAO psyAsuntoAmbientalDAO;
    @Autowired
    private IPsyImpactoAmbientalDAO psyImpactoAmbientalDAO;

    /**
    * Logic injected by Spring that manages PsyAsuntoAmbiental entities
    *
    */
    @Autowired
    IPsyAsuntoAmbientalLogic logicPsyAsuntoAmbiental1;

    /**
    * Logic injected by Spring that manages PsyImpactoAmbiental entities
    *
    */
    @Autowired
    IPsyImpactoAmbientalLogic logicPsyImpactoAmbiental2;

    /**
    * Logic injected by Spring that manages PsyMatrizErida entities
    *
    */
    @Autowired
    IPsyMatrizEridaLogic logicPsyMatrizErida3;

    @Transactional(readOnly = true)
    public List<PsyDetalleErida> getPsyDetalleErida() throws Exception {
        log.debug("finding all PsyDetalleErida instances");

        List<PsyDetalleErida> list = new ArrayList<PsyDetalleErida>();

        try {
            list = psyDetalleEridaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyDetalleErida failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyDetalleErida");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyDetalleErida(PsyDetalleErida entity)
        throws Exception {
        log.debug("saving PsyDetalleErida instance");

        try {
            if (entity.getPsyAsuntoAmbiental() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyAsuntoAmbiental");
            }

            if (entity.getPsyImpactoAmbiental() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyImpactoAmbiental");
            }

            if (entity.getPsyMatrizErida() == null) {
                throw new ZMessManager().new ForeignException("psyMatrizErida");
            }

            if (entity.getCalificacion() == null) {
                throw new ZMessManager().new EmptyFieldException("calificacion");
            }

            if ((entity.getCalificacion() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getCalificacion(), 2, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "calificacion");
            }

//            if (entity.getDeerCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException("deerCodigo");
//            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estadoRegistro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoRegistro");
            }

            if (entity.getPeso() == null) {
                throw new ZMessManager().new EmptyFieldException("peso");
            }

//            if ((entity.getPeso() != null) &&
//                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
//                        entity.getPeso(), 5, 0) == false)) {
//                throw new ZMessManager().new NotValidFormatException("peso");
//            }

//            if ((entity.getValoracion() != null) &&
//                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
//                        entity.getValoracion(), 10, 0) == false)) {
//                throw new ZMessManager().new NotValidFormatException(
//                    "valoracion");
//            }

            if (entity.getPsyAsuntoAmbiental().getAsamCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "asamCodigo_PsyAsuntoAmbiental");
            }

            if (entity.getPsyImpactoAmbiental().getImamCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "imamCodigo_PsyImpactoAmbiental");
            }

            if (entity.getPsyMatrizErida().getMaerCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "maerCodigo_PsyMatrizErida");
            }
//
//            if (getPsyDetalleErida(entity.getDeerCodigo()) != null) {
//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//            }

            psyDetalleEridaDAO.save(entity);

            log.debug("save PsyDetalleErida successful");
        } catch (Exception e) {
            log.error("save PsyDetalleErida failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyDetalleErida(PsyDetalleErida entity)
        throws Exception {
        log.debug("deleting PsyDetalleErida instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("PsyDetalleErida");
        }

        if (entity.getDeerCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("deerCodigo");
        }

        try {
            psyDetalleEridaDAO.delete(entity);

            log.debug("delete PsyDetalleErida successful");
        } catch (Exception e) {
            log.error("delete PsyDetalleErida failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyDetalleErida(PsyDetalleErida entity)
        throws Exception {
        log.debug("updating PsyDetalleErida instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PsyDetalleErida");
            }

            if (entity.getPsyAsuntoAmbiental() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyAsuntoAmbiental");
            }

            if (entity.getPsyImpactoAmbiental() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyImpactoAmbiental");
            }

            if (entity.getPsyMatrizErida() == null) {
                throw new ZMessManager().new ForeignException("psyMatrizErida");
            }

            if (entity.getCalificacion() == null) {
                throw new ZMessManager().new EmptyFieldException("calificacion");
            }

            if ((entity.getCalificacion() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getCalificacion(), 2, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "calificacion");
            }

            if (entity.getDeerCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("deerCodigo");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estadoRegistro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoRegistro");
            }

            if (entity.getPeso() == null) {
                throw new ZMessManager().new EmptyFieldException("peso");
            }

            if ((entity.getPeso() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getPeso(), 5, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("peso");
            }

            if ((entity.getValoracion() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getValoracion(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "valoracion");
            }

            if (entity.getPsyAsuntoAmbiental().getAsamCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "asamCodigo_PsyAsuntoAmbiental");
            }

            if (entity.getPsyImpactoAmbiental().getImamCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "imamCodigo_PsyImpactoAmbiental");
            }

            if (entity.getPsyMatrizErida().getMaerCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "maerCodigo_PsyMatrizErida");
            }

            psyDetalleEridaDAO.update(entity);

            log.debug("update PsyDetalleErida successful");
        } catch (Exception e) {
            log.error("update PsyDetalleErida failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyDetalleEridaDTO> getDataPsyDetalleErida()
        throws Exception {
        try {
            List<PsyDetalleErida> psyDetalleErida = psyDetalleEridaDAO.findAll();

            List<PsyDetalleEridaDTO> psyDetalleEridaDTO = new ArrayList<PsyDetalleEridaDTO>();

            for (PsyDetalleErida psyDetalleEridaTmp : psyDetalleErida) {
                PsyDetalleEridaDTO psyDetalleEridaDTO2 = new PsyDetalleEridaDTO();

                psyDetalleEridaDTO2.setDeerCodigo(psyDetalleEridaTmp.getDeerCodigo());
                psyDetalleEridaDTO2.setCalificacion((psyDetalleEridaTmp.getCalificacion() != null)
                    ? psyDetalleEridaTmp.getCalificacion() : null);
                psyDetalleEridaDTO2.setEstadoRegistro((psyDetalleEridaTmp.getEstadoRegistro() != null)
                    ? psyDetalleEridaTmp.getEstadoRegistro() : null);
                psyDetalleEridaDTO2.setPeso((psyDetalleEridaTmp.getPeso() != null)
                    ? psyDetalleEridaTmp.getPeso() : null);
                psyDetalleEridaDTO2.setValoracion((psyDetalleEridaTmp.getValoracion() != null)
                    ? psyDetalleEridaTmp.getValoracion() : null);
                psyDetalleEridaDTO2.setAsamCodigo_PsyAsuntoAmbiental((psyDetalleEridaTmp.getPsyAsuntoAmbiental()
                                                                                        .getAsamCodigo() != null)
                    ? psyDetalleEridaTmp.getPsyAsuntoAmbiental().getAsamCodigo()
                    : null);
                psyDetalleEridaDTO2.setImamCodigo_PsyImpactoAmbiental((psyDetalleEridaTmp.getPsyImpactoAmbiental()
                                                                                         .getImamCodigo() != null)
                    ? psyDetalleEridaTmp.getPsyImpactoAmbiental().getImamCodigo()
                    : null);
                psyDetalleEridaDTO2.setMaerCodigo_PsyMatrizErida((psyDetalleEridaTmp.getPsyMatrizErida()
                                                                                    .getMaerCodigo() != null)
                    ? psyDetalleEridaTmp.getPsyMatrizErida().getMaerCodigo()
                    : null);
                psyDetalleEridaDTO.add(psyDetalleEridaDTO2);
            }

            return psyDetalleEridaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyDetalleErida getPsyDetalleErida(Long deerCodigo)
        throws Exception {
        log.debug("getting PsyDetalleErida instance");

        PsyDetalleErida entity = null;

        try {
            entity = psyDetalleEridaDAO.findById(deerCodigo);
        } catch (Exception e) {
            log.error("get PsyDetalleErida failed", e);
            throw new ZMessManager().new FindingException("PsyDetalleErida");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyDetalleErida> findPagePsyDetalleErida(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<PsyDetalleErida> entity = null;

        try {
            entity = psyDetalleEridaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyDetalleErida Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyDetalleErida() throws Exception {
        Long entity = null;

        try {
            entity = psyDetalleEridaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyDetalleErida Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<PsyDetalleErida> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyDetalleErida> list = new ArrayList<PsyDetalleErida>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = psyDetalleEridaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    @Transactional(readOnly = true)
   	public List<PsyTablaEridaDTO> consultarTablaErida(Long mearCodigo,Long asamCodigo)
   			throws Exception {
   		List<PsyTablaEridaDTO> listPsyTablaEridaDTO;
   		try {
   			listPsyTablaEridaDTO= psyDetalleEridaDAO.consultarTablaErida(mearCodigo,asamCodigo);
   			  if(listPsyTablaEridaDTO.size()==0){
   				  listPsyTablaEridaDTO = psyDetalleEridaDAO.llenarTablaErida();
   			  }
   		} catch (Exception e) {
   			throw new Exception(e.getMessage());
   		}
   		return listPsyTablaEridaDTO;
   	}

       @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
   	public void guardarTablaErida(List<PsyTablaEridaDTO> tablaEridaDTO,
   			Long maerCodigo,Long asamCodigo) throws Exception {
       	
       	PsyMatrizErida psyMatrizErida= psyMatrizEridaDAO.findById(maerCodigo);
       	List<PsyDetalleErida> list = new ArrayList<PsyDetalleErida>();
       	
   		try {
   			List<Double> resultadosTotalesTablaErida= calcularTotalesTablaErida(tablaEridaDTO);
   			
   			if (resultadosTotalesTablaErida.get(0)!=100) {
   			  throw new ZMessManager("La suma del los peso debe ser igual a 100%");
			}
   			//list =psyDetalleEridaDAO.find("from PsyDetalleErida where psyMatrizErida="+maerCodigo+" and psyAsuntoAmbiental="+asamCodigo);
   			for (int i = 0; i < tablaEridaDTO.size(); i++) {
   				
   				list =psyDetalleEridaDAO.find("from PsyDetalleErida where psyMatrizErida="+maerCodigo+" and psyAsuntoAmbiental="+asamCodigo+" and psyImpactoAmbiental="+tablaEridaDTO.get(i).getImamCodigo());
   				
   				if(list.size()!=0){
   					
   					if (tablaEridaDTO.get(i).getCalificacion()==0) {
   						throw new ZMessManager("La calificación para el asunto ambiental "+tablaEridaDTO.get(i).getImpactoAmbiental()+" debe estar entre 1 y 10");
   		            }
   					if (tablaEridaDTO.get(i).getPeso()<=0) {
   		                throw new ZMessManager("El peso para el asunto ambiental "+tablaEridaDTO.get(i).getImpactoAmbiental()+" debe ser mayor a 0%");
   		            }
   					if (tablaEridaDTO.get(i).getCalificacion()>10) {
   		                throw new ZMessManager("La calificación para el asunto ambiental "+tablaEridaDTO.get(i).getImpactoAmbiental()+" debe estar entre 1 y 10");
   		            }
   					
   					PsyDetalleErida detalleErida = psyDetalleEridaDAO.findById(list.get(0).getDeerCodigo());  					
   					detalleErida.setPeso(tablaEridaDTO.get(i).getPeso());
   					detalleErida.setCalificacion(tablaEridaDTO.get(i).getCalificacion());
   					detalleErida.setValoracion(tablaEridaDTO.get(i).getPeso()*0.01*tablaEridaDTO.get(i).getCalificacion());
   				
   					psyDetalleEridaDAO.update(detalleErida);
   					
   				}else{
   					if (tablaEridaDTO.get(i).getCalificacion()==0) {
   						throw new ZMessManager("La calificación para el asunto ambiental "+tablaEridaDTO.get(i).getImpactoAmbiental()+" debe estar entre 1 y 10");
   		            }
   					if (tablaEridaDTO.get(i).getPeso()<=0) {
   		                throw new ZMessManager("El peso para el asunto ambiental "+tablaEridaDTO.get(i).getImpactoAmbiental()+" debe ser mayor a 0%");
   		            }
   					if (tablaEridaDTO.get(i).getCalificacion()>10) {
   		                throw new ZMessManager("La calificación para el asunto ambiental "+tablaEridaDTO.get(i).getImpactoAmbiental()+" debe estar entre 1 y 10");
   		            }
   				
   				PsyDetalleErida detalleErida = new PsyDetalleErida();
   				detalleErida.setPeso(tablaEridaDTO.get(i).getPeso());
   				detalleErida.setCalificacion(tablaEridaDTO.get(i).getCalificacion());
   				detalleErida.setValoracion(tablaEridaDTO.get(i).getPeso()*0.01*tablaEridaDTO.get(i).getCalificacion());
   				detalleErida.setPsyMatrizErida(psyMatrizErida);
   				detalleErida.setPsyImpactoAmbiental(psyImpactoAmbientalDAO.findById(tablaEridaDTO.get(i).getImamCodigo()));
   				detalleErida.setPsyAsuntoAmbiental(psyAsuntoAmbientalDAO.findById(asamCodigo));
   				detalleErida.setEstadoRegistro("A");
   				savePsyDetalleErida(detalleErida);
   			}
   			}
   		} catch (Exception e) {
   			throw new Exception(e.getMessage());
   		}
   		
   	}

	@Override
	public List<Double> calcularTotalesTablaErida(
			List<PsyTablaEridaDTO> listPsyTablaEridaDTO) throws Exception {
		List<Double> resultados  =  new ArrayList<Double>();
		double sumaPeso =0;
		double sumaCalificacion=0;
		double sumaValoracion=0;
	try {
		for (int i = 0; i < listPsyTablaEridaDTO.size(); i++) {
			sumaPeso =  sumaPeso + listPsyTablaEridaDTO.get(i).getPeso();
			sumaCalificacion=sumaCalificacion+listPsyTablaEridaDTO.get(i).getCalificacion();
			sumaValoracion=sumaValoracion+listPsyTablaEridaDTO.get(i).getValoracion();
		}
		resultados.add(Utilities.redondear(sumaPeso, 2));
		resultados.add(Utilities.redondear(sumaCalificacion,2));
		resultados.add(Utilities.redondear(sumaValoracion,2));
		
	} catch (Exception e) {
		throw new Exception(e.getMessage());
	}
		return resultados;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<PsyPriorizacionAsuntoAmbientalDTO> PriorizacionAsuntoAmbiental(
			Long mearCodigo) throws Exception {
		List<PsyPriorizacionAsuntoAmbientalDTO> lstPsyPriorizacionAsuntoAmbiental = null;
		try {
			lstPsyPriorizacionAsuntoAmbiental = psyDetalleEridaDAO.PriorizacionAsuntoAmbiental(mearCodigo);
				
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return lstPsyPriorizacionAsuntoAmbiental;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<PsyPriorizacionImpactosAmbientalesDTO> PriorizacionImpactosAmbientales(
			Long mearCodigo) throws Exception {
		List<PsyPriorizacionImpactosAmbientalesDTO> lstPsyPsyPriorizacionImpactosAmbientalesDTO = null;
		try {
			lstPsyPsyPriorizacionImpactosAmbientalesDTO = psyDetalleEridaDAO.PriorizacionImpactosAmbientales(mearCodigo);
				
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return lstPsyPsyPriorizacionImpactosAmbientalesDTO;
	}

	

	
}
