package com.vortexbird.seguridad.modelo;
// Generated 14/11/2012 05:54:52 PM by Zathura powered by Hibernate Tools 3.2.4.GA


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
 * SegRol generated by Zathura powered by Hibernate-tools(hbm2java)
 */
@Entity
@Table(name="SEG_ROL"
,schema="PUBLIC"
		)
public class SegRol  implements java.io.Serializable {


	private Long rolCodigo;
	private SegUsuario segUsuario;
	private SegSistema segSistema;
	private String rolNombre;
	private String rolDescripcion;
	private Long rolDiasCaducidadPwd;
	private String rolEstadoRegistro;
	private Set<SegPermiso> segPermisos = new HashSet<SegPermiso>(0);
	private Set<SegRolUsuario> segRolUsuarios = new HashSet<SegRolUsuario>(0);

	public SegRol() {
	}


	public SegRol(Long rolCodigo, SegSistema segSistema, String rolNombre, String rolEstadoRegistro) {
		this.rolCodigo = rolCodigo;
		this.segSistema = segSistema;
		this.rolNombre = rolNombre;
		this.rolEstadoRegistro = rolEstadoRegistro;
	}
	public SegRol(Long rolCodigo, SegUsuario segUsuario, SegSistema segSistema, String rolNombre, String rolDescripcion, Long rolDiasCaducidadPwd, String rolEstadoRegistro, Set<SegPermiso> segPermisos, Set<SegRolUsuario> segRolUsuarios) {
		this.rolCodigo = rolCodigo;
		this.segUsuario = segUsuario;
		this.segSistema = segSistema;
		this.rolNombre = rolNombre;
		this.rolDescripcion = rolDescripcion;
		this.rolDiasCaducidadPwd = rolDiasCaducidadPwd;
		this.rolEstadoRegistro = rolEstadoRegistro;
		this.segPermisos = segPermisos;
		this.segRolUsuarios = segRolUsuarios;
	}

	@Id 
	@SequenceGenerator(name = "seg_rol_rol_codigo_seq", sequenceName = "seg_rol_rol_codigo_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "seg_rol_rol_codigo_seq", strategy = GenerationType.SEQUENCE)
	@Column(name="ROL_CODIGO", unique=true, nullable=false, precision=5, scale=0)
	public Long getRolCodigo() {
		return this.rolCodigo;
	}

	public void setRolCodigo(Long rolCodigo) {
		this.rolCodigo = rolCodigo;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MOD_USU_CODIGO")
	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SEG_SISTEMA_SIS_CODIGO", nullable=false)
	public SegSistema getSegSistema() {
		return this.segSistema;
	}

	public void setSegSistema(SegSistema segSistema) {
		this.segSistema = segSistema;
	}


	@Column(name="ROL_NOMBRE", nullable=false, length=50)
	public String getRolNombre() {
		return this.rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}


	@Column(name="ROL_DESCRIPCION", length=200)
	public String getRolDescripcion() {
		return this.rolDescripcion;
	}

	public void setRolDescripcion(String rolDescripcion) {
		this.rolDescripcion = rolDescripcion;
	}


	@Column(name="ROL_DIAS_CADUCIDAD_PWD", precision=3, scale=0)
	public Long getRolDiasCaducidadPwd() {
		return this.rolDiasCaducidadPwd;
	}

	public void setRolDiasCaducidadPwd(Long rolDiasCaducidadPwd) {
		this.rolDiasCaducidadPwd = rolDiasCaducidadPwd;
	}


	@Column(name="ROL_ESTADO_REGISTRO", nullable=false, length=1)
	public String getRolEstadoRegistro() {
		return this.rolEstadoRegistro;
	}

	public void setRolEstadoRegistro(String rolEstadoRegistro) {
		this.rolEstadoRegistro = rolEstadoRegistro;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="segRol")
	public Set<SegPermiso> getSegPermisos() {
		return this.segPermisos;
	}

	public void setSegPermisos(Set<SegPermiso> segPermisos) {
		this.segPermisos = segPermisos;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="segRol")
	public Set<SegRolUsuario> getSegRolUsuarios() {
		return this.segRolUsuarios;
	}

	public void setSegRolUsuarios(Set<SegRolUsuario> segRolUsuarios) {
		this.segRolUsuarios = segRolUsuarios;
	}




}


