package com.vortexbird.pusay.cuestionarios.web.paginator;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.vortexbird.pusay.cuestionarios.model.CueOpcion;

/**
 * 
 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
 * @project cuestionario-web
 * @class OpcionDataModel
 * @date 23/07/2013
 *
 */
public class OpcionDataModel extends ListDataModel<CueOpcion> implements SelectableDataModel<CueOpcion> {

	public OpcionDataModel(List<CueOpcion> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	public CueOpcion getRowData(String rowKey) {
		// In a real app, a more efficient way like a query by rowKey should be
		// implemented to deal with huge data

		List<CueOpcion> datos = (List<CueOpcion>) getWrappedData();

		if (datos != null) {
			for (CueOpcion dato : datos) {
				if (dato.getConsecutivo().toString().equals(rowKey))
					return dato;
			}
		}
		return null;
	}

	public Object getRowKey(CueOpcion data) {
		return data.getConsecutivo();
	}
}