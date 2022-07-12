package com.tt.modelo.envio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tt.modelo.venta.Producto;

@Entity
@Table(name = "almacenes")
public class Almacen implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "cantidad")
	private int cantidad;

	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto idProducto;

	@ManyToOne
	@JoinColumn(name = "idProveedorEmpresa")
	private ProveedorEmpresa idProveedorEmpresa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Producto idProducto) {
		this.idProducto = idProducto;
	}

	public ProveedorEmpresa getIdProveedorEmpresa() {
		return idProveedorEmpresa;
	}

	public void setIdProveedorEmpresa(ProveedorEmpresa idProveedorEmpresa) {
		this.idProveedorEmpresa = idProveedorEmpresa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
