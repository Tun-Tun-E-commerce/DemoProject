package com.tt.modelo.usuario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombre", length = 50)
	private String nombre;
	@Column(name = "descripcion")
	private String descripcion;
	@OneToMany(targetEntity = Privilegio.class, mappedBy = "idRol")
	private List<Privilegio> listaPrivilegio;
	@OneToMany(targetEntity = Usuario.class, mappedBy = "idRol")
	private List<Usuario> listaUsuario;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Privilegio> getListaPrivilegio() {
		return listaPrivilegio;
	}
	public void setListaPrivilegio(List<Privilegio> listaPrivilegio) {
		this.listaPrivilegio = listaPrivilegio;
	}
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
