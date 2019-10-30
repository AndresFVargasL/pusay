package com.vortexbird.pusay.cuestionarios.services.control.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.pusay.cuestionarios.api.Utilities;
import com.vortexbird.pusay.cuestionarios.api.dto.CueCuestionarioDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;
import com.vortexbird.pusay.cuestionarios.dao.ICueCategoriaDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICueCuestionarioDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICueListaCuestionarioDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICueNavegacionDAO;
import com.vortexbird.pusay.cuestionarios.model.CueCategoria;
import com.vortexbird.pusay.cuestionarios.model.CueConfiguracion;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionarioTipo;
import com.vortexbird.pusay.cuestionarios.model.CueEstado;
import com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CueNavegacion;
import com.vortexbird.pusay.cuestionarios.model.CueResponsable;
import com.vortexbird.pusay.cuestionarios.services.control.ICueConfiguracionLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueCuestionarioLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueCuestionarioTipoLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueEstadoLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueResponsableLogic;

@Scope("singleton")
@Service("CueCuestionarioLogic")
public class CueCuestionarioLogic implements ICueCuestionarioLogic {
	/**
	 * DAO injected by Spring that manages CueCuestionario entities support Andr�s Mauricio C�rdenas
	 * mauriciocardenasp@gmail.com
	 */
	@Autowired
	private ICueCuestionarioDAO cueCuestionarioDAO;

	/**
	 * DAO injected by Spring that manages CueCategoria entities
	 * 
	 */
	@Autowired
	private ICueCategoriaDAO cueCategoriaDAO;

	/**
	 * DAO injected by Spring that manages CueListaCuestionario entities
	 * 
	 */
	@Autowired
	private ICueListaCuestionarioDAO cueListaCuestionarioDAO;

	/**
	 * DAO injected by Spring that manages CueNavegacion entities
	 * 
	 */
	@Autowired
	private ICueNavegacionDAO cueNavegacionDAO;

	/**
	 * Logic injected by Spring that manages CueConfiguracion entities
	 * 
	 */
	@Autowired
	ICueConfiguracionLogic logicCueConfiguracion1;

	/**
	 * Logic injected by Spring that manages CueCuestionarioTipo entities
	 * 
	 */
	@Autowired
	ICueCuestionarioTipoLogic logicCueCuestionarioTipo2;

	/**
	 * Logic injected by Spring that manages CueEstado entities
	 * 
	 */
	@Autowired
	ICueEstadoLogic logicCueEstado3;

	/**
	 * Logic injected by Spring that manages CueResponsable entities
	 * 
	 */
	@Autowired
	ICueResponsableLogic logicCueResponsable4;

	private String lbl_consecutivo = Propiedades.getInstance().getMensaje("lbl_cuestionario_consecutivo");
	private String lbl_cueEstado = Propiedades.getInstance().getMensaje("lbl_cuestionario_cueEstado");
	private String lbl_cueConfiguracion = Propiedades.getInstance().getMensaje("lbl_cuestionario_cueConfiguracion");
	private String lbl_cueResponsable = Propiedades.getInstance().getMensaje("lbl_cuestionario_cueResponsable");
	private String lbl_cueCuestionarioTipo = Propiedades.getInstance().getMensaje(
			"lbl_cuestionario_cueCuestionarioTipo");
	private String lbl_titulo = Propiedades.getInstance().getMensaje("lbl_cuestionario_titulo");
	private String lbl_descripcion = Propiedades.getInstance().getMensaje("lbl_cuestionario_descripcion");
	private String lbl_terminos = Propiedades.getInstance().getMensaje("lbl_cuestionario_terminos");
	private String lbl_fechaCreacion = Propiedades.getInstance().getMensaje("lbl_cuestionario_fechaCreacion");
	private String lbl_lista = Propiedades.getInstance().getMensaje("lbl_cuestionario_lista");

