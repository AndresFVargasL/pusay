package com.vortexbird.seguridad.modelo;
// Generated 14/11/2012 05:54:52 PM by Zathura powered by Hibernate Tools 3.2.4.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * SegUsuario generated by Zathura powered by Hibernate-tools(hbm2java)
 */
@Entity
@Table(name="SEG_USUARIO"
    ,schema="PUBLIC"
)
public class SegUsuario  implements java.io.Serializable {


     private Long usuCodigo;
     private SegUsuario segUsuario;
     private String usuNombres;
     private String usuApellidos;
     private String usuLogin;
     private String usuConstrasena;
     private String usuEstadoRegistro;
     private String usuCodigoInterno;
     private Date usuUltmimaModificacionPass;
     private String usuCorreo;
     private Long usuIntentosFallidos;
     private SegCompania usuCompaniaCiaCodigo;
     private Set<SegGrupoOpcion> segGrupoOpcions = new HashSet<SegGrupoOpcion>(0);
     private Set<SegSistemaCia> segSistemaCias = new HashSet<SegSistemaCia>(0);
     private Set<SegParametro> segParametros = new HashSet<SegParametro>(0);
     private Set<SegPermiso> segPermisosForUsuCodigo = new HashSet<SegPermiso>(0);
     private Set<SegRol> segRols = new HashSet<SegRol>(0);
     private Set<SegCambioPass> segCambioPasses = new HashSet<SegCambioPass>(0);
     private Set<SegOpcion> segOpcions = new HashSet<SegOpcion>(0);
     private Set<SegRolUsuario> segRolUsuariosForModUsuCodigo = new HashSet<SegRolUsuario>(0);
     private Set<SegPermiso> segPermisosForModUsuCodigo = new HashSet<SegPermiso>(0);
     private Set<SegHistorialConstrasena> segHistorialConstrasenas = new HashSet<SegHistorialConstrasena>(0);
     private Set<SegCompania> segCompanias = new HashSet<SegCompania>(0);
     private Set<SegRolUsuario> segRolUsuariosForSegUsuarioUsuCodigo = new HashSet<SegRolUsuario>(0);
     private Set<SegSistema> segSistemas = new HashSet<SegSistema>(0);
     private Set<SegUsuario> segUsuarios = new HashSet<SegUsuario>(0);
     private Set<SegSucursal> segSucursals = new HashSet<SegSucursal>(0);
     private Set<SegAuditoria> segAuditorias = new HashSet<SegAuditoria>(0);

    public SegUsuario() {
    }

	
    public SegUsuario(Long usuCodigo, String usuNombres, String usuApellidos, String usuLogin, String usuConstrasena, String usuEstadoRegistro, String usuCodigoInterno) {
        this.usuCodigo = usuCodigo;
        this.usuNombres = usuNombres;
        this.usuApellidos = usuApellidos;
        this.usuLogin = usuLogin;
        this.usuConstrasena = usuConstrasena;
        this.usuEstadoRegistro = usuEstadoRegistro;
        this.usuCodigoInterno = usuCodigoInterno;
    }
    public SegUsuario(Long usuCodigo, SegUsuario segUsuario, String usuNombres, String usuApellidos, String usuLogin, String usuConstrasena, String usuEstadoRegistro, String usuCodigoInterno, Date usuUltmimaModificacionPass, String usuCorreo, Long usuIntentosFallidos, SegCompania usuCompaniaCiaCodigo, Set<SegGrupoOpcion> segGrupoOpcions, Set<SegSistemaCia> segSistemaCias, Set<SegParametro> segParametros, Set<SegPermiso> segPermisosForUsuCodigo, Set<SegRol> segRols, Set<SegCambioPass> segCambioPasses, Set<SegOpcion> segOpcions, Set<SegRolUsuario> segRolUsuariosForModUsuCodigo, Set<SegPermiso> segPermisosForModUsuCodigo, Set<SegHistorialConstrasena> segHistorialConstrasenas, Set<SegCompania> segCompanias, Set<SegRolUsuario> segRolUsuariosForSegUsuarioUsuCodigo, Set<SegSistema> segSistemas, Set<SegUsuario> segUsuarios, Set<SegSucursal> segSucursals, Set<SegAuditoria> segAuditorias) {
       this.usuCodigo = usuCodigo;
       this.segUsuario = segUsuario;
       this.usuNombres = usuNombres;
       this.usuApellidos = usuApellidos;
       this.usuLogin = usuLogin;
       this.usuConstrasena = usuConstrasena;
       this.usuEstadoRegistro = usuEstadoRegistro;
       this.usuCodigoInterno = usuCodigoInterno;
       this.usuUltmimaModificacionPass = usuUltmimaModificacionPass;
       this.usuCorreo = usuCorreo;
       this.usuIntentosFallidos = usuIntentosFallidos;
       this.usuCompaniaCiaCodigo = usuCompaniaCiaCodigo;
       this.segGrupoOpcions = segGrupoOpcions;
       this.segSistemaCias = segSistemaCias;
       this.segParametros = segParametros;
       this.segPermisosForUsuCodigo = segPermisosForUsuCodigo;
       this.segRols = segRols;
       this.segCambioPasses = segCambioPasses;
       this.segOpcions = segOpcions;
       this.segRolUsuariosForModUsuCodigo = segRolUsuariosForModUsuCodigo;
       this.segPermisosForModUsuCodigo = segPermisosForModUsuCodigo;
       this.segHistorialConstrasenas = segHistorialConstrasenas;
       this.segCompanias = segCompanias;
       this.segRolUsuariosForSegUsuarioUsuCodigo = segRolUsuariosForSegUsuarioUsuCodigo;
       this.segSistemas = segSistemas;
       this.segUsuarios = segUsuarios;
       this.segSucursals = segSucursals;
       this.segAuditorias = segAuditorias;
    }
   
