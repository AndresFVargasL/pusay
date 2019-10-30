package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegGrupoOpcion;
import com.vortexbird.seguridad.modelo.SegOpcion;
import com.vortexbird.seguridad.modelo.SegRol;


public class SegTreeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private SegRol segRol;
    private List<SegGrupoOpcion> listGrupoOpcion;
    private List<List<SegOpcion>> listOpciones;
    private Boolean selectedCheck;
    private List<SegOpcion> listOpcionsFinal;
    
	public SegTreeDTO() {
		super();
	}

	public SegRol getSegRol() {
		return segRol;
	}

	public void setSegRol(SegRol segRol) {
		this.segRol = segRol;
	}

	public List<SegGrupoOpcion> getListGrupoOpcion() {
		return listGrupoOpcion;
	}

	public void setListGrupoOpcion(List<SegGrupoOpcion> listGrupoOpcion) {
		this.listGrupoOpcion = listGrupoOpcion;
	}

	public List<List<SegOpcion>> getListOpciones() {
		return listOpciones;
	}

	public void setListOpciones(List<List<SegOpcion>> listOpciones) {
		this.listOpciones = listOpciones;
	}

	public Boolean getSelectedCheck() {
		return selectedCheck;
	}

	public void setSelectedCheck(Boolean selectedCheck) {
		this.selectedCheck = selectedCheck;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<SegOpcion> getListOpcionsFinal() {
		return listOpcionsFinal;
	}

	public void setListOpcionsFinal(List<SegOpcion> listOpcionsFinal) {
		this.listOpcionsFinal = listOpcionsFinal;
	}
    
}

