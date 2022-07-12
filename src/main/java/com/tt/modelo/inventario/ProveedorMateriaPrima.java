package com.tt.modelo.inventario;

import java.io.Serializable;
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

import com.tt.modelo.venta.Producto;

@Entity
@Table(name = "proveedoresmateriaprima")
public class ProveedorMateriaPrima implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombre", length = 30)
	private String nombre;
	@Column(name = "telefono")
	private int telefono;
	@Column(name = "direccion", length = 40)
	private String direccion;
	@ManyToOne
	@JoinColumn(name = "idMateriaPrima")
	private MateriaPrima idMateriaPrima;

	@OneToMany(targetEntity = Producto.class, mappedBy = "idProveedorMateria")
	private List<Producto> listaProductos;

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

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public MateriaPrima getIdMateriaPrima() {
		return idMateriaPrima;
	}

	public void setIdMateriaPrima(MateriaPrima idMateriaPrima) {
		this.idMateriaPrima = idMateriaPrima;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
