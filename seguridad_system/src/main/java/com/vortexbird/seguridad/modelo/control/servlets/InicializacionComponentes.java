package com.vortexbird.seguridad.modelo.control.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;


/**
 * @author Oscar Javier Salazar 
 * Coomeva - Unidad de Tecnolog�a Inform�tica
 * 
 * Clase encargada de manejar la inicializaci�n de los 
 * componentes de la aplicaci�n.
 */
public class InicializacionComponentes extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public InicializacionComponentes() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException 
	{
		System.out.println("##########################################################");
		System.out.println("         INICIALIZANDO COMPONENTES SIS_SEGURIDAD");
		System.out.println("##########################################################");
		System.out.println("         [INICIALIZANDO SESSION DE BD....]");
		
		try {
			EntityManagerHelper.getEntityManager();
		} catch (Exception e) {
			System.out.println("Error en la conexi�n de la Base de Datos");
		}
	}
	
}
