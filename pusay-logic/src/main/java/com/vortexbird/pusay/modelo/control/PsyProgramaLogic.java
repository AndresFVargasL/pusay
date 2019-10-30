package com.vortexbird.pusay.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.pusay.dataaccess.dao.IPsyPlanAccionDAO;
import com.vortexbird.pusay.dataaccess.dao.IPsyPlanEstrategiaDAO;
import com.vortexbird.pusay.dataaccess.dao.IPsyProgramaDAO;
import com.vortexbird.pusay.exceptions.ZMessManager;
import com.vortexbird.pusay.modelo.PsyDetalleMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.PsyPlanEstrategia;
import com.vortexbird.pusay.modelo.PsyPrograma;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEstrategiasDTO;
import com.vortexbird.pusay.modelo.dto.PsyProgramaDTO;
import com.vortexbird.pusay.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("PsyProgramaLogic")
public class PsyProgramaLogic implements IPsyProgramaLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyProgramaLogic.class);
    private static final String ESTADO_REGISTRO_ACTIVO = "A";

    /**
     * DAO injected by Spring that manages PsyPrograma entities
     *
     */
    @Autowired
    private IPsyProgramaDAO psyProgramaDAO;

    /**
    * DAO injected by Spring that manages PsyPlanAccion entities
    *
    */
    @Autowired
    private IPsyPlanAccionDAO psyPlanAccionDAO;

    /**
    * DAO injected by Spring that manages PsyPlanEstrategia entities
    *
    */
    @Autowired
    private IPsyPlanEstrategiaDAO psyPlanEstrategiaDAO;
    
    @Autowired
    private IPsyDetalleMapaEstrategicoLogic psyDetalleMapaEstrategicoLogic;
    
    @Autowired
    private IPsyPlanEstrategiaLogic psyPlanEstrategiaLogic;

    @Transactional(readOnly = true)
    public List<PsyPrograma> getPsyPrograma() throws Exception {
        log.debug("finding all PsyPrograma instances");

        List<PsyPrograma> list = new ArrayList<PsyPrograma>();

        try {
            list = psyProgramaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyPrograma failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyPrograma");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyPrograma(PsyPrograma entity, String dmaeCodigo, PsyEmpresa empresa) throws Exception {
        log.debug("saving PsyPrograma instance");

        try {
            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoRegistro");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        250) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }
            
            

            psyProgramaDAO.save(entity);
            
            //DESPUES DE GUARDAR EL PLAN DE ACCION SE PROCEDE A GUARDAR EL PLAN ESTRATEGIA
            
            if (dmaeCodigo == null) {
				throw new ZMessManager(
						"Debe seleccionar una estrategia");
			}

			if (dmaeCodigo.trim().equals("")) {
				throw new ZMessManager(
						"Debe seleccionar una estrategia");
			}
			
			if (dmaeCodigo.trim().equals("0")) {
				throw new ZMessManager(
						"Debe seleccionar una estrategia");
			}
			
			Long detalleCodigo = new Long(dmaeCodigo);
			
			List<PsyDetalleMapaEstrategico> detalles = psyPlanEstrategiaDAO.validacionEstrategiasPlanActual(empresa, detalleCodigo);
			
			if(detalles.size() > 0){
				throw new ZMessManager(
						"Esa estrategia ya esta asignada a otro programa");
			}
			
            log.debug("saving PsyPlanEstrategia instance");
            PsyPlanEstrategia plEstrategia = new PsyPlanEstrategia();
            
            plEstrategia.setPsyPrograma(entity);
            
            plEstrategia.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
            
            plEstrategia
			.setPsyDetalleMapaEstrategico(psyDetalleMapaEstrategicoLogic.getPsyDetalleMapaEstrategico(detalleCodigo));
			
			if (plEstrategia.getPsyPrograma().getProgCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Plan de Accion");
			}

			if (plEstrategia.getPsyPrograma() == null) {
				throw new ZMessManager().new ForeignException(
						"Plan de Accion");
			}

			if (plEstrategia.getEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Estado Registro");
			}

			if ((plEstrategia.getEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							plEstrategia.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado registro");
			}
			
			if (plEstrategia.getPsyDetalleMapaEstrategico()
					.getDmaeCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Mapa Estrategico");
			}

			if (plEstrategia.getPsyDetalleMapaEstrategico() == null) {
				throw new ZMessManager().new ForeignException(
						"Detalle del Mapa Estrategico");
			}

			psyPlanEstrategiaDAO.save(plEstrategia);

			log.debug("save PsyPlanEstrategia successful");
		

    		

            log.debug("save PsyPrograma successful");
        } catch (Exception e) {
            log.error("save PsyPrograma failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyPrograma(PsyPrograma entity) throws Exception {
        log.debug("deleting PsyPrograma instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("PsyPrograma");
        }

        if (entity.getProgCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("progCodigo");
        }

        List<PsyPlanAccion> psyPlanAccions = null;
        List<PsyPlanEstrategia> psyPlanEstrategias = null;

        try {
            psyPlanAccions = psyPlanAccionDAO.findByProperty("psyPrograma.progCodigo",
                    entity.getProgCodigo());

            if (Utilities.validationsList(psyPlanAccions) == true) {
                throw new ZMessManager().new DeletingException("Plan de Accion");
            }
            
            

            psyPlanEstrategias = psyPlanEstrategiaDAO.findByProperty("psyPrograma.progCodigo",
            		entity.getProgCodigo());
            
            if(psyPlanEstrategias.size() > 0){
            	for (PsyPlanEstrategia psyPlanEstrategia : psyPlanEstrategias) {
            		psyPlanEstrategiaLogic.deletePsyPlanEstrategia(psyPlanEstrategia);
            	}
            }
            
            psyPlanEstrategias = psyPlanEstrategiaDAO.findByProperty("psyPrograma.progCodigo",
            		entity.getProgCodigo());

            if (Utilities.validationsList(psyPlanEstrategias) == true) {
                throw new ZMessManager().new DeletingException(
                    "Plan Estrategia");
            }

            psyProgramaDAO.delete(entity);

            log.debug("delete PsyPrograma successful");
        } catch (Exception e) {
            log.error("delete PsyPrograma failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyPrograma(PsyPrograma entity, String dmaeCodigo, PsyEmpresa empresa) throws Exception {
        log.debug("updating PsyPrograma instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("PsyPrograma");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoRegistro");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        250) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            psyProgramaDAO.update(entity);
            
            //DESPUES DE GUARDAR EL PROGRAMA SE PROCEDE A GUARDAR EL PLAN ESTRATEGIA
            
            if (dmaeCodigo == null) {
				throw new ZMessManager(
						"Debe seleccionar una estrategia");
			}

			if (dmaeCodigo.trim().equals("")) {
				throw new ZMessManager(
						"Debe seleccionar una estrategia");
			}
			
			if (dmaeCodigo.trim().equals("0")) {
				throw new ZMessManager(
						"Debe seleccionar una estrategia");
			}
			
			Long detalleCodigo = new Long(dmaeCodigo);
			
			List<PsyDetalleMapaEstrategico> detalles = psyPlanEstrategiaDAO.validacionEstrategiasPlanActual(empresa, detalleCodigo);
			
			if(detalles.size() > 0){
				throw new ZMessManager(
						"Esa estrategia ya esta asignada a otro programa");
			}
			
            log.debug("saving PsyPlanEstrategia instance");
            
            Object[] variables = {"psyPrograma.progCodigo",false,entity.getProgCodigo(),"=","estadoRegistro",true,"A","="};
            List<PsyPlanEstrategia> losPlanes = psyPlanEstrategiaLogic.findByCriteria(variables, null, null); 
            
            PsyPlanEstrategia plEstrategia = new PsyPlanEstrategia();
            plEstrategia = losPlanes.get(0);
            
            plEstrategia.setPsyPrograma(entity);
            
            plEstrategia.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
            
            plEstrategia
			.setPsyDetalleMapaEstrategico(psyDetalleMapaEstrategicoLogic.getPsyDetalleMapaEstrategico(detalleCodigo));
			
			if (plEstrategia.getPsyPrograma().getProgCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Programa");
			}

			if (plEstrategia.getPsyPrograma() == null) {
				throw new ZMessManager().new ForeignException(
						"Programa");
			}

			if (plEstrategia.getEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Estado Registro");
			}

			if ((plEstrategia.getEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							plEstrategia.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado registro");
			}

			if (plEstrategia.getPsyDetalleMapaEstrategico()
					.getDmaeCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Mapa Estrategico");
			}

			if (plEstrategia.getPsyDetalleMapaEstrategico() == null) {
				throw new ZMessManager().new ForeignException(
						"Detalle de Mapa Estrategico");
			}

			psyPlanEstrategiaDAO.update(plEstrategia);

			log.debug("save PsyPlanEstrategia successful");
			
            log.debug("update PsyPrograma successful");
        } catch (Exception e) {
            log.error("update PsyPrograma failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyProgramaDTO> getDataPsyPrograma(PsyEmpresa empresa) throws Exception {
        try {
            List<PsyProgramaDTO> psyPrograma = psyProgramaDAO.consultaProgramaPorEmpresa(empresa);

            List<PsyProgramaDTO> psyProgramaDTO = new ArrayList<PsyProgramaDTO>();

            for (PsyProgramaDTO psyProgramaTmp : psyPrograma) {
                PsyProgramaDTO psyProgramaDTO2 = new PsyProgramaDTO();

                psyProgramaDTO2.setProgCodigo(psyProgramaTmp.getProgCodigo());
                
                psyProgramaDTO2.setEstadoRegistro((psyProgramaTmp.getEstadoRegistro().trim().equals("A")) ?
                		"Activo" : "Inactivo");
                
                psyProgramaDTO2.setNombre((psyProgramaTmp.getNombre() != null)
                    ? psyProgramaTmp.getNombre() : null);
                
                psyProgramaDTO2.setDmaeCodigo((psyProgramaTmp.getDmaeCodigo() != null)
                        ? psyProgramaTmp.getDmaeCodigo() : null);
                
                psyProgramaDTO2.setMacoCodigo((psyProgramaTmp.getMacoCodigo() != null)
                        ? psyProgramaTmp.getMacoCodigo() : null);
                
                psyProgramaDTO2.setMaesCodigo((psyProgramaTmp.getMaesCodigo() != null)
                        ? psyProgramaTmp.getMaesCodigo() : null);
                
                psyProgramaDTO2.setTipo((psyProgramaTmp.getTipo() != null)
                        ? psyProgramaTmp.getTipo() : null);
                
                psyProgramaDTO2.setDmaeEstadoRegistro((psyProgramaTmp.getDmaeEstadoRegistro() != null)
                        ? psyProgramaTmp.getDmaeEstadoRegistro() : null);
                
                psyProgramaDTO.add(psyProgramaDTO2);
            }

            return psyProgramaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyPrograma getPsyPrograma(Long progCodigo)
        throws Exception {
        log.debug("getting PsyPrograma instance");

        PsyPrograma entity = null;

        try {
            entity = psyProgramaDAO.findById(progCodigo);
        } catch (Exception e) {
            log.error("get PsyPrograma failed", e);
            throw new ZMessManager().new FindingException("PsyPrograma");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyPrograma> findPagePsyPrograma(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<PsyPrograma> entity = null;

        try {
            entity = psyProgramaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyPrograma Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyPrograma() throws Exception {
        Long entity = null;

        try {
            entity = psyProgramaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyPrograma Count");
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
    public List<PsyPrograma> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyPrograma> list = new ArrayList<PsyPrograma>();
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
            list = psyProgramaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    @Transactional(readOnly = true)
    public List<PsyDetalleEstrategiasDTO> consultaEstrategiasParaPrograma(PsyEmpresa empresa)
            throws Exception{
    	if(empresa==null){
    		return null;
    	}else{
    		return psyProgramaDAO.consultaEstrategiasParaPrograma(empresa);
    	}
    }
    @Transactional(readOnly = true)
    public List<PsyDetalleEstrategiasDTO> consultaEstrategiasParaProgramaDepurada(PsyEmpresa empresa)
            throws Exception{
    	if(empresa==null){
    		return null;
    	}else{
    		return psyProgramaDAO.consultaEstrategiasParaProgramaDepurada(empresa);
    	}
    }
}
