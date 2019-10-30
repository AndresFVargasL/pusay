package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyDetalleMapaEstrategicoDTO;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategiaDTO;
import com.vortexbird.pusay.utilities.Utilities;

import org.hibernate.Session;
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
@Service("PsyPlanEstrategiaLogic")
public class PsyPlanEstrategiaLogic implements IPsyPlanEstrategiaLogic {
	private static final Logger log = LoggerFactory
			.getLogger(PsyPlanEstrategiaLogic.class);

	/**
	 * DAO injected by Spring that manages PsyPlanEstrategia entities
	 *
	 */
	@Autowired
	private IPsyPlanEstrategiaDAO psyPlanEstrategiaDAO;

	/**
	 * Logic injected by Spring that manages PsyDetalleMapaEstrategico entities
	 *
	 */
	@Autowired
	IPsyDetalleMapaEstrategicoLogic logicPsyDetalleMapaEstrategico1;

	/**
	 * Logic injected by Spring that manages PsyPlanAccion entities
	 *
	 */
	@Autowired
	IPsyPlanAccionLogic logicPsyPlanAccion2;

	@Transactional(readOnly = true)
	public List<PsyPlanEstrategia> getPsyPlanEstrategia() throws Exception {
		log.debug("finding all PsyPlanEstrategia instances");

		List<PsyPlanEstrategia> list = new ArrayList<PsyPlanEstrategia>();

		try {
			list = psyPlanEstrategiaDAO.findAll();
		} catch (Exception e) {
			log.error("finding all PsyPlanEstrategia failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL
					+ "PsyPlanEstrategia");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePsyPlanEstrategia(PsyPlanEstrategia entity,
			List<String> estrategiasTarget) throws Exception {
		
		
		log.debug("saving PsyPlanEstrategia instance");
		PsyPlanEstrategia planEstrategiaInstance = null;
		for (String string : estrategiasTarget) {
			planEstrategiaInstance = new PsyPlanEstrategia();
			planEstrategiaInstance
					.setEstadoRegistro(entity.getEstadoRegistro());
			planEstrategiaInstance.setPsyPrograma(entity.getPsyPrograma());
			try {
				if (estrategiasTarget == null) {
					throw new ZMessManager(
							"Debe seleccionar almenos una estrategia");
				}

				if (estrategiasTarget.size() == 0) {
					throw new ZMessManager(
							"Debe seleccionar almenos una estrategia");
				}

				if (planEstrategiaInstance.getPsyPrograma().getProgCodigo() == null) {
					throw new ZMessManager().new EmptyFieldException(
							"Plan de Accion");
				}

				if (planEstrategiaInstance.getPsyPrograma() == null) {
					throw new ZMessManager().new ForeignException(
							"Plan de Accion");
				}

				if (planEstrategiaInstance.getEstadoRegistro() == null) {
					throw new ZMessManager().new EmptyFieldException(
							"Estado Registro");
				}

				if ((planEstrategiaInstance.getEstadoRegistro() != null)
						&& (Utilities.checkWordAndCheckWithlength(
								planEstrategiaInstance.getEstadoRegistro(), 1) == false)) {
					throw new ZMessManager().new NotValidFormatException(
							"Estado registro");
				}

				PsyDetalleMapaEstrategico detalleMapaEstrategico = new PsyDetalleMapaEstrategico();
				List<PsyDetalleMapaEstrategico> listDetalle = psyPlanEstrategiaDAO
						.consultaDetalleMapaEstrategicoPorNombreEstrategia(string);
				for (PsyDetalleMapaEstrategico psyDetalleMapaEstrategico : listDetalle) {
					detalleMapaEstrategico = psyDetalleMapaEstrategico;
				}
				planEstrategiaInstance
						.setPsyDetalleMapaEstrategico(detalleMapaEstrategico);

				if (planEstrategiaInstance.getPsyDetalleMapaEstrategico()
						.getDmaeCodigo() == null) {
					throw new ZMessManager().new EmptyFieldException(
							"Mapa Estrategico");
				}

				if (planEstrategiaInstance.getPsyDetalleMapaEstrategico() == null) {
					throw new ZMessManager().new ForeignException(
							"psyDetalleMapaEstrategico");
				}

				psyPlanEstrategiaDAO.save(planEstrategiaInstance);

				log.debug("save PsyPlanEstrategia successful");
			} catch (Exception e) {
				log.error("save PsyPlanEstrategia failed", e);
				throw e;
			} finally {

			}

		}

	
	
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePsyPlanEstrategia(PsyPlanEstrategia entity)
			throws Exception {
		log.debug("deleting PsyPlanEstrategia instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion(
					"Plan Estrategia");
		}

		if (entity.getPlesCodigo() == null) {
			throw new ZMessManager().new EmptyFieldException("Codigo");
		}

		try {
			psyPlanEstrategiaDAO.delete(entity);

			log.debug("delete PsyPlanEstrategia successful");
		} catch (Exception e) {
			log.error("delete PsyPlanEstrategia failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePsyPlanEstrategia(PsyPlanEstrategia entity,
			List<String> estrategiasTarget) throws Exception {
		log.debug("updating PsyPlanEstrategia instance");
		PsyPlanEstrategia planEstrategiaInstance =null;
		for (String string : estrategiasTarget) {
			planEstrategiaInstance = new PsyPlanEstrategia();
			planEstrategiaInstance
					.setEstadoRegistro(entity.getEstadoRegistro());
			planEstrategiaInstance.setPsyPrograma(entity.getPsyPrograma());
			planEstrategiaInstance.setPlesCodigo(entity.getPlesCodigo());
			planEstrategiaInstance.setPsyDetalleMapaEstrategico(entity
					.getPsyDetalleMapaEstrategico());
			try {
				if (estrategiasTarget == null) {
					throw new ZMessManager(
							"Debe seleccionar almenos una estrategia");
				}

				if (estrategiasTarget.size() == 0) {
					throw new ZMessManager(
							"Debe seleccionar almenos una estrategia");
				}

				if (planEstrategiaInstance.getPsyPrograma().getProgCodigo() == null) {
					throw new ZMessManager().new EmptyFieldException(
							"Plan de Accion");
				}

				if (planEstrategiaInstance.getPsyPrograma() == null) {
					throw new ZMessManager().new ForeignException(
							"Plan de Accion");
				}

				if (planEstrategiaInstance.getEstadoRegistro() == null) {
					throw new ZMessManager().new EmptyFieldException(
							"Estado Registro");
				}

				if ((planEstrategiaInstance.getEstadoRegistro() != null)
						&& (Utilities.checkWordAndCheckWithlength(
								planEstrategiaInstance.getEstadoRegistro(), 1) == false)) {
					throw new ZMessManager().new NotValidFormatException(
							"Estado registro");
				}

				PsyDetalleMapaEstrategico detalleMapaEstrategico = new PsyDetalleMapaEstrategico();
				List<PsyDetalleMapaEstrategico> listDetalle = psyPlanEstrategiaDAO
						.consultaDetalleMapaEstrategicoPorNombreEstrategia(string);
				for (PsyDetalleMapaEstrategico psyDetalleMapaEstrategico : listDetalle) {
					detalleMapaEstrategico = psyDetalleMapaEstrategico;
				}
				planEstrategiaInstance
						.setPsyDetalleMapaEstrategico(detalleMapaEstrategico);

				if (planEstrategiaInstance.getPsyDetalleMapaEstrategico()
						.getDmaeCodigo() == null) {
					throw new ZMessManager().new EmptyFieldException(
							"Mapa Estrategico");
				}

				if (planEstrategiaInstance.getPsyDetalleMapaEstrategico() == null) {
					throw new ZMessManager().new ForeignException(
							"psyDetalleMapaEstrategico");
				}

				psyPlanEstrategiaDAO.update(planEstrategiaInstance);
				planEstrategiaInstance.hashCode();
				log.debug("update PsyPlanEstrategia successful");
			} catch (Exception e) {
				log.error("update PsyPlanEstrategia failed", e);
				throw e;
			} finally {
			}

		}

	}

	@Transactional(readOnly = true)
	public List<PsyPlanEstrategiaDTO> getDataPsyPlanEstrategia()
			throws Exception {
		try {
			List<PsyPlanEstrategia> psyPlanEstrategia = psyPlanEstrategiaDAO
					.findAll();

			List<PsyPlanEstrategiaDTO> psyPlanEstrategiaDTO = new ArrayList<PsyPlanEstrategiaDTO>();

			for (PsyPlanEstrategia psyPlanEstrategiaTmp : psyPlanEstrategia) {
				PsyPlanEstrategiaDTO psyPlanEstrategiaDTO2 = new PsyPlanEstrategiaDTO();

				psyPlanEstrategiaDTO2.setPlesCodigo(psyPlanEstrategiaTmp
						.getPlesCodigo());
				psyPlanEstrategiaDTO2.setEstadoRegistro((psyPlanEstrategiaTmp
						.getEstadoRegistro() != null) ? psyPlanEstrategiaTmp
						.getEstadoRegistro() : null);
				psyPlanEstrategiaDTO2
						.setDmaeCodigo_PsyDetalleMapaEstrategico((psyPlanEstrategiaTmp
								.getPsyDetalleMapaEstrategico().getDmaeCodigo() != null) ? psyPlanEstrategiaTmp
								.getPsyDetalleMapaEstrategico().getDmaeCodigo()
								: null);
				psyPlanEstrategiaDTO2
						.setProgCodigo_PsyPrograma((psyPlanEstrategiaTmp
								.getPsyPrograma().getProgCodigo() != null) ? psyPlanEstrategiaTmp
								.getPsyPrograma().getProgCodigo() : null);
				psyPlanEstrategiaDTO.add(psyPlanEstrategiaDTO2);
			}

			return psyPlanEstrategiaDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public PsyPlanEstrategia getPsyPlanEstrategia(Long plesCodigo)
			throws Exception {
		log.debug("getting PsyPlanEstrategia instance");

		PsyPlanEstrategia entity = null;

		try {
			entity = psyPlanEstrategiaDAO.findById(plesCodigo);
		} catch (Exception e) {
			log.error("get PsyPlanEstrategia failed", e);
			throw new ZMessManager().new FindingException("PsyPlanEstrategia");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<PsyPlanEstrategia> findPagePsyPlanEstrategia(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<PsyPlanEstrategia> entity = null;

		try {
			entity = psyPlanEstrategiaDAO.findPage(sortColumnName,
					sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyPlanEstrategia Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberPsyPlanEstrategia() throws Exception {
		Long entity = null;

		try {
			entity = psyPlanEstrategiaDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyPlanEstrategia Count");
		} finally {
		}

		return entity;
	}
	@Transactional(readOnly = true)
	public List<String> getEstrategias(PsyEmpresa empresa, Long placCodigo) throws Exception {
		//retorna el codigo del mapa estrategico del una de las ultimas estrategias seleccionadas del plan de accion
		List<Object> maesCodigo = psyPlanEstrategiaDAO.consultaDmaePorNombreEstrategia(empresa, placCodigo);
		
		String maesCodigoTemporalString=null;
		
		//se asigna ese codigo mediante un for
		List<String> estrategiasString = new ArrayList<String>();
		
		for (Object codigoTmp : maesCodigo) {

			maesCodigoTemporalString = codigoTmp.toString();
		}
		
		//con el anterior codigo en un string instanciamos un long con el codigo en string
		Long maesCodigoTemporal = new Long(maesCodigoTemporalString.trim());
		
		// se buscan las estrategias y se traen las que correspondan co el codigo del mapa estrategico anteriormente consultado
		List<Object> estrategias = psyPlanEstrategiaDAO.consultaDmaePorPorMaesCodigo(empresa, placCodigo, maesCodigoTemporal);
		
		for (Object estrategiasTmp : estrategias) {

			String est = estrategiasTmp.toString();

			estrategiasString.add(est);
		}
		

		return estrategiasString;
	}

	@Transactional(readOnly = true)
	public List<String> getEstrategiasSeleccionadas(PsyEmpresa empresa,
			Long placCodigo) throws Exception {
		List<Object> estrategias = psyPlanEstrategiaDAO
				.consultaEstrategiasSeleccionadas(empresa, placCodigo);

		List<String> estrategiasString = new ArrayList<String>();
		for (Object estrategiasTmp : estrategias) {

			String estrategiasTemp = estrategiasTmp.toString();

			estrategiasString.add(estrategiasTemp);
		}

		return estrategiasString;
	}
	
	@Transactional(readOnly = true)
	public List<String> getEstrategiasRestantes(PsyEmpresa empresa,
			Long placCodigo, List<String> estrategiasSeleccionadas)
			throws Exception {
		
		List<String> estrategias = getEstrategias(empresa, placCodigo);
		
		List<String> estrategiasRestantes = new ArrayList<String>();

		for(String cache : estrategias){
			
			String estrategiasTemp = cache.toString();

			estrategiasRestantes.add(estrategiasTemp);
			
		}

		for (String estrategiasTmp : estrategias) {
			
			String estTemp = estrategiasTmp.toString();

			for (String estrategiasTmp2 : estrategiasSeleccionadas) {

				String estTemp2 = estrategiasTmp2.toString();

				if ((estTemp.equals(estTemp2))) {
					
					estrategiasRestantes.remove(estTemp);
					
				}

			}

		}
		

		return estrategiasRestantes;
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
	public List<PsyPlanEstrategia> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<PsyPlanEstrategia> list = new ArrayList<PsyPlanEstrategia>();
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
			list = psyPlanEstrategiaDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}
	
	@Transactional(readOnly = true)
	public List<String> getEstrategiasBtnNew(PsyEmpresa empresa) throws Exception {
		List<String> estrategiasString = new ArrayList<String>();
		try {
		List<Object> estrategias = psyPlanEstrategiaDAO
				.consultaEstrategiasBtnNew(empresa);

		
		for (Object estrategiasTmp : estrategias) {

			String articulosDTO2 = new String();
			String articulosTemp = estrategiasTmp.toString();

			estrategiasString.add(articulosTemp);
		}
		
		} catch (Exception e) {
			// TODO: handle exception

		}

		return estrategiasString;	
		
	}
}
