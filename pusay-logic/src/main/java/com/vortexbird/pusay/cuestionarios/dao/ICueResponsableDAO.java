package com.vortexbird.pusay.cuestionarios.dao;

import com.vortexbird.pusay.cuestionarios.api.dao.support.Dao;
import com.vortexbird.pusay.cuestionarios.model.CueResponsable;

public interface ICueResponsableDAO extends Dao<CueResponsable, Long> {
	// property constants
	public static final String APELLIDO = "apellido";
	public static final String EMAIL = "email";
	public static final String EMAILSOPORTE = "emailSoporte";
	public static final String IDENTIFICACION = "identificacion";
	public static final String NOMBRE = "nombre";
	public static final String PAGINASOPORTE = "paginaSoporte";
	public static final String RAZONSOCIAL = "razonSocial";
	public static final String TELEFONO1 = "telefono1";
	public static final String TELEFONO2 = "telefono2";
}
