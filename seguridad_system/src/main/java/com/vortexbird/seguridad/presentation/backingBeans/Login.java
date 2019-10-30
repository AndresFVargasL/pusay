package com.vortexbird.seguridad.presentation.backingBeans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import com.vortexbird.seguridad.modelo.SegCompania;
import com.vortexbird.seguridad.modelo.SegSistema;
import com.vortexbird.seguridad.modelo.SegUsuario;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.FacesUtils;
import com.vortexbird.seguridad.utilities.FacesUtils2;

public class Login {
	
	
	
	private InputText login;
	private Password password;
	private boolean usuarioAutenticado;
	private InputText id;
	private CommandButton enter;
	private InputText usuCodigo;
	private Password passNuevo;
	private Password confirmarPassNuevo;
	private String message="";
	private boolean visiblePopUpError;
	private Logger logger = Logger.getLogger(Login.class);
	private SelectOneMenu somSistemas;
	private List<SelectItem> listSistemas;
	private String selectedSistema;
	private SelectOneMenu somCompanias;
	private SelectItem[] listCompanias;
	private UsuarioDTO usuarioDTO;

	private CommandButton btnaceptar;
	HtmlOutputText otMensaje=new HtmlOutputText();
	HtmlOutputLabel olMensaje=new HtmlOutputLabel();
	private MenuModel model;
	
	public String cerrarAviso(){
		visiblePopUpError = false;
		return "";
	}

	public String action_clean(){
		if (login != null) login.setValue("");
		if (password != null) password.setValue("");
		if (somSistemas != null) somSistemas.setValue("-1");
		if (somCompanias != null) somCompanias.setValue("-1");
		if (listCompanias != null) listCompanias = null;
		if (listSistemas != null) listSistemas = null;
		return "";
	}


