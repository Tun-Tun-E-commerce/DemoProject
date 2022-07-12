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

import com.tt.implementacion.envio.ProveedorEmpresaImp;
import com.tt.implementacion.inventario.ProveedorMateriaPrimaImp;
import com.tt.implementacion.inventario.ReferenciaProductoImp;
import com.tt.implementacion.usuario.UsuarioImp;
import com.tt.implementacion.venta.ProductoImp;
import com.tt.modelo.envio.ProveedorEmpresa;
import com.tt.modelo.inventario.ProveedorMateriaPrima;
import com.tt.modelo.inventario.ReferenciaProducto;
import com.tt.modelo.usuario.Usuario;
import com.tt.modelo.venta.Producto;
import com.tt.utilidades.venta.ExportarExcelProducto;

@ManagedBean(name = "proBean")
@RequestScoped
public class ProductoBean {
	Producto p = new Producto();
	List<Producto> listaProducto = new ArrayList<Producto>();
	List<Usuario> listaUsuario = new ArrayList<Usuario>();
	List<ProveedorEmpresa> listaProveedorE = new ArrayList<ProveedorEmpresa>();
	List<ReferenciaProducto> listaReferencia = new ArrayList<ReferenciaProducto>();
	List<ProveedorMateriaPrima> listaProveedorM = new ArrayList<ProveedorMateriaPrima>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idProductoValor;

	private int idProductoCantidad;

	private int idUsuario;

	private int idProveedorE;

	private int idReferenciaP;

	private int idProveedorM;

	private void LlenarProducto() {
		ProductoImp pImp = new ProductoImp();
		this.listaProducto = pImp.encontrarTodo();
	}

	private void LlenarUsuario() {
		UsuarioImp uImp = new UsuarioImp();
		this.listaUsuario = uImp.encontrarTodo();
	}

	private void LlenarProveedorE() {
		ProveedorEmpresaImp peImp = new ProveedorEmpresaImp();
		this.listaProveedorE = peImp.encontrarTodo();
	}

	private void LlenarReferencia() {
		ReferenciaProductoImp rfImp = new ReferenciaProductoImp();
		this.listaReferencia = rfImp.encontrarTodo();
	}

	private void LlenarProveedorM() {
		ProveedorMateriaPrimaImp pmImp = new ProveedorMateriaPrimaImp();
		this.listaProveedorM = pmImp.encontrarTodo();
	}

	public Producto getP() {
		return p;
	}

