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

import com.tt.modelo.venta.Producto;

@Entity
@Table(name = "proveedoresempresa")
public class ProveedorEmpresa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombre", length = 45)
	private String nombre;
	@Column(name = "direccion", length = 45)
	private String direccion;
	@Column(name = "telefono", length = 50)
	private int telefono;
	@Column(name = "observaciones")
	private String observaciones;

	@OneToMany(targetEntity = Producto.class, mappedBy = "idProveedorEmpresa")
	private List<Producto> listaProducto;

	@OneToMany(targetEntity = Almacen.class, mappedBy = "idProveedorEmpresa")
	private List<Almacen> listaAlmacen;

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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public List<Almacen> getListaAlmacen() {
		return listaAlmacen;
	}

	public void setListaAlmacen(List<Almacen> listaAlmacen) {
		this.listaAlmacen = listaAlmacen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
