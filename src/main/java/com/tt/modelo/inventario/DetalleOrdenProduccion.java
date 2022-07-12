package com.tt.modelo.inventario;

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
@Table(name = "detallesordenproduccion")
public class DetalleOrdenProduccion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "direccion", length = 100)
	private String direccion;
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@ManyToOne
	@JoinColumn(name = "idOrdenProduccion")
	private OrdenProduccion idOrdenProduccion;
	@ManyToOne
	@JoinColumn(name = "idMateriaPrima")
	private MateriaPrima idMateriaPrima;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public OrdenProduccion getIdOrdenProduccion() {
		return idOrdenProduccion;
	}
	public void setIdOrdenProduccion(OrdenProduccion idOrdenProduccion) {
		this.idOrdenProduccion = idOrdenProduccion;
	}
	public MateriaPrima getIdMateriaPrima() {
		return idMateriaPrima;
	}
	public void setIdMateriaPrima(MateriaPrima idMateriaPrima) {
		this.idMateriaPrima = idMateriaPrima;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

}
