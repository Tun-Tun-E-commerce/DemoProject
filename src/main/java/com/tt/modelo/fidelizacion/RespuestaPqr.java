package com.tt.modelo.fidelizacion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "respuestaspqrs")
public class RespuestaPqr implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "respuesta")
	private String respuesta;
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column(name = "estado", length = 10)
	private String estado;
	@Column(name = "nombreAdministrador", length = 20)
	private String nombreAdministrador;
	@ManyToOne
	@JoinColumn(name = "idPqr")
	private Pqr idPqr;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombreAdministrador() {
		return nombreAdministrador;
	}
	public void setNombreAdministrador(String nombreAdministrador) {
		this.nombreAdministrador = nombreAdministrador;
	}
	public Pqr getIdPqr() {
		return idPqr;
	}
	public void setIdPqr(Pqr idPqr) {
		this.idPqr = idPqr;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
