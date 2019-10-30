package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyImpactoObjetivoDTO;
import com.vortexbird.pusay.modelo.dto.PsyImpactoObjetivoTableDTO;
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

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Header;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyImpactoObjetivoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyImpactoObjetivoView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtEstadoRegistro;
    private InputText txtImamCodigo_PsyImpactoAmbiental;
    private InputText txtCodigo_PsyObjetivoAmbiental;
    private InputText txtImobCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyImpactoObjetivoDTO> data;
    private List<PsyImpactoObjetivoTableDTO> dataImob = null;
    private PsyImpactoObjetivoDTO selectedPsyImpactoObjetivo;
    private PsyImpactoObjetivo entity;
    private boolean showDialog;
    private String somImpactoAmbiental;
    private String somObjetivoAmbiental;
    private List<SelectItem> losObjetivos;
    private List<SelectItem> losImpactos;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyImpactoObjetivoView() {
        super();
    }
    
    @PostConstruct
    public void init(){
    	action_clear();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyImpactoObjetivoDTO psyImpactoObjetivoDTO = (PsyImpactoObjetivoDTO) e.getObject();

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyImpactoObjetivoDTO.getEstadoRegistro());

            if (txtImamCodigo_PsyImpactoAmbiental == null) {
                txtImamCodigo_PsyImpactoAmbiental = new InputText();
            }

            txtImamCodigo_PsyImpactoAmbiental.setValue(psyImpactoObjetivoDTO.getImamCodigo_PsyImpactoAmbiental());

            if (txtCodigo_PsyObjetivoAmbiental == null) {
                txtCodigo_PsyObjetivoAmbiental = new InputText();
            }

            txtCodigo_PsyObjetivoAmbiental.setValue(psyImpactoObjetivoDTO.getCodigo_PsyObjetivoAmbiental());

            if (txtImobCodigo == null) {
                txtImobCodigo = new InputText();
            }

            txtImobCodigo.setValue(psyImpactoObjetivoDTO.getImobCodigo());

            Long imobCodigo = FacesUtils.checkLong(txtImobCodigo);
            entity = businessDelegatorView.getPsyImpactoObjetivo(imobCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyImpactoObjetivo = null;
        setShowDialog(true);
        txtEstadoRegistro.setValue("Activo");
        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyImpactoObjetivo = null;
        
        setSomImpactoAmbiental("0");
        setSomObjetivoAmbiental("0");

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
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
            Long imobCodigo = FacesUtils.checkLong(txtImobCodigo);
            entity = (imobCodigo != null)
                ? businessDelegatorView.getPsyImpactoObjetivo(imobCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoRegistro.setDisabled(false);
            txtImamCodigo_PsyImpactoAmbiental.setDisabled(false);
            txtCodigo_PsyObjetivoAmbiental.setDisabled(false);
            txtImobCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtImamCodigo_PsyImpactoAmbiental.setValue(entity.getPsyImpactoAmbiental()
                                                             .getImamCodigo());
            txtImamCodigo_PsyImpactoAmbiental.setDisabled(false);
            txtCodigo_PsyObjetivoAmbiental.setValue(entity.getPsyObjetivoAmbiental()
                                                          .getCodigo());
            txtCodigo_PsyObjetivoAmbiental.setDisabled(false);
            txtImobCodigo.setValue(entity.getImobCodigo());
            txtImobCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyImpactoObjetivo = (PsyImpactoObjetivoDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedPsyImpactoObjetivo"));
        txtEstadoRegistro.setValue(selectedPsyImpactoObjetivo.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        setSomImpactoAmbiental((selectedPsyImpactoObjetivo.getImamCodigo_PsyImpactoAmbiental()).toString());
        setSomObjetivoAmbiental((selectedPsyImpactoObjetivo.getCodigo_PsyObjetivoAmbiental()).toString());
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyImpactoObjetivo == null) && (entity == null)) {
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
            entity = new PsyImpactoObjetivo();

            entity.setPsyImpactoAmbiental((somImpactoAmbiental != null && !somImpactoAmbiental.trim().equals("0"))
                ? businessDelegatorView.getPsyImpactoAmbiental(Long.parseLong(somImpactoAmbiental))
                : null);
            entity.setPsyObjetivoAmbiental((somObjetivoAmbiental != null && !somObjetivoAmbiental.trim().equals("0"))
                    ? businessDelegatorView.getPsyObjetivoAmbiental(Long.parseLong(somObjetivoAmbiental))
                            : null);
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? 
            				ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            businessDelegatorView.savePsyImpactoObjetivo(entity);
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
                Long imobCodigo = new Long(selectedPsyImpactoObjetivo.getImobCodigo());
                entity = businessDelegatorView.getPsyImpactoObjetivo(imobCodigo);
            }

            entity.setPsyImpactoAmbiental((somImpactoAmbiental != null && !somImpactoAmbiental.trim().equals("0"))
            		? businessDelegatorView.getPsyImpactoAmbiental(Long.parseLong(somImpactoAmbiental))
            				: null);
            entity.setPsyObjetivoAmbiental((somObjetivoAmbiental != null && !somObjetivoAmbiental.trim().equals("0"))
            		? businessDelegatorView.getPsyObjetivoAmbiental(Long.parseLong(somObjetivoAmbiental))
            				: null);
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? 
            				ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            businessDelegatorView.updatePsyImpactoObjetivo(entity);
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
            selectedPsyImpactoObjetivo = (PsyImpactoObjetivoDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedPsyImpactoObjetivo"));

            Long imobCodigo = new Long(selectedPsyImpactoObjetivo.getImobCodigo());
            entity = businessDelegatorView.getPsyImpactoObjetivo(imobCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long imobCodigo = FacesUtils.checkLong(txtImobCodigo);
            entity = businessDelegatorView.getPsyImpactoObjetivo(imobCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyImpactoObjetivo(entity);
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

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedPsyImpactoObjetivo = (PsyImpactoObjetivoDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedPsyImpactoObjetivo"));

            Long imobCodigo = new Long(selectedPsyImpactoObjetivo.getImobCodigo());
            entity = businessDelegatorView.getPsyImpactoObjetivo(imobCodigo);
            businessDelegatorView.deletePsyImpactoObjetivo(entity);
            data.remove(selectedPsyImpactoObjetivo);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoRegistro, Long imobCodigo,
        Long imamCodigo_PsyImpactoAmbiental, Long codigo_PsyObjetivoAmbiental)
        throws Exception {
        try {
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            businessDelegatorView.updatePsyImpactoObjetivo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyImpactoObjetivoView").requestRender();
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

    public InputText getTxtImamCodigo_PsyImpactoAmbiental() {
        return txtImamCodigo_PsyImpactoAmbiental;
    }

    public void setTxtImamCodigo_PsyImpactoAmbiental(
        InputText txtImamCodigo_PsyImpactoAmbiental) {
        this.txtImamCodigo_PsyImpactoAmbiental = txtImamCodigo_PsyImpactoAmbiental;
    }

    public InputText getTxtCodigo_PsyObjetivoAmbiental() {
        return txtCodigo_PsyObjetivoAmbiental;
    }

    public void setTxtCodigo_PsyObjetivoAmbiental(
        InputText txtCodigo_PsyObjetivoAmbiental) {
        this.txtCodigo_PsyObjetivoAmbiental = txtCodigo_PsyObjetivoAmbiental;
    }

    public InputText getTxtImobCodigo() {
        return txtImobCodigo;
    }

    public void setTxtImobCodigo(InputText txtImobCodigo) {
        this.txtImobCodigo = txtImobCodigo;
    }

    public List<PsyImpactoObjetivoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyImpactoObjetivo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyImpactoObjetivoDTO> psyImpactoObjetivoDTO) {
        this.data = psyImpactoObjetivoDTO;
    }

    public PsyImpactoObjetivoDTO getSelectedPsyImpactoObjetivo() {
        return selectedPsyImpactoObjetivo;
    }

    public void setSelectedPsyImpactoObjetivo(
        PsyImpactoObjetivoDTO psyImpactoObjetivo) {
        this.selectedPsyImpactoObjetivo = psyImpactoObjetivo;
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

	public String getSomImpactoAmbiental() {
		return somImpactoAmbiental;
	}

	public void setSomImpactoAmbiental(String somImpactoAmbiental) {
		this.somImpactoAmbiental = somImpactoAmbiental;
	}

	public String getSomObjetivoAmbiental() {
		return somObjetivoAmbiental;
	}

	public void setSomObjetivoAmbiental(String somObjetivoAmbiental) {
		this.somObjetivoAmbiental = somObjetivoAmbiental;
	}

	public List<SelectItem> getLosObjetivos() {
		
		try {
			losObjetivos = new ArrayList<SelectItem>();
			List<PsyObjetivoAmbiental> losTiposObjetivos = businessDelegatorView
					.getPsyObjetivoAmbiental();
			for (PsyObjetivoAmbiental objetivo : losTiposObjetivos) {
				if (objetivo.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							objetivo.getCodigo(), objetivo.getDescripcion());
					losObjetivos.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los objetivos ambientales");
		}
		
		return losObjetivos;
	}

	public void setLosObjetivos(List<SelectItem> losObjetivos) {
		this.losObjetivos = losObjetivos;
	}

	public List<SelectItem> getLosImpactos() {
		
		try {
			losImpactos = new ArrayList<SelectItem>();
			List<PsyImpactoAmbiental> losTiposImpactos = businessDelegatorView
					.getPsyImpactoAmbiental();
			for (PsyImpactoAmbiental impacto : losTiposImpactos) {
				if (impacto.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							impacto.getImamCodigo(), impacto.getNombre());
					losImpactos.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los impactos ambientales");
		}
		
		return losImpactos;
	}

	public void setLosImpactos(List<SelectItem> losImpactos) {
		this.losImpactos = losImpactos;
	}

	public List<PsyImpactoObjetivoTableDTO> getDataImob() {
		
		try {
            if (dataImob == null) {
            	dataImob = businessDelegatorView.getDataImpactoObjetivo(FacesUtils.getEmpresaIntoSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return dataImob;
	}

	public void setDataImob(List<PsyImpactoObjetivoTableDTO> dataImob) {
		this.dataImob = dataImob;
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
        					  "Nombre del Documento: Objetivos Estrategicos Ambientales \n"+
        					  "Fecha: "+ new Date().toString()+"\n\n"));
				
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error generando el pdf. El error fue : "+e.getMessage());
		}		
        
        
        
    }
    
    
}