	public String action_enter(){
		try {
			
			if(login.getValue().toString().trim().equals("") || password.getValue().toString().trim().equals("")) {
				throw new Exception("Ingrese login y password.");
			}
			
			usuarioAutenticado = false;
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			usuarioDTO = BusinessDelegatorView.autenticar(login.getValue().toString(), password.getValue().toString());

			if (usuarioDTO==null){
				throw new Exception("No existe el usuario. Intente de nuevo");
			}else{
				System.out.println("#####################################resLDAPE###Seguridad_System"+" "+usuarioDTO.getEstado());
				//TODO Preguntar cuales son los estados
				if (!usuarioDTO.getEstado().equals("1") && !usuarioDTO.getEstado().equals("2")){
					throw new Exception("No existe el usuario, la clave es incorrecta o no tiene acceso a las aplicaciones.");
				}else{
					usuarioAutenticado = true;

					session.setAttribute("loginSession", usuarioDTO.getUsu_login());
					session.setAttribute("cia", "");
					session.setAttribute("usuActivo", usuarioDTO.getUsu_activo());
					session.setAttribute("sucursal", "");
					session.setAttribute("codigoLogin", usuarioDTO.getUsu_codigo());

					FacesUtils.setManagedBeanInSession("loginSession", usuarioDTO.getUsu_login());
					FacesUtils.setManagedBeanInSession("cia", "");
					FacesUtils.setManagedBeanInSession("codigoLogin", usuarioDTO.getUsu_codigo());
					session.setAttribute("usuActivo", usuarioDTO.getUsu_activo());
					session.setAttribute("sucursal", "");
					System.out.println(usuarioDTO.getUsu_codigo().toString());

					Long usuSession = Long.parseLong(usuarioDTO.getUsu_codigo().toString());
					List<SegSistema> sistemas = BusinessDelegatorView.getSistemasDeUsuarioPorRol(usuSession);

					if (sistemas!=null && sistemas.size()>0) {
						listSistemas = new ArrayList<SelectItem>();

						for (int i = 0; i < sistemas.size(); i++) {
							SegSistema sis = sistemas.get(i);
							Long codigoSistema = sis.getSisCodigo();
							String nombreSistema = sis.getSisNombre();

							SelectItem si = new SelectItem(codigoSistema, nombreSistema);
							listSistemas.add(si);
						}
					}else {
						throw new Exception("El usuario "+ usuarioDTO.getUsu_login() +" no es administrador de ningun sistema");
					}

					if (listSistemas!=null && listSistemas.size()>0){
						return action_seleccionarSistema();
					}
				}
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}


	public String action_seleccionarSistema(){
		try {
			if (listSistemas != null && listSistemas.size() > 0){
				//Si solo hay un sistema, se escoge
				if (listSistemas.size()==1 && (listCompanias!=null && listCompanias.length==1)) {
					selectedSistema = listSistemas.get(0).getValue().toString();
					FacesUtils.setManagedBeanInSession("sistema", selectedSistema);
					FacesUtils2.setSessionParameter("sistema", selectedSistema);
					FacesUtils.setManagedBeanInSession("compania", "1");
					FacesUtils2.setSessionParameter("compania", "1");
					return "goInitialMenu";
				}else{
					return "goSeleccionarSisYCias"; 
				}
			}
			return "";

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return "";
		}
	}


	public String irMenu(){

		try {

			if (somSistemas.getValue()==null || somSistemas.getValue().toString().equals("-1")) {
				throw new Exception("Debe seleccionar un sistema");
			}

			if (somCompanias.getValue()==null || somCompanias.getValue().toString().equals("-1")) {
				throw new Exception("Debe seleccionar una compa�ia");
			}

			String sistemaSeleccionado = somSistemas.getValue() != null ? somSistemas.getValue().toString():null;
			String companiaSeleccionada = somCompanias.getValue() != null ? somCompanias.getValue().toString():null;
			FacesUtils.setManagedBeanInSession("sistema", sistemaSeleccionado);
			FacesUtils2.setSessionParameter("sistema", sistemaSeleccionado);
			FacesUtils.setManagedBeanInSession("compania", companiaSeleccionada);
			FacesUtils2.setSessionParameter("compania", companiaSeleccionada);
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return "";
		}
		action_clean();
		return "goInitialMenu";
	}

	public String action_cambiar_pass() {

		try{
		if(passNuevo.getValue().toString().equals("")||confirmarPassNuevo.getValue().toString().equals("")) {
			message = "La contrase�a no puede estar vac�a";
			otMensaje.setValue(message);
			visiblePopUpError = true;
		}
		else if(passNuevo.getValue().toString().equals(confirmarPassNuevo.getValue().toString())==false) {
			message = "Las contrase�as no coinciden";
			otMensaje.setValue(message);
			visiblePopUpError = true;
		}else{
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			int usuCodigo = Integer.parseInt(session.getAttribute("usuCodigo").toString());
			logger.info("El valor del usucodigo llega con: " + usuCodigo);
			String pass = convertirMD5(passNuevo.getValue().toString());

			SegUsuario u = BusinessDelegatorView.getSegUsuario(new Long(usuCodigo));
			u.setUsuConstrasena(passNuevo.getValue().toString());
			BusinessDelegatorView.updateSegUsuario(u.getUsuApellidos(), u.getUsuCodigo(), u.getUsuCodigoInterno(), pass,
					u.getUsuCorreo(), u.getUsuEstadoRegistro(), u.getUsuIntentosFallidos(), u.getUsuCompaniaCiaCodigo().getCiaCodigo(), u.getUsuLogin(), u.getUsuNombres(), new Date(), u.getSegUsuario().getUsuCodigo());
			FacesUtils.addInfoMessage("El password se modifico satisfactoriamente");
		}
		}catch(Exception ex){
			ex.printStackTrace();
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
		}	        			        			
		hash=hexString+"";

		return hash;

	}

	public void listener_Sistema(){
		try {
			Long usuSession = Long.parseLong(usuarioDTO.getUsu_codigo().toString());
			listCompanias = null;
			somCompanias.setValue(-1);

			List<SegCompania> companias = BusinessDelegatorView.getCompaniasDeUsuarioPorRol(usuSession, Long.parseLong(somSistemas.getValue().toString()));

			if (companias!=null && companias.size()>0) {
				listCompanias=new SelectItem[companias.size()];
				int i=0;
				for (SegCompania segCompania : companias) {
					listCompanias[i]= new SelectItem(segCompania.getCiaCodigo(),segCompania.getCiaNombre().toString());
					i++;
				}
			}else {
				throw new Exception("El sistema no tiene compa�ias parametrizadas");
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public boolean isUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(boolean usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isVisiblePopUpError() {
		return visiblePopUpError;
	}

	public void setVisiblePopUpError(boolean visiblePopUpError) {
		this.visiblePopUpError = visiblePopUpError;
	}

	public InputText getLogin() {
		return login;
	}

	public void setLogin(InputText login) {
		this.login = login;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public InputText getId() {
		return id;
	}

	public void setId(InputText id) {
		this.id = id;
	}

	public CommandButton getEnter() {
		return enter;
	}

	public void setEnter(CommandButton enter) {
		this.enter = enter;
	}

	public InputText getUsuCodigo() {
		return usuCodigo;
	}

	public void setUsuCodigo(InputText usuCodigo) {
		this.usuCodigo = usuCodigo;
	}

	public Password getPassNuevo() {
		return passNuevo;
	}

	public void setPassNuevo(Password passNuevo) {
		this.passNuevo = passNuevo;
	}

	public Password getConfirmarPassNuevo() {
		return confirmarPassNuevo;
	}

	public void setConfirmarPassNuevo(Password confirmarPassNuevo) {
		this.confirmarPassNuevo = confirmarPassNuevo;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public SelectOneMenu getSomSistemas() {
		return somSistemas;
	}

	public void setSomSistemas(SelectOneMenu somSistemas) {
		this.somSistemas = somSistemas;
	}

	public List<SelectItem> getListSistemas() {
		return listSistemas;
	}

	public void setListSistemas(List<SelectItem> listSistemas) {
		this.listSistemas = listSistemas;
	}

	public String getSelectedSistema() {
		return selectedSistema;
	}

	public void setSelectedSistema(String selectedSistema) {
		this.selectedSistema = selectedSistema;
	}

	public SelectOneMenu getSomCompanias() {
		return somCompanias;
	}

	public void setSomCompanias(SelectOneMenu somCompanias) {
		this.somCompanias = somCompanias;
	}

	public SelectItem[] getListCompanias() {
		return listCompanias;
	}

	public void setListCompanias(SelectItem[] listCompanias) {
		this.listCompanias = listCompanias;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	
	public String salir_action(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		if (session!=null) {
			session.invalidate();	
		}
		return "/login2.xhtml?faces-redirect=true";
	}

	public MenuModel getModel() {
		
		if(model==null){
			model = new DefaultMenuModel();
			
			DefaultMenuItem menuItemSalir = new DefaultMenuItem();
			menuItemSalir.setValue("Salir");
			menuItemSalir.setIcon("ui-icon-close");
			menuItemSalir.setCommand("#{login.salir_action}");
			model.addElement(menuItemSalir);
			
		
			DefaultMenuItem menuUrl = new DefaultMenuItem();
			menuUrl.setUrl("#");
			model.addElement(menuUrl);
		}

		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	

	public CommandButton getBtnaceptar() {
		return btnaceptar;
	}

	public void setBtnaceptar(CommandButton btnaceptar) {
		this.btnaceptar = btnaceptar;
	}

	public HtmlOutputText getOtMensaje() {
		return otMensaje;
	}

	public void setOtMensaje(HtmlOutputText otMensaje) {
		this.otMensaje = otMensaje;
	}

	public HtmlOutputLabel getOlMensaje() {
		return olMensaje;
	}

	public void setOlMensaje(HtmlOutputLabel olMensaje) {
		this.olMensaje = olMensaje;
	}

	
	
}