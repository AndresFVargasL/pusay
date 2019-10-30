package com.vortexbird.seguridad.presentation.backingBeans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.effect.Effect;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.vortexbird.seguridad.exceptions.ZMessManager;
import com.vortexbird.seguridad.modelo.RolUserObject;
import com.vortexbird.seguridad.modelo.SegCompania;
import com.vortexbird.seguridad.modelo.SegGrupoOpcion;
import com.vortexbird.seguridad.modelo.SegOpcion;
import com.vortexbird.seguridad.modelo.SegPermiso;
import com.vortexbird.seguridad.modelo.SegRol;
import com.vortexbird.seguridad.modelo.SegUsuario;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.FacesUtils;
import com.vortexbird.seguridad.utilities.FacesUtils2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.Serializable;

public class TreeController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7111347899816166831L;
	private RolUserObject selectedUserObject;
	protected Effect valueChangeEffect;
	private SelectItem[] losRoles;
	private SelectOneMenu somRoles;
	private SelectOneMenu somCompanias;
	private SelectItem[] lasCompanias;
	private CommandButton btnRol;
	private Boolean visible=false;
	private Boolean visibleCheck = true;

	private TreeNode rootNode;
	private TreeNode[] selectedNodes; 

	private CommandButton btnSave;
	private boolean flagSave = true;
	private CommandButton btnUpdate;
	private Logger logger = Logger.getLogger(TreeController.class);

	private SelectOneMenu somUsuarios;
	private SelectItem[] losUsuarios;

	public TreeController() {

	}

	@PostConstruct
	public void postConstruct(){
	}

	public String action_selectRol(){

		try {
			if (!somRoles.getValue().toString().equals("-2")==true) {
				flagSave = false;
				createTree();
			}else {
				throw new Exception("Seleccione un rol valido");
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return null;
	}

	public String action_clear() {
		somCompanias.setValue("-1");
		somRoles.setValue("-2");
		somUsuarios.setValue("-1");
		visible = false;
		btnUpdate.setDisabled(true);
		btnSave.setDisabled(false);
		rootNode = new DefaultTreeNode("Root", null);
		return "";
	}

	public void listener_rol(){

		List<SegCompania> listCompanias = new ArrayList<SegCompania>();
		SegCompania comp = null;
		somUsuarios.setValue("-1");
		losUsuarios = null;
		somCompanias.setValue("-1");
		lasCompanias = null;

		try {
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

			Long codigoRol = Long.parseLong(somRoles.getValue().toString());
			//Carga los usuarios cuando el rol seleccionado es admin_
			List<SegUsuario> usuarios = BusinessDelegatorView.getUsuarioPorRol(codigoRol);

			if (usuarios!=null && usuarios.size()>0) {
				losUsuarios=new SelectItem[usuarios.size()];
				int i=0;
				for (SegUsuario usu : usuarios) {
					losUsuarios[i]= new SelectItem(usu.getUsuCodigo(), usu.getUsuNombres()  + " - " + usu.getUsuLogin().toString());
					i++;
				}
			}

			if (usuSession==0) {
				listCompanias = BusinessDelegatorView.getCompaniasPorRol(codigoRol);
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
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void displaySelectedMultiple(ActionEvent event) {  
		if(selectedNodes != null && selectedNodes.length > 0) {  
			StringBuilder builder = new StringBuilder();  

			for(TreeNode node : selectedNodes) {  
				builder.append(node.getData().toString());  
				builder.append("<br />");  
			}  

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", builder.toString());  

			FacesContext.getCurrentInstance().addMessage(null, message);  
		}  
	}  


	protected TreeNode createTree(){

		rootNode = new DefaultTreeNode("Root", null);
		TreeNode segRolNode = null;
		flagSave = false;
		visible = true;

		List<SegOpcion> opcionesComparativas = new ArrayList<SegOpcion>();

		try {

			if (somCompanias.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar una compa�ia valida");
			}

			Long codigoRol = Long.parseLong(somRoles.getValue().toString());
			SegRol rol = BusinessDelegatorView.getSegRol(codigoRol);
			String rolNombre = rol.getRolNombre().toString().toUpperCase();
			boolean isAdmin = rolNombre.startsWith("ADMIN");

			if (isAdmin) {
				if (somUsuarios.getValue().toString().equals("-1")) {
					throw new Exception("Debe seleccionar un usuario");
				}
			}

			String codigoUsario= somUsuarios.getValue().toString(); 
			Long codigoCompania = Long.parseLong(somCompanias.getValue().toString());

			//lista que consulta las opciones de cada rol parametrizados en la tabla permisos
			List<SegOpcion> opcionesPorRol = BusinessDelegatorView.consultarOpcionesPorRolUnionUserInPermisos(codigoRol,codigoCompania, codigoUsario);

			for (int i = 0; i < opcionesPorRol.size(); i++) {
				System.out.println(opcionesPorRol.get(i).getOpcCodigo());
			}

			//Crear nodo principal (ROL)
			segRolNode = new DefaultTreeNode(rol.getRolNombre(), rootNode);

			//consultar las GrupoOpcion para ese rol
			Set<SegGrupoOpcion> setGrupoOpcion = rol.getSegSistema().getSegGrupoOpcions();
			List<SegGrupoOpcion> listGrupoOpcion = new ArrayList<SegGrupoOpcion>(setGrupoOpcion);

			//Agregar los nodos de las grupoOpcion del rol seleccionado
			if (listGrupoOpcion.size()>0) {
				for (int i = 0; i < listGrupoOpcion.size(); i++) {

					TreeNode segGrupoNode = new DefaultTreeNode(listGrupoOpcion.get(i).getGruNombre(),segRolNode);

					//consultar las opciones de cada grupoOpcion
					Set<SegOpcion> setOpciones = listGrupoOpcion.get(i).getSegOpcions();
					List<SegOpcion> listOpciones = new ArrayList<SegOpcion>(setOpciones);

					//Agregar los nodos de opciones al grupoOpcion correspondiente
					if (listOpciones.size()>0) {
						for (int j = 0; j < listOpciones.size(); j++) {
							TreeNode segOpcionsNode = new DefaultTreeNode(listOpciones.get(j).getOpcCodigo() + "-" + listOpciones.get(j).getOpcNombre(), segGrupoNode);

							//Almaceno en la lista los nodos (opciones) de cada grupoOpcion
							opcionesComparativas.add(listOpciones.get(j));	
							paintTreeCheks(rol, codigoCompania, opcionesPorRol, opcionesComparativas, segOpcionsNode, codigoUsario);
						}
					}
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}

		return segRolNode;
	}

	/*
	 * 		Metodo que compara cada opcion guardada en la BD con las q se muestra en el arbol y saber a cual poner chek true
	 * 
	 */
	public void paintTreeCheks(SegRol rol, Long codigoCompania, List<SegOpcion> opcionesPorRol,List<SegOpcion>opcionesComparativas, TreeNode segOpcionsNode, String codigoUsario){

		try {
			for (int k = 0; k < opcionesPorRol.size(); k++) {
				SegOpcion segOpcionRol = opcionesPorRol.get(k);

				for (int l = 0; l < opcionesComparativas.size(); l++) {
					SegOpcion segOpcionCompara = opcionesComparativas.get(l);

					if ((segOpcionRol.getOpcCodigo().toString().equals(segOpcionCompara.getOpcCodigo().toString())==true)) {

						Long codigoOpcion = segOpcionRol.getOpcCodigo();
						List<SegPermiso> permisosCambiarEstado = BusinessDelegatorView.getPermisosCriteria(rol.getRolCodigo(),codigoCompania,codigoOpcion, codigoUsario);

						if ((permisosCambiarEstado.get(0).getPerEstadoRegistro().equals("1")==true)){
							segOpcionsNode.setSelected(true);
							opcionesComparativas.remove(segOpcionCompara);
							opcionesPorRol.remove(segOpcionRol);
							btnSave.setDisabled(true);
							btnUpdate.setDisabled(false);
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
	}

	public String save_action(){

		TreeNode segRolNode = createTree();
		List<TreeNode> nodosSeleccion = Arrays.asList(selectedNodes);
		List<SegOpcion> listOpciones = new ArrayList<SegOpcion>();

		try {

			if (somRoles.getValue().toString().equals("-2")==true) {
				throw new Exception("Debe seleccionar un rol valido");
			}

			if (somCompanias.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar una compa�ia valida");
			}

			for (int j = 0; j < nodosSeleccion.size(); j++) {

				String [] arr = nodosSeleccion.get(j).getData().toString().split("-");

				if (arr.length>1) {

					List<SegOpcion> opcionUnica=BusinessDelegatorView.findByCriteriaInSegOpcion(new Object[]{"opcCodigo",false,arr[0].toString(),"="},null, null);

					if (opcionUnica.size()>0) {
						listOpciones.add(opcionUnica.get(0));
					}
				}
			}

			List<SegRol> listRoles=BusinessDelegatorView.findByCriteriaInSegRol(new Object[]{"rolNombre",true,segRolNode.getData().toString(),"="},null, null);
			Long rolCodigo = listRoles.get(0).getRolCodigo();
			Long sisCodigo = listRoles.get(0).getSegSistema().getSisCodigo();
			Long codigoCompania = Long.parseLong(somCompanias.getValue().toString());
			String codigoUsuario = somUsuarios.getValue().toString();  

			if (nodosSeleccion.size()>0) {
				BusinessDelegatorView.saveOpcionesPorRolInPermisos(rolCodigo, listOpciones, codigoCompania, sisCodigo, codigoUsuario);
				FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
				action_clear();
			}else {
				throw new Exception("No hay opciones validas seleccionadas");
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return "";
	}


	public String update_action(){

		TreeNode segRolNode = createTree();
		List<TreeNode> nodosSeleccion = Arrays.asList(selectedNodes);
		List<SegOpcion> listOpciones = new ArrayList<SegOpcion>();
		String codigoUsuario = somUsuarios.getValue().toString();  

		try {

			if (somRoles.getValue().toString().equals("-2")==true) {
				throw new Exception("Debe seleccionar un rol valido");
			}

			if (somCompanias.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar una compa�ia valida");
			}

			for (int j = 0; j < nodosSeleccion.size(); j++) {
				List<SegOpcion> opcionUnica= new ArrayList<SegOpcion>();

				String [] arr = nodosSeleccion.get(j).getData().toString().split("-");

				if (arr.length>1) {

					opcionUnica = BusinessDelegatorView.findByCriteriaInSegOpcion(new Object[]{"opcCodigo",false,arr[0].toString(),"="},null, null);

					if (opcionUnica.size()>0) {
						listOpciones.add(opcionUnica.get(0));}
				}
			}

			List<SegRol> listRoles=BusinessDelegatorView.findByCriteriaInSegRol(new Object[]{"rolNombre",true,segRolNode.getData().toString(),"="},null, null);
			Long rolCodigo = listRoles.get(0).getRolCodigo();
			Long codigoCompania = Long.parseLong(somCompanias.getValue().toString());
			Long sisCodigo = listRoles.get(0).getSegSistema().getSisCodigo();

			if (listOpciones.size()>0) {
				BusinessDelegatorView.updateOpcionesPorRolInPermisos(rolCodigo, listOpciones, codigoCompania,sisCodigo,codigoUsuario);
				FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
				action_clear();
			}else {
				throw new Exception("No hay opciones validas seleccionadas");
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return "";
	}

	public Effect getValueChangeEffect() {
		return valueChangeEffect;

	}

	public void setValueChangeEffect(Effect valueChangeEffect) {
		this.valueChangeEffect = valueChangeEffect;
	}

	public SelectItem[] getLosRoles() {
		try {
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			List<SegRol> listRoles = new ArrayList<SegRol>();

			if (usuSession==0) {
				listRoles=BusinessDelegatorView.findByCriteriaInSegRol(new Object[]{"rolEstadoRegistro",true,"1","="},null, null);	
			}else {
				//Trae todos los roles excepto el que se empiece con ADMIN
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				listRoles=BusinessDelegatorView.getRolesBySystemModelNoAdmin(sistema);
			}

			losRoles=new SelectItem[listRoles.size()];
			int i=0;
			for (SegRol segRol : listRoles) {
				losRoles[i]= new SelectItem(segRol.getRolCodigo(),segRol.getRolNombre().toString());
				i++;
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return losRoles;
	}

	public void setLosRoles(SelectItem[] losRoles) {
		this.losRoles = losRoles;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}


	public void setSelectedUserObject(RolUserObject selectedUserObject) {
		this.selectedUserObject = selectedUserObject;
	}

	public Boolean getVisibleCheck() {
		return visibleCheck;
	}

	public void setVisibleCheck(Boolean visibleCheck) {
		this.visibleCheck = visibleCheck;
	}

	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}

	public SelectItem[] getLasCompanias() {
		return lasCompanias;
	}

	public void setLasCompanias(SelectItem[] lasCompanias) {
		this.lasCompanias = lasCompanias;
	}

	public SelectOneMenu getSomRoles() {
		return somRoles;
	}

	public void setSomRoles(SelectOneMenu somRoles) {
		this.somRoles = somRoles;
	}

	public SelectOneMenu getSomCompanias() {
		return somCompanias;
	}

	public void setSomCompanias(SelectOneMenu somCompanias) {
		this.somCompanias = somCompanias;
	}

	public CommandButton getBtnRol() {
		return btnRol;
	}

	public void setBtnRol(CommandButton btnRol) {
		this.btnRol = btnRol;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(CommandButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public RolUserObject getSelectedUserObject() {
		return selectedUserObject;
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public boolean isFlagSave() {
		return flagSave;
	}

	public void setFlagSave(boolean flagSave) {
		this.flagSave = flagSave;
	}

	public SelectOneMenu getSomUsuarios() {
		return somUsuarios;
	}

	public void setSomUsuarios(SelectOneMenu somUsuarios) {
		this.somUsuarios = somUsuarios;
	}

	public SelectItem[] getLosUsuarios() {
		return losUsuarios;
	}

	public void setLosUsuarios(SelectItem[] losUsuarios) {
		this.losUsuarios = losUsuarios;
	}
}
