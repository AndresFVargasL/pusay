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
import com.vortexbird.seguridad.utilities.Utilities;


/**
 * interface SegParametroLogic
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegParametroLogic implements ISegParametroLogic {
    public List<SegParametro> getSegParametro() throws Exception {
        List<SegParametro> list = new ArrayList<SegParametro>();

        try {
            list = JPADaoFactory.getInstance().getSegParametroDAO().findAll(0);
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SegParametro");
        } finally {
        }

        return list;
    }

    public void saveSegParametro(String parEstadoRegistro,
        String parNombre, String parValorAlfanumerico, Date parValorFecha,
        Double parValorNumerico, Long usuCodigo_SegUsuario)
        throws Exception {
        SegParametro entity = null;

        try {
        	
            if (parEstadoRegistro == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "parEstadoRegistro");
            }

            if ((parEstadoRegistro != null) &&
                    (Utilities.checkWordAndCheckWithlength(parEstadoRegistro, 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parEstadoRegistro");
            }

            if (parNombre == null) {
                throw new ZMessManager().new EmptyFieldException("parNombre");
            }

            if ((parNombre != null) &&
                    (Utilities.checkWordAndCheckWithlength(parNombre, 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parNombre");
            }

            if ((parValorAlfanumerico != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        parValorAlfanumerico, 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parValorAlfanumerico");
            }

            if ((parValorNumerico != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        parValorNumerico, 6, 4) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parValorNumerico");
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

            entity = new SegParametro();
            //entity.setParCodigo(parCodigo);
            entity.setParEstadoRegistro(parEstadoRegistro);
            entity.setParNombre(parNombre);
            entity.setParValorAlfanumerico(parValorAlfanumerico);
            entity.setParValorFecha(parValorFecha);
            entity.setParValorNumerico(parValorNumerico);
            entity.setSegUsuario(segUsuarioClass);
            EntityManagerHelper.beginTransaction();
            JPADaoFactory.getInstance().getSegParametroDAO().save(entity);
            EntityManagerHelper.commit();
        } catch (Exception e) {
            EntityManagerHelper.rollback();
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    public void deleteSegParametro(Long parCodigo) throws Exception {
        SegParametro entity = null;

        if (parCodigo == null) {
            throw new ZMessManager().new EmptyFieldException("parCodigo");
        }

        entity = getSegParametro(parCodigo);

        if (entity == null) {
            throw new ZMessManager().new EmptyFieldException("SegParametro");
        }

        try {
            EntityManagerHelper.beginTransaction();
            JPADaoFactory.getInstance().getSegParametroDAO().delete(entity);
            EntityManagerHelper.commit();
        } catch (Exception e) {
            EntityManagerHelper.rollback();
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    public void updateSegParametro(Long parCodigo, String parEstadoRegistro,
        String parNombre, String parValorAlfanumerico, Date parValorFecha,
        Double parValorNumerico, Long usuCodigo_SegUsuario)
        throws Exception {
        SegParametro entity = null;

        try {
            if (parCodigo == null) {
                throw new ZMessManager().new EmptyFieldException("parCodigo");
            }

            if ((parCodigo != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        parCodigo, 5, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parCodigo");
            }

            if (parEstadoRegistro == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "parEstadoRegistro");
            }

            if ((parEstadoRegistro != null) &&
                    (Utilities.checkWordAndCheckWithlength(parEstadoRegistro, 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parEstadoRegistro");
            }

            if (parNombre == null) {
                throw new ZMessManager().new EmptyFieldException("parNombre");
            }

            if ((parNombre != null) &&
                    (Utilities.checkWordAndCheckWithlength(parNombre, 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parNombre");
            }

            if ((parValorAlfanumerico != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        parValorAlfanumerico, 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parValorAlfanumerico");
            }

            if ((parValorNumerico != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        parValorNumerico, 6, 4) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parValorNumerico");
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

            entity = getSegParametro(parCodigo);

            if (entity == null) {
                throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
            }

            entity.setParCodigo(parCodigo);
            entity.setParEstadoRegistro(parEstadoRegistro);
            entity.setParNombre(parNombre);
            entity.setParValorAlfanumerico(parValorAlfanumerico);
            entity.setParValorFecha(parValorFecha);
            entity.setParValorNumerico(parValorNumerico);
            entity.setSegUsuario(segUsuarioClass);
            EntityManagerHelper.beginTransaction();
            JPADaoFactory.getInstance().getSegParametroDAO().update(entity);
            EntityManagerHelper.commit();
        } catch (Exception e) {
            EntityManagerHelper.rollback();
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    public SegParametro getSegParametro(Long parCodigo)
        throws Exception {
        SegParametro entity = null;

        try {
            entity = JPADaoFactory.getInstance().getSegParametroDAO()
                                  .findById(parCodigo);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegParametro");
        } finally {
        }

        return entity;
    }

    public List<SegParametro> findPageSegParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SegParametro> entity = null;

        try {
            entity = JPADaoFactory.getInstance().getSegParametroDAO()
                                  .findPageSegParametro(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegParametro");
        }

        return entity;
    }

    public Long findTotalNumberSegParametro() throws Exception {
        Long entity = null;

        try {
            entity = JPADaoFactory.getInstance().getSegParametroDAO()
                                  .findTotalNumberSegParametro();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegParametro Count");
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
    public List<SegParametro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SegParametro> list = new ArrayList<SegParametro>();
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
            list = JPADaoFactory.getInstance().getSegParametroDAO()
                                .findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
