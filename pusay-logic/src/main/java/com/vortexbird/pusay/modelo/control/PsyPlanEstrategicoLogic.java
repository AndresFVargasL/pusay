package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.exceptions.ZMessManager.DeletingException;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategicoDTO;
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

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("PsyPlanEstrategicoLogic")
public class PsyPlanEstrategicoLogic implements IPsyPlanEstrategicoLogic {
	private static final Logger log = LoggerFactory
			.getLogger(PsyPlanEstrategicoLogic.class);
	private static final String ESTADO_REGISTRO_ACTIVO = "A";

	/**
	 * DAO injected by Spring that manages PsyPlanEstrategico entities
	 *
	 */
	@Autowired
	private IPsyPlanEstrategicoDAO psyPlanEstrategicoDAO;

	/**
	 * DAO injected by Spring that manages PsyMapaEstrategico entities
	 *
	 */
	@Autowired
	private IPsyMapaEstrategicoDAO psyMapaEstrategicoDAO;

	/**
	 * DAO injected by Spring that manages PsyMatrizEncuesta entities
	 *
	 */
	@Autowired
	private IPsyMatrizEncuestaDAO psyMatrizEncuestaDAO;

	/**
	 * DAO injected by Spring that manages PsyMatrizErida entities
	 *
	 */
	@Autowired
	private IPsyMatrizEridaDAO psyMatrizEridaDAO;

	/**
	 * DAO injected by Spring that manages PsyObjetivoPlan entities
	 *
	 */
	@Autowired
	private IPsyObjetivoPlanDAO psyObjetivoPlanDAO;

	/**
	 * Logic injected by Spring that manages PsyEmpresa entities
	 *
	 */
	@Autowired
    private IPsyPlanEstrategicoAmbientalDAO psyPlanEstrategicoAmbientalDAO;

    /**
    * Logic injected by Spring that manages PsyEmpresa entities
    *
    */
	@Autowired
	IPsyEmpresaLogic logicPsyEmpresa1;
	
	@Autowired
	IPsyMatrizEncuestaLogic logicPsyMatrizEncuestaLogic2;

