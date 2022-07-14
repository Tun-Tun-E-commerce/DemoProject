package com.tt.modelo.fidelizacion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tt.modelo.usuario.Usuario;

@Entity
@Table(name = "pqrs")
public class Pqr implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "titulo", length = 20)
	private String titulo;
	@Column(name = "descripcion")
	private String descripcion;
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;
	@Column(name = "tipo", length = 50)
	private String tipo;
	@OneToMany(targetEntity = RespuestaPqr.class, mappedBy = "idPqr")
	private List<RespuestaPqr> listaRespuestaPqr;
	@OneToMany(targetEntity = Calificacion.class, mappedBy = "idPqr")
	private List<RespuestaPqr> listaCalificacion;
	@ManyToMany(mappedBy = "listaPqr", cascade = { CascadeType.MERGE })
	private List<Usuario> listaUsuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<RespuestaPqr> getListaRespuestaPqr() {
		return listaRespuestaPqr;
	}

	public void setListaRespuestaPqr(List<RespuestaPqr> listaRespuestaPqr) {
		this.listaRespuestaPqr = listaRespuestaPqr;
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

	public List<RespuestaPqr> getListaCalificacion() {
		return listaCalificacion;
	}

	public void setListaCalificacion(List<RespuestaPqr> listaCalificacion) {
		this.listaCalificacion = listaCalificacion;
	}

}
