package  com.vortexbird.seguridad.dataaccess.daoFactory;
import com.vortexbird.seguridad.dataaccess.dao.*;
/**
 * Factory for Data Access Objects Strategy The DAO pattern can be made highly flexible 
 * by adopting the Abstract Factory [GoF] and the Factory Method [GoF] patterns.
 * When the underlying storage is not subject to change from one implementation to another, 
 * this strategy can be implemented using the Factory Method pattern to produce a number of DAOs needed by the application. 
 * This class is a Factory Method pattern
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 */
public class JPADaoFactory {
private static JPADaoFactory instance = null;
/**
* 
* @return JPADaoFactory
*/
public static JPADaoFactory getInstance(){
if(instance == null){
instance = new JPADaoFactory();
}
return instance;
}	
private JPADaoFactory(){
}
    /**
    * This method instantiates the ISegAuditoriaDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegAuditoriaDAO implementation
    */
    public ISegAuditoriaDAO getSegAuditoriaDAO() {
    return new SegAuditoriaDAO();
    }
    /**
    * This method instantiates the ISegCambioPassDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegCambioPassDAO implementation
    */
    public ISegCambioPassDAO getSegCambioPassDAO() {
    return new SegCambioPassDAO();
    }
    /**
    * This method instantiates the ISegCompaniaDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegCompaniaDAO implementation
    */
    public ISegCompaniaDAO getSegCompaniaDAO() {
    return new SegCompaniaDAO();
    }
    /**
    * This method instantiates the ISegGrupoOpcionDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegGrupoOpcionDAO implementation
    */
    public ISegGrupoOpcionDAO getSegGrupoOpcionDAO() {
    return new SegGrupoOpcionDAO();
    }
    /**
    * This method instantiates the ISegHistorialConstrasenaDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegHistorialConstrasenaDAO implementation
    */
    public ISegHistorialConstrasenaDAO getSegHistorialConstrasenaDAO() {
    return new SegHistorialConstrasenaDAO();
    }
    /**
    * This method instantiates the ISegOpcionDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegOpcionDAO implementation
    */
    public ISegOpcionDAO getSegOpcionDAO() {
    return new SegOpcionDAO();
    }
    /**
    * This method instantiates the ISegParametroDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegParametroDAO implementation
    */
    public ISegParametroDAO getSegParametroDAO() {
    return new SegParametroDAO();
    }
    /**
    * This method instantiates the ISegPermisoDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegPermisoDAO implementation
    */
    public ISegPermisoDAO getSegPermisoDAO() {
    return new SegPermisoDAO();
    }
    /**
    * This method instantiates the ISegRolDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegRolDAO implementation
    */
    public ISegRolDAO getSegRolDAO() {
    return new SegRolDAO();
    }
    /**
    * This method instantiates the ISegRolUsuarioDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegRolUsuarioDAO implementation
    */
    public ISegRolUsuarioDAO getSegRolUsuarioDAO() {
    return new SegRolUsuarioDAO();
    }
    /**
    * This method instantiates the ISegSistemaDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegSistemaDAO implementation
    */
    public ISegSistemaDAO getSegSistemaDAO() {
    return new SegSistemaDAO();
    }
    /**
    * This method instantiates the ISegSistemaCiaDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegSistemaCiaDAO implementation
    */
    public ISegSistemaCiaDAO getSegSistemaCiaDAO() {
    return new SegSistemaCiaDAO();
    }
    /**
    * This method instantiates the ISegSucursalDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegSucursalDAO implementation
    */
    public ISegSucursalDAO getSegSucursalDAO() {
    return new SegSucursalDAO();
    }
    /**
    * This method instantiates the ISegUsuarioDAO class for JPA 
    * that is used in this applications deployment environment to access the data. 
    * @return ISegUsuarioDAO implementation
    */
    public ISegUsuarioDAO getSegUsuarioDAO() {
    return new SegUsuarioDAO();
    }
}
