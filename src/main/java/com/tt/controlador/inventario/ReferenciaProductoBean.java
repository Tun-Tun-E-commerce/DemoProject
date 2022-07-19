package com.tt.controlador.inventario;

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

import com.tt.implementacion.inventario.ReferenciaProductoImp;
import com.tt.modelo.inventario.ReferenciaProducto;
import com.tt.utilidades.inventario.ExportarExcelReferenciaProducto;

@ManagedBean(name = "rfBean")
@RequestScoped
public class ReferenciaProductoBean {
	ReferenciaProducto referenciaProducto = new ReferenciaProducto();
	List<ReferenciaProducto> listaReferenciaProducto = new ArrayList<ReferenciaProducto>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public ReferenciaProducto getReferenciaProducto() {
		return referenciaProducto;
	}

	public void setReferenciaProducto(ReferenciaProducto referenciaProducto) {
		this.referenciaProducto = referenciaProducto;
	}

	public List<ReferenciaProducto> getListaReferenciaProducto() {
		return listaReferenciaProducto;
	}

	public void setListaReferenciaProducto(List<ReferenciaProducto> listaReferenciaProducto) {
		this.listaReferenciaProducto = listaReferenciaProducto;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<ReferenciaProducto> encontrarTodo() {
		ReferenciaProductoImp refImp = new ReferenciaProductoImp();
		this.listaReferenciaProducto = refImp.encontrarTodo();
		return this.listaReferenciaProducto;
	}

	public String agregar() {
		ReferenciaProductoImp refImp = new ReferenciaProductoImp();
		refImp.agregar(referenciaProducto);
		return "/faces/Admin/inventario/referenciaproducto.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		ReferenciaProductoImp refImp = new ReferenciaProductoImp();
		referenciaProducto = refImp.econtrarId(id);
		this.sessionMap.put("referenciaProducto", referenciaProducto);
		return "/faces/Admin/editar/inventario/referenciaproducto.xhtml?faces-redirect=true";
	}

	public String actualizar(ReferenciaProducto referenciaProducto) {
		System.out.println("Entro a actualizar Rol");
		ReferenciaProductoImp refImp = new ReferenciaProductoImp();
		refImp.actualizar(referenciaProducto);
		return "/faces/Admin/inventario/referenciaproducto.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		ReferenciaProductoImp refImp = new ReferenciaProductoImp();
		refImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/inventario/referenciaproducto.xhtml?faces-redirect=true";	
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaReferencia " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		ReferenciaProductoImp rImp = new ReferenciaProductoImp();
		this.listaReferenciaProducto = rImp.encontrarTodo();

		ExportarExcelReferenciaProducto excelExportar = new ExportarExcelReferenciaProducto(
				this.listaReferenciaProducto);
		excelExportar.export(response);
	}

}
