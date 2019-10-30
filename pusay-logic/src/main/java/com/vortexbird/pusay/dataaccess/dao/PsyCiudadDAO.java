package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyCiudad;

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
 * PsyCiudad entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyCiudad
 */
@Scope("singleton")
@Repository("PsyCiudadDAO")
public class PsyCiudadDAO extends HibernateDaoImpl<PsyCiudad, Long>
    implements IPsyCiudadDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyCiudadDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyCiudadDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyCiudadDAO) ctx.getBean("PsyCiudadDAO");
    }
}
