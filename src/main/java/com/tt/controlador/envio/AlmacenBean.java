package com.tt.controlador.envio;

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

import com.tt.implementacion.envio.AlmacenImp;
import com.tt.implementacion.envio.ProveedorEmpresaImp;
import com.tt.implementacion.venta.ProductoImp;
import com.tt.modelo.envio.Almacen;
import com.tt.modelo.envio.ProveedorEmpresa;
import com.tt.modelo.venta.Producto;
import com.tt.utilidades.envio.ExportarExcelAlmacen;

@ManagedBean(name = "aBean")
@RequestScoped
public class AlmacenBean {
	Almacen a = new Almacen();
	List<Almacen> listaAlmacen = new ArrayList<Almacen>();
	List<Producto> listaProducto = new ArrayList<Producto>();
	List<ProveedorEmpresa> listaProveedor = new ArrayList<ProveedorEmpresa>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idProducto;
	private int idProveedor;

	private void LlenarProducto() {
		ProductoImp proImp = new ProductoImp();
		this.listaProducto = proImp.encontrarTodo();
	}

	private void LlenarProveedor() {
		ProveedorEmpresaImp peImp = new ProveedorEmpresaImp();
		this.listaProveedor = peImp.encontrarTodo();
	}

	public Almacen getA() {
		return a;
	}

	public void setA(Almacen a) {
		this.a = a;
	}

	public List<Almacen> getListaAlmacen() {
		return listaAlmacen;
	}

	public void setListaAlmacen(List<Almacen> listaAlmacen) {
		this.listaAlmacen = listaAlmacen;
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public List<ProveedorEmpresa> getListaProveedor() {
		return listaProveedor;
	}

	public void setListaProveedor(List<ProveedorEmpresa> listaProveedor) {
		this.listaProveedor = listaProveedor;
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

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public AlmacenBean() {
		this.LlenarProducto();
		this.LlenarProveedor();
	}

	public List<Almacen> encontrarTodo() {
		AlmacenImp aImp = new AlmacenImp();
		this.listaAlmacen = aImp.encontrarTodo();
		return this.listaAlmacen;
	}

	public String agregar() {
		AlmacenImp aImp = new AlmacenImp();
		ProductoImp pImp = new ProductoImp();
		ProveedorEmpresaImp peImp = new ProveedorEmpresaImp();
		Producto p = new Producto();
		ProveedorEmpresa pe = new ProveedorEmpresa();
		p = pImp.econtrarId(idProducto);
		pe = peImp.econtrarId(idProveedor);
		a.setIdProducto(p);
		a.setIdProveedorEmpresa(pe);
		aImp.agregar(a);
		return "/faces/Admin/almacenes.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		AlmacenImp aImp = new AlmacenImp();
		a = aImp.econtrarId(id);
		this.sessionMap.put("a", a);
		return "/faces/Admin/editar/envio/almacen.xhtml?faces-redirect=true";
	}

	public String actualizar(Almacen a) {
		System.out.println("Entro a actualizar Almacen");
		AlmacenImp aImp = new AlmacenImp();
		ProductoImp pImp = new ProductoImp();
		ProveedorEmpresaImp peImp = new ProveedorEmpresaImp();
		Producto p = new Producto();
		ProveedorEmpresa pe = new ProveedorEmpresa();
		p = pImp.econtrarId(a.getIdProducto().getId());
		pe = peImp.econtrarId(a.getIdProveedorEmpresa().getId());
		System.out.println("Producto" + p.toString());
		System.out.println("Proveedor" + pe.toString());
		a.setIdProducto(p);
		a.setIdProveedorEmpresa(pe);
		aImp.actualizar(a);
		return "/faces/Admin/almacenes.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		AlmacenImp aImp = new AlmacenImp();
		aImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/almacenes.xhtml?faces-redirect=true";
	}
	
	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaAlmacen " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		AlmacenImp aImp = new AlmacenImp();
		if(idProducto !=0) {
			this.listaAlmacen = aImp.exportAlmacen(idProducto);
		}else {
			this.listaAlmacen = aImp.encontrarTodo();
		}

		
		ExportarExcelAlmacen excelExportar = new ExportarExcelAlmacen(this.listaAlmacen);
		excelExportar.export(response);

	}

}
