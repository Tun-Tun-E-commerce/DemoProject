package com.tt.modelo.inventario;

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
@Table(name = "materiasprimas")
public class MateriaPrima implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombre", length = 30)
	private String nombre;
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "valor")
	private double valor;
	@Column(name = "descripcion")
	private String descripcion;

	@OneToMany(targetEntity = InventarioMateriaPrima.class, mappedBy = "idMateriaPrima")
	private List<InventarioMateriaPrima> listaInventarioPrima;
	@OneToMany(targetEntity = ProveedorMateriaPrima.class, mappedBy = "idMateriaPrima")
	private List<ProveedorMateriaPrima> listaProveedorMateriaPrima;
	@OneToMany(targetEntity = DetalleOrdenProduccion.class, mappedBy = "idMateriaPrima")
	private List<DetalleOrdenProduccion> listaDetalleOrdenProduccion;
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
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<InventarioMateriaPrima> getListaInventarioPrima() {
		return listaInventarioPrima;
	}
	public void setListaInventarioPrima(List<InventarioMateriaPrima> listaInventarioPrima) {
		this.listaInventarioPrima = listaInventarioPrima;
	}
	public List<ProveedorMateriaPrima> getListaProveedorMateriaPrima() {
		return listaProveedorMateriaPrima;
	}
	public void setListaProveedorMateriaPrima(List<ProveedorMateriaPrima> listaProveedorMateriaPrima) {
		this.listaProveedorMateriaPrima = listaProveedorMateriaPrima;
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
