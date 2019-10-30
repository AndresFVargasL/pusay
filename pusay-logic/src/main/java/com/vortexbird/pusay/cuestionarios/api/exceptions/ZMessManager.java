package com.vortexbird.pusay.cuestionarios.api.exceptions;

import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public class ZMessManager extends Exception {
	private static final long serialVersionUID = 1L;

	
	
	public final static String ALL = Propiedades.getInstance().getMensajeGeneral("ALL");
	public final static String ENTCHILD = Propiedades.getInstance().getMensajeGeneral("ENTCHILD");
	public final static String FOREIGNDATA = Propiedades.getInstance().getMensajeGeneral("ENTCHILD");
	public static String ENTITY_SUCCESFULLYSAVED = Propiedades.getInstance().getMensajeGeneral("ENTITY_SUCCESFULLYSAVED");
	public static String ENTITY_SUCCESFULLYDELETED = Propiedades.getInstance().getMensajeGeneral("ENTITY_SUCCESFULLYDELETED");
	public static String ENTITY_SUCCESFULLYMODIFIED = Propiedades.getInstance().getMensajeGeneral("ENTITY_SUCCESFULLYMODIFIED");
	public static String ENTITY_WITHSAMEKEY = Propiedades.getInstance().getMensajeGeneral("ENTITY_WITHSAMEKEY");
	public static String ENTITY_NOENTITYTOUPDATE = Propiedades.getInstance().getMensajeGeneral("ENTITY_NOENTITYTOUPDATE");
	public static String CUESTIONARIO_SUCCESFULLYSAVED = Propiedades.getInstance().getMensajeGeneral("CUESTIONARIO_SUCCESFULLYSAVED");
	public static String CUESTIONARIO_NO_RESPUESTAS_ALMACENADO = Propiedades.getInstance().getMensajeGeneral("CUESTIONARIO_NO_RESPUESTAS_ALMACENADO");
	public static String CUESTIONARIO_DILIGENCIADO = Propiedades.getInstance().getMensajeGeneral("CUESTIONARIO_DILIGENCIADO");
	public static String CUESTIONARIO_TERMINADO = Propiedades.getInstance().getMensajeGeneral("CUESTIONARIO_TERMINADO");
	public static String CUESTIONARIO_INACTIVO = Propiedades.getInstance().getMensajeGeneral("CUESTIONARIO_INACTIVO");
	public static String CUESTIONARIO_NOVIGENTE = Propiedades.getInstance().getMensajeGeneral("CUESTIONARIO_NOVIGENTE");
	public static String NO_CONFIGURACION = Propiedades.getInstance().getMensajeGeneral("NO_CONFIGURACION");
	public static String NO_LISTA_ASIGANADA = Propiedades.getInstance().getMensajeGeneral("NO_LISTA");
	public static String YA_REQUIERE_AMPLIACION = Propiedades.getInstance().getMensajeGeneral("YA_REQUIERE_AMPLIACION");
	
	public ZMessManager() {
	}

	public ZMessManager(String exception) {
		super(exception);
	}

	public class NotValidFieldException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NotValidFieldException(String info) {
			super(Propiedades.getInstance().getMensajeGeneral("NOVALIDVALUE1") + info + Propiedades.getInstance().getMensajeGeneral("NOVALIDVALUE2"));
		}
	}

	public class EmptyFieldException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public EmptyFieldException(String info) {
			super(Propiedades.getInstance().getMensajeGeneral("NOVALIDVALUE3") + info
					+ Propiedades.getInstance().getMensajeGeneral("NOVALIDVALUE4"));
		}
	}

	
	
	public class NotValidFormatException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NotValidFormatException(String info) {
			super(Propiedades.getInstance().getMensajeGeneral("NOVALIDFORMAT1") + info
					+ Propiedades.getInstance().getMensajeGeneral("NOVALIDFORMAT2"));
		}
	}

	public class DeletingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public DeletingException(String info) {
			super(Propiedades.getInstance().getMensajeGeneral("ENTITYRELATED") + info+"\"");
		}
	}
	
	public class ForeignException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public ForeignException(String info) {
			super(Propiedades.getInstance().getMensajeGeneral("NODATARELATED") + info+ "\"");
		}
	}	

	public class GettingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public GettingException(String info) {
			super(Propiedades.getInstance().getMensajeGeneral("ERRORGETTING") + info);
		}
	}

	public class FindingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public FindingException(String info) {
			super(Propiedades.getInstance().getMensajeGeneral("ERRORGETTING") + info);
		}
	}

}