	@Transactional(readOnly = true)
	public List<PsyPlanEstrategico> getPsyPlanEstrategico() throws Exception {
		log.debug("finding all PsyPlanEstrategico instances");

		List<PsyPlanEstrategico> list = new ArrayList<PsyPlanEstrategico>();

		try {
			list = psyPlanEstrategicoDAO.findAll();
		} catch (Exception e) {
			log.error("finding all PsyPlanEstrategico failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL
					+ "PsyPlanEstrategico");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePsyPlanEstrategico(PsyPlanEstrategico entity, PsyEmpresa empresa)
			throws Exception {
		log.debug("Guardando un plan estrategico");

		try {
			if (entity.getPsyEmpresa() == null) {
				throw new ZMessManager().new ForeignException("Empresa");
			}

			if ((entity.getDescripcion() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getDescripcion(), 2000) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Descripcion");
			}

			if (entity.getEstadoPlan() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Estado del Plan");
			}

			if ((entity.getEstadoPlan() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoPlan(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado del Plan");
			}

			if (entity.getEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Estado Registro");
			}

			if ((entity.getEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado Registro");
			}

			if (entity.getFechaInicio() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Fecha de Inicio");
			}
			
			if (entity.getFechaFin() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Fecha Final");
			}

			if (entity.getNombre() == null) {
				throw new ZMessManager().new EmptyFieldException("Nombre");
			}

			if ((entity.getNombre() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getNombre(), 255) == false)) {
				throw new ZMessManager().new NotValidFormatException("Nombre");
			}

			if (entity.getPsyEmpresa().getEmprCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("Empresa");
			}

			if (entity.getFechaFin().before(entity.getFechaInicio())) {
				throw new ZMessManager(
						"La fecha final debe ser mayor la fecha inicial");
			}

			if (entity.getFechaInicio().after(entity.getFechaFin())) {
				throw new ZMessManager(
						"La fecha inicial no puede estar despues de la fecha final");
			}
			
			Object[] variablesConsultaPlan = {"nombre",true,entity.getNombre(),"=",
					"estadoRegistro",true,"A","=",
					"psyEmpresa.emprCodigo",false,empresa.getEmprCodigo(),"="};
			
			List<PsyPlanEstrategico> validaNombresRepetidos = findByCriteria(variablesConsultaPlan, null, null);
			
			if(validaNombresRepetidos.size()>0){
				throw new ZMessManager(
						"Ya existe un plan estrategico con ese nombre");
			}
			
			
			Object[] variables = {"psyEmpresa.emprCodigo",false,empresa.getEmprCodigo(),"=","estadoRegistro",true,"A","="};
			List<PsyPlanEstrategico> verificacionPlanes = findByCriteria(variables, null, null);
			for (PsyPlanEstrategico planTmp : verificacionPlanes) {
				if(planTmp.getEstadoPlan().trim().equals("A")){
					throw new ZMessManager(
							"Ya existe un plan estrategico abierto, cierrelo para poder crear uno nuevo.");
				}
				if(planTmp.getEstadoPlan().trim().equals("I")){
					throw new ZMessManager(
							"Ya existe un plan estrategico iniciado, cierrelo para poder crear uno nuevo.");
				}
			}
			
			

			psyPlanEstrategicoDAO.save(entity);
			
			
			
			//
			Object[] variablesConsultaPlanEstrategico = {"nombre",true,entity.getNombre(),"=",
					"estadoRegistro",true,"A","=",
					"psyEmpresa.emprCodigo",false,empresa.getEmprCodigo(),"="};
			entity = findByCriteria(variablesConsultaPlanEstrategico, null, null).get(0);
			Long pestCodigo = new Long(entity.getPestCodigo());
			
			Object[] variablesAsociarEncuestas = {"psyPlanEstrategico.pestCodigo",false,pestCodigo,"=","estadoRegistro",true,"A","="};
			List<PsyMatrizEncuesta> validar = logicPsyMatrizEncuestaLogic2.findByCriteria(variablesAsociarEncuestas, null, null);
			
			if(validar==null || validar.size()==0){
			PsyPlanEstrategico psyPlanEstrategico = new PsyPlanEstrategico();
			psyPlanEstrategico = getPsyPlanEstrategico(pestCodigo);
			
			for (int i = 1; i <= 3; i++) {
				PsyMatrizEncuesta matrizEncuesta = new PsyMatrizEncuesta();
				matrizEncuesta.setPsyPlanEstrategico(psyPlanEstrategico);
				matrizEncuesta.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
				matrizEncuesta.setCodigoEncuesta(String.valueOf(i));
				logicPsyMatrizEncuestaLogic2.savePsyMatrizEncuesta(matrizEncuesta);
			}
			
			}else{
				throw new ZMessManager("Las encuestas para este plan ya se han asociado");
			}
			
			//

			log.debug("Plan estratégico creado exitosamente");
		} catch (Exception e) {
			log.error("Error Creando el plan estratégico", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePsyPlanEstrategico(PsyPlanEstrategico entity)
			throws Exception {
		log.debug("deleting PsyPlanEstrategico instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion(
					"PsyPlanEstrategico");
		}

		if (entity.getPestCodigo() == null) {
			throw new ZMessManager().new EmptyFieldException("pestCodigo");
		}

		List<PsyMapaEstrategico> psyMapaEstrategicos = null;
		List<PsyMatrizEncuesta> psyMatrizEncuestas = null;
		List<PsyMatrizErida> psyMatrizEridas = null;
		List<PsyObjetivoPlan> psyObjetivoPlans = null;
		List<PsyPlanEstrategicoAmbiental> psyPlanEstrategicoAmbientals = null;

		try {
			psyMapaEstrategicos = psyMapaEstrategicoDAO.findByProperty(
					"psyPlanEstrategico.pestCodigo", entity.getPestCodigo());

			if (Utilities.validationsList(psyMapaEstrategicos) == true) {
				throw new ZMessManager().new DeletingException(
						"psyMapaEstrategicos");
			}

			psyMatrizEncuestas = psyMatrizEncuestaDAO.findByProperty(
					"psyPlanEstrategico.pestCodigo", entity.getPestCodigo());

			if (Utilities.validationsList(psyMatrizEncuestas) == true) {
				throw new ZMessManager().new DeletingException(
						"psyMatrizEncuestas");
			}

			psyMatrizEridas = psyMatrizEridaDAO.findByProperty(
					"psyPlanEstrategico.pestCodigo", entity.getPestCodigo());

			if (Utilities.validationsList(psyMatrizEridas) == true) {
				throw new ZMessManager().new DeletingException(
						"psyMatrizEridas");
			}

			psyObjetivoPlans = psyObjetivoPlanDAO.findByProperty(
					"psyPlanEstrategico.pestCodigo", entity.getPestCodigo());

			if (Utilities.validationsList(psyObjetivoPlans) == true) {
				throw new ZMessManager().new DeletingException(
						"psyObjetivoPlans");
			}
			
			psyPlanEstrategicoAmbientals = psyPlanEstrategicoAmbientalDAO.findByProperty("psyPlanEstrategico.pestCodigo",
                    entity.getPestCodigo());

            if (Utilities.validationsList(psyPlanEstrategicoAmbientals) == true) {
                throw new ZMessManager().new DeletingException(
                    "psyPlanEstrategicoAmbientals");
            }

			psyPlanEstrategicoDAO.delete(entity);

			log.debug("delete PsyPlanEstrategico successful");
		} catch (Exception e) {
			log.error("delete PsyPlanEstrategico failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePsyPlanEstrategico(PsyPlanEstrategico entity)
			throws Exception {
		log.debug("updating PsyPlanEstrategico instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion(
						"PsyPlanEstrategico");
			}

			if (entity.getPsyEmpresa() == null) {
				throw new ZMessManager().new ForeignException("psyEmpresa");
			}

			if ((entity.getDescripcion() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getDescripcion(), 2000) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"descripcion");
			}

			if (entity.getEstadoPlan() == null) {
				throw new ZMessManager().new EmptyFieldException("estadoPlan");
			}

			if ((entity.getEstadoPlan() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoPlan(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"estadoPlan");
			}

			if (entity.getEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"estadoRegistro");
			}

			if ((entity.getEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"estadoRegistro");
			}

			if (entity.getFechaInicio() == null) {
				throw new ZMessManager().new EmptyFieldException("fechaInicio");
			}

			if (entity.getNombre() == null) {
				throw new ZMessManager().new EmptyFieldException("nombre");
			}

			if ((entity.getNombre() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getNombre(), 255) == false)) {
				throw new ZMessManager().new NotValidFormatException("nombre");
			}

			if (entity.getPestCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("pestCodigo");
			}

			if (entity.getPsyEmpresa().getEmprCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"emprCodigo_PsyEmpresa");
			}

			if (entity.getFechaFin().before(entity.getFechaInicio())) {
				throw new ZMessManager(
						"La fecha final debe ser mayor la fecha inicial");
			}

			if (entity.getFechaInicio().after(entity.getFechaFin())) {
				throw new ZMessManager(
						"La fecha inicial no puede estar despues de la fecha final");
			}

			psyPlanEstrategicoDAO.update(entity);

			log.debug("update PsyPlanEstrategico successful");
		} catch (Exception e) {
			log.error("update PsyPlanEstrategico failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<PsyPlanEstrategicoDTO> getDataPsyPlanEstrategico(PsyEmpresa empresa)
			throws Exception {
		try {
			Object[] variables = {"psyEmpresa.emprCodigo",false,empresa.getEmprCodigo(),"=","estadoRegistro",true,"A","="};
			List<PsyPlanEstrategico> psyPlanEstrategico = findByCriteria(variables, null, null);

			List<PsyPlanEstrategicoDTO> psyPlanEstrategicoDTO = new ArrayList<PsyPlanEstrategicoDTO>();

			for (PsyPlanEstrategico psyPlanEstrategicoTmp : psyPlanEstrategico) {
				PsyPlanEstrategicoDTO psyPlanEstrategicoDTO2 = new PsyPlanEstrategicoDTO();

				psyPlanEstrategicoDTO2.setPestCodigo(psyPlanEstrategicoTmp
						.getPestCodigo());
				psyPlanEstrategicoDTO2.setDescripcion((psyPlanEstrategicoTmp
						.getDescripcion() != null) ? psyPlanEstrategicoTmp
						.getDescripcion() : null);
				psyPlanEstrategicoDTO2
						.setEstadoPlan((psyPlanEstrategicoTmp.getEstadoPlan() != null) ? ((psyPlanEstrategicoTmp
								.getEstadoPlan()).equals("A")) ? "Abierto"
								: ((psyPlanEstrategicoTmp.getEstadoPlan())
										.equals("I")) ? "Iniciado" : "Cerrado"
								: null);
				psyPlanEstrategicoDTO2.setEstadoRegistro((psyPlanEstrategicoTmp
						.getEstadoRegistro() != null) ? psyPlanEstrategicoTmp
						.getEstadoRegistro() : null);
				psyPlanEstrategicoDTO2.setFechaFin(psyPlanEstrategicoTmp
						.getFechaFin());
				psyPlanEstrategicoDTO2.setFechaInicio(psyPlanEstrategicoTmp
						.getFechaInicio());
				psyPlanEstrategicoDTO2.setNombre((psyPlanEstrategicoTmp
						.getNombre() != null) ? psyPlanEstrategicoTmp
						.getNombre() : null);
				psyPlanEstrategicoDTO2
						.setEmprCodigo_PsyEmpresa((psyPlanEstrategicoTmp
								.getPsyEmpresa().getEmprCodigo() != null) ? psyPlanEstrategicoTmp
								.getPsyEmpresa().getEmprCodigo() : null);
				psyPlanEstrategicoDTO.add(psyPlanEstrategicoDTO2);
			}

			return psyPlanEstrategicoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public PsyPlanEstrategico getPsyPlanEstrategico(Long pestCodigo)
			throws Exception {
		log.debug("getting PsyPlanEstrategico instance");

		PsyPlanEstrategico entity = null;

		try {
			entity = psyPlanEstrategicoDAO.findById(pestCodigo);
		} catch (Exception e) {
			log.error("get PsyPlanEstrategico failed", e);
			throw new ZMessManager().new FindingException("PsyPlanEstrategico");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<PsyPlanEstrategico> findPagePsyPlanEstrategico(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<PsyPlanEstrategico> entity = null;

		try {
			entity = psyPlanEstrategicoDAO.findPage(sortColumnName,
					sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyPlanEstrategico Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberPsyPlanEstrategico() throws Exception {
		Long entity = null;

		try {
			entity = psyPlanEstrategicoDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyPlanEstrategico Count");
		} finally {
		}

		return entity;
	}

	/**
	 *
	 * @param varibles
	 *            este arreglo debera tener:
	 *
	 *            [0] = String variable = (String) varibles[i]; representa como
	 *            se llama la variable en el pojo
	 *
	 *            [1] = Boolean booVariable = (Boolean) varibles[i + 1];
	 *            representa si el valor necesita o no ''(comillas simples)usado
	 *            para campos de tipo string
	 *
	 *            [2] = Object value = varibles[i + 2]; representa el valor que
	 *            se va a buscar en la BD
	 *
	 *            [3] = String comparator = (String) varibles[i + 3]; representa
	 *            que tipo de busqueda voy a hacer.., ejemplo: where
	 *            nombre=william o where nombre<>william, en este campo iria el
	 *            tipo de comparador que quiero si es = o <>
	 *
	 *            Se itera de 4 en 4..., entonces 4 registros del arreglo
	 *            representan 1 busqueda en un campo, si se ponen mas pues el
	 *            continuara buscando en lo que se le ingresen en los otros 4
	 *
	 *
	 * @param variablesBetween
	 *
	 *            la diferencia son estas dos posiciones
	 *
	 *            [0] = String variable = (String) varibles[j]; la variable ne
	 *            la BD que va a ser buscada en un rango
	 *
	 *            [1] = Object value = varibles[j + 1]; valor 1 para buscar en
	 *            un rango
	 *
	 *            [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en
	 *            un rango ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria
	 *            value2
	 *
	 *            [3] = String comparator1 = (String) varibles[j + 3];
	 *            comparador 1 ejemplo: a comparator1 1 and a < 5
	 *
	 *            [4] = String comparator2 = (String) varibles[j + 4];
	 *            comparador 2 ejemplo: a comparador1>1 and a comparador2<5 (el
	 *            original: a > 1 and a < 5) *
	 * @param variablesBetweenDates
	 *            (en este caso solo para mysql) [0] = String variable =
	 *            (String) varibles[k]; el nombre de la variable que hace
	 *            referencia a una fecha
	 *
	 *            [1] = Object object1 = varibles[k + 2]; fecha 1 a
	 *            comparar(deben ser dates)
	 *
	 *            [2] = Object object2 = varibles[k + 3]; fecha 2 a
	 *            comparar(deben ser dates)
	 *
	 *            esto hace un between entre las dos fechas.
	 *
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<PsyPlanEstrategico> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<PsyPlanEstrategico> list = new ArrayList<PsyPlanEstrategico>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null)
						&& (variables[i + 2] != null)
						&& (variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0) ? ("(model."
								+ variable + " " + comparator + " \'" + value + "\' )")
								: (tempWhere + " AND (model." + variable + " "
										+ comparator + " \'" + value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0) ? ("(model."
								+ variable + " " + comparator + " " + value + " )")
								: (tempWhere + " AND (model." + variable + " "
										+ comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null)
						&& (variablesBetween[j + 1] != null)
						&& (variablesBetween[j + 2] != null)
						&& (variablesBetween[j + 3] != null)
						&& (variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0) ? ("(" + value + " "
							+ comparator1 + " " + variable + " and " + variable
							+ " " + comparator2 + " " + value2 + " )")
							: (tempWhere + " AND (" + value + " " + comparator1
									+ " " + variable + " and " + variable + " "
									+ comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null)
						&& (variablesBetweenDates[k + 1] != null)
						&& (variablesBetweenDates[k + 2] != null)) {
					String variable = (String) variablesBetweenDates[k];
					Object object1 = variablesBetweenDates[k + 1];
					Object object2 = variablesBetweenDates[k + 2];
					String value = null;
					String value2 = null;

					try {
						Date date1 = (Date) object1;
						Date date2 = (Date) object2;
						value = Utilities
								.formatDateWithoutTimeInAStringForBetweenWhere(date1);
						value2 = Utilities
								.formatDateWithoutTimeInAStringForBetweenWhere(date2);
					} catch (Exception e) {
						list = null;
						throw e;
					}

					tempWhere = (tempWhere.length() == 0) ? ("(model."
							+ variable + " between \'" + value + "\' and \'"
							+ value2 + "\')") : (tempWhere + " AND (model."
							+ variable + " between \'" + value + "\' and \'"
							+ value2 + "\')");
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
			list = psyPlanEstrategicoDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PsyPlanEstrategico> consultarPlanEstrategicoEmpresa(
			PsyEmpresa empresa, String estado) throws Exception {

		List<PsyPlanEstrategico> lstPlanEstrategicos = null;
		PsyPlanEstrategico planEstrategico = null;

		if (estado != null) {

			Object[] variablesPlanEstrategico = { "psyEmpresa.emprCodigo",
					false, empresa.getEmprCodigo(), "=", "estadoPlan", true,
					estado, "=", "estadoRegistro", true,
					"A" ,"=" };
			//Se consulta todos lo planes estrategicos en el estado pasado por parametro
			lstPlanEstrategicos = findByCriteria(variablesPlanEstrategico,
					null, null);			

		} else {
			Object[] variablesPlanEstrategico = { "psyEmpresa.emprCodigo",
					false, empresa.getEmprCodigo(), "=",  "estadoRegistro", true,
					"A" ,"=" };
			lstPlanEstrategicos = findByCriteria(variablesPlanEstrategico,
					null, null);
			
		}
		

		return lstPlanEstrategicos;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PsyMatrizEncuesta> consultaAsociacionDeCuestionarios(PsyEmpresa empresa) throws Exception {
		if(empresa==null){
			throw new ZMessManager("Para hacer la consulta de encuestas la empresa no puede estar nula");
		}
		return psyPlanEstrategicoDAO.consultaAsociacionDeCuestionarios(empresa);
	}
}
