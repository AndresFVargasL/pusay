package com.vortexbird.pusay.cuestionarios.services.control.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.pusay.cuestionarios.api.Utilities;
import com.vortexbird.pusay.cuestionarios.api.dto.CueResponsableDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;
import com.vortexbird.pusay.cuestionarios.dao.ICueCuestionarioDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICueResponsableDAO;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CueResponsable;
import com.vortexbird.pusay.cuestionarios.services.control.ICueResponsableLogic;

@Scope("singleton")
@Service("CueResponsableLogic")
public class CueResponsableLogic implements ICueResponsableLogic {
	/**
	 * DAO injected by Spring that manages CueResponsable entities support Andr�s Mauricio C�rdenas
	 * mauriciocardenasp@gmail.com
	 */
	@Autowired
	private ICueResponsableDAO cueResponsableDAO;

	/**
	 * DAO injected by Spring that manages CueCuestionario entities
	 * 
	 */
	@Autowired
	private ICueCuestionarioDAO cueCuestionarioDAO;

	private String lbl_identificacion = Propiedades.getInstance().getMensaje(
			"lbl_responsable_identificacion");
	private String lbl_razonSocial = Propiedades.getInstance().getMensaje(
			"lbl_responsable_razonSocial");
	private String lbl_nombre = Propiedades.getInstance().getMensaje(
			"lbl_responsable_razonSocial");
	private String lbl_apellido = Propiedades.getInstance().getMensaje(
			"lbl_responsable_razonSocial");
	private String lbl_email = Propiedades.getInstance().getMensaje(
			"lbl_responsable_razonSocial");
	private String lbl_emailSoporte = Propiedades.getInstance().getMensaje(
			"lbl_responsable_razonSocial");
	private String lbl_paginaSoporte = Propiedades.getInstance().getMensaje(
			"lbl_responsable_razonSocial");
	private String lbl_telefono1 = Propiedades.getInstance().getMensaje(
			"lbl_responsable_razonSocial");
	private String lbl_telefono2 = Propiedades.getInstance().getMensaje(
			"lbl_responsable_razonSocial");
	