	public void setP(Producto p) {
		this.p = p;
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

	public List<ProveedorEmpresa> getListaProveedorE() {
		return listaProveedorE;
	}

	public void setListaProveedorE(List<ProveedorEmpresa> listaProveedorE) {
		this.listaProveedorE = listaProveedorE;
	}

	public List<ReferenciaProducto> getListaReferencia() {
		return listaReferencia;
	}

	public void setListaReferencia(List<ReferenciaProducto> listaReferencia) {
		this.listaReferencia = listaReferencia;
	}

	public List<ProveedorMateriaPrima> getListaProveedorM() {
		return listaProveedorM;
	}

	public void setListaProveedorM(List<ProveedorMateriaPrima> listaProveedorM) {
		this.listaProveedorM = listaProveedorM;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdProveedorE() {
		return idProveedorE;
	}

	public void setIdProveedorE(int idProveedorE) {
		this.idProveedorE = idProveedorE;
	}

	public int getIdReferenciaP() {
		return idReferenciaP;
	}

	public void setIdReferenciaP(int idReferenciaP) {
		this.idReferenciaP = idReferenciaP;
	}

	public int getIdProveedorM() {
		return idProveedorM;
	}

	public void setIdProveedorM(int idProveedorM) {
		this.idProveedorM = idProveedorM;
	}

	public int getIdProductoValor() {
		return idProductoValor;
	}

	public int getIdProductoCantidad() {
		return idProductoCantidad;
	}

	public void setIdProductoCantidad(int idProductoCantidad) {
		this.idProductoCantidad = idProductoCantidad;
	}

	public void setIdProductoValor(int idProductoValor) {
		this.idProductoValor = idProductoValor;
	}

	public ProductoBean() {
		this.LlenarProducto();
		this.LlenarUsuario();
		this.LlenarProveedorE();
		this.LlenarReferencia();
		this.LlenarProveedorM();
	}

	public List<Producto> encontrarTodo() {
		ProductoImp pImp = new ProductoImp();
		this.listaProducto = pImp.encontrarTodo();
		return this.listaProducto;
	}

	public String agregar() {
		ProductoImp pImp = new ProductoImp();
		UsuarioImp uImp = new UsuarioImp();
		ProveedorEmpresaImp peImp = new ProveedorEmpresaImp();
		ReferenciaProductoImp rfImp = new ReferenciaProductoImp();
		ProveedorMateriaPrimaImp mImp = new ProveedorMateriaPrimaImp();
		Usuario usuario = new Usuario();
		ProveedorEmpresa proveedorE = new ProveedorEmpresa();
		ReferenciaProducto rf = new ReferenciaProducto();
		ProveedorMateriaPrima proveedorM = new ProveedorMateriaPrima();
		usuario = uImp.econtrarId(idUsuario);
		proveedorE = peImp.econtrarId(idProveedorE);
		proveedorM = mImp.econtrarId(idProveedorM);
		rf = rfImp.econtrarId(idReferenciaP);
		p.setIdUsuario(usuario);
		p.setIdProveedorEmpresa(proveedorE);
		p.setIdReferenciaProducto(rf);
		p.setIdProveedorMateria(proveedorM);
		pImp.agregar(p);
		return "/faces/Admin/product.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		ProductoImp pImp = new ProductoImp();
		p = pImp.econtrarId(id);
		this.sessionMap.put("p", p);
		return "/faces/Admin/editar/editarPRODUCTO.xhtml?faces-redirect=true";
	}

	public String actualizar(Producto p) {
		System.out.println("Entro a actualizar Producto");
		ProductoImp pImp = new ProductoImp();
		UsuarioImp uImp = new UsuarioImp();
		ProveedorEmpresaImp peImp = new ProveedorEmpresaImp();
		ReferenciaProductoImp rfImp = new ReferenciaProductoImp();
		ProveedorMateriaPrimaImp mImp = new ProveedorMateriaPrimaImp();
		Usuario usuario = new Usuario();
		ProveedorEmpresa proveedorE = new ProveedorEmpresa();
		ReferenciaProducto rf = new ReferenciaProducto();
		ProveedorMateriaPrima proveedorM = new ProveedorMateriaPrima();
		usuario = uImp.econtrarId(p.getIdUsuario().getId());
		proveedorE = peImp.econtrarId(p.getIdProveedorEmpresa().getId());
		rf = rfImp.econtrarId(p.getIdReferenciaProducto().getId());
		proveedorM = mImp.econtrarId(p.getIdProveedorMateria().getId());
		System.out.println("Usuario" + usuario.toString());
		System.out.println("ProveedorE" + proveedorE.toString());
		System.out.println("ReferenciaP" + rf.toString());
		System.out.println("ProveedorM" + proveedorM.toString());
		p.setIdUsuario(usuario);
		p.setIdProveedorEmpresa(proveedorE);
		p.setIdReferenciaProducto(rf);
		p.setIdProveedorMateria(proveedorM);
		pImp.actualizar(p);
		return "/faces/Admin/product.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		ProductoImp pImp = new ProductoImp();
		pImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/product.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaProducto " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		ProductoImp pImp = new ProductoImp();
		if (idProductoValor != 0 && idProductoCantidad != 0) {
			this.listaProducto = pImp.exportarMulticriterio(idProductoValor, idProductoCantidad);
		} else if (idProductoValor != 0) {
			this.listaProducto = pImp.exportProductoValor(idProductoValor);
		} else if (idProductoCantidad != 0) {
			this.listaProducto = pImp.exportProductoCantidad(idProductoCantidad);
		} else {
			this.listaProducto = pImp.encontrarTodo();
		}

		ExportarExcelProducto excelExportar = new ExportarExcelProducto(this.listaProducto);
		excelExportar.export(response);
	}

}
