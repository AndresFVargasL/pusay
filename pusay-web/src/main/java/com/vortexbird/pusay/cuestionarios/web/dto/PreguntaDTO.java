/**
 * 
 */
package com.vortexbird.pusay.cuestionarios.web.dto;

import com.vortexbird.pusay.cuestionarios.model.CueOpcion;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;
import com.vortexbird.pusay.cuestionarios.web.paginator.OpcionDataModel;

/**
 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
 * @project cuestionario-web
 * @class PreguntaDTO
 * @date 23/07/2013
 * 
 */
public class PreguntaDTO {

	private CuePregunta pregunta;
	private OpcionDataModel dataOpcionDataModel;
	private CueOpcion[] selectedListOpciones;

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the dataOpcionDataModel
	 */
	public OpcionDataModel getDataOpcionDataModel() {
		if (this.dataOpcionDataModel == null) {
			this.dataOpcionDataModel = new OpcionDataModel(this.pregunta.getListOpciones());
		}
		return this.dataOpcionDataModel;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param dataOpcionDataModel
	 *            the dataOpcionDataModel to set
	 */
	public void setDataOpcionDataModel(OpcionDataModel dataOpcionDataModel) {
		this.dataOpcionDataModel = dataOpcionDataModel;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the selectedListOpciones
	 */
	public CueOpcion[] getSelectedListOpciones() {
		return selectedListOpciones;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param selectedListOpciones
	 *            the selectedListOpciones to set
	 */
	public void setSelectedListOpciones(CueOpcion[] selectedListOpciones) {
		this.selectedListOpciones = selectedListOpciones;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the pregunta
	 */
	public CuePregunta getPregunta() {
		return pregunta;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param pregunta
	 *            the pregunta to set
	 */
	public void setPregunta(CuePregunta pregunta) {
		this.pregunta = pregunta;
	}
}