package com.tt.modelo.usuario;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tt.modelo.envio.Envio;
import com.tt.modelo.fidelizacion.Pqr;
import com.tt.modelo.venta.CarritoCompra;
import com.tt.modelo.venta.Producto;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombre", length = 50)
	private String nombre;
	@Column(name = "apellido", length = 50)
	private String apellido;
	@Column(name = "contrasena", length = 70)
	private String contrasena;
	@Column(name = "telefono", length = 50)
	private String telefono;
	@Column(name = "direccion", length = 100)
	private String direccion;
	@Column(name = "correo", length = 50)
	private String correo;
	@Column(name = "identificacion")
	private int identificacion;
	@Column(name = "fechaNacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "idTipoDocumento", referencedColumnName = "id")
	private TipoDocumento idTipoDocumento;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "idRol", referencedColumnName = "id")
	private Rol idRol;

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "usuariospqrs", joinColumns = @JoinColumn(name = "idUsuario", nullable = false), inverseJoinColumns = @JoinColumn(name = "idPqr", nullable = false))
	private List<Pqr> listaPqr;

	@OneToMany(targetEntity = Producto.class, mappedBy = "idUsuario")
	private List<Producto> listaProducto;

	@OneToMany(targetEntity = CarritoCompra.class, mappedBy = "idUsuario")
	private List<CarritoCompra> listaCarrito;

	@OneToMany(targetEntity = Envio.class, mappedBy = "idUsuario")
	private List<Envio> listaEnvio;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public TipoDocumento getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(TipoDocumento idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public Rol getIdRol() {
		return idRol;
	}

	public void setIdRol(Rol idRol) {
		this.idRol = idRol;
	}

	public List<Pqr> getListaPqr() {
		return listaPqr;
	}

	public void setListaPqr(List<Pqr> listaPqr) {
		this.listaPqr = listaPqr;
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public List<CarritoCompra> getListaCarrito() {
		return listaCarrito;
	}

	public void setListaCarrito(List<CarritoCompra> listaCarrito) {
		this.listaCarrito = listaCarrito;
	}

	public List<Envio> getListaEnvio() {
		return listaEnvio;
	}

	public void setListaEnvio(List<Envio> listaEnvio) {
		this.listaEnvio = listaEnvio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
}
