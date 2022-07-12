package com.tt.modelo.venta;

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

import com.tt.modelo.usuario.Usuario;

@Entity
@Table(name = "carritocompras")
public class CarritoCompra implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "subTotal")
	private double subTotal;
	@Column(name = "cantidad")
	private int cantidad;
	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto idProducto;
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario idUsuario;

	@OneToMany(targetEntity = Factura.class, mappedBy = "idCarrito")
	private List<Factura> listaFactura;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
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

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuarioFk(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Factura> getListaFactura() {
		return listaFactura;
	}

	public void setListaFactura(List<Factura> listaFactura) {
		this.listaFactura = listaFactura;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
