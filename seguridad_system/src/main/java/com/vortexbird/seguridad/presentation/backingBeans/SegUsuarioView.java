package com.vortexbird.seguridad.presentation.backingBeans;

import org.apache.log4j.Logger;
import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegUsuarioDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

public class SegUsuarioView {
	private InputText txtUsuApellidos;
	private InputText txtUsuCodigoInterno;
	private Password txtUsuConstrasena;
	private InputText txtUsuCorreo;
	private SelectOneMenu txtUsuEstadoRegistro;
	private InputText txtUsuIntentosFallidos;
	private InputText txtUsuLogin;
	private InputText txtUsuNombres;
	private InputText txtUsuCodigo_SegUsuario;
	private InputText txtUsuCodigo;
	private Calendar txtUsuUltmimaModificacionPass;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<SegUsuarioDTO> data;
	private SegUsuarioDTO selectedSegUsuario;
	private SegUsuarioDTO usuarioSeleccionado;
	private List<String> rolesAsignados;
	private Logger logger = Logger.getLogger(SegUsuarioView.class);
	private SelectCheckboxMenu scmRoles;
	private Map<String, String> selectRoles;
	private boolean flagSeg=true;
	private String usuCod;
	
	
	 private SelectOneMenu somCompanias;
	 private SelectItem[] lasCompanias;


	public SegUsuarioView() {
		super();
	}

