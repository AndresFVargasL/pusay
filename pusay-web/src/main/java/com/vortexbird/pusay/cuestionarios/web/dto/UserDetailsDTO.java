package com.vortexbird.pusay.cuestionarios.web.dto;

public class UserDetailsDTO {// extends User {

	/**
	 * public UserDetailsDTO(String username, String password, boolean enabled, boolean accountNonExpired, boolean
	 * credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities, String login) {
	 * super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	 * this.nombre = login; }
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;

	/**
	 * @author <a href="mailto:manuel.galvez@premize.com">Manuel Alejandro Galvez</a>
	 * @date May 2, 2012
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @author <a href="mailto:manuel.galvez@premize.com">Manuel Alejandro Galvez</a>
	 * @date May 2, 2012
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
