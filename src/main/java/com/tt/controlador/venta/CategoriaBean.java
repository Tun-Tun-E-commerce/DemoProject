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

import com.tt.implementacion.venta.CategoriaImp;
import com.tt.modelo.venta.Categoria;
import com.tt.utilidades.venta.ExportarExcelCategoria;

@ManagedBean(name = "catBean")
@RequestScoped
public class CategoriaBean {
	Categoria c = new Categoria();
	List<Categoria> listaCategoria = new ArrayList<Categoria>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public Categoria getC() {
		return c;
	}

	public void setC(Categoria c) {
		this.c = c;
	}

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<Categoria> encontrarTodo() {
		CategoriaImp cImp = new CategoriaImp();
		this.listaCategoria = cImp.encontrarTodo();
		return this.listaCategoria;
	}

	public String agregar() {
		CategoriaImp cImp = new CategoriaImp();
		cImp.agregar(c);
		return "/faces/Admin/categories.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		CategoriaImp cImp = new CategoriaImp();
		c = cImp.econtrarId(id);
		this.sessionMap.put("c", c);
		return "/faces/Admin/editar/editarCATEGORIA.xhtml?faces-redirect=true";
	}

	public String actualizar(Categoria c) {
		System.out.println("Entro a actualizar Categoria");
		CategoriaImp cImp = new CategoriaImp();
		cImp.actualizar(c);
		return "/faces/Admin/categories.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		CategoriaImp cImp = new CategoriaImp();
		cImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/categories.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaCategoria " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		CategoriaImp cImp = new CategoriaImp();
		this.listaCategoria = cImp.encontrarTodo();

		ExportarExcelCategoria excelExportar = new ExportarExcelCategoria(this.listaCategoria);
		excelExportar.export(response);

	}

}
	