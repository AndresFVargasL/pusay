package com.vortexbird.pusay.cuestionarios.api.util;

/**
 * @author <a href="mailto:oscar_salazar@coomeva.com.co">Oscar Javier Salazar
 *         A</a>
 * @project encuesta-util
 * @class Propiedades
 * @date 5/01/2012
 **/
public class Propiedades {

	private static Propiedades instance;
	private static LoadBundle mensajes;
	private static LoadBundle mensajesGeneral;
	private static LoadBundle parametros;
	
	private Propiedades(){
		mensajes  = new LoadBundle("com.vortexbird.pusay.cuestionarios.api.mensajes.msj_validacion_es");
		mensajesGeneral  = new LoadBundle("com.vortexbird.pusay.cuestionarios.api.mensajes.msj_general_es");
		parametros = new LoadBundle("");
	}
	
	public static Propiedades getInstance(){
		if( instance == null ){
			instance = new Propiedades();
		}
		return instance;
	}
	
	public String getMensaje(String llave){
		return mensajes.getProperty(llave);
	}
	
	public String getMensajeGeneral(String key, String...campos) {
		try {
			String msg = mensajesGeneral.getProperty(key);
			if(campos!=null && campos.length>0){
			    for (int i=0; i<campos.length; i++) {
				String valorCampo = campos[i];
				msg = msg.replaceAll("\\{" + i +"\\}", valorCampo);
			    }
			}
			return msg;
		}
		catch (Exception e) {
			return key;
		}
	}
	
	public String getMensajeGeneral(String llave){
		return mensajesGeneral.getProperty(llave);
	}
	
	public String getMensaje(String key, String...campos) {
		try {
			String msg = mensajes.getProperty(key);
			if(campos!=null && campos.length>0){
			    for (int i=0; i<campos.length; i++) {
				String valorCampo = campos[i];
				msg = msg.replaceAll("\\{" + i +"\\}", valorCampo);
			    }
			}
			return msg;
		}
		catch (Exception e) {
			return key;
		}
	}
	
	public String getParametroS(String llave){
		return parametros.getProperty(llave);
	}
	
	public int getParametroI(String llave){
		int valor;
		try {
			valor = Integer.parseInt(parametros.getProperty(llave));
			return valor;
		} catch (Exception e) {
			valor = 0;
		}
		return valor;
	}
	
	public long getParametroL(String llave){
		long valor;
		 try{
			valor = Long.parseLong(parametros.getProperty(llave));
			return valor;
		} catch (Exception e) {
			valor = 0;
		}
		return valor;
	}
	
}
	
