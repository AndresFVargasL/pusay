package com.vortexbird.seguridad.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;

public class EntityManagerFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(EntityManagerFilter.class);

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
	
		logger.info("Entro a el filtro");
		EntityManagerHelper.getEntityManager();
			filterChain.doFilter(servletRequest, servletResponse);
		EntityManagerHelper.closeEntityManager();
		logger.info("Salio del filtro");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
