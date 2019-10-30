package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vortexbird.seguridad.dataaccess.daoFactory.JPADaoFactory;
import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegAuditoriaDTO;
import com.vortexbird.seguridad.utilities.Utilities;


/**
 * interface SegAuditoriaLogic
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegAuditoriaLogic implements ISegAuditoriaLogic {
    public List<SegAuditoria> getSegAuditoria() throws Exception {
        List<SegAuditoria> list = new ArrayList<SegAuditoria>();

        try {
            list = JPADaoFactory.getInstance().getSegAuditoriaDAO().findAll(0);
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SegAuditoria");
        } finally {
        	EntityManagerHelper.closeEntityManager();
        }

        return list;
    }

    public void saveSegAuditoria(Long autCodigo, String campo,
        Long codRegistro, Date fecha, String tabla, String tipo,
        String valorAnterior, String valorNuevo, Long usuCodigo_SegUsuario)
        throws Exception {
        SegAuditoria entity = null;

        try {
            if (autCodigo == null) {
                throw new ZMessManager().new EmptyFieldException("autCodigo");
            }

            if ((autCodigo != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        autCodigo, 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "autCodigo");
            }

            if ((campo != null) &&
                    (Utilities.checkWordAndCheckWithlength(campo, 200) == false)) {
                throw new ZMessManager().new NotValidFormatException("campo");
            }

            if ((codRegistro != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        codRegistro, 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "codRegistro");
            }

            if ((tabla != null) &&
                    (Utilities.checkWordAndCheckWithlength(tabla, 200) == false)) {
                throw new ZMessManager().new NotValidFormatException("tabla");
            }

            if ((tipo != null) &&
                    (Utilities.checkWordAndCheckWithlength(tipo, 100) == false)) {
                throw new ZMessManager().new NotValidFormatException("tipo");
            }

            if ((valorAnterior != null) &&
                    (Utilities.checkWordAndCheckWithlength(valorAnterior, 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "valorAnterior");
            }

            if ((valorNuevo != null) &&
                    (Utilities.checkWordAndCheckWithlength(valorNuevo, 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "valorNuevo");
            }

            if (usuCodigo_SegUsuario == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            if ((usuCodigo_SegUsuario != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        usuCodigo_SegUsuario, 5, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuCodigo_SegUsuario");
            }

            ISegUsuarioLogic logicSegUsuario1 = new SegUsuarioLogic();
            SegUsuario segUsuarioClass = logicSegUsuario1.getSegUsuario(usuCodigo_SegUsuario);

            if (segUsuarioClass == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            entity = getSegAuditoria(autCodigo);

            if (entity != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            entity = new SegAuditoria();
            entity.setAutCodigo(autCodigo);
            entity.setCampo(campo);
            entity.setCodRegistro(codRegistro);
            entity.setFecha(fecha);
            entity.setTabla(tabla);
            entity.setTipo(tipo);
            entity.setValorAnterior(valorAnterior);
            entity.setValorNuevo(valorNuevo);
            entity.setSegUsuario(segUsuarioClass);
            EntityManagerHelper.beginTransaction();
            JPADaoFactory.getInstance().getSegAuditoriaDAO().save(entity);
            EntityManagerHelper.commit();
        } catch (Exception e) {
            EntityManagerHelper.rollback();
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    public void deleteSegAuditoria(Long autCodigo) throws Exception {
        SegAuditoria entity = null;

        if (autCodigo == null) {
            throw new ZMessManager().new EmptyFieldException("autCodigo");
        }

        entity = getSegAuditoria(autCodigo);

        if (entity == null) {
            throw new ZMessManager().new EmptyFieldException("SegAuditoria");
        }

        try {
            EntityManagerHelper.beginTransaction();
            JPADaoFactory.getInstance().getSegAuditoriaDAO().delete(entity);
            EntityManagerHelper.commit();
        } catch (Exception e) {
            EntityManagerHelper.rollback();
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    public void updateSegAuditoria(Long autCodigo, String campo,
        Long codRegistro, Date fecha, String tabla, String tipo,
        String valorAnterior, String valorNuevo, Long usuCodigo_SegUsuario)
        throws Exception {
        SegAuditoria entity = null;

        try {
            if (autCodigo == null) {
                throw new ZMessManager().new EmptyFieldException("autCodigo");
            }

            if ((autCodigo != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        autCodigo, 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "autCodigo");
            }

            if ((campo != null) &&
                    (Utilities.checkWordAndCheckWithlength(campo, 200) == false)) {
                throw new ZMessManager().new NotValidFormatException("campo");
            }

            if ((codRegistro != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        codRegistro, 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "codRegistro");
            }

            if ((tabla != null) &&
                    (Utilities.checkWordAndCheckWithlength(tabla, 200) == false)) {
                throw new ZMessManager().new NotValidFormatException("tabla");
            }

            if ((tipo != null) &&
                    (Utilities.checkWordAndCheckWithlength(tipo, 100) == false)) {
                throw new ZMessManager().new NotValidFormatException("tipo");
            }

            if ((valorAnterior != null) &&
                    (Utilities.checkWordAndCheckWithlength(valorAnterior, 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "valorAnterior");
            }

            if ((valorNuevo != null) &&
                    (Utilities.checkWordAndCheckWithlength(valorNuevo, 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "valorNuevo");
            }

            if (usuCodigo_SegUsuario == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            if ((usuCodigo_SegUsuario != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        usuCodigo_SegUsuario, 5, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuCodigo_SegUsuario");
            }

            ISegUsuarioLogic logicSegUsuario1 = new SegUsuarioLogic();
            SegUsuario segUsuarioClass = logicSegUsuario1.getSegUsuario(usuCodigo_SegUsuario);

            if (segUsuarioClass == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            entity = getSegAuditoria(autCodigo);

            if (entity == null) {
                throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
            }

            entity.setAutCodigo(autCodigo);
            entity.setCampo(campo);
            entity.setCodRegistro(codRegistro);
            entity.setFecha(fecha);
            entity.setTabla(tabla);
            entity.setTipo(tipo);
            entity.setValorAnterior(valorAnterior);
            entity.setValorNuevo(valorNuevo);
            entity.setSegUsuario(segUsuarioClass);
            EntityManagerHelper.beginTransaction();
            JPADaoFactory.getInstance().getSegAuditoriaDAO().update(entity);
            EntityManagerHelper.commit();
        } catch (Exception e) {
            EntityManagerHelper.rollback();
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    public SegAuditoria getSegAuditoria(Long autCodigo)
        throws Exception {
        SegAuditoria entity = null;

        try {
            entity = JPADaoFactory.getInstance().getSegAuditoriaDAO()
                                  .findById(autCodigo);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegAuditoria");
        } finally {
        }

        return entity;
    }

    public List<SegAuditoria> findPageSegAuditoria(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SegAuditoria> entity = null;

        try {
            entity = JPADaoFactory.getInstance().getSegAuditoriaDAO()
                                  .findPageSegAuditoria(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegAuditoria");
        }

        return entity;
    }

    public Long findTotalNumberSegAuditoria() throws Exception {
        Long entity = null;

        try {
            entity = JPADaoFactory.getInstance().getSegAuditoriaDAO()
                                  .findTotalNumberSegAuditoria();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegAuditoria Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    public List<SegAuditoria> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SegAuditoria> list = new ArrayList<SegAuditoria>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = JPADaoFactory.getInstance().getSegAuditoriaDAO()
                                .findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

	@Override
	public List<SegAuditoriaDTO> getDataSegAuditoria() throws Exception {
		  try {
	            List<SegAuditoria> segAuditoria = JPADaoFactory.getInstance().getSegAuditoriaDAO().findAll(0);

	            List<SegAuditoriaDTO> segAuditoriaDTO = new ArrayList<SegAuditoriaDTO>();

	            for (SegAuditoria segAuditoriaTmp : segAuditoria) {
	                SegAuditoriaDTO segAuditoriaDTO2 = new SegAuditoriaDTO();

	                segAuditoriaDTO2.setAutCodigo(segAuditoriaTmp.getAutCodigo().toString());
	                segAuditoriaDTO2.setCampo((segAuditoriaTmp.getCampo() != null)? segAuditoriaTmp.getCampo() : null);
	                segAuditoriaDTO2.setCodRegistro((segAuditoriaTmp.getCodRegistro().toString() != null)? segAuditoriaTmp.getCodRegistro().toString() : null);
	                segAuditoriaDTO2.setFecha(segAuditoriaTmp.getFecha());
	                segAuditoriaDTO2.setTabla((segAuditoriaTmp.getTabla() != null)? segAuditoriaTmp.getTabla() : null);
	                segAuditoriaDTO2.setTipo((segAuditoriaTmp.getTipo() != null)? segAuditoriaTmp.getTipo() : null);
	                segAuditoriaDTO2.setValorAnterior((segAuditoriaTmp.getValorAnterior() != null)? segAuditoriaTmp.getValorAnterior() : null);
	                segAuditoriaDTO2.setValorNuevo((segAuditoriaTmp.getValorNuevo() != null)? segAuditoriaTmp.getValorNuevo() : null);
	                segAuditoriaDTO2.setUsuCodigo_SegUsuario((segAuditoriaTmp.getSegUsuario()!= null)? segAuditoriaTmp.getSegUsuario().getUsuNombres() : null);
	                
	                segAuditoriaDTO.add(segAuditoriaDTO2);
	            }

	            return segAuditoriaDTO;
	        } catch (Exception e) {
	            throw e;
	        }finally{
	        	EntityManagerHelper.closeEntityManager();
	        }
	}
}