     @Id 
 	@SequenceGenerator(name = "seg_usuario_usu_codigo_seq", sequenceName = "seg_usuario_usu_codigo_seq", initialValue = 1, allocationSize = 1)
 	@GeneratedValue(generator = "seg_usuario_usu_codigo_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="USU_CODIGO", unique=true, nullable=false, precision=5, scale=0)
    public Long getUsuCodigo() {
        return this.usuCodigo;
    }
    
    public void setUsuCodigo(Long usuCodigo) {
        this.usuCodigo = usuCodigo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MOD_USU_CODIGO")
    public SegUsuario getSegUsuario() {
        return this.segUsuario;
    }
    
    public void setSegUsuario(SegUsuario segUsuario) {
        this.segUsuario = segUsuario;
    }

    
    @Column(name="USU_NOMBRES", nullable=false, length=50)
    public String getUsuNombres() {
        return this.usuNombres;
    }
    
    public void setUsuNombres(String usuNombres) {
        this.usuNombres = usuNombres;
    }

    
    @Column(name="USU_APELLIDOS", nullable=false, length=50)
    public String getUsuApellidos() {
        return this.usuApellidos;
    }
    
    public void setUsuApellidos(String usuApellidos) {
        this.usuApellidos = usuApellidos;
    }

    
    @Column(name="USU_LOGIN", nullable=false, length=30)
    public String getUsuLogin() {
        return this.usuLogin;
    }
    
    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    
    @Column(name="USU_CONSTRASENA", nullable=false, length=50)
    public String getUsuConstrasena() {
        return this.usuConstrasena;
    }
    
    public void setUsuConstrasena(String usuConstrasena) {
        this.usuConstrasena = usuConstrasena;
    }

    
    @Column(name="USU_ESTADO_REGISTRO", nullable=false, length=1)
    public String getUsuEstadoRegistro() {
        return this.usuEstadoRegistro;
    }
    
    public void setUsuEstadoRegistro(String usuEstadoRegistro) {
        this.usuEstadoRegistro = usuEstadoRegistro;
    }

    
    @Column(name="USU_CODIGO_INTERNO", nullable=false, length=50)
    public String getUsuCodigoInterno() {
        return this.usuCodigoInterno;
    }
    
    public void setUsuCodigoInterno(String usuCodigoInterno) {
        this.usuCodigoInterno = usuCodigoInterno;
    }

    
    @Column(name="USU_ULTMIMA_MODIFICACION_PASS", length=7)
    public Date getUsuUltmimaModificacionPass() {
        return this.usuUltmimaModificacionPass;
    }
    
    public void setUsuUltmimaModificacionPass(Date usuUltmimaModificacionPass) {
        this.usuUltmimaModificacionPass = usuUltmimaModificacionPass;
    }

    
    @Column(name="USU_CORREO", length=200)
    public String getUsuCorreo() {
        return this.usuCorreo;
    }
    
    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    
    @Column(name="USU_INTENTOS_FALLIDOS", precision=22, scale=0)
    public Long getUsuIntentosFallidos() {
        return this.usuIntentosFallidos;
    }
    
    public void setUsuIntentosFallidos(Long usuIntentosFallidos) {
        this.usuIntentosFallidos = usuIntentosFallidos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuario")
    public Set<SegGrupoOpcion> getSegGrupoOpcions() {
        return this.segGrupoOpcions;
    }
    
    public void setSegGrupoOpcions(Set<SegGrupoOpcion> segGrupoOpcions) {
        this.segGrupoOpcions = segGrupoOpcions;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuario")
    public Set<SegSistemaCia> getSegSistemaCias() {
        return this.segSistemaCias;
    }
    
    public void setSegSistemaCias(Set<SegSistemaCia> segSistemaCias) {
        this.segSistemaCias = segSistemaCias;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuario")
    public Set<SegParametro> getSegParametros() {
        return this.segParametros;
    }
    
    public void setSegParametros(Set<SegParametro> segParametros) {
        this.segParametros = segParametros;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuarioByUsuCodigo")
    public Set<SegPermiso> getSegPermisosForUsuCodigo() {
        return this.segPermisosForUsuCodigo;
    }
    
    public void setSegPermisosForUsuCodigo(Set<SegPermiso> segPermisosForUsuCodigo) {
        this.segPermisosForUsuCodigo = segPermisosForUsuCodigo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuario")
    public Set<SegRol> getSegRols() {
        return this.segRols;
    }
    
    public void setSegRols(Set<SegRol> segRols) {
        this.segRols = segRols;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuario")
    public Set<SegCambioPass> getSegCambioPasses() {
        return this.segCambioPasses;
    }
    
    public void setSegCambioPasses(Set<SegCambioPass> segCambioPasses) {
        this.segCambioPasses = segCambioPasses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuario")
    public Set<SegOpcion> getSegOpcions() {
        return this.segOpcions;
    }
    
    public void setSegOpcions(Set<SegOpcion> segOpcions) {
        this.segOpcions = segOpcions;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuarioByModUsuCodigo")
    public Set<SegRolUsuario> getSegRolUsuariosForModUsuCodigo() {
        return this.segRolUsuariosForModUsuCodigo;
    }
    
    public void setSegRolUsuariosForModUsuCodigo(Set<SegRolUsuario> segRolUsuariosForModUsuCodigo) {
        this.segRolUsuariosForModUsuCodigo = segRolUsuariosForModUsuCodigo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuarioByModUsuCodigo")
    public Set<SegPermiso> getSegPermisosForModUsuCodigo() {
        return this.segPermisosForModUsuCodigo;
    }
    
    public void setSegPermisosForModUsuCodigo(Set<SegPermiso> segPermisosForModUsuCodigo) {
        this.segPermisosForModUsuCodigo = segPermisosForModUsuCodigo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuario")
    public Set<SegHistorialConstrasena> getSegHistorialConstrasenas() {
        return this.segHistorialConstrasenas;
    }
    
    public void setSegHistorialConstrasenas(Set<SegHistorialConstrasena> segHistorialConstrasenas) {
        this.segHistorialConstrasenas = segHistorialConstrasenas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuario")
    public Set<SegCompania> getSegCompanias() {
        return this.segCompanias;
    }
    
    public void setSegCompanias(Set<SegCompania> segCompanias) {
        this.segCompanias = segCompanias;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuarioBySegUsuarioUsuCodigo")
    public Set<SegRolUsuario> getSegRolUsuariosForSegUsuarioUsuCodigo() {
        return this.segRolUsuariosForSegUsuarioUsuCodigo;
    }
    
    public void setSegRolUsuariosForSegUsuarioUsuCodigo(Set<SegRolUsuario> segRolUsuariosForSegUsuarioUsuCodigo) {
        this.segRolUsuariosForSegUsuarioUsuCodigo = segRolUsuariosForSegUsuarioUsuCodigo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuario")
    public Set<SegSistema> getSegSistemas() {
        return this.segSistemas;
    }
    
    public void setSegSistemas(Set<SegSistema> segSistemas) {
        this.segSistemas = segSistemas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuario")
    public Set<SegUsuario> getSegUsuarios() {
        return this.segUsuarios;
    }
    
    public void setSegUsuarios(Set<SegUsuario> segUsuarios) {
        this.segUsuarios = segUsuarios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuario")
    public Set<SegSucursal> getSegSucursals() {
        return this.segSucursals;
    }
    
    public void setSegSucursals(Set<SegSucursal> segSucursals) {
        this.segSucursals = segSucursals;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segUsuario")
    public Set<SegAuditoria> getSegAuditorias() {
        return this.segAuditorias;
    }
    
    public void setSegAuditorias(Set<SegAuditoria> segAuditorias) {
        this.segAuditorias = segAuditorias;
    }



	

    @ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name="USU_COMPANIA_CIA_CODIGO", nullable=false)
	public SegCompania getUsuCompaniaCiaCodigo() {
		return usuCompaniaCiaCodigo;
	}


	public void setUsuCompaniaCiaCodigo(SegCompania usuCompaniaCiaCodigo) {
		this.usuCompaniaCiaCodigo = usuCompaniaCiaCodigo;
	}
	
	
	


}


