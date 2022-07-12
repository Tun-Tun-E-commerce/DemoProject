package com.tt.modelo.venta;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tt.modelo.envio.Almacen;
import com.tt.modelo.envio.DetallePedidoProducto;
import com.tt.modelo.envio.ProveedorEmpresa;
import com.tt.modelo.inventario.ProveedorMateriaPrima;
import com.tt.modelo.inventario.ReferenciaProducto;
import com.tt.modelo.usuario.Usuario;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombre", length = 50)
	private String nombre;
	@Column(name = "valorUnitario")
	private double valorUnitario;
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "marca", length = 50)
	private String marca;
	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario idUsuario;
	@ManyToOne
	@JoinColumn(name = "idProveedorEmpresa")
	private ProveedorEmpresa idProveedorEmpresa;
	@ManyToOne
	@JoinColumn(name = "idReferenciaProducto")
	private ReferenciaProducto idReferenciaProducto;
	@ManyToOne
	@JoinColumn(name = "idProveedorMateria")
	private ProveedorMateriaPrima idProveedorMateria;

	@OneToMany(targetEntity = Almacen.class, mappedBy = "idProducto")
	private List<Almacen> listaAlmacen;
	@OneToMany(targetEntity = CarritoCompra.class, mappedBy = "idProducto")
	private List<CarritoCompra> listaCarritoCompra;
	@OneToMany(targetEntity = DetallePedidoProducto.class, mappedBy = "idProducto")
	private List<DetallePedidoProducto> listaDetallePedidoProducto;

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "productoscategorias", joinColumns = @JoinColumn(name = "idProducto", nullable = false), inverseJoinColumns = @JoinColumn(name = "idCategoria", nullable = false))
	private List<Categoria> listaCategoria;

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

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public ProveedorEmpresa getIdProveedorEmpresa() {
		return idProveedorEmpresa;
	}

	public void setIdProveedorEmpresa(ProveedorEmpresa idProveedorEmpresa) {
		this.idProveedorEmpresa = idProveedorEmpresa;
	}

	public ReferenciaProducto getIdReferenciaProducto() {
		return idReferenciaProducto;
	}

	public void setIdReferenciaProducto(ReferenciaProducto idReferenciaProducto) {
		this.idReferenciaProducto = idReferenciaProducto;
	}

	public ProveedorMateriaPrima getIdProveedorMateria() {
		return idProveedorMateria;
	}

	public void setIdProveedorMateria(ProveedorMateriaPrima idProveedorMateria) {
		this.idProveedorMateria = idProveedorMateria;
	}

	public List<Almacen> getListaAlmacen() {
		return listaAlmacen;
	}

	public void setListaAlmacen(List<Almacen> listaAlmacen) {
		this.listaAlmacen = listaAlmacen;
	}

	public List<CarritoCompra> getListaCarritoCompra() {
		return listaCarritoCompra;
	}

	public void setListaCarritoCompra(List<CarritoCompra> listaCarritoCompra) {
		this.listaCarritoCompra = listaCarritoCompra;
	}

	public List<DetallePedidoProducto> getListaDetallePedidoProducto() {
		return listaDetallePedidoProducto;
	}

	public void setListaDetallePedidoProducto(List<DetallePedidoProducto> listaDetallePedidoProducto) {
		this.listaDetallePedidoProducto = listaDetallePedidoProducto;
	}

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
