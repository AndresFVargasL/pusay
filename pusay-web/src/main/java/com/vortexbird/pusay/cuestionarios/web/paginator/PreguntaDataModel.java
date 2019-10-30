package com.vortexbird.pusay.cuestionarios.web.paginator;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.vortexbird.pusay.cuestionarios.model.CuePregunta;

/**
 * 
 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
 * @project cuestionario-web
 * @class PreguntaDataModel
 * @date 23/07/2013
 * 
 */
public class PreguntaDataModel extends ListDataModel<CuePregunta> implements SelectableDataModel<CuePregunta> {

	public PreguntaDataModel(List<CuePregunta> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	public CuePregunta getRowData(String rowKey) {
		// In a real app, a more efficient way like a query by rowKey should be
		// implemented to deal with huge data

		List<CuePregunta> datos = (List<CuePregunta>) getWrappedData();

		if (datos != null) {
			for (CuePregunta dato : datos) {
				if (dato.getConsecutivo().toString().equals(rowKey))
					return dato;
			}
		}
		return null;
	}

	public Object getRowKey(CuePregunta data) {
		return data.getConsecutivo();
	}
}
