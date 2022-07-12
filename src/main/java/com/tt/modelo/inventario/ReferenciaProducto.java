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

import com.tt.modelo.venta.Producto;

@Entity
@Table(name = "referenciaproductos")
public class ReferenciaProducto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "talla")
	private short  talla;
	@Column(name = "color", length = 30)
	private String color;

	@OneToMany(targetEntity = Producto.class, mappedBy = "idReferenciaProducto")
	private List<Producto> listaProductos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getTalla() {
		return talla;
	}

	public void setTalla(short talla) {
		this.talla = talla;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
