package com.vortexbird.seguridad.presentation.backingBeans;

import org.apache.log4j.Logger;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegSistemaCiaDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


public class SegSistemaCiaView {
	private InputText txtCiaCodigo_SegCompania;
	private InputText txtSisCodigo_SegSistema;
	private InputText txtUsuCodigo_SegUsuario;
	private InputText txtSicCodigo;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<SegSistemaCiaDTO> data;
	private SegSistemaCiaDTO selectedSegSistemaCia;

	private SelectOneMenu somEstadosRegistro;
	private SelectItem[] losEstadosRegistro;
	private SelectOneMenu somCompanias;
	private SelectItem[] lasCompanias;
	private SelectOneMenu somSistemas;
	private SelectItem[] losSistemas;
	private Logger logger = Logger.getLogger(SegSistemaCiaView.class);

	public SegSistemaCiaView() {
		super();
	}

	public String action_clear() {
		btnSave.setDisabled(false);
		btnModify.setDisabled(true);
		btnClear.setDisabled(false);
		txtSicCodigo.setValue(null);
		somEstadosRegistro.setValue("-1");
		somCompanias.setValue("-1");
		somSistemas.setValue("-1");
		return "";
	}

	public String action_save() {
		try {

			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());


			if (somSistemas.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un sistema valido");
			}

			if (somCompanias.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar una compa�ia valida");
			}

			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			String estadoRegistro = somEstadosRegistro.getValue().toString();
			Long codigoCompania = Long.parseLong(somCompanias.getValue().toString());
			Long codigoSistema = Long.parseLong(somSistemas.getValue().toString());

			BusinessDelegatorView.saveSegSistemaCia(
					null,
					estadoRegistro,
					codigoCompania,
					codigoSistema,
					usuSession);

//			SegSistemaCiaDTO sisCiaDTO = new SegSistemaCiaDTO();
//			sisCiaDTO.setSicCodigo(null);
//			sisCiaDTO.setSicEstadoRegistro(estadoRegistro.equals("0")?"Inactivo":"Activo");
//
//			SegCompania compania = BusinessDelegatorView.getSegCompania(codigoCompania);
//			sisCiaDTO.setCiaNombre_SegCompania(compania.getCiaNombre());
//
//			SegSistema sistema = BusinessDelegatorView.getSegSistema(codigoSistema);
//			sisCiaDTO.setSisNombre_SegSistema(sistema.getSisNombre());
//			data.add(sisCiaDTO);
			
			if (usuSession == 0) {
				data = BusinessDelegatorView.getDataSegSistemaCia();	
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				String compania = FacesUtils2.getSessionParameter("compania").toString();
				data = BusinessDelegatorView.getSistemaCiaQuery(sistema , compania);	
			}
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return "";
	}


	public String action_modify() {

		List<SegSistemaCia> losSistemaCia = new ArrayList<SegSistemaCia>();
		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

		try {

			if (somSistemas.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un sistema valido");
			}

			if (somCompanias.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar una compa�ia valida");
			}

			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			Long codigoSisCia = Long.parseLong(txtSicCodigo.getValue().toString());
			String estadoRegistro = somEstadosRegistro.getValue().toString();
			Long codigoCompania = Long.parseLong(somCompanias.getValue().toString());
			Long codigoSistema = Long.parseLong(somSistemas.getValue().toString());

			BusinessDelegatorView.updateSegSistemaCia(
					codigoSisCia,
					estadoRegistro,
					codigoCompania,
					codigoSistema,
					usuSession);

//			losSistemaCia = BusinessDelegatorView.getSegSistemaCia();
//			data = BusinessDelegatorView.getSegSistemaCiaDTO(losSistemaCia);
			
			if (usuSession == 0) {
				data = BusinessDelegatorView.getDataSegSistemaCia();	
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				String compania = FacesUtils2.getSessionParameter("compania").toString();
				data = BusinessDelegatorView.getSistemaCiaQuery(sistema , compania);	
			}
			
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}

		return "";
	}

