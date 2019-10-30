package com.vortexbird.pusay.cuestionarios.dao;

import com.vortexbird.pusay.cuestionarios.api.dao.support.Dao;
import com.vortexbird.pusay.cuestionarios.model.CueLista;

public interface ICueListaDAO extends Dao<CueLista, Long> {
	// property constants
	public static final String CONSECUTIVO = "consecutivo";
	public static final String DESCRIPCION = "descripcion";
	public static final String ESTADO = "estado";
	public static final String NOMBRE = "nombre";
}