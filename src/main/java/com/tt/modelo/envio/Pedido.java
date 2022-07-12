package com.tt.modelo.envio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.tt.modelo.venta.Factura;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaPedido")
	private Date fechaPedido;
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaEntrega")
	private Date fechaEntrega;
	@Column(name = "fechaEnvio")
	@Temporal(TemporalType.DATE)
	private Date fechaEnvio;
	@Column(name = "formaEnvio", length = 50)
	private String formaEnvio;
	@Column(name = "destinatario", length = 45)
	private String destinatario;
	@Column(name = "direccion", length = 100)
	private String direccion;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "idFactura", referencedColumnName = "id")
	private Factura idFactura;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "idEnvio", referencedColumnName = "id")
	private Envio idEnvio;

	@OneToMany(targetEntity = DetallePedidoProducto.class, mappedBy = "idPedido")
	private List<DetallePedidoProducto> listaDetallePedidoProducto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getFormaEnvio() {
		return formaEnvio;
	}

	public void setFormaEnvio(String formaEnvio) {
		this.formaEnvio = formaEnvio;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Factura getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Factura idFactura) {
		this.idFactura = idFactura;
	}

	public Envio getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(Envio idEnvio) {
		this.idEnvio = idEnvio;
	}

	public List<DetallePedidoProducto> getListaDetallePedidoProducto() {
		return listaDetallePedidoProducto;
	}

	public void setListaDetallePedidoProducto(List<DetallePedidoProducto> listaDetallePedidoProducto) {
		this.listaDetallePedidoProducto = listaDetallePedidoProducto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
