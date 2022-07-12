package com.tt.modelo.envio;

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
@Table(name = "companiaenvios")
public class CompaniaEnvio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombre", length = 50)
	private String nombre;
	@Column(name = "telefono", length = 50)
	private String telefono;

	@OneToMany(targetEntity = Envio.class, mappedBy = "idCompaniaEnvio")
	private List<Envio> listaEnvio;

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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Envio> getListaEnvio() {
		return listaEnvio;
	}

	public void setListaEnvio(List<Envio> listaEnvio) {
		this.listaEnvio = listaEnvio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