	@Transactional(readOnly = true)
	public List<CueResponsable> getCueResponsable() throws Exception {
		List<CueResponsable> list = new ArrayList<CueResponsable>();

		try {
			list = cueResponsableDAO.findAll();
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "CueResponsable");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueResponsable(CueResponsable responsable) throws Exception {
		CueResponsable entity = null;

		try {
			if ((responsable.getApellido() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getApellido(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_apellido);
			}

			if (responsable.getEmail() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_email);
			}

			if ((responsable.getEmail() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getEmail(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_email);
			}

			if ((responsable.getEmailSoporte() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getEmailSoporte(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_emailSoporte);
			}

			if (responsable.getIdentificacion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_identificacion);
			}

			if ((responsable.getIdentificacion() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + responsable.getIdentificacion(), 17, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_identificacion);
			}

			if ((responsable.getNombre() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getNombre(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nombre);
			}

			if ((responsable.getPaginaSoporte() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getPaginaSoporte(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_paginaSoporte);
			}

			if ((responsable.getRazonSocial() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getRazonSocial(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_razonSocial);
			}

			if (responsable.getTelefono1() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_telefono1);
			}

			if ((responsable.getTelefono1() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getTelefono1(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_telefono1);
			}

			if ((responsable.getTelefono2() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getTelefono2(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_telefono2);
			}

			entity = getCueResponsable(responsable.getIdentificacion());

			if (entity != null) {
				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			}

			entity = new CueResponsable();
			entity.setApellido(responsable.getApellido());
			entity.setEmail(responsable.getEmail());
			entity.setEmailSoporte(responsable.getEmailSoporte());
			entity.setIdentificacion(responsable.getIdentificacion());
			entity.setNombre(responsable.getNombre());
			entity.setPaginaSoporte(responsable.getPaginaSoporte());
			entity.setRazonSocial(responsable.getRazonSocial());
			entity.setTelefono1(responsable.getTelefono1());
			entity.setTelefono2(responsable.getTelefono2());
			this.cueResponsableDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCueResponsable(Long identificacion) throws Exception {
		CueResponsable entity = null;

		if (identificacion == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_identificacion);
		}

		List<CueCuestionario> cueCuestionarios = null;
		entity = getCueResponsable(identificacion);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("CueResponsable");
		}

		try {
			cueCuestionarios = cueCuestionarioDAO.findByProperty("cueResponsable.identificacion", identificacion);

			if (Utilities.validationsList(cueCuestionarios) == true) {
				throw new ZMessManager().new DeletingException("cueCuestionarios");
			}

			this.cueResponsableDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueResponsable(CueResponsable responsable) throws Exception {
		CueResponsable entity = null;

		try {
			if ((responsable.getApellido() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getApellido(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_apellido);
			}

			if (responsable.getEmail() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_email);
			}

			if ((responsable.getEmail() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getEmail(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_email);
			}

			if ((responsable.getEmailSoporte() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getEmailSoporte(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_emailSoporte);
			}

			if (responsable.getIdentificacion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_identificacion);
			}

			if ((responsable.getIdentificacion() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + responsable.getIdentificacion(), 17, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_identificacion);
			}

			if ((responsable.getNombre() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getNombre(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nombre);
			}

			if ((responsable.getPaginaSoporte() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getPaginaSoporte(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_paginaSoporte);
			}

			if ((responsable.getRazonSocial() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getRazonSocial(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_razonSocial);
			}

			if (responsable.getTelefono1() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_telefono1);
			}

			if ((responsable.getTelefono1() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getTelefono1(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_telefono1);
			}

			if ((responsable.getTelefono2() != null)
					&& (Utilities.checkWordAndCheckWithlength(responsable.getTelefono2(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_telefono2);
			}

			entity = getCueResponsable(responsable.getIdentificacion());

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setApellido(responsable.getApellido());
			entity.setEmail(responsable.getEmail());
			entity.setEmailSoporte(responsable.getEmailSoporte());
			entity.setIdentificacion(responsable.getIdentificacion());
			entity.setNombre(responsable.getNombre());
			entity.setPaginaSoporte(responsable.getPaginaSoporte());
			entity.setRazonSocial(responsable.getRazonSocial());
			entity.setTelefono1(responsable.getTelefono1());
			entity.setTelefono2(responsable.getTelefono2());
			this.cueResponsableDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueResponsableDTO> getDataCueResponsable() throws Exception {
		try {
			List<CueResponsable> cueResponsable = cueResponsableDAO.findAll();

			List<CueResponsableDTO> cueResponsableDTO = new ArrayList<CueResponsableDTO>();

			for (CueResponsable cueResponsableTmp : cueResponsable) {
				CueResponsableDTO cueResponsableDTO2 = new CueResponsableDTO();

				cueResponsableDTO2.setIdentificacion(cueResponsableTmp.getIdentificacion());
				cueResponsableDTO2.setApellido((cueResponsableTmp.getApellido() != null) ? cueResponsableTmp
						.getApellido() : null);
				cueResponsableDTO2.setEmail((cueResponsableTmp.getEmail() != null) ? cueResponsableTmp.getEmail()
						: null);
				cueResponsableDTO2.setEmailSoporte((cueResponsableTmp.getEmailSoporte() != null) ? cueResponsableTmp
						.getEmailSoporte() : null);
				cueResponsableDTO2.setNombre((cueResponsableTmp.getNombre() != null) ? cueResponsableTmp.getNombre()
						: null);
				cueResponsableDTO2.setPaginaSoporte((cueResponsableTmp.getPaginaSoporte() != null) ? cueResponsableTmp
						.getPaginaSoporte() : null);
				cueResponsableDTO2.setRazonSocial((cueResponsableTmp.getRazonSocial() != null) ? cueResponsableTmp
						.getRazonSocial() : null);
				cueResponsableDTO2.setTelefono1((cueResponsableTmp.getTelefono1() != null) ? cueResponsableTmp
						.getTelefono1() : null);
				cueResponsableDTO2.setTelefono2((cueResponsableTmp.getTelefono2() != null) ? cueResponsableTmp
						.getTelefono2() : null);
				cueResponsableDTO.add(cueResponsableDTO2);
			}

			return cueResponsableDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public CueResponsable getCueResponsable(Long identificacion) throws Exception {
		CueResponsable entity = null;

		try {
			entity = cueResponsableDAO.findById(identificacion);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueResponsable");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<CueResponsable> findPageCueResponsable(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<CueResponsable> entity = null;

		try {
			entity = cueResponsableDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueResponsable Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCueResponsable() throws Exception {
		Long entity = null;

		try {
			entity = cueResponsableDAO.findTotalNumber();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueResponsable Count");
		} finally {
		}

		return entity;
	}

	/**
	 * 
	 * @param varibles
	 *            este arreglo debera tener:
	 * 
	 *            [0] = String variable = (String) varibles[i]; representa como se llama la variable en el pojo
	 * 
	 *            [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el valor necesita o no
	 *            ''(comillas simples)usado para campos de tipo string
	 * 
	 *            [2] = Object value = varibles[i + 2]; representa el valor que se va a buscar en la BD
	 * 
	 *            [3] = String comparator = (String) varibles[i + 3]; representa que tipo de busqueda voy a hacer..,
	 *            ejemplo: where nombre=william o where nombre<>william, en este campo iria el tipo de comparador que
	 *            quiero si es = o <>
	 * 
	 *            Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1 busqueda en un campo, si se
	 *            ponen mas pues el continuara buscando en lo que se le ingresen en los otros 4
	 * 
	 * 
	 * @param variablesBetween
	 * 
	 *            la diferencia son estas dos posiciones
	 * 
	 *            [0] = String variable = (String) varibles[j]; la variable ne la BD que va a ser buscada en un rango
	 * 
	 *            [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
	 * 
	 *            [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango ejempolo: a > 1 and a < 5 --> 1
	 *            seria value y 5 seria value2
	 * 
	 *            [3] = String comparator1 = (String) varibles[j + 3]; comparador 1 ejemplo: a comparator1 1 and a < 5
	 * 
	 *            [4] = String comparator2 = (String) varibles[j + 4]; comparador 2 ejemplo: a comparador1>1 and a
	 *            comparador2<5 (el original: a > 1 and a < 5) *
	 * @param variablesBetweenDates
	 *            (en este caso solo para mysql) [0] = String variable = (String) varibles[k]; el nombre de la variable
	 *            que hace referencia a una fecha
	 * 
	 *            [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser dates)
	 * 
	 *            [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser dates)
	 * 
	 *            esto hace un between entre las dos fechas.
	 * 
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<CueResponsable> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<CueResponsable> list = new ArrayList<CueResponsable>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null) && (variables[i + 2] != null)
						&& (variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0) ? ("(model." + variable + " " + comparator + " \'"
								+ value + "\' )") : (tempWhere + " AND (model." + variable + " " + comparator + " \'"
								+ value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0) ? ("(model." + variable + " " + comparator + " " + value + " )")
								: (tempWhere + " AND (model." + variable + " " + comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null) && (variablesBetween[j + 1] != null)
						&& (variablesBetween[j + 2] != null) && (variablesBetween[j + 3] != null)
						&& (variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0) ? ("(" + value + " " + comparator1 + " " + variable + " and "
							+ variable + " " + comparator2 + " " + value2 + " )")
							: (tempWhere + " AND (" + value + " " + comparator1 + " " + variable + " and " + variable
									+ " " + comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null) && (variablesBetweenDates[k + 1] != null)
						&& (variablesBetweenDates[k + 2] != null)) {
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

					tempWhere = (tempWhere.length() == 0) ? ("(model." + variable + " between \'" + value + "\' and \'"
							+ value2 + "\')") : (tempWhere + " AND (model." + variable + " between \'" + value
							+ "\' and \'" + value2 + "\')");
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
			list = cueResponsableDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}
}
