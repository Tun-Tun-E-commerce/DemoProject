package com.tt.modelo.venta;

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

import com.tt.modelo.envio.Devolucion;
import com.tt.modelo.envio.Pedido;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "codigo", length = 50)
	private String codigo;
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column(name = "valor")
	private double valor;
	@ManyToOne
	@JoinColumn(name = "idCarrito")
	private CarritoCompra idCarrito;
	@ManyToOne
	@JoinColumn(name = "idMetodoPago")
	private MetodoPago idMetodoPago;
	@ManyToOne
	@JoinColumn(name = "idPeriodo")
	private Periodo idPeriodo;

	@OneToMany(targetEntity = Devolucion.class, mappedBy = "idFactura")
	private List<Devolucion> listaDevolucion;
	@OneToMany(targetEntity = Pedido.class, mappedBy = "idFactura")
	private List<Pedido> listaPedido;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public CarritoCompra getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(CarritoCompra idCarrito) {
		this.idCarrito = idCarrito;
	}

	public MetodoPago getIdMetodoPago() {
		return idMetodoPago;
	}

	public void setIdMetodoPago(MetodoPago idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}

	public Periodo getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Periodo idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public List<Devolucion> getListaDevolucion() {
		return listaDevolucion;
	}

	public void setListaDevolucion(List<Devolucion> listaDevolucion) {
		this.listaDevolucion = listaDevolucion;
	}

	public List<Pedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
