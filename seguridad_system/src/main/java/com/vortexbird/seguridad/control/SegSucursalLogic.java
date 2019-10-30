package com.vortexbird.seguridad.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vortexbird.seguridad.dataaccess.daoFactory.JPADaoFactory;
import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegSucursalDTO;
import com.vortexbird.seguridad.utilities.Utilities;


/**
 * interface SegSucursalLogic
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegSucursalLogic implements ISegSucursalLogic {
    public List<SegSucursal> getSegSucursal() throws Exception {
        List<SegSucursal> list = new ArrayList<SegSucursal>();

        try {
            list = JPADaoFactory.getInstance().getSegSucursalDAO().findAll(0);
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SegSucursal");
        } finally {
        	EntityManagerHelper.closeEntityManager();
        }

        return list;
    }

    public void saveSegSucursal(Long sucCodigo, String sucCodigoInterno,
        String sucEstadoRegistro, String sucNombre, Long ciaCodigo_SegCompania,
        Long usuCodigo_SegUsuario) throws Exception {
        SegSucursal entity = null;

        try {
            if (sucCodigoInterno == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sucCodigoInterno");
            }

            if ((sucCodigoInterno != null) &&
                    (Utilities.checkWordAndCheckWithlength(sucCodigoInterno, 15) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sucCodigoInterno");
            }

            if (sucEstadoRegistro == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sucEstadoRegistro");
            }

            if ((sucEstadoRegistro != null) &&
                    (Utilities.checkWordAndCheckWithlength(sucEstadoRegistro, 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sucEstadoRegistro");
            }

            if (sucNombre == null) {
                throw new ZMessManager().new EmptyFieldException("sucNombre");
            }

            if ((sucNombre != null) &&
                    (Utilities.checkWordAndCheckWithlength(sucNombre, 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sucNombre");
            }

            if (ciaCodigo_SegCompania == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "ciaCodigo_SegCompania");
            }

            if ((ciaCodigo_SegCompania != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        ciaCodigo_SegCompania, 5, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "ciaCodigo_SegCompania");
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

            ISegCompaniaLogic logicSegCompania1 = new SegCompaniaLogic();
            ISegUsuarioLogic logicSegUsuario2 = new SegUsuarioLogic();
            SegCompania segCompaniaClass = logicSegCompania1.getSegCompania(ciaCodigo_SegCompania);
            SegUsuario segUsuarioClass = logicSegUsuario2.getSegUsuario(usuCodigo_SegUsuario);

            if (segCompaniaClass == null) {
                throw new ZMessManager().new ForeignException("segCompania");
            }

            if (segUsuarioClass == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            entity = new SegSucursal();
            entity.setSucCodigoInterno(sucCodigoInterno);
            entity.setSucEstadoRegistro(sucEstadoRegistro);
            entity.setSucNombre(sucNombre);
            entity.setSegCompania(segCompaniaClass);
            entity.setSegUsuario(segUsuarioClass);
            EntityManagerHelper.beginTransaction();
            JPADaoFactory.getInstance().getSegSucursalDAO().save(entity);
            EntityManagerHelper.commit();
        } catch (Exception e) {
            EntityManagerHelper.rollback();
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    public void deleteSegSucursal(Long sucCodigo) throws Exception {
        SegSucursal entity = null;

        if (sucCodigo == null) {
            throw new ZMessManager().new EmptyFieldException("sucCodigo");
        }

        List<SegPermiso> segPermisos = null;
        entity = getSegSucursal(sucCodigo);

        if (entity == null) {
            throw new ZMessManager().new EmptyFieldException("SegSucursal");
        }

        try {
            segPermisos = JPADaoFactory.getInstance().getSegPermisoDAO()
                                       .findByProperty("segSucursal.sucCodigo",
                    sucCodigo, 0);

            if (Utilities.validationsList(segPermisos) == true) {
                throw new ZMessManager().new DeletingException("segPermisos");
            }

            EntityManagerHelper.beginTransaction();
            JPADaoFactory.getInstance().getSegSucursalDAO().delete(entity);
            EntityManagerHelper.commit();
        } catch (Exception e) {
            EntityManagerHelper.rollback();
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    public void updateSegSucursal(Long sucCodigo, String sucCodigoInterno,
        String sucEstadoRegistro, String sucNombre, Long ciaCodigo_SegCompania,
        Long usuCodigo_SegUsuario) throws Exception {
        SegSucursal entity = null;

        try {
            if (sucCodigo == null) {
                throw new ZMessManager().new EmptyFieldException("sucCodigo");
            }

            if ((sucCodigo != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        sucCodigo, 5, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sucCodigo");
            }

            if (sucCodigoInterno == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sucCodigoInterno");
            }

            if ((sucCodigoInterno != null) &&
                    (Utilities.checkWordAndCheckWithlength(sucCodigoInterno, 15) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sucCodigoInterno");
            }

            if (sucEstadoRegistro == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sucEstadoRegistro");
            }

            if ((sucEstadoRegistro != null) &&
                    (Utilities.checkWordAndCheckWithlength(sucEstadoRegistro, 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sucEstadoRegistro");
            }

            if (sucNombre == null) {
                throw new ZMessManager().new EmptyFieldException("sucNombre");
            }

            if ((sucNombre != null) &&
                    (Utilities.checkWordAndCheckWithlength(sucNombre, 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sucNombre");
            }

            if (ciaCodigo_SegCompania == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "ciaCodigo_SegCompania");
            }

            if ((ciaCodigo_SegCompania != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        ciaCodigo_SegCompania, 5, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "ciaCodigo_SegCompania");
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

            ISegCompaniaLogic logicSegCompania1 = new SegCompaniaLogic();

            ISegUsuarioLogic logicSegUsuario2 = new SegUsuarioLogic();
            SegCompania segCompaniaClass = logicSegCompania1.getSegCompania(ciaCodigo_SegCompania);
            SegUsuario segUsuarioClass = logicSegUsuario2.getSegUsuario(usuCodigo_SegUsuario);

            if (segCompaniaClass == null) {
                throw new ZMessManager().new ForeignException("segCompania");
            }

            if (segUsuarioClass == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            entity = getSegSucursal(sucCodigo);

            if (entity == null) {
                throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
            }

            entity.setSucCodigo(sucCodigo);
            entity.setSucCodigoInterno(sucCodigoInterno);
            entity.setSucEstadoRegistro(sucEstadoRegistro);
            entity.setSucNombre(sucNombre);
            entity.setSegCompania(segCompaniaClass);
            entity.setSegUsuario(segUsuarioClass);
            EntityManagerHelper.beginTransaction();
            JPADaoFactory.getInstance().getSegSucursalDAO().update(entity);
            EntityManagerHelper.commit();
        } catch (Exception e) {
            EntityManagerHelper.rollback();
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    public SegSucursal getSegSucursal(Long sucCodigo) throws Exception {
        SegSucursal entity = null;

        try {
            entity = JPADaoFactory.getInstance().getSegSucursalDAO()
                                  .findById(sucCodigo);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegSucursal");
        } finally {
        }

        return entity;
    }

    public List<SegSucursal> findPageSegSucursal(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SegSucursal> entity = null;

        try {
            entity = JPADaoFactory.getInstance().getSegSucursalDAO()
                                  .findPageSegSucursal(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegSucursal");
        }

        return entity;
    }

    public Long findTotalNumberSegSucursal() throws Exception {
        Long entity = null;

        try {
            entity = JPADaoFactory.getInstance().getSegSucursalDAO()
                                  .findTotalNumberSegSucursal();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegSucursal Count");
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
    public List<SegSucursal> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SegSucursal> list = new ArrayList<SegSucursal>();
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
            list = JPADaoFactory.getInstance().getSegSucursalDAO()
                                .findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

	@Override
	public List<SegSucursalDTO> findSucursalesDTO(List<SegSucursal> lasSucursales) throws Exception {
		try {
			List<SegSucursalDTO> sucursalesDTO = new ArrayList<SegSucursalDTO>(lasSucursales.size());

			for (SegSucursal sucursal : lasSucursales) {
				SegSucursalDTO sucuDTO = new SegSucursalDTO();
				sucuDTO.setSucCodigo((sucursal.getSucCodigo().toString()));
				sucuDTO.setSucCodigoInterno((sucursal.getSucCodigoInterno() != null) ? sucursal.getSucCodigoInterno().toString(): null);
				
				if (sucursal.getSucEstadoRegistro().equals("0")==true) {
					sucuDTO.setSucEstadoRegistroNombre("Inactivo");
				}else {
					sucuDTO.setSucEstadoRegistroNombre("Activo");
				}	
				sucuDTO.setSucNombre((sucursal.getSucNombre() != null)? sucursal.getSucNombre().toString() : null);
				sucuDTO.setCiaNombre_SegCompania((sucursal.getSegCompania().getCiaNombre()!=null)?sucursal.getSegCompania().getCiaNombre().toString():null);
				
				sucursalesDTO.add(sucuDTO);
			}
			return sucursalesDTO;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
    public List<SegSucursalDTO> getDataSegSucursal() throws Exception {
        try {
            List<SegSucursal> segSucursal = JPADaoFactory.getInstance().getSegSucursalDAO().findAll(0);

            List<SegSucursalDTO> segSucursalDTO = new ArrayList<SegSucursalDTO>();

            for (SegSucursal segSucursalTmp : segSucursal) {
            	
                SegSucursalDTO segSucursalDTO2 = new SegSucursalDTO();

                segSucursalDTO2.setSucCodigo(segSucursalTmp.getSucCodigo().toString());
                segSucursalDTO2.setSucCodigoInterno((segSucursalTmp.getSucCodigoInterno() != null)? segSucursalTmp.getSucCodigoInterno() : null);
                segSucursalDTO2.setSucEstadoRegistro((segSucursalTmp.getSucEstadoRegistro() != null)? segSucursalTmp.getSucEstadoRegistro() : null);
                segSucursalDTO2.setSucNombre((segSucursalTmp.getSucNombre() != null)? segSucursalTmp.getSucNombre() : null);
                segSucursalDTO2.setCiaCodigo_SegCompania((segSucursalTmp.getSegCompania().getCiaCodigo().toString() != null)? segSucursalTmp.getSegCompania().getCiaCodigo().toString() : null);
                segSucursalDTO2.setUsuCodigo_SegUsuario((segSucursalTmp.getSegUsuario().getUsuCodigo().toString() != null)? segSucursalTmp.getSegUsuario().getUsuCodigo().toString() : null);
                segSucursalDTO2.setCiaNombre_SegCompania((segSucursalTmp.getSegCompania().getCiaNombre() != null)? segSucursalTmp.getSegCompania().getCiaNombre().toString() : null);
                segSucursalDTO2.setSucEstadoRegistroNombre((segSucursalTmp.getSucEstadoRegistro().equals("0")) ? "Inactivo" : "Activo");
                segSucursalDTO.add(segSucursalDTO2);
            }

            return segSucursalDTO;
        } catch (Exception e) {
            throw e;
        }finally{
        	EntityManagerHelper.closeEntityManager();
        }
    }
}