	@Transactional(readOnly = true)
	public List<CueCuestionario> getCueCuestionario() throws Exception {
		List<CueCuestionario> list = new ArrayList<CueCuestionario>();

		try {
			list = cueCuestionarioDAO.findAll();
			for (CueCuestionario cueCuestionario : list) {
				Hibernate.initialize(cueCuestionario.getCueResponsable());
				Hibernate.initialize(cueCuestionario.getCueConfiguracion());
				Hibernate.initialize(cueCuestionario.getCueCuestionarioTipo());
				Hibernate.initialize(cueCuestionario.getCueEstado());
			}
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + lbl_lista);
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueCuestionario(CueCuestionario cuestionario) throws Exception {
		CueCuestionario entity = null;

		try {
			if (cuestionario.getDescripcion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_descripcion);
			}

			if ((cuestionario.getDescripcion() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuestionario.getDescripcion(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_descripcion);
			}

			if (cuestionario.getFechaCreacion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_fechaCreacion);
			}

			if ((cuestionario.getTerminos() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuestionario.getTerminos(), 1024) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_terminos);
			}

			if (cuestionario.getTitulo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_titulo);
			}

			if ((cuestionario.getTitulo() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuestionario.getTitulo(), 128) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_titulo);
			}

			if (cuestionario.getCueConfiguracion().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueConfiguracion);
			}

			if ((cuestionario.getCueConfiguracion().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cuestionario.getCueConfiguracion().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueConfiguracion);
			}

			if (cuestionario.getCueCuestionarioTipo().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueCuestionarioTipo);
			}

			if ((cuestionario.getCueCuestionarioTipo().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cuestionario.getCueCuestionarioTipo().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueCuestionarioTipo);
			}

			if (cuestionario.getCueEstado().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueEstado);
			}

			if ((cuestionario.getCueEstado().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cuestionario.getCueEstado().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueEstado);
			}

			if (cuestionario.getCueResponsable().getIdentificacion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueResponsable);
			}

			if ((cuestionario.getCueResponsable().getIdentificacion() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cuestionario.getCueResponsable().getIdentificacion(), 17, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueResponsable);
			}

			CueConfiguracion cueConfiguracionClass = this.logicCueConfiguracion1.getCueConfiguracion(cuestionario
					.getCueConfiguracion().getConsecutivo());
			CueCuestionarioTipo cueCuestionarioTipoClass = this.logicCueCuestionarioTipo2
					.getCueCuestionarioTipo(cuestionario.getCueCuestionarioTipo().getConsecutivo());
			CueEstado cueEstadoClass = this.logicCueEstado3.getCueEstado(cuestionario.getCueEstado().getConsecutivo());
			CueResponsable cueResponsableClass = this.logicCueResponsable4.getCueResponsable(cuestionario
					.getCueResponsable().getIdentificacion());

			if (cueConfiguracionClass == null) {
				throw new ZMessManager().new ForeignException("cueConfiguracion");
			}

			if (cueCuestionarioTipoClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueCuestionarioTipo);
			}

			if (cueEstadoClass == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueEstado);
			}

			if (cueResponsableClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueResponsable);
			}

			entity = new CueCuestionario();
			entity.setDescripcion(cuestionario.getDescripcion());
			entity.setFechaCreacion(cuestionario.getFechaCreacion());
			entity.setTerminos(cuestionario.getTerminos());
			entity.setTitulo(cuestionario.getTitulo());
			entity.setCueConfiguracion(cueConfiguracionClass);
			entity.setCueCuestionarioTipo(cueCuestionarioTipoClass);
			entity.setCueEstado(cueEstadoClass);
			entity.setCueResponsable(cueResponsableClass);
			cueCuestionarioDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCueCuestionario(Long consecutivo) throws Exception {
		CueCuestionario entity = null;

		if (consecutivo == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
		}

		List<CueCategoria> cueCategorias = null;
		List<CueListaCuestionario> cueListaCuestionarios = null;
		List<CueNavegacion> cueNavegacions = null;
		entity = getCueCuestionario(consecutivo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_lista);
		}

		try {
			cueCategorias = cueCategoriaDAO.findByProperty("cueCuestionario.consecutivo", consecutivo);

			if (Utilities.validationsList(cueCategorias) == true) {
				throw new ZMessManager().new DeletingException("cueCategorias");
			}

			cueListaCuestionarios = cueListaCuestionarioDAO.findByProperty("cueCuestionario.consecutivo", consecutivo);

			if (Utilities.validationsList(cueListaCuestionarios) == true) {
				throw new ZMessManager().new DeletingException("cueListaCuestionarios");
			}

			cueNavegacions = cueNavegacionDAO.findByProperty("cueCuestionario.consecutivo", consecutivo);

			if (Utilities.validationsList(cueNavegacions) == true) {
				throw new ZMessManager().new DeletingException("cueNavegacions");
			}

			cueCuestionarioDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueCuestionario(CueCuestionario cuestionario) throws Exception {
		CueCuestionario entity = null;

		try {
			if (cuestionario.getDescripcion() == null || cuestionario.getDescripcion().length() == 0) {
				throw new ZMessManager().new EmptyFieldException(lbl_descripcion);
			}

			if ((cuestionario.getDescripcion() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuestionario.getDescripcion(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_descripcion);
			}

			if (cuestionario.getFechaCreacion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_fechaCreacion);
			}

			if ((cuestionario.getTerminos() != null || cuestionario.getTerminos().length() == 0)
					&& (Utilities.checkWordAndCheckWithlength(cuestionario.getTerminos(), 1024) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_terminos);
			}

			if (cuestionario.getTitulo() == null || cuestionario.getTitulo().length() == 0) {
				throw new ZMessManager().new EmptyFieldException(lbl_titulo);
			}

			if ((cuestionario.getTitulo() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuestionario.getTitulo(), 128) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_titulo);
			}

			if (cuestionario.getCueConfiguracion().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueConfiguracion);
			}

			if ((cuestionario.getCueConfiguracion().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cuestionario.getCueConfiguracion().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueConfiguracion);
			}

			if (cuestionario.getCueCuestionarioTipo().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueCuestionarioTipo);
			}

			if ((cuestionario.getCueCuestionarioTipo().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cuestionario.getCueCuestionarioTipo().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueCuestionarioTipo);
			}

			if (cuestionario.getCueEstado().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueEstado);
			}

			if ((cuestionario.getCueEstado().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cuestionario.getCueEstado().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueEstado);
			}

			if (cuestionario.getCueResponsable().getIdentificacion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueResponsable);
			}

			if ((cuestionario.getCueResponsable().getIdentificacion() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cuestionario.getCueResponsable().getIdentificacion(), 17, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueResponsable);
			}

			CueConfiguracion cueConfiguracionClass = this.logicCueConfiguracion1.getCueConfiguracion(cuestionario
					.getCueConfiguracion().getConsecutivo());
			CueCuestionarioTipo cueCuestionarioTipoClass = this.logicCueCuestionarioTipo2
					.getCueCuestionarioTipo(cuestionario.getCueCuestionarioTipo().getConsecutivo());
			CueEstado cueEstadoClass = this.logicCueEstado3.getCueEstado(cuestionario.getCueEstado().getConsecutivo());
			CueResponsable cueResponsableClass = this.logicCueResponsable4.getCueResponsable(cuestionario
					.getCueResponsable().getIdentificacion());

			if (cueConfiguracionClass == null) {
				throw new ZMessManager().new ForeignException("cueConfiguracion");
			}

			if (cueCuestionarioTipoClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueCuestionarioTipo);
			}

			if (cueEstadoClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueEstado);
			}

			if (cueResponsableClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueResponsable);
			}

			entity = getCueCuestionario(cuestionario.getConsecutivo());

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setDescripcion(cuestionario.getDescripcion());
			entity.setFechaCreacion(cuestionario.getFechaCreacion());
			entity.setTerminos(cuestionario.getTerminos());
			entity.setTitulo(cuestionario.getTitulo());
			entity.setCueConfiguracion(cueConfiguracionClass);
			entity.setCueCuestionarioTipo(cueCuestionarioTipoClass);
			entity.setCueEstado(cueEstadoClass);
			entity.setCueResponsable(cueResponsableClass);
			cueCuestionarioDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueCuestionarioDTO> getDataCueCuestionario() throws Exception {
		try {
			List<CueCuestionario> cueCuestionario = cueCuestionarioDAO.findAll();

			List<CueCuestionarioDTO> cueCuestionarioDTO = new ArrayList<CueCuestionarioDTO>();

			for (CueCuestionario cueCuestionarioTmp : cueCuestionario) {
				CueCuestionarioDTO cueCuestionarioDTO2 = new CueCuestionarioDTO();

				cueCuestionarioDTO2.setConsecutivo(cueCuestionarioTmp.getConsecutivo());
				cueCuestionarioDTO2.setDescripcion((cueCuestionarioTmp.getDescripcion() != null) ? cueCuestionarioTmp
						.getDescripcion() : null);
				cueCuestionarioDTO2.setFechaCreacion(cueCuestionarioTmp.getFechaCreacion());
				cueCuestionarioDTO2.setTerminos((cueCuestionarioTmp.getTerminos() != null) ? cueCuestionarioTmp
						.getTerminos() : null);
				cueCuestionarioDTO2.setTitulo((cueCuestionarioTmp.getTitulo() != null) ? cueCuestionarioTmp.getTitulo()
						: null);
				cueCuestionarioDTO2.setConsecutivo_CueConfiguracion((cueCuestionarioTmp.getCueConfiguracion()
						.getConsecutivo() != null) ? cueCuestionarioTmp.getCueConfiguracion().getConsecutivo() : null);
				cueCuestionarioDTO2.setConsecutivo_CueCuestionarioTipo((cueCuestionarioTmp.getCueCuestionarioTipo()
						.getConsecutivo() != null) ? cueCuestionarioTmp.getCueCuestionarioTipo().getConsecutivo()
						: null);
				cueCuestionarioDTO2
						.setConsecutivo_CueEstado((cueCuestionarioTmp.getCueEstado().getConsecutivo() != null) ? cueCuestionarioTmp
								.getCueEstado().getConsecutivo() : null);
				cueCuestionarioDTO2.setIdentificacion_CueResponsable((cueCuestionarioTmp.getCueResponsable()
						.getIdentificacion() != null) ? cueCuestionarioTmp.getCueResponsable().getIdentificacion()
						: null);
				cueCuestionarioDTO.add(cueCuestionarioDTO2);
			}

			return cueCuestionarioDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public CueCuestionario getCueCuestionario(Long consecutivo) throws Exception {
		CueCuestionario entity = null;

		try {
			entity = cueCuestionarioDAO.findById(consecutivo);
			Hibernate.initialize(entity.getCueResponsable());
			Hibernate.initialize(entity.getCueConfiguracion());
			Hibernate.initialize(entity.getCueCuestionarioTipo());
			Hibernate.initialize(entity.getCueEstado());
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(lbl_lista);
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<CueCuestionario> findPageCueCuestionario(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<CueCuestionario> entity = null;

		try {
			entity = cueCuestionarioDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueCuestionario Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCueCuestionario() throws Exception {
		Long entity = null;

		try {
			entity = cueCuestionarioDAO.findTotalNumber();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueCuestionario Count");
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
	public List<CueCuestionario> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<CueCuestionario> list = new ArrayList<CueCuestionario>();
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
			list = cueCuestionarioDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<CueCuestionario> getCueCuestionariosActivos() {
		return this.cueCuestionarioDAO.findByProperty("cueEstado.consecutivo", 1L);
	}
}
