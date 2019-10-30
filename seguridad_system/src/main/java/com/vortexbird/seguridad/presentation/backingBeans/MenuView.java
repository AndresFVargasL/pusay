package com.vortexbird.seguridad.presentation.backingBeans;

import java.util.List;

import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.OpcionDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.FacesUtils;


public class MenuView {

	private Boolean permisoActivoConsultaCartera=false;
	private Boolean permisoActivoCapturaRecaudo= false;

	public MenuView() {
		super();
		try {
			String sucursal = FacesUtils.getManagedBean("sucursal").toString();
			String usuLogin = FacesUtils.getManagedBean("loginSession").toString();
			String sistema = FacesUtils.getManagedBean("sistema").toString();
			String cia = FacesUtils.getManagedBean("cia").toString();

			List<GrupoDTO> menus = BusinessDelegatorView.getOpciones(usuLogin, sistema, sucursal, cia);

			for (int i = 0; i < menus.size(); i++) {
				GrupoDTO grupoDTO = menus.get(i);
				List<OpcionDTO> opcionesDTO = grupoDTO.getOpciones();

				for (int k = 0; k < opcionesDTO.size(); k++) {
					OpcionDTO opDTO = opcionesDTO.get(k);
					String opcionNombre = opDTO.getOpc_nombre();
					System.out.println(opcionNombre);

					if (opcionNombre.equals("CONSULTA DE CARTERA")) {
						permisoActivoConsultaCartera = true;
					}
					
					else if (opcionNombre.equals("CAPTURA DE RECAUDOS")) {
						permisoActivoCapturaRecaudo = true;
					}
				}
			}
			System.out.println(permisoActivoCapturaRecaudo + "CAPTURA RECAUDO");
			System.out.println(permisoActivoConsultaCartera + "CONSULTA CARTERA");
			FacesUtils.setManagedBeanInSession("sucursal", sucursal);
			FacesUtils.setManagedBeanInSession("loginSession", usuLogin);
			FacesUtils.setManagedBeanInSession("sistema", sistema);
			FacesUtils.setManagedBeanInSession("cia", cia);
			
			System.out.println(sucursal + usuLogin + sistema + cia);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String action_consultaCartera(){
		return "goConsultarCart";
	}
	public String action_recaudo(){
		return "goCapRecaudo";
	}
	public String action_consultaRecaudos(){
		return "";
	}
	public String action_salir(){
		return "goLogin";
	}

	public Boolean getPermisoActivoConsultaCartera() {
		return permisoActivoConsultaCartera;
	}

	public void setPermisoActivoConsultaCartera(Boolean permisoActivoConsultaCartera) {
		this.permisoActivoConsultaCartera = permisoActivoConsultaCartera;
	}
	public Boolean getPermisoActivoCapturaRecaudo() {
		return permisoActivoCapturaRecaudo;
	}
	public void setPermisoActivoCapturaRecaudo(Boolean permisoActivoCapturaRecaudo) {
		this.permisoActivoCapturaRecaudo = permisoActivoCapturaRecaudo;
	}
	
}