	public String action_modifyWitDTO(Long sicCodigo, String sicEstadoRegistro,
			Long ciaCodigo_SegCompania, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception {
		try {
			BusinessDelegatorView.updateSegSistemaCia(sicCodigo,
					sicEstadoRegistro, ciaCodigo_SegCompania, sisCodigo_SegSistema,
					usuCodigo_SegUsuario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public String action_selected(){

		action_clear();
		btnModify.setDisabled(false);
		btnSave.setDisabled(true);

		FacesContext context = FacesContext.getCurrentInstance();  
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();

		String sicCodigo = (String)requestMap.get("sicCodigo");

		try {

			SegSistemaCia entity = BusinessDelegatorView.getSegSistemaCia(Long.parseLong(sicCodigo));
			somEstadosRegistro.setValue(entity.getSicEstadoRegistro());
			somCompanias.setValue(entity.getSegCompania().getCiaCodigo());
			txtSicCodigo.setValue(entity.getSicCodigo());
			somSistemas.setValue(entity.getSegSistema().getSisCodigo());
			btnSave.setDisabled(true);
			btnModify.setDisabled(false);

		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}

	public InputText getTxtCiaCodigo_SegCompania() {
		return txtCiaCodigo_SegCompania;
	}

	public void setTxtCiaCodigo_SegCompania(InputText txtCiaCodigo_SegCompania) {
		this.txtCiaCodigo_SegCompania = txtCiaCodigo_SegCompania;
	}

	public InputText getTxtSisCodigo_SegSistema() {
		return txtSisCodigo_SegSistema;
	}

	public void setTxtSisCodigo_SegSistema(InputText txtSisCodigo_SegSistema) {
		this.txtSisCodigo_SegSistema = txtSisCodigo_SegSistema;
	}

	public InputText getTxtUsuCodigo_SegUsuario() {
		return txtUsuCodigo_SegUsuario;
	}

	public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
		this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
	}

	public InputText getTxtSicCodigo() {
		return txtSicCodigo;
	}

	public void setTxtSicCodigo(InputText txtSicCodigo) {
		this.txtSicCodigo = txtSicCodigo;
	}

	public List<SegSistemaCiaDTO> getData() {
		try {
			if (data == null) {
				
				Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

				if (usuSession == 0) {
					data = BusinessDelegatorView.getDataSegSistemaCia();	
				}else {
					String sistema = FacesUtils2.getSessionParameter("sistema").toString();
					String compania = FacesUtils2.getSessionParameter("compania").toString();
					data = BusinessDelegatorView.getSistemaCiaQuery(sistema , compania);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void setData(List<SegSistemaCiaDTO> segSistemaCiaDTO) {
		this.data = segSistemaCiaDTO;
	}

	public SegSistemaCiaDTO getSelectedSegSistemaCia() {
		return selectedSegSistemaCia;
	}

	public void setSelectedSegSistemaCia(SegSistemaCiaDTO segSistemaCia) {
		this.selectedSegSistemaCia = segSistemaCia;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(CommandButton btnModify) {
		this.btnModify = btnModify;
	}

	public CommandButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(CommandButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public CommandButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(CommandButton btnClear) {
		this.btnClear = btnClear;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}


	public SelectOneMenu getSomEstadosRegistro() {
		return somEstadosRegistro;
	}


	public void setSomEstadosRegistro(SelectOneMenu somEstadosRegistro) {
		this.somEstadosRegistro = somEstadosRegistro;
	}


	public SelectItem[] getLosEstadosRegistro() {
		losEstadosRegistro=new SelectItem[3];
		losEstadosRegistro[0]= new SelectItem("-1", "Seleccione un Estado");
		losEstadosRegistro[1]= new SelectItem("0","Inactivo");
		losEstadosRegistro[2]= new SelectItem("1","Activo");
		return losEstadosRegistro;
	}


	public void setLosEstadosRegistro(SelectItem[] losEstadosRegistro) {
		this.losEstadosRegistro = losEstadosRegistro;
	}


	public SelectOneMenu getSomCompanias() {
		return somCompanias;
	}


	public void setSomCompanias(SelectOneMenu somCompanias) {
		this.somCompanias = somCompanias;
	}


	public SelectItem[] getLasCompanias() {
		
		try {

			List<SegCompania> listCompanias = new ArrayList<SegCompania>();
			SegCompania comp = null;

			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

			if (usuSession==0) {
				listCompanias=BusinessDelegatorView.getSegCompania();
			}else {
				long codigoCompania =  Long.parseLong(FacesUtils.getManagedBean("compania").toString()); 
				comp = BusinessDelegatorView.getSegCompania(codigoCompania);
				listCompanias.add(comp); 
			}

			lasCompanias=new SelectItem[listCompanias.size()];
			int i=0;
			for (SegCompania segCompania : listCompanias) {
				lasCompanias[i]= new SelectItem(segCompania.getCiaCodigo(),segCompania.getCiaNombre().toString());
				i++;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return lasCompanias;
	}


	public void setLasCompanias(SelectItem[] lasCompanias) {
		this.lasCompanias = lasCompanias;
	}


	public SelectOneMenu getSomSistemas() {
		return somSistemas;
	}


	public void setSomSistemas(SelectOneMenu somSistemas) {
		this.somSistemas = somSistemas;
	}


	public SelectItem[] getLosSistemas() {
		try {
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			List<SegSistema> listSistemas = new ArrayList<SegSistema>();

			if (usuSession == 0) {
				listSistemas=BusinessDelegatorView.getSegSistema();
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				listSistemas = BusinessDelegatorView.getSistemasDeUsuario(usuSession, sistema);
			}

			losSistemas=new SelectItem[listSistemas.size()];
			int i=0;
			for (SegSistema segSistema : listSistemas) {
				losSistemas[i]= new SelectItem(segSistema.getSisCodigo(),segSistema.getSisNombre().toString());
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return losSistemas;
	}


	public void setLosSistemas(SelectItem[] losSistemas) {
		this.losSistemas = losSistemas;
	}

}
