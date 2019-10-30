package com.vortexbird.seguridad.presentation.backingBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;





import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.OpcionDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;


public class MenuBarBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private MenuModel model;
	private DefaultSubMenu subMenu;
	private boolean flag = true;
	private static Logger logger = Logger.getLogger(MenuBarBean.class);
	
	private static final String SUCURSAL = "";
	private static final String SISTEMA = "1";
	private static final String COMPANIA = "1";
	
	private String mensajeBienvenida = "Bienvenido ";
	

	public MenuBarBean() {

		if (flag) {
			System.out.println("############################# ENTRO AL CONSTRUCTOR #############################");
			model = new DefaultMenuModel();
			
			DefaultMenuItem menuItemSalir = new DefaultMenuItem();
			menuItemSalir.setValue("Salir");
			menuItemSalir.setIcon("ui-icon-close");
			menuItemSalir.setCommand("#{menuBar.salir_action}");
			model.addElement(menuItemSalir);
			
			DefaultMenuItem menuUrl = new DefaultMenuItem();
			menuUrl.setUrl("#");
			model.addElement(menuUrl);

			try {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				String login = session.getAttribute("loginSession") != null ? session.getAttribute("loginSession").toString() : null;
				Long usuSession = session.getAttribute("codigoLogin") != null ? Long.parseLong(session.getAttribute("codigoLogin").toString()) : null;

				if (login == null || login.equals("")) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/seguridad_system/");
				}
				mensajeBienvenida += login;
				
				List<GrupoDTO> grupos = BusinessDelegatorView.getOpciones(login, SISTEMA, SUCURSAL, COMPANIA);

				if (grupos != null && grupos.isEmpty() == false) {

					for (GrupoDTO grupo : grupos) {

						//Es SA_SEGURIDAD
						if (usuSession == 0) {

							subMenu = new DefaultSubMenu();
							subMenu.setLabel(grupo.getGru_nombre());
							subMenu.setIcon("ui-icon-help");

							List<OpcionDTO> opciones = grupo.getOpciones();

							for (OpcionDTO opcion : opciones) {

								//Es SA_SEGURIDAD
								if (usuSession == 0) {
									if (opcion.getOpc_codigo().equals("6") || opcion.getOpc_codigo().equals("11")) {
										System.out.println("La opcion: " + opcion.getOpc_nombre() + " no hace parte del menu de SA_SEGURIDAD");
									}else {
										DefaultMenuItem menuItem = new DefaultMenuItem();
										menuItem.setAjax(false);
										menuItem.setValue(opcion.getOpc_nombre());
										menuItem.setTitle(opcion.getOpc_nombre());
										menuItem.setUrl(opcion.getOpc_llave_acceso());
										subMenu.addElement(menuItem);
									}
								}else {
									if (opcion.getOpc_codigo().equals("3") || opcion.getOpc_codigo().equals("9") || opcion.getOpc_codigo().equals("6")) {
										System.out.println("La opcion: " + opcion.getOpc_nombre() +" no pertenecen al menu de ese sistema");
									}else {
										DefaultMenuItem menuItem = new DefaultMenuItem();
										menuItem.setAjax(false);
										menuItem.setValue(opcion.getOpc_nombre());
										menuItem.setTitle(opcion.getOpc_nombre());
										menuItem.setUrl(opcion.getOpc_llave_acceso());
										subMenu.addElement(menuItem);
									}
								}
							}
						}else {
							if (grupo.getGru_codigo().equals("6")) {
								System.out.println("El grupo: " + grupo.getGru_nombre() +" no pertenecen al menu de ese sistema");
							}else {

								subMenu = new DefaultSubMenu();
								subMenu.setLabel(grupo.getGru_nombre());
								subMenu.setIcon("ui-icon-help");

								List<OpcionDTO> opciones = grupo.getOpciones();

								for (OpcionDTO opcion : opciones) {
									if (opcion.getOpc_codigo().equals("3") || opcion.getOpc_codigo().equals("9") || opcion.getOpc_codigo().equals("6")) {
										System.out.println("La opcion: " + opcion.getOpc_nombre() +" no pertenecen al menu de ese sistema");
									}else {
										DefaultMenuItem menuItem = new DefaultMenuItem();
										menuItem.setAjax(false);
										menuItem.setValue(opcion.getOpc_nombre());
										menuItem.setTitle(opcion.getOpc_nombre());
										menuItem.setUrl(opcion.getOpc_llave_acceso());
										subMenu.addElement(menuItem);
									}
								}
							}
						}
						model.addElement(subMenu);
					}
				} else {
					System.out.println("Usted no tiene los permisos suficientes para ver esto");
				}						
				flag = false;
				System.out.println("############################# SALIO DEL CONSTRUCTOR #############################");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String salir_action(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		if (session!=null) {
			session.invalidate();	
		}
		return "/login2.xhtml?faces-redirect=true";
	}

	
	public DefaultSubMenu getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(DefaultSubMenu subMenu) {
		this.subMenu = subMenu;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		MenuBarBean.logger = logger;
	}

	public String getMensajeBienvenida() {
		return mensajeBienvenida;
	}

	public void setMensajeBienvenida(String mensajeBienvenida) {
		this.mensajeBienvenida = mensajeBienvenida;
	}
}