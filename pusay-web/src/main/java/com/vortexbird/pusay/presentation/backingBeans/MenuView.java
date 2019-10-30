package com.vortexbird.pusay.presentation.backingBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.pusay.presentation.utilities.FacesUtils;
import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.OpcionDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;
import com.vortexbird.seguridad.rest.LoginRestServiceClient;



/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class MenuView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String nombreRolUsuarioConsulta = "USUARIO CONSULTA";
	private static final Logger log = LoggerFactory.getLogger(MenuView.class);
	private String nombrePersona;
	private String empresaPersona;
	private MenuModel model;
	private String esDisableUsuarioConsulta="block";
	
	
	
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public MenuView() {
		super();

	}

	@PostConstruct
	public void infoPersonaTopBar(){
		UsuarioDTO usuarioDTO = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTO");
		if(usuarioDTO!=null){
			esDisableUsuarioConsulta = (usuarioDTO.getNombre_rol().trim().equals(nombreRolUsuarioConsulta)) ? "none" : "block";
		}
		PsyEmpresa empresa = (PsyEmpresa) FacesUtils.getfromSession("empresa");
		//Se asigna el nombre de la persona y la empresa para la que actualmente esta registrada en el TOP BAR
		setNombrePersona(usuarioDTO.getUsu_nombres());
		setEmpresaPersona(empresa.getNombre());
		//Se traen las opciones por el rol del usuario.
		List<GrupoDTO> listaOpciones=null;
		try {
			listaOpciones = LoginRestServiceClient.getOpcionesPorRol(usuarioDTO.getUsu_login(),businessDelegatorView.getPsyParametro("urlServiciosRest").getValor());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Se declara el modelo que contendra todas las opciones
		model = new DefaultMenuModel();

		DefaultMenuItem dashboard = new DefaultMenuItem();
		dashboard.setId("sm_dashboard");
		dashboard.setValue("Inicio");
		dashboard.setIcon("icon-home-outline");
		dashboard.setOutcome("dashboard");
		dashboard.setContainerStyleClass("layout-menubar-active");
		model.addElement(dashboard);
		
		//se itera cada grupo de la lista de opciones
		for (GrupoDTO grupoDTO : listaOpciones) {
			DefaultSubMenu subMenu = new DefaultSubMenu();
			subMenu.setId(grupoDTO.getGru_codigo());
			subMenu.setLabel(grupoDTO.getGru_descripcion());
			subMenu.setIcon(grupoDTO.getGru_icono());
			//se itera cada una de las opciones por grupo
			if(grupoDTO.getOpciones().size() > 0){
				for (OpcionDTO opcion : grupoDTO.getOpciones()) {
					//para cada opcion, se crea un item y se agrega al submenu
					DefaultMenuItem item = new DefaultMenuItem();
					item.setId(opcion.getOpc_codigo());
					item.setValue(opcion.getOpc_nombre());
					//Se valida si la llave acceso es un comando o una url
					if(opcion.getOpc_llave_acceso().startsWith("#")){
						item.setCommand(opcion.getOpc_llave_acceso());
					}else{
						item.setUrl(opcion.getOpc_llave_acceso());
					}
					subMenu.addElement(item);

				}
			}
			//se valida si se encuentra presente el grupo de configuracion para añadir el modulo de encuestas
			if(grupoDTO.getGru_codigo().trim().equals("15")){

				DefaultSubMenu subMenuEncuestas = new DefaultSubMenu();
				subMenuEncuestas.setId("smEncuestas");
				subMenuEncuestas.setLabel("Encuestas");
				subMenuEncuestas.setIcon("icon-docs");

				DefaultMenuItem itemRespnsable = new DefaultMenuItem();
				itemRespnsable.setId("miResponsable");
				itemRespnsable.setValue("Responsables");
				itemRespnsable.setIcon("icon-user-2");
				itemRespnsable.setUrl("/gui/forms/admin/responsable/responsable.xhtml");
				subMenuEncuestas.addElement(itemRespnsable);

				DefaultSubMenu subMenuListas = new DefaultSubMenu();
				subMenuListas.setId("smListas");
				subMenuListas.setLabel("Listas");
				subMenuListas.setIcon("icon-list-bullet");

				DefaultMenuItem itemCrearLista = new DefaultMenuItem();
				itemCrearLista.setId("miCrearLista");
				itemCrearLista.setValue("Crear Lista");
				itemCrearLista.setUrl("/gui/forms/admin/lista/lista.xhtml");
				subMenuListas.addElement(itemCrearLista);

				DefaultMenuItem itemCrearContacto = new DefaultMenuItem();
				itemCrearContacto.setId("miCrearContacto");
				itemCrearContacto.setValue("Crear Contacto");
				itemCrearContacto.setUrl("/gui/forms/admin/lista/contacto.xhtml");
				subMenuListas.addElement(itemCrearContacto);

				subMenuEncuestas.addElement(subMenuListas);

				DefaultSubMenu subMenuAdminCuestionarios = new DefaultSubMenu();
				subMenuAdminCuestionarios.setId("smAdminCuestionarios");
				subMenuAdminCuestionarios.setLabel("Admin Cuestionarios");
				subMenuAdminCuestionarios.setIcon("icon-list-alt");

				DefaultMenuItem itemTiposCuestionario = new DefaultMenuItem();
				itemTiposCuestionario.setId("miTiposDeCuestionario");
				itemTiposCuestionario.setValue("Tipos de Cuestionario");
				itemTiposCuestionario.setUrl("/gui/forms/admin/cuestionario/cuestionarioTipo.xhtml");
				subMenuAdminCuestionarios.addElement(itemTiposCuestionario);

				DefaultMenuItem itemEstadosCuestionario = new DefaultMenuItem();
				itemEstadosCuestionario.setId("miEstadosCuestionario");
				itemEstadosCuestionario.setValue("Estados Cuestionario");
				itemEstadosCuestionario.setUrl("/gui/forms/admin/cuestionario/estado.xhtml");
				subMenuAdminCuestionarios.addElement(itemEstadosCuestionario);

				DefaultMenuItem itemConfiguracion = new DefaultMenuItem();
				itemConfiguracion.setId("miConfiguracion");
				itemConfiguracion.setValue("Configuracion");
				itemConfiguracion.setUrl("/gui/forms/admin/cuestionario/configuracion.xhtml");
				subMenuAdminCuestionarios.addElement(itemConfiguracion);

				DefaultMenuItem itemCuestionario = new DefaultMenuItem();
				itemCuestionario.setId("miCuestionario");
				itemCuestionario.setValue("Cuestionario");
				itemCuestionario.setUrl("/gui/forms/admin/cuestionario/cuestionario.xhtml");
				subMenuAdminCuestionarios.addElement(itemCuestionario);

				DefaultMenuItem itemCategorias = new DefaultMenuItem();
				itemCategorias.setId("miCategorias");
				itemCategorias.setValue("Categorias");
				itemCategorias.setUrl("/gui/forms/admin/cuestionario/categoria.xhtml");
				subMenuAdminCuestionarios.addElement(itemCategorias);

				DefaultMenuItem itemPreguntas = new DefaultMenuItem();
				itemPreguntas.setId("miPreguntas");
				itemPreguntas.setValue("Preguntas");
				itemPreguntas.setUrl("/gui/forms/admin/cuestionario/pregunta.xhtml");
				subMenuAdminCuestionarios.addElement(itemPreguntas);

				subMenuEncuestas.addElement(subMenuAdminCuestionarios);

				DefaultSubMenu subMenuReportes = new DefaultSubMenu();
				subMenuReportes.setId("smReportes");
				subMenuReportes.setLabel("Reportes");
				subMenuReportes.setIcon("icon-edit-1");

				DefaultMenuItem itemReporteGeneral = new DefaultMenuItem();
				itemReporteGeneral.setId("miReporteGeneral");
				itemReporteGeneral.setValue("Reporte General");
				itemReporteGeneral.setUrl("/gui/forms/front/reportes/consultarReportes.xhtml");
				subMenuReportes.addElement(itemReporteGeneral);

				subMenuEncuestas.addElement(subMenuReportes);

				//Item temporal para asignar iconos a los submenu y menu item
				DefaultMenuItem sm_fonts = new DefaultMenuItem();
				sm_fonts.setId("sm_fonts");
				sm_fonts.setValue("Fonts");
				sm_fonts.setIcon("icon-book");
				sm_fonts.setOutcome("font-icons.xhtml");
				subMenuEncuestas.addElement(sm_fonts);	

				//Finalmente se adiciona al submenu de Configuracion, el submenu de encuestas
				subMenu.addElement(subMenuEncuestas);

			}
			model.addElement(subMenu);
		}
		
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getEmpresaPersona() {
		return empresaPersona;
	}

	public void setEmpresaPersona(String empresaPersona) {
		this.empresaPersona = empresaPersona;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public String getEsDisableUsuarioConsulta() {
		return esDisableUsuarioConsulta;
	}

	public void setEsDisableUsuarioConsulta(String esDisableUsuarioConsulta) {
		this.esDisableUsuarioConsulta = esDisableUsuarioConsulta;
	}

	



}
