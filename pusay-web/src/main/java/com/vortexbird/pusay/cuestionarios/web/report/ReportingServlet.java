package com.vortexbird.pusay.cuestionarios.web.report;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vortexbird.pusay.cuestionarios.web.utils.StringUtils;

/**
 * Servlet que permite exportar documentos.
 * 
 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
 * @project cuestionario-web
 * @class ReportingServlet
 * @date Aug 4, 2013
 * 
 */
public class ReportingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final String XLS = "xls";

	private static final String PDF = "pdf";

	private static final long serialVersionUID = 7585940379727489280L;

	private static Logger logger = LoggerFactory.getLogger(ReportingServlet.class);

	private final Map<String, String> contents = new HashMap<String, String>();

	private DataSource dataSource;

	protected WebApplicationContext ctx;

	public ReportingServlet() {
		contents.put("html", "text/html");
		contents.put("csv", "text/plain");
		contents.put(PDF, "application/octet-stream");
		contents.put(XLS, "application/vnd.ms-excel");
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		if (ctx.containsBean("dataSource")) {
			dataSource = (DataSource) ctx.getBean("dataSource");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		internalDoService(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		internalDoService(request, response);
	}

	@SuppressWarnings("rawtypes")
	protected void internalDoService(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Map<String, Object> parameters = new HashMap<String, Object>();

		String reportName = request.getParameter("reportName");
		String useDatasource = request.getParameter("useDataSource");
		String format = request.getParameter("format");
		if (format == null || format.length() == 0) {
			format = PDF;
		}

		if (getContentDisposition(format) == null) {
			throw new ServletException("Formato no soportado. Posibles formatos son: pdf, xls, csv y html");
		}

		// locale por defecto. Esta parametro se puede cambiar por el request especificando el parametro REPORT_LOCALE
		parameters.put(JRParameter.REPORT_LOCALE, StringUtils.getLocale("es"));
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();

			String[] value = request.getParameterValues(name);

			if (JRParameter.REPORT_LOCALE.equals(name)) {
				parameters.put(JRParameter.REPORT_LOCALE, StringUtils.getLocale(value[0]));
			} else {
				parameters.put(name, value.length > 1 ? Arrays.asList(value) : value[0]);
			}
		}

		// como utilizar hibernate en reportes.
		// parameters.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, session);

		InputStream is = null;
		Connection conn = null;
		try {

			is = getServletContext().getResourceAsStream("/WEB-INF/classes/reportes/" + reportName + ".jasper");

			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
			JasperPrint jasperPrint;

			// si el parametro useDataSource es false entonces se crea un datasource vacion de jasper.
			if ("false".equals(useDatasource)) {
				JRDataSource ds = new JREmptyDataSource();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
			} else {
				conn = dataSource.getConnection();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
			}

			response.setContentType(getContentDisposition(format));
			response.setHeader("Content-Disposition", "attachment; filename=" + reportName + "." + format);
			response.setHeader("ContentLength", "999999");// cualquier tamanio
			response.setHeader("Cache-Control", "must-revalidate,post-check=0,pre-check=0");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");

			if (PDF.equals(format)) {
				JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			} else if (XLS.equals(format)) {
				JExcelApiExporter xlsExporter = new JExcelApiExporter();
				xlsExporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, jasperPrint);
				xlsExporter.setParameter(JExcelApiExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				xlsExporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				xlsExporter.exportReport();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	private String getContentDisposition(String contentType) {
		return contents.get(contentType);
	}
}