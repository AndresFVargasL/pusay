package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegSucursal;
import com.vortexbird.seguridad.presentation.backingBeans.SegSucursalView;


/**
 *
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegSucursalDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sucCodigoInterno;
	private String sucEstadoRegistro;
	private String sucNombre;
	private String ciaCodigo_SegCompania;
	private String ciaNombre_SegCompania;
	private String usuCodigo_SegUsuario;
	private String sucCodigo;
	private boolean rowSelected = false;
	private SegSucursalView segSucursalView;
	private SegSucursal segSucursal;
	private String sucEstadoRegistroNombre;

	public SegSucursal getSegSucursal() {
		return segSucursal;
	}

	public String listener_update(ActionEvent e) {
		try {
			segSucursalView.action_modifyWitDTO(((sucCodigo == null) ||
					sucCodigo.equals("")) ? null : new Long(sucCodigo),
							((sucCodigoInterno == null) || sucCodigoInterno.equals(""))
							? null : new String(sucCodigoInterno),
									((sucEstadoRegistro == null) || sucEstadoRegistro.equals(""))
									? null : new String(sucEstadoRegistro),
											((sucNombre == null) || sucNombre.equals("")) ? null
													: new String(
															sucNombre),
															((ciaCodigo_SegCompania == null) ||
																	ciaCodigo_SegCompania.equals("")) ? null
																			: new Long(
																					ciaCodigo_SegCompania),
																					((usuCodigo_SegUsuario == null) ||
																							usuCodigo_SegUsuario.equals("")) ? null
																									: new Long(
																											usuCodigo_SegUsuario));
			rowSelected = !rowSelected;
		} catch (Exception ex) {
			return "";
		}

		return "";
	}

	public void listener_cancel(ActionEvent e) {
		sucCodigo = segSucursal.getSucCodigo().toString();
		sucCodigoInterno = (segSucursal.getSucCodigoInterno() != null)
				? segSucursal.getSucCodigoInterno().toString() : null;
				sucEstadoRegistro = (segSucursal.getSucEstadoRegistro() != null)
						? segSucursal.getSucEstadoRegistro().toString() : null;
						sucNombre = (segSucursal.getSucNombre() != null)
								? segSucursal.getSucNombre().toString() : null;
								ciaCodigo_SegCompania = (segSucursal.getSegCompania().getCiaCodigo() != null)
										? segSucursal.getSegCompania().getCiaCodigo().toString() : null;
										usuCodigo_SegUsuario = (segSucursal.getSegUsuario().getUsuCodigo() != null)
												? segSucursal.getSegUsuario().getUsuCodigo().toString() : null;
												rowSelected = !rowSelected;
	}

	/**
	 * <p>Bound to commandLink actionListener in the ui that renders/unrenders
	 * the Customer details for editing.</p>
	 */
	public void toggleSelected(ActionEvent e) {
		sucCodigo = segSucursal.getSucCodigo().toString();
		sucCodigoInterno = (segSucursal.getSucCodigoInterno() != null)
				? segSucursal.getSucCodigoInterno().toString() : null;
				sucEstadoRegistro = (segSucursal.getSucEstadoRegistro() != null)
						? segSucursal.getSucEstadoRegistro().toString() : null;
						sucNombre = (segSucursal.getSucNombre() != null)
								? segSucursal.getSucNombre().toString() : null;
								ciaCodigo_SegCompania = (segSucursal.getSegCompania().getCiaCodigo() != null)
										? segSucursal.getSegCompania().getCiaCodigo().toString() : null;
										usuCodigo_SegUsuario = (segSucursal.getSegUsuario().getUsuCodigo() != null)
												? segSucursal.getSegUsuario().getUsuCodigo().toString() : null;
												rowSelected = !rowSelected;
	}

	public void setSegSucursal(SegSucursal segSucursal) {
		this.segSucursal = segSucursal;
	}

	public SegSucursalView getSegSucursalView() {
		return segSucursalView;
	}

	public void setSegSucursalView(SegSucursalView segSucursalView) {
		this.segSucursalView = segSucursalView;
	}

	public String getSucCodigoInterno() {
		return sucCodigoInterno;
	}

	public void setSucCodigoInterno(String sucCodigoInterno) {
		this.sucCodigoInterno = sucCodigoInterno;
	}

	public String getSucEstadoRegistro() {
		return sucEstadoRegistro;
	}

	public void setSucEstadoRegistro(String sucEstadoRegistro) {
		this.sucEstadoRegistro = sucEstadoRegistro;
	}

	public String getSucNombre() {
		return sucNombre;
	}

	public void setSucNombre(String sucNombre) {
		this.sucNombre = sucNombre;
	}

	public String getCiaCodigo_SegCompania() {
		return ciaCodigo_SegCompania;
	}

	public void setCiaCodigo_SegCompania(String ciaCodigo_SegCompania) {
		this.ciaCodigo_SegCompania = ciaCodigo_SegCompania;
	}

	public String getUsuCodigo_SegUsuario() {
		return usuCodigo_SegUsuario;
	}

	public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
		this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
	}

	public String getSucCodigo() {
		return sucCodigo;
	}

	public void setSucCodigo(String sucCodigo) {
		this.sucCodigo = sucCodigo;
	}

	public boolean isRowSelected() {
		return rowSelected;
	}

	public void setRowSelected(boolean rowSelected) {
		this.rowSelected = rowSelected;
	}

	public String getCiaNombre_SegCompania() {
		return ciaNombre_SegCompania;
	}

	public void setCiaNombre_SegCompania(String ciaNombreSegCompania) {
		ciaNombre_SegCompania = ciaNombreSegCompania;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSucEstadoRegistroNombre() {
		return sucEstadoRegistroNombre;
	}

	public void setSucEstadoRegistroNombre(String sucEstadoRegistroNombre) {
		this.sucEstadoRegistroNombre = sucEstadoRegistroNombre;
	}
}