	@PostConstruct
	public void SegUsuarioViewPostConstruct() {
		try {
			if (flagSeg) {
				selectRoles = new HashMap<String, String>();
				List<SegRol> roles=new ArrayList<SegRol>();  
				List<SegRol> rolesConOpciones=new ArrayList<SegRol>();

				Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
				
				if (usuSession==0) {
					roles = BusinessDelegatorView.findAdministradores();
					for (int i = 0; i < roles.size(); i++) {
						rolesConOpciones.add(roles.get(i));
					}

				}else {
					String sistema = FacesUtils2.getSessionParameter("sistema").toString();
					roles=BusinessDelegatorView.getRolesBySystemModel(usuSession,sistema);

					Long codigoCompania = Long.parseLong(FacesUtils2.getSessionParameter("compania").toString());
					for (int i = 0; i < roles.size(); i++) {
						List<SegOpcion> opcionesDeUsuario = BusinessDelegatorView.consultarOpcionesPorUsuarioInPermisosSisCia(roles.get(i).getRolCodigo(),codigoCompania, usuSession);
						if (opcionesDeUsuario.size()>0) {
							rolesConOpciones.add(roles.get(i));
						}
					}
				}

				for (SegRol rol: rolesConOpciones) {		
//					selectRoles.put(rol.getRolCodigo().toString() + " - " + rol.getRolNombre() , rol.getRolCodigo().toString());
					selectRoles.put(rol.getRolNombre() , rol.getRolCodigo().toString());
				}		
				flagSeg=false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public String action_clear() {
		txtUsuApellidos.setValue(null);
		txtUsuCodigoInterno.setValue(null);
		txtUsuConstrasena.setValue(null);
		txtUsuEstadoRegistro.setValue("-1");
		somCompanias.setValue("-1");
		txtUsuLogin.setValue(null);
		txtUsuNombres.setValue(null);
		txtUsuCodigo.setValue(null);
		btnSave.setDisabled(false);
		btnModify.setDisabled(true);
		btnClear.setDisabled(false);
		txtUsuCorreo.setValue("");
		scmRoles.setValue(null);
		scmRoles.setSelectedValues(null);
		rolesAsignados = new ArrayList<String>();
		return "";
	}


	public void listener_login(){

		String sistema = FacesUtils2.getSessionParameter("sistema").toString();
		SegUsuario entity= null;


		try {
			String usuLogin = txtUsuLogin.getValue().toString();
			List<SegUsuario> usuarios = BusinessDelegatorView.findByCriteriaInSegUsuario(new Object[]{"usuLogin",true,usuLogin,"="},null, null);

			if (usuarios.size()==1) {

				entity = usuarios.get(0);

				if (entity!=null) {

					txtUsuCodigoInterno.setValue(entity.getUsuCodigoInterno());
					txtUsuNombres.setValue(entity.getUsuNombres());
					txtUsuApellidos.setValue(entity.getUsuApellidos());
					txtUsuConstrasena.setValue("");
					txtUsuEstadoRegistro.setValue(entity.getUsuEstadoRegistro());
					txtUsuCorreo.setValue(entity.getUsuCorreo());
					somCompanias.setValue(entity.getUsuCompaniaCiaCodigo());

					Object[] selectedValues = BusinessDelegatorView.getRolesSeleccionadosPreviamente(entity.getUsuCodigo().toString());
					rolesAsignados = new ArrayList<String>();

					for (int i = 0; i < selectedValues.length; i++) {
						rolesAsignados.add(selectedValues[i].toString());
					}

					btnSave.setDisabled(true);
					btnModify.setDisabled(false);
				}
			}
		}
		catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}


	public void listener_codigoInterno(){

		String sistema = FacesUtils2.getSessionParameter("sistema").toString();
		SegUsuario entity= null;

		try {
			String codigoInterno = txtUsuCodigoInterno.getValue().toString();
			List<SegUsuario> usuarios = BusinessDelegatorView.findByCriteriaInSegUsuario(new Object[]{"usuCodigoInterno",true,codigoInterno,"="},null, null);

			if (usuarios.size()==1) {

				entity = usuarios.get(0);

				if (entity!=null) {

					txtUsuLogin.setValue(entity.getUsuLogin());
					txtUsuNombres.setValue(entity.getUsuNombres());
					txtUsuApellidos.setValue(entity.getUsuApellidos());
					txtUsuConstrasena.setValue("");
					txtUsuEstadoRegistro.setValue(entity.getUsuEstadoRegistro());
					txtUsuCorreo.setValue(entity.getUsuCorreo());
					somCompanias.setValue(entity.getUsuCompaniaCiaCodigo());
					//Agregado para poder modificar
					txtUsuCodigo.setValue(entity.getUsuCodigo());

					Object[] selectedValues = BusinessDelegatorView.getRolesSeleccionadosPreviamente(entity.getUsuCodigo().toString());
					rolesAsignados = new ArrayList<String>();

					for (int i = 0; i < selectedValues.length; i++) {
						rolesAsignados.add(selectedValues[i].toString());
					}

					btnSave.setDisabled(true);
					btnModify.setDisabled(false);
				}
			}
		}
		catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String action_selected(){

		action_clear();

		FacesContext context = FacesContext.getCurrentInstance();  
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();

		String usuCodigo = (String)requestMap.get("usuCodigo");
		FacesUtils2.setSessionParameter("usu", usuCodigo);
		String sistema = FacesUtils2.getSessionParameter("sistema").toString();

		try {

			Object[] selectedValues = BusinessDelegatorView.getRolesSeleccionadosPreviamente(usuCodigo);
			rolesAsignados = new ArrayList<String>();

			for (int i = 0; i < selectedValues.length; i++) {
				rolesAsignados.add(selectedValues[i].toString());
			}

			SegUsuario entity = BusinessDelegatorView.getSegUsuario(Long.parseLong(usuCodigo));
			txtUsuApellidos.setValue(entity.getUsuApellidos());
			txtUsuCodigoInterno.setValue(entity.getUsuCodigoInterno());
			txtUsuConstrasena.setValue("");
			txtUsuEstadoRegistro.setValue(entity.getUsuEstadoRegistro());
			txtUsuLogin.setValue(entity.getUsuLogin());
			txtUsuNombres.setValue(entity.getUsuNombres());
			txtUsuCodigo.setValue(entity.getUsuCodigo());
			txtUsuCorreo.setValue(entity.getUsuCorreo());
			somCompanias.setValue(entity.getUsuCompaniaCiaCodigo().getCiaCodigo());
			btnSave.setDisabled(true);
			btnModify.setDisabled(false);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String action_save() {

		try {

			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			String sistema = FacesUtils2.getSessionParameter("sistema").toString();
			String compania = FacesUtils2.getSessionParameter("compania").toString();
			
			
			if (txtUsuCodigoInterno.getValue()==null||txtUsuCodigoInterno.getValue().toString().equals("")==true ) {
				throw new Exception("El Codigo Interno no puede estar vacio");
			}

			if (txtUsuLogin.getValue()==null||txtUsuLogin.getValue().toString().equals("")==true ) {
				throw new Exception("El Login no puede estar vacio");
			}

			if (txtUsuNombres.getValue()==null||txtUsuNombres.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre no puede estar vacio");
			}

			if (txtUsuApellidos.getValue()==null||txtUsuApellidos.getValue().toString().equals("")==true ) {
				throw new Exception("El Apellido no puede estar vacio");
			}

			if (txtUsuConstrasena.getValue()==null||txtUsuConstrasena.getValue().toString().equals("")==true ) {
				throw new Exception("El Password no puede estar vacio");
			}

			if (txtUsuEstadoRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtUsuCorreo.getValue()==null||txtUsuCorreo.getValue().toString().equals("")==true ) {
				throw new Exception("El correo no puede estar vacio");
			}

			if (Utilities.correElectronico(txtUsuCorreo.getValue().toString())==false){
				throw new Exception("El correo debe tener el formato correcto");
			}

			if (rolesAsignados==null || rolesAsignados.size()==0) {
				throw new Exception("Debe asignarle roles al usuario creado");
			}
			
			if (somCompanias.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar una compania valida");
			}

			String usuLogin = txtUsuLogin.getValue().toString();
			Long intentosFallidos = 0L;
			Date ultimaFechaModificacion = new Date();
			String usuApellidos = txtUsuApellidos.getValue().toString();
			String usuCodigoInterno = txtUsuCodigoInterno.getValue().toString();
			String usuContrasena = txtUsuConstrasena.getValue().toString();
			String usuCorreo = txtUsuCorreo.getValue().toString();
			String usuEstadoRegistro = txtUsuEstadoRegistro.getValue().toString();
			String usuNombres = txtUsuNombres.getValue().toString();
			Long usuCompania = Long.parseLong(somCompanias.getValue().toString());

			BusinessDelegatorView.saveSegUsuarioConRoles(usuApellidos,
					usuCodigoInterno,
					convertirMD5(usuContrasena),
					usuCorreo,
					usuEstadoRegistro,
					intentosFallidos,
					usuCompania,
					usuLogin.toUpperCase(),
					usuNombres,
					ultimaFechaModificacion,
					usuSession, rolesAsignados, 
					compania, sistema);
//			
//			BusinessDelegatorView.saveSegUsuario(usuApellidos, usuCodigoInterno, convertirMD5(usuContrasena), 
//					usuCorreo, usuEstadoRegistro, intentosFallidos, usuCompania, usuLogin, 
//					usuNombres, ultimaFechaModificacion, usuSession);

			if (usuSession == 0) {
				data = BusinessDelegatorView.getDataUsersAdmin();	
			}else {
				String sistemas = FacesUtils2.getSessionParameter("sistema").toString();
				String companias = FacesUtils2.getSessionParameter("compania").toString();
				data = BusinessDelegatorView.getUsuariosPorSistemaDTO(sistemas, companias);	
			}
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {

		try {
			
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			String sistema = FacesUtils2.getSessionParameter("sistema").toString();
			String compania = FacesUtils2.getSessionParameter("compania").toString();
			 

			if (txtUsuCodigoInterno.getValue()==null||txtUsuCodigoInterno.getValue().toString().equals("")==true ) {
				throw new Exception("El Codigo Interno no puede estar vacio");
			}

			if (txtUsuLogin.getValue()==null||txtUsuLogin.getValue().toString().equals("")==true ) {
				throw new Exception("El Login no puede estar vacio");
			}

			if (txtUsuNombres.getValue()==null||txtUsuNombres.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre no puede estar vacio");
			}

			if (txtUsuApellidos.getValue()==null||txtUsuApellidos.getValue().toString().equals("")==true ) {
				throw new Exception("El Apellido no puede estar vacio");
			}

			if (txtUsuEstadoRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtUsuCorreo.getValue()==null||txtUsuCorreo.getValue().toString().equals("")==true ) {
				throw new Exception("El correo no puede estar vacio");
			}

			if (Utilities.correElectronico(txtUsuCorreo.getValue().toString())==false){
				throw new Exception("El correo debe tener el formato correcto");
			}

			if (rolesAsignados==null || rolesAsignados.size()==0) {
				throw new Exception("Debe asignarle roles al usuario creado");
			}
			
			if (somCompanias.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar una compania valida");
			}

			String usuLogin = txtUsuLogin.getValue().toString();
			Long intentosFallidos = 0L;
			Date ultimaFechaModificacion = new Date();
			String usuApellidos = txtUsuApellidos.getValue().toString();
			String usuCodigoInterno = txtUsuCodigoInterno.getValue().toString();
			String usuContrasena = txtUsuConstrasena.getValue().toString();
			String usuCorreo = txtUsuCorreo.getValue().toString();
			String usuEstadoRegistro = txtUsuEstadoRegistro.getValue().toString();
			String usuNombres = txtUsuNombres.getValue().toString();
			Long usuCompania = Long.parseLong(somCompanias.getValue().toString());

			BusinessDelegatorView.updateSegUsuarioConRoles(usuApellidos,
					new Long(txtUsuCodigo.getValue().toString()),
					usuCodigoInterno,
					(usuContrasena.equals("") ? "" : convertirMD5(usuContrasena.toString())),
					usuCorreo,
					usuEstadoRegistro,
					intentosFallidos,
					usuCompania,
					usuLogin.toUpperCase(),
					usuNombres,
					ultimaFechaModificacion,
					usuSession, rolesAsignados,compania, sistema);
			
//			BusinessDelegatorView.updateSegUsuario(usuApellidos, new Long(txtUsuCodigo.getValue().toString()), 
//					usuCodigoInterno, (usuContrasena.equals("") ? "" : convertirMD5(usuContrasena.toString())), 
//					usuCorreo, usuEstadoRegistro, intentosFallidos, usuCompania, usuLogin, usuNombres, 
//					ultimaFechaModificacion, usuSession);

			if (usuSession == 0) {
				data = BusinessDelegatorView.getDataUsersAdmin();	
			}else {
				String sistemas = FacesUtils2.getSessionParameter("sistema").toString();
				String companias = FacesUtils2.getSessionParameter("compania").toString();
				data = BusinessDelegatorView.getUsuariosPorSistemaDTO(sistemas, companias);	
			}
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}


	public String convertirMD5(String password) throws NoSuchAlgorithmException {

		String hash=password;
		byte[] defaultBytes = password.getBytes();	        	
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		algorithm.reset();
		algorithm.update(defaultBytes);
		byte messageDigest[] = algorithm.digest();	        		            
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<messageDigest.length;i++) {
			int val = 0xff &  messageDigest[i];
			if (val < 16)
				hexString.append("0");
			hexString.append(Integer.toHexString(val));

			//hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
		}	        			        			
		hash=hexString+"";

		return hash;

	}


	public String action_modifyWitDTO(String usuApellidos, Long usuCodigo,
			String usuCodigoInterno, String usuConstrasena, String usuCorreo,
			String usuEstadoRegistro, Long usuIntentosFallidos, String usuLogin,
			String usuNombres, Date usuUltmimaModificacionPass,
			Long usuCodigo_SegUsuario) throws Exception {
		return "";
	}

	public InputText getTxtUsuApellidos() {
		return txtUsuApellidos;
	}

	public void setTxtUsuApellidos(InputText txtUsuApellidos) {
		this.txtUsuApellidos = txtUsuApellidos;
	}

	public InputText getTxtUsuCodigoInterno() {
		return txtUsuCodigoInterno;
	}

	public void setTxtUsuCodigoInterno(InputText txtUsuCodigoInterno) {
		this.txtUsuCodigoInterno = txtUsuCodigoInterno;
	}


	public Password getTxtUsuConstrasena() {
		return txtUsuConstrasena;
	}

	public void setTxtUsuConstrasena(Password txtUsuConstrasena) {
		this.txtUsuConstrasena = txtUsuConstrasena;
	}

	public InputText getTxtUsuCorreo() {
		return txtUsuCorreo;
	}

	public void setTxtUsuCorreo(InputText txtUsuCorreo) {
		this.txtUsuCorreo = txtUsuCorreo;
	}


	public InputText getTxtUsuIntentosFallidos() {
		return txtUsuIntentosFallidos;
	}

	public void setTxtUsuIntentosFallidos(InputText txtUsuIntentosFallidos) {
		this.txtUsuIntentosFallidos = txtUsuIntentosFallidos;
	}

	public InputText getTxtUsuLogin() {
		return txtUsuLogin;
	}

	public void setTxtUsuLogin(InputText txtUsuLogin) {
		this.txtUsuLogin = txtUsuLogin;
	}

	public InputText getTxtUsuNombres() {
		return txtUsuNombres;
	}

	public void setTxtUsuNombres(InputText txtUsuNombres) {
		this.txtUsuNombres = txtUsuNombres;
	}

	public InputText getTxtUsuCodigo_SegUsuario() {
		return txtUsuCodigo_SegUsuario;
	}

	public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
		this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
	}

	public Calendar getTxtUsuUltmimaModificacionPass() {
		return txtUsuUltmimaModificacionPass;
	}

	public void setTxtUsuUltmimaModificacionPass(
			Calendar txtUsuUltmimaModificacionPass) {
		this.txtUsuUltmimaModificacionPass = txtUsuUltmimaModificacionPass;
	}

	public InputText getTxtUsuCodigo() {
		return txtUsuCodigo;
	}

	public void setTxtUsuCodigo(InputText txtUsuCodigo) {
		this.txtUsuCodigo = txtUsuCodigo;
	}

	public List<SegUsuarioDTO> getData() {
		try {
			if (data == null) {

				Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

				if (usuSession == 0) {
//					data = BusinessDelegatorView.getDataSegUsuario();
					//Consultar TODOS los usuarios que empiecen con admin
					data = BusinessDelegatorView.getDataUsersAdmin();
				}else {
					String sistema = FacesUtils2.getSessionParameter("sistema").toString();
					String compania = FacesUtils2.getSessionParameter("compania").toString();
					data = BusinessDelegatorView.getUsuariosPorSistemaDTO(sistema, compania);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void setData(List<SegUsuarioDTO> segUsuarioDTO) {
		this.data = segUsuarioDTO;
	}

	public SegUsuarioDTO getSelectedSegUsuario() {
		return selectedSegUsuario;
	}

	public void setSelectedSegUsuario(SegUsuarioDTO segUsuario) {
		this.selectedSegUsuario = segUsuario;
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

	public SelectOneMenu getTxtUsuEstadoRegistro() {
		return txtUsuEstadoRegistro;
	}

	public void setTxtUsuEstadoRegistro(SelectOneMenu txtUsuEstadoRegistro) {
		this.txtUsuEstadoRegistro = txtUsuEstadoRegistro;
	}

	public SegUsuarioDTO getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(SegUsuarioDTO usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public List<String> getRolesAsignados() {
		return rolesAsignados;
	}

	public void setRolesAsignados(List<String> rolesAsignados) {
		this.rolesAsignados = rolesAsignados;
	}


	public Map<String, String> getSelectRoles() {
		return selectRoles;	
	}


	public void setSelectRoles(Map<String, String> selectRoles) {
		this.selectRoles = selectRoles;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public SelectCheckboxMenu getScmRoles() {
		return scmRoles;
	}

	public void setScmRoles(SelectCheckboxMenu scmRoles) {
		this.scmRoles = scmRoles;
	}

	public boolean isFlagSeg() {
		return flagSeg;
	}

	public void setFlagSeg(boolean flagSeg) {
		this.flagSeg = flagSeg;
	}

	public String getUsuCod() {
		return usuCod;
	}

	public void setUsuCod(String usuCod) {
		this.usuCod = usuCod;
	}
	
	


	public SelectItem[] getLasCompanias() {
		try {
			
			List<SegCompania> listCompania = new ArrayList<SegCompania>();
			listCompania=BusinessDelegatorView.getSegCompania();
			lasCompanias = new SelectItem[listCompania.size()];
			int i=0;
			for (SegCompania segCompania : listCompania) {
			lasCompanias[i] = new SelectItem(segCompania.getCiaCodigo(), segCompania.getCiaNombre());
			i++;
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		return lasCompanias;
	}

	public void setLasCompanias(SelectItem[] lasCompanias) {
		this.lasCompanias = lasCompanias;
	}

	public SelectOneMenu getSomCompanias() {
		return somCompanias;
	}

	public void setSomCompanias(SelectOneMenu somCompanias) {
		this.somCompanias = somCompanias;
	}

	
	
}
