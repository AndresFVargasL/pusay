package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.cuestionarios.model.CueOpcion;
import com.vortexbird.pusay.cuestionarios.model.CueRespuesta;
import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyMatrizEncuesta;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * PsyMatrizEncuesta entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyMatrizEncuesta
 */
@Scope("singleton")
@Repository("PsyMatrizEncuestaDAO")
public class PsyMatrizEncuestaDAO extends HibernateDaoImpl<PsyMatrizEncuesta, Long>
    implements IPsyMatrizEncuestaDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyMatrizEncuestaDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyMatrizEncuestaDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyMatrizEncuestaDAO) ctx.getBean("PsyMatrizEncuestaDAO");
    }
    
    @Override
	public List<CueOpcion> consultaRespuestasPorCuestionario(Long pestCodigo, Long codigoCuestionario) throws Exception {
		String hql2 = "SELECT opciones FROM CueOpcion opciones, CueCategoria cate, CuePregunta preg, CueRespuesta respuesta, CueListaContacto list "
				+ "WHERE list.pestCodigo="+pestCodigo+" and list.consecutivo=respuesta.cueListaContacto.consecutivo and respuesta.cueOpcion.consecutivo=opciones.consecutivo"
						+ " and opciones.cuePregunta.consecutivo=preg.consecutivo and preg.cueCategoria.consecutivo=cate.consecutivo and "
						+ "cate.cueCuestionario.consecutivo="+codigoCuestionario;
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<CueOpcion> lasRespuestas = query.list();
		return lasRespuestas;
    	
    }
}
