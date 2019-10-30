package com.vortexbird.pusay.cuestionarios.dao;

import com.vortexbird.pusay.cuestionarios.api.dao.support.Dao;
import com.vortexbird.pusay.cuestionarios.model.CueContacto;

/**
 * Interface for CueContactoDAO.
 * 
 */
public interface ICueContactoDAO extends Dao<CueContacto, Long> {
	// property constants
	public static final String APELLIDO = "apellido";
	public static final String CELULAR = "celular";
	public static final String EMAIL = "email";
	public static final String EMPRESA = "empresa";
	public static final String ESTADO = "estado";
	public static final String IDENTIFICACION = "identificacion";
	public static final String NOMBRE = "nombre";
}