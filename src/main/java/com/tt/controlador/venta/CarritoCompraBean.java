package com.tt.controlador.venta;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.tt.implementacion.usuario.UsuarioImp;
import com.tt.implementacion.venta.CarritoCompraImp;
import com.tt.implementacion.venta.ProductoImp;
import com.tt.modelo.usuario.Usuario;
import com.tt.modelo.venta.CarritoCompra;
import com.tt.modelo.venta.Producto;
import com.tt.utilidades.venta.ExportarExcelCarritoCompra;

@ManagedBean(name = "ccBean")
@RequestScoped
public class CarritoCompraBean {
	CarritoCompra c = new CarritoCompra();
	List<CarritoCompra> listaCarritoCompra = new ArrayList<CarritoCompra>();
	List<Producto> listaProducto = new ArrayList<Producto>();
	List<Usuario> listaUsuario = new ArrayList<Usuario>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idProducto;

	private int idUsuario;

	private void LlenarProducto() {
		ProductoImp pImp = new ProductoImp();
		this.listaProducto = pImp.encontrarTodo();
	}

	private void LlenarUsuario() {
		UsuarioImp uImp = new UsuarioImp();
		this.listaUsuario = uImp.encontrarTodo();
	}

	public CarritoCompra getC() {
		return c;
	}

	public void setC(CarritoCompra c) {
		this.c = c;
	}

	public List<CarritoCompra> getListaCarritoCompra() {
		return listaCarritoCompra;
	}

	public void setListaCarritoCompra(List<CarritoCompra> listaCarritoCompra) {
		this.listaCarritoCompra = listaCarritoCompra;
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public CarritoCompraBean() {
		this.LlenarUsuario();
		this.LlenarProducto();
	}

	public List<CarritoCompra> encontrarTodo() {
		CarritoCompraImp cImp = new CarritoCompraImp();
		this.listaCarritoCompra = cImp.encontrarTodo();
		return this.listaCarritoCompra;
	}

	public String agregar() {
		CarritoCompraImp cImp = new CarritoCompraImp();
		ProductoImp pImp = new ProductoImp();
		UsuarioImp uImp = new UsuarioImp();
		Usuario usuario = new Usuario();
		Producto p = new Producto();
		p = pImp.econtrarId(idProducto);
		usuario = uImp.econtrarId(idUsuario);
		c.setIdProducto(p);
		c.setIdUsuarioFk(usuario);
		cImp.agregar(c);
		return "/faces/Admin/Carrito.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		CarritoCompraImp cImp = new CarritoCompraImp();
		c = cImp.econtrarId(id);
		this.sessionMap.put("c", c);
		return "/faces/Admin/editar/editarCARRITO.xhtml?faces-redirect=true";
	}

	public String actualizar(CarritoCompra c) {
		System.out.println("Entro a actualizar CarritoCompra");
		CarritoCompraImp cImp = new CarritoCompraImp();
		ProductoImp pImp = new ProductoImp();
		UsuarioImp uImp = new UsuarioImp();
		Usuario usuario = new Usuario();
		Producto p = new Producto();
		usuario = uImp.econtrarId(c.getIdUsuario().getId());
		p = pImp.econtrarId(c.getIdProducto().getId());
		System.out.println("Usuario" + usuario.toString());
		System.out.println("Producto" + p.toString());
		c.setIdProducto(p);
		c.setIdUsuarioFk(usuario);
		cImp.actualizar(c);
		return "/faces/Admin/Carrito.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		CarritoCompraImp cImp = new CarritoCompraImp();
		cImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/Carrito.xhtml?faces-redirect=true";
	}
// Mejora
	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaCarritoCompra " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		CarritoCompraImp cImp = new CarritoCompraImp();
		if (idProducto != 0) {
			this.listaCarritoCompra = cImp.exportCarritoCompra(idProducto);
		} else {
			this.listaCarritoCompra = cImp.encontrarTodo();
		}

		ExportarExcelCarritoCompra excelExportar = new ExportarExcelCarritoCompra(this.listaCarritoCompra);
		excelExportar.export(response);

	}

}
