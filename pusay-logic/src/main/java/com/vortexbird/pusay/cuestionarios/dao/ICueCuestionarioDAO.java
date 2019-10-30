package com.vortexbird.pusay.cuestionarios.dao;

import com.vortexbird.pusay.cuestionarios.api.dao.support.Dao;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;

/**
 * Interface for CueCuestionarioDAO.
 * 
 */
public interface ICueCuestionarioDAO extends Dao<CueCuestionario, Long> {
	// property constants
	public static final String CONSECUTIVO = "consecutivo";
	public static final String DESCRIPCION = "descripcion";
	public static final String FECHACREACION = "fechaCreacion";
	public static final String TERMINOS = "terminos";
	public static final String TITULO = "titulo";
}