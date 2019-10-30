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
import com.vortexbird.pusay.cuestionarios.api.dto.CueContactoDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;
import com.vortexbird.pusay.cuestionarios.dao.ICueContactoDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICueListaContactoDAO;
import com.vortexbird.pusay.cuestionarios.model.CueContacto;
import com.vortexbird.pusay.cuestionarios.model.CueListaContacto;
import com.vortexbird.pusay.cuestionarios.services.control.ICueContactoLogic;

@Scope("singleton")
@Service("CueContactoLogic")
public class CueContactoLogic implements ICueContactoLogic {
	/**
	 * DAO injected by Spring that manages CueContacto entities support Andr�s Mauricio C�rdenas
	 * mauriciocardenasp@gmail.com
	 */
	@Autowired
	private ICueContactoDAO cueContactoDAO;

	/**
	 * DAO injected by Spring that manages CueListaContacto entities
	 * 
	 */
	@Autowired
	private ICueListaContactoDAO cueListaContactoDAO;

	private String lbl_consecutivo = Propiedades.getInstance().getMensaje(
			"lbl_contacto_consecutivo");
	private String lbl_identificacion = Propiedades.getInstance().getMensaje(
			"lbl_contacto_identificacion");
	private String lbl_nombre = Propiedades.getInstance().getMensaje(
			"lbl_contacto_nombre");
	private String lbl_apellido = Propiedades.getInstance().getMensaje(
			"lbl_contacto_apellido");
	private String lbl_email = Propiedades.getInstance().getMensaje(
			"lbl_contacto_email");
	private String lbl_celular = Propiedades.getInstance().getMensaje(
			"lbl_contacto_celular");
	private String lbl_empresa = Propiedades.getInstance().getMensaje(
			"lbl_contacto_empresa");
	private String lbl_estado = Propiedades.getInstance().getMensaje(
			"lbl_contacto_estado");
	private String lbl_lista = Propiedades.getInstance().getMensaje(
			"lbl_contacto_lista");
	
	
	@Transactional(readOnly = true)
	public List<CueContacto> getCueContacto() throws Exception {
		List<CueContacto> list = new ArrayList<CueContacto>();

		try {
			list = cueContactoDAO.findAll();
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + lbl_lista);
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueContacto(String apellido, String celular, String email, String empresa, Long estado,
			Long identificacion, String nombre) throws Exception {
		CueContacto entity = null;

		try {
			if (apellido == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_apellido);
			}

			if ((apellido != null) && (Utilities.checkWordAndCheckWithlength(apellido, 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_apellido);
			}

			if ((celular != null) && (Utilities.checkWordAndCheckWithlength(celular, 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_email);
			}

			if ((email != null) && (Utilities.checkWordAndCheckWithlength(email, 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_celular);
			}

			if ((empresa != null) && (Utilities.checkWordAndCheckWithlength(empresa, 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_empresa);
			}

			if (estado == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((estado != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + estado, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (identificacion == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_identificacion);
			}

			if ((identificacion != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + identificacion, 17, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_identificacion);
			}

			if (nombre == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_nombre);
			}

			if ((nombre != null) && (Utilities.checkWordAndCheckWithlength(nombre, 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nombre);
			}

			entity = getCueContacto(identificacion);

			if (entity != null) {
				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			}

			entity = new CueContacto();
			entity.setApellido(apellido);
			entity.setCelular(celular);
			entity.setEmail(email);
			entity.setEmpresa(empresa);
			entity.setEstado(estado);
			entity.setIdentificacion(identificacion);
			entity.setNombre(nombre);
			cueContactoDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCueContacto(Long identificacion) throws Exception {
		CueContacto entity = null;

		if (identificacion == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_identificacion);
		}

		List<CueListaContacto> cueListaContactos = null;
		entity = getCueContacto(identificacion);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_lista);
		}

		try {
			cueListaContactos = cueListaContactoDAO.findByProperty("cueContacto.identificacion", identificacion);

			if (Utilities.validationsList(cueListaContactos) == true) {
				throw new ZMessManager().new DeletingException("cueListaContactos");
			}

			cueContactoDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueContacto(String apellido, String celular, String email, String empresa, Long estado,
			Long identificacion, String nombre) throws Exception {
		CueContacto entity = null;

		try {
			if (apellido == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_apellido);
			}

			if ((apellido != null) && (Utilities.checkWordAndCheckWithlength(apellido, 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_apellido);
			}

			if ((celular != null) && (Utilities.checkWordAndCheckWithlength(celular, 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_email);
			}

			if ((email != null) && (Utilities.checkWordAndCheckWithlength(email, 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_celular);
			}

			if ((empresa != null) && (Utilities.checkWordAndCheckWithlength(empresa, 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_empresa);
			}

			if (estado == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((estado != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + estado, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (identificacion == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_identificacion);
			}

			if ((identificacion != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + identificacion, 17, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_identificacion);
			}

			if (nombre == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_nombre);
			}

			if ((nombre != null) && (Utilities.checkWordAndCheckWithlength(nombre, 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nombre);
			}

			entity = getCueContacto(identificacion);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setApellido(apellido);
			entity.setCelular(celular);
			entity.setEmail(email);
			entity.setEmpresa(empresa);
			entity.setEstado(estado);
			entity.setIdentificacion(identificacion);
			entity.setNombre(nombre);
			cueContactoDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueContactoDTO> getDataCueContacto() throws Exception {
		try {
			List<CueContacto> cueContacto = cueContactoDAO.findAll();

			List<CueContactoDTO> cueContactoDTO = new ArrayList<CueContactoDTO>();

			for (CueContacto cueContactoTmp : cueContacto) {
				CueContactoDTO cueContactoDTO2 = new CueContactoDTO();

				cueContactoDTO2.setIdentificacion(cueContactoTmp.getIdentificacion());
				cueContactoDTO2.setApellido((cueContactoTmp.getApellido() != null) ? cueContactoTmp.getApellido()
						: null);
				cueContactoDTO2.setCelular((cueContactoTmp.getCelular() != null) ? cueContactoTmp.getCelular() : null);
				cueContactoDTO2.setEmail((cueContactoTmp.getEmail() != null) ? cueContactoTmp.getEmail() : null);
				cueContactoDTO2.setEmpresa((cueContactoTmp.getEmpresa() != null) ? cueContactoTmp.getEmpresa() : null);
				cueContactoDTO2.setEstado((cueContactoTmp.getEstado() != null) ? cueContactoTmp.getEstado() : null);
				cueContactoDTO2.setNombre((cueContactoTmp.getNombre() != null) ? cueContactoTmp.getNombre() : null);
				cueContactoDTO.add(cueContactoDTO2);
			}

			return cueContactoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public CueContacto getCueContacto(Long identificacion) throws Exception {
		CueContacto entity = null;

		try {
			entity = cueContactoDAO.findById(identificacion);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(lbl_lista);
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<CueContacto> findPageCueContacto(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<CueContacto> entity = null;

		try {
			entity = cueContactoDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueContacto Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCueContacto() throws Exception {
		Long entity = null;

		try {
			entity = cueContactoDAO.findTotalNumber();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueContacto Count");
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
	public List<CueContacto> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<CueContacto> list = new ArrayList<CueContacto>();
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
			list = cueContactoDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueContacto(CueContacto cueContacto) throws Exception {
		CueContacto entity = null;
		try {
			if (cueContacto.getApellido() == null || cueContacto.getApellido().length() == 0) {
				throw new ZMessManager().new EmptyFieldException(lbl_apellido);
			}

			if ((cueContacto.getApellido() != null) && (Utilities.checkWordAndCheckWithlength(cueContacto.getApellido(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_apellido);
			}

			if ((cueContacto.getCelular() != null  && cueContacto.getCelular().length() == 0) && (Utilities.checkWordAndCheckWithlength(cueContacto.getCelular(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_email);
			}

			if (cueContacto.getEmail() == null ) {
				throw new ZMessManager().new NotValidFormatException(lbl_email);
			}
			
			if ((cueContacto.getEmail() != null && cueContacto.getEmail().length() == 0) && (Utilities.checkWordAndCheckWithlength(cueContacto.getEmail() , 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_celular);
			}

			if ((cueContacto.getEmpresa()  != null  && cueContacto.getEmpresa().length() == 0) && (Utilities.checkWordAndCheckWithlength(cueContacto.getEmpresa(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_empresa);
			}

			if (cueContacto.getEstado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((cueContacto.getEstado() != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueContacto.getEstado(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (cueContacto.getIdentificacion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_identificacion);
			}

			if ((cueContacto.getIdentificacion() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueContacto.getIdentificacion(), 17, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_identificacion);
			}

			if (cueContacto.getNombre() == null || cueContacto.getNombre().length() == 0) {
				throw new ZMessManager().new EmptyFieldException(lbl_nombre);
			}

			if ((cueContacto.getNombre() != null) && (Utilities.checkWordAndCheckWithlength(cueContacto.getNombre(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nombre);
			}

			entity = getCueContacto(cueContacto.getIdentificacion());

			if (entity == null) {
				entity = new CueContacto();
				entity.setApellido(cueContacto.getApellido());
				entity.setCelular(cueContacto.getCelular());
				entity.setEmail(cueContacto.getEmail());
				entity.setEmpresa(cueContacto.getEmpresa());
				entity.setEstado(cueContacto.getEstado());
				entity.setIdentificacion(cueContacto.getIdentificacion());
				entity.setNombre(cueContacto.getNombre());
				cueContactoDAO.save(entity);
			}else{
				entity.setApellido(cueContacto.getApellido());
				entity.setCelular(cueContacto.getCelular());
				entity.setEmail(cueContacto.getEmail());
				entity.setEmpresa(cueContacto.getEmpresa());
				entity.setEstado(cueContacto.getEstado());
				entity.setIdentificacion(cueContacto.getIdentificacion());
				entity.setNombre(cueContacto.getNombre());
				cueContactoDAO.update(entity);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueContacto(CueContacto cueContacto) throws Exception {
		CueContacto entity = null;
		try {
			if (cueContacto.getApellido() == null || cueContacto.getApellido().length() == 0) {
				throw new ZMessManager().new EmptyFieldException(lbl_apellido);
			}

			if ((cueContacto.getApellido() != null) && (Utilities.checkWordAndCheckWithlength(cueContacto.getApellido(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_apellido);
			}

			if ((cueContacto.getCelular() != null  || cueContacto.getCelular().length() == 0) && (Utilities.checkWordAndCheckWithlength(cueContacto.getCelular(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_email);
			}

			if ((cueContacto.getEmail() != null || cueContacto.getEmail().length() == 0) && (Utilities.checkWordAndCheckWithlength(cueContacto.getEmail() , 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_celular);
			}

			if ((cueContacto.getEmpresa()  != null  || cueContacto.getEmpresa().length() == 0) && (Utilities.checkWordAndCheckWithlength(cueContacto.getEmpresa(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_empresa);
			}

			if (cueContacto.getEstado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((cueContacto.getEstado() != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueContacto.getEstado(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (cueContacto.getIdentificacion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_identificacion);
			}

			if ((cueContacto.getIdentificacion() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueContacto.getIdentificacion(), 17, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_identificacion);
			}

			if (cueContacto.getNombre() == null || cueContacto.getNombre().length() == 0) {
				throw new ZMessManager().new EmptyFieldException(lbl_nombre);
			}

			if ((cueContacto.getNombre() != null) && (Utilities.checkWordAndCheckWithlength(cueContacto.getNombre(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nombre);
			}

			entity = getCueContacto(cueContacto.getIdentificacion());

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setApellido(cueContacto.getApellido());
			entity.setCelular(cueContacto.getCelular());
			entity.setEmail(cueContacto.getEmail());
			entity.setEmpresa(cueContacto.getEmpresa());
			entity.setEstado(cueContacto.getEstado());
			entity.setIdentificacion(cueContacto.getIdentificacion());
			entity.setNombre(cueContacto.getNombre());
			cueContactoDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<CueContacto> getCueContactosActivos() {
		return cueContactoDAO.findByProperty("estado", 1L);
	}
}