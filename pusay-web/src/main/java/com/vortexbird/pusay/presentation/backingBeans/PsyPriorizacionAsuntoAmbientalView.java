package com.vortexbird.pusay.presentation.backingBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.pusay.modelo.dto.PsyPriorizacionAsuntoAmbientalDTO;
import com.vortexbird.pusay.presentation.businessDelegate.IBusinessDelegatorView;

@ManagedBean
@ViewScoped
public class PsyPriorizacionAsuntoAmbientalView {
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyAsuntoAmbientalView.class);
    List<PsyPriorizacionAsuntoAmbientalDTO> data;
	 @ManagedProperty(value = "#{BusinessDelegatorView}")
	    private IBusinessDelegatorView businessDelegatorView;
	
	 
	 
	 public void PriorizacionAsuntoAmbiental(){
		
			 data=null;
		
	 }
	 
	public List<PsyPriorizacionAsuntoAmbientalDTO> getData() {
		try {
			if(data==null){
				data = businessDelegatorView.PriorizacionAsuntoAmbiental(1L);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public void setData(List<PsyPriorizacionAsuntoAmbientalDTO> data) {
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
