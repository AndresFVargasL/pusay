package com.vortexbird.pusay.presentation.backingBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.pusay.modelo.dto.PsyPriorizacionImpactosAmbientalesDTO;
import com.vortexbird.pusay.presentation.businessDelegate.IBusinessDelegatorView;


@ManagedBean
@ViewScoped
public class PsyPriorizacionImpactosAmbientalesView {
	
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyAsuntoAmbientalView.class);
    List<PsyPriorizacionImpactosAmbientalesDTO> data;
    
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	 private IBusinessDelegatorView businessDelegatorView;
	
	
	
	public void PriorizacionImpactosAmbientales(){
		data=null;
	}

	public List<PsyPriorizacionImpactosAmbientalesDTO> getData() {
		try {
			if(data==null){
				data = businessDelegatorView.PriorizacionImpactosAmbientales(1L);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void setData(List<PsyPriorizacionImpactosAmbientalesDTO> data) {
		this.data = data;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}
	 
	
	 
	 
}
