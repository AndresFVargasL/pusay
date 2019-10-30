package com.vortexbird.pusay.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.pusay.exceptions.ZMessManager;
import com.vortexbird.pusay.modelo.PsyCiudad;
import com.vortexbird.pusay.modelo.PsyPais;
import com.vortexbird.pusay.modelo.PsyProvincia;
import com.vortexbird.pusay.modelo.dto.PsyCiudadDTO;
import com.vortexbird.pusay.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.pusay.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.pusay.presentation.utilities.FacesUtils;



/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyCiudadView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyCiudadView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtProvCodigo_PsyProvincia;
    private InputText txtCiudCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyCiudadDTO> data;
    private PsyCiudadDTO selectedPsyCiudad;
    private PsyCiudad entity;
    private boolean showDialog;
    private String somPais;
    private String somProvincia;
    private Long defaultPais = new Long(0);
    private Long defaultProvincia = new Long(0);
    private List<SelectItem> losPaises;
    private List<SelectItem> lasProvincias;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyCiudadView() {
        super();
        
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyCiudadDTO psyCiudadDTO = (PsyCiudadDTO) e.getObject();

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyCiudadDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyCiudadDTO.getNombre());

            if (txtProvCodigo_PsyProvincia == null) {
                txtProvCodigo_PsyProvincia = new InputText();
            }

            txtProvCodigo_PsyProvincia.setValue(psyCiudadDTO.getProvCodigo_PsyProvincia());

            if (txtCiudCodigo == null) {
                txtCiudCodigo = new InputText();
            }

            txtCiudCodigo.setValue(psyCiudadDTO.getCiudCodigo());

            Long ciudCodigo = FacesUtils.checkLong(txtCiudCodigo);
            entity = businessDelegatorView.getPsyCiudad(ciudCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        txtEstadoRegistro.setValue("Activo");
        setSomPais(defaultPais.toString());
        setSomProvincia(defaultProvincia.toString());
        selectedPsyCiudad = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyCiudad = null;

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
        }

        if (txtProvCodigo_PsyProvincia != null) {
            txtProvCodigo_PsyProvincia.setValue(null);
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
            Long ciudCodigo = FacesUtils.checkLong(txtCiudCodigo);
            entity = (ciudCodigo != null)
                ? businessDelegatorView.getPsyCiudad(ciudCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtProvCodigo_PsyProvincia.setDisabled(false);
            txtCiudCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtProvCodigo_PsyProvincia.setValue(entity.getPsyProvincia()
                                                      .getProvCodigo());
            txtProvCodigo_PsyProvincia.setDisabled(false);
            txtCiudCodigo.setValue(entity.getCiudCodigo());
            txtCiudCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
    	try {
        selectedPsyCiudad = (PsyCiudadDTO) (evt.getComponent().getAttributes()
                                               .get("selectedPsyCiudad"));
        txtEstadoRegistro.setValue(selectedPsyCiudad.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        txtNombre.setValue(selectedPsyCiudad.getNombre());
        txtNombre.setDisabled(false);
        setSomProvincia((selectedPsyCiudad.getProvCodigo_PsyProvincia()).toString());
        setSomPais((businessDelegatorView.consultarPaisPorProvincias(selectedPsyCiudad.getProvCodigo_PsyProvincia())).getPaisCodigo().toString());
        btnSave.setDisabled(false);
        setShowDialog(true);
    	} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyCiudad == null) && (entity == null)) {
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
            entity = new PsyCiudad();

            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPsyProvincia((somProvincia != null && !somProvincia.trim().equals("0"))
                ? businessDelegatorView.getPsyProvincia(Long.parseLong(somProvincia)) : null);
            businessDelegatorView.savePsyCiudad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
            data=null;
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long ciudCodigo = new Long(selectedPsyCiudad.getCiudCodigo());
                entity = businessDelegatorView.getPsyCiudad(ciudCodigo);
            }

            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPsyProvincia((somProvincia != null && !somProvincia.trim().equals("0"))
                    ? businessDelegatorView.getPsyProvincia(Long.parseLong(somProvincia)) : null);
            businessDelegatorView.updatePsyCiudad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear();
            data=null;
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyCiudad = (PsyCiudadDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedPsyCiudad"));

            Long ciudCodigo = new Long(selectedPsyCiudad.getCiudCodigo());
            entity = businessDelegatorView.getPsyCiudad(ciudCodigo);
            action_delete();
            data=null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long ciudCodigo = FacesUtils.checkLong(txtCiudCodigo);
            entity = businessDelegatorView.getPsyCiudad(ciudCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyCiudad(entity);
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
            selectedPsyCiudad = (PsyCiudadDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedPsyCiudad"));

            Long ciudCodigo = new Long(selectedPsyCiudad.getCiudCodigo());
            entity = businessDelegatorView.getPsyCiudad(ciudCodigo);
            businessDelegatorView.deletePsyCiudad(entity);
            data.remove(selectedPsyCiudad);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long ciudCodigo, String estadoRegistro,
        String nombre, Long provCodigo_PsyProvincia) throws Exception {
        try {
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePsyCiudad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyCiudadView").requestRender();
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

    public InputText getTxtProvCodigo_PsyProvincia() {
        return txtProvCodigo_PsyProvincia;
    }

    public void setTxtProvCodigo_PsyProvincia(
        InputText txtProvCodigo_PsyProvincia) {
        this.txtProvCodigo_PsyProvincia = txtProvCodigo_PsyProvincia;
    }

    public InputText getTxtCiudCodigo() {
        return txtCiudCodigo;
    }

    public void setTxtCiudCodigo(InputText txtCiudCodigo) {
        this.txtCiudCodigo = txtCiudCodigo;
    }

    public List<PsyCiudadDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyCiudad();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyCiudadDTO> psyCiudadDTO) {
        this.data = psyCiudadDTO;
    }

    public PsyCiudadDTO getSelectedPsyCiudad() {
        return selectedPsyCiudad;
    }

    public void setSelectedPsyCiudad(PsyCiudadDTO psyCiudad) {
        this.selectedPsyCiudad = psyCiudad;
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

	public String getSomPais() {
		return somPais;
	}

	public void setSomPais(String somPais) {
		this.somPais = somPais;
	}

	public Long getDefaultPais() {
		return defaultPais;
	}

	public void setDefaultPais(Long defaultPais) {
		this.defaultPais = defaultPais;
	}

	public List<SelectItem> getLosPaises() {
		try {
			losPaises = new ArrayList<SelectItem>();
			List<PsyPais> losTiposPaises = businessDelegatorView
					.getPsyPais();
			for (PsyPais pais : losTiposPaises) {
				if (pais.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							pais.getPaisCodigo(), pais.getNombre());
					losPaises.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los paises");
		}
		
		return losPaises;
	}

	public void setLosPaises(List<SelectItem> losPaises) {
		this.losPaises = losPaises;
	}

	public String getSomProvincia() {
		return somProvincia;
	}

	public void setSomProvincia(String somProvincia) {
		this.somProvincia = somProvincia;
	}

	public List<SelectItem> getLasProvincias() {
		try {
			if(somPais!=null){
			if(!somPais.trim().equals("0")){
			lasProvincias = new ArrayList<SelectItem>();
			List<PsyProvincia> losTiposProvincias = businessDelegatorView.consultarProvinciasPorPais(Long.parseLong(somPais.trim()));
			for (PsyProvincia provincia : losTiposProvincias) {
				if (provincia.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							provincia.getProvCodigo(), provincia.getNombre());
					lasProvincias.add(selectItem);
				}

			}
			}
			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando las provincias");
		}
		
		return lasProvincias;
		
	}

	public void setLasProvincias(List<SelectItem> lasProvincias) {
		this.lasProvincias = lasProvincias;
	}
	
	public void listener_SomPais() {
		
		if(somPais.trim().equals("0")){
			lasProvincias=null;
		}
		
	}
}
