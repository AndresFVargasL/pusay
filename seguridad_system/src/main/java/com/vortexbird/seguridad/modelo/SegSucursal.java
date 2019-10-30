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
 * SegSucursal generated by Zathura powered by Hibernate-tools(hbm2java)
 */
@Entity
@Table(name="SEG_SUCURSAL"
,schema="PUBLIC"
		)
public class SegSucursal  implements java.io.Serializable {


	private Long sucCodigo;
	private SegUsuario segUsuario;
	private SegCompania segCompania;
	private String sucCodigoInterno;
	private String sucNombre;
	private String sucEstadoRegistro;
	private Set<SegPermiso> segPermisos = new HashSet<SegPermiso>(0);

	public SegSucursal() {
	}


	public SegSucursal(Long sucCodigo, SegCompania segCompania, String sucCodigoInterno, String sucNombre, String sucEstadoRegistro) {
		this.sucCodigo = sucCodigo;
		this.segCompania = segCompania;
		this.sucCodigoInterno = sucCodigoInterno;
		this.sucNombre = sucNombre;
		this.sucEstadoRegistro = sucEstadoRegistro;
	}
	public SegSucursal(Long sucCodigo, SegUsuario segUsuario, SegCompania segCompania, String sucCodigoInterno, String sucNombre, String sucEstadoRegistro, Set<SegPermiso> segPermisos) {
		this.sucCodigo = sucCodigo;
		this.segUsuario = segUsuario;
		this.segCompania = segCompania;
		this.sucCodigoInterno = sucCodigoInterno;
		this.sucNombre = sucNombre;
		this.sucEstadoRegistro = sucEstadoRegistro;
		this.segPermisos = segPermisos;
	}

	@Id 
	@SequenceGenerator(name = "seg_sucursal_suc_codigo_seq", sequenceName = "seg_sucursal_suc_codigo_seq")
	@GeneratedValue(generator = "seg_sucursal_suc_codigo_seq", strategy = GenerationType.SEQUENCE)
	@Column(name="SUC_CODIGO", unique=true, nullable=false)
	public Long getSucCodigo() {
		return this.sucCodigo;
	}

	public void setSucCodigo(Long sucCodigo) {
		this.sucCodigo = sucCodigo;
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
	@JoinColumn(name="CIA_CODIGO", nullable=false)
	public SegCompania getSegCompania() {
		return this.segCompania;
	}

	public void setSegCompania(SegCompania segCompania) {
		this.segCompania = segCompania;
	}


	@Column(name="SUC_CODIGO_INTERNO", nullable=false, length=15)
	public String getSucCodigoInterno() {
		return this.sucCodigoInterno;
	}

	public void setSucCodigoInterno(String sucCodigoInterno) {
		this.sucCodigoInterno = sucCodigoInterno;
	}


	@Column(name="SUC_NOMBRE", nullable=false, length=100)
	public String getSucNombre() {
		return this.sucNombre;
	}

	public void setSucNombre(String sucNombre) {
		this.sucNombre = sucNombre;
	}


	@Column(name="SUC_ESTADO_REGISTRO", nullable=false, length=1)
	public String getSucEstadoRegistro() {
		return this.sucEstadoRegistro;
	}

	public void setSucEstadoRegistro(String sucEstadoRegistro) {
		this.sucEstadoRegistro = sucEstadoRegistro;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="segSucursal")
	public Set<SegPermiso> getSegPermisos() {
		return this.segPermisos;
	}

	public void setSegPermisos(Set<SegPermiso> segPermisos) {
		this.segPermisos = segPermisos;
	}




}


