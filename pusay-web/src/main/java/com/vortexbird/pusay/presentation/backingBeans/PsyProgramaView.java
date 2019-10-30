package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEstrategiasDTO;
import com.vortexbird.pusay.modelo.dto.PsyProgramaDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.FacesUtils;
import com.vortexbird.pusay.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import java.util.TimeZone;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyProgramaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private static final Logger log = LoggerFactory.getLogger(PsyProgramaView.class);
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtProgCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyProgramaDTO> data;
    private List<SelectItem> lasEstrategias;
    private String somEstrategia;
    private PsyProgramaDTO selectedPsyPrograma;
    private PsyPrograma entity;
    private boolean showDialog;
    private boolean editando=false;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyProgramaView() {
        super();
    }
    
    public PsyEmpresa getEmpresaIntoSession(){
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		return (PsyEmpresa) httpSession.getAttribute("empresa");		
	}

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyProgramaDTO psyProgramaDTO = (PsyProgramaDTO) e.getObject();

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyProgramaDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyProgramaDTO.getNombre());

            if (txtProgCodigo == null) {
                txtProgCodigo = new InputText();
            }

            txtProgCodigo.setValue(psyProgramaDTO.getProgCodigo());

            Long progCodigo = FacesUtils.checkLong(txtProgCodigo);
            entity = businessDelegatorView.getPsyPrograma(progCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
    	txtEstadoRegistro.setValue("Activo");
        action_clear();
        selectedPsyPrograma = null;
        setSomEstrategia("0");
        setShowDialog(true);
        setEditando(false);
        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyPrograma = null;

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(false);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Long progCodigo = FacesUtils.checkLong(txtProgCodigo);
            entity = (progCodigo != null)
                ? businessDelegatorView.getPsyPrograma(progCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtProgCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtProgCodigo.setValue(entity.getProgCodigo());
            txtProgCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
    	setEditando(true);
        selectedPsyPrograma = (PsyProgramaDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedPsyPrograma"));
        
        txtEstadoRegistro.setValue(selectedPsyPrograma.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        txtNombre.setValue(selectedPsyPrograma.getNombre());
        txtNombre.setDisabled(false);
        btnSave.setDisabled(false);
        setSomEstrategia(selectedPsyPrograma.getDmaeCodigo().toString());
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyPrograma == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new PsyPrograma();

            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? 
            				ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);  
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.savePsyPrograma(entity, somEstrategia, getEmpresaIntoSession());
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
            data=null;
            action_closeDialog();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long progCodigo = new Long(selectedPsyPrograma.getProgCodigo());
                entity = businessDelegatorView.getPsyPrograma(progCodigo);
            }

            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? 
            				ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);  
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.updatePsyPrograma(entity, somEstrategia, getEmpresaIntoSession());
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear();
            data=null;
            action_closeDialog();
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyPrograma = (PsyProgramaDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedPsyPrograma"));

            Long progCodigo = new Long(selectedPsyPrograma.getProgCodigo());
            entity = businessDelegatorView.getPsyPrograma(progCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long progCodigo = FacesUtils.checkLong(txtProgCodigo);
            entity = businessDelegatorView.getPsyPrograma(progCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyPrograma(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();
        setEditando(false);
        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedPsyPrograma = (PsyProgramaDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedPsyPrograma"));

            Long progCodigo = new Long(selectedPsyPrograma.getProgCodigo());
            entity = businessDelegatorView.getPsyPrograma(progCodigo);
            businessDelegatorView.deletePsyPrograma(entity);
            data.remove(selectedPsyPrograma);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoRegistro, String nombre,
        Long progCodigo) throws Exception {
        try {
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePsyPrograma(entity, somEstrategia, getEmpresaIntoSession());
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyProgramaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtProgCodigo() {
        return txtProgCodigo;
    }

    public void setTxtProgCodigo(InputText txtProgCodigo) {
        this.txtProgCodigo = txtProgCodigo;
    }

    public List<PsyProgramaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyPrograma(getEmpresaIntoSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyProgramaDTO> psyProgramaDTO) {
        this.data = psyProgramaDTO;
    }

    public PsyProgramaDTO getSelectedPsyPrograma() {
        return selectedPsyPrograma;
    }

    public void setSelectedPsyPrograma(PsyProgramaDTO psyPrograma) {
        this.selectedPsyPrograma = psyPrograma;
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

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

	public List<SelectItem> getLasEstrategias() {
		try {
			lasEstrategias = new ArrayList<SelectItem>();
			List<PsyDetalleEstrategiasDTO> laEstrategia = null;
			if(editando==true){     
				laEstrategia = businessDelegatorView
						.consultaEstrategiasParaPrograma(getEmpresaIntoSession());
			}else if (editando==false) {
				laEstrategia = businessDelegatorView
						.consultaEstrategiasParaProgramaDepurada(getEmpresaIntoSession());
			}
			for (PsyDetalleEstrategiasDTO detalleEstrategias : laEstrategia) {
				if (detalleEstrategias.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(detalleEstrategias.getDmaeCodigo(), detalleEstrategias.getObesNombre()+" | "+
				detalleEstrategias.getImamNombre()+" | "+
							detalleEstrategias.getEsamNombre());
					lasEstrategias.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando las estrategias");
		}
		return lasEstrategias;
	}

	public void setLasEstrategias(List<SelectItem> lasEstrategias) {
		this.lasEstrategias = lasEstrategias;
	}

	public String getSomEstrategia() {
		return somEstrategia;
	}

	public void setSomEstrategia(String somEstrategia) {
		this.somEstrategia = somEstrategia;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {

		try {

			Document pdf = (Document) document;
			pdf.open();
			pdf.setPageSize(PageSize.LETTER);
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String logo = servletContext.getRealPath("") + "images" + File.separator + "logoPusay.png";
			Image imageCenter = Image.getInstance(logo);
			imageCenter.setAlignment(Image.MIDDLE);
			pdf.add(imageCenter);
			pdf.add(new Paragraph("Empresa: "+FacesUtils.getEmpresaIntoSession().getNombre()+"\n"+
					"Plan Estratégico: "+businessDelegatorView.getPlanEstrategicoActivoByPEA(FacesUtils.getEmpresaIntoSession()).getNombre()+"\n"+
					"Nombre del Documento: Tacticas ( Programas y / o Proyectos ) \n"+
					"Fecha: "+ new Date().toString()+"\n\n"));

		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error generando el pdf. El error fue : "+e.getMessage());
		}		



	}

	

	
    
    
}
