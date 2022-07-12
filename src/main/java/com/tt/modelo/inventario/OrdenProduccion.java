package com.tt.modelo.inventario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ordenproducciones")
public class OrdenProduccion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "observacion")
	private String observacion;
	@ManyToOne
	@JoinColumn(name = "idInventarioMateriaPrima")
	private InventarioMateriaPrima idInventarioMateriaPrima;

	@OneToMany(targetEntity = DetalleOrdenProduccion.class, mappedBy = "idOrdenProduccion")
	private List<DetalleOrdenProduccion> listaDetalleOrdenProduccion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public InventarioMateriaPrima getIdInventarioMateriaPrima() {
		return idInventarioMateriaPrima;
	}

	public void setIdInventarioMateriaPrima(InventarioMateriaPrima idInventarioMateriaPrima) {
		this.idInventarioMateriaPrima = idInventarioMateriaPrima;
	}

	public List<DetalleOrdenProduccion> getListaDetalleOrdenProduccion() {
		return listaDetalleOrdenProduccion;
	}

	public void setListaDetalleOrdenProduccion(List<DetalleOrdenProduccion> listaDetalleOrdenProduccion) {
		this.listaDetalleOrdenProduccion = listaDetalleOrdenProduccion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
