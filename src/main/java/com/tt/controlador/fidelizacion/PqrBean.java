package com.tt.controlador.fidelizacion;

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

import com.tt.implementacion.fidelizacion.PqrImp;
import com.tt.modelo.fidelizacion.Pqr;
import com.tt.utilidades.fidelizacion.ExportarExcelPqr;

@ManagedBean(name = "pqrBean")
@RequestScoped
public class PqrBean {
	Pqr pqr = new Pqr();
	List<Pqr> listPqr = new ArrayList<Pqr>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public Pqr getPqr() {
		return pqr;
	}

	public void setPqr(Pqr pqr) {
		this.pqr = pqr;
	}

	public List<Pqr> getListPqr() {
		return listPqr;
	}

	public void setListPqr(List<Pqr> listPqr) {
		this.listPqr = listPqr;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<Pqr> encontrarTodo() {
		PqrImp pqrImp = new PqrImp();
		this.listPqr = pqrImp.encontrarTodo();
		return this.listPqr;
	}

	public String agregar() {
		PqrImp pqrImp = new PqrImp();
		pqrImp.agregar(pqr);
		return "/faces/Admin/PQRS.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		PqrImp pqrImp = new PqrImp();
		pqr = pqrImp.econtrarId(id);
		this.sessionMap.put("pqr", pqr);
		return "/faces/Admin/editar/editarPQR.xhtml?faces-redirect=true";
	}

	public String actualizar(Pqr pqr) {
		System.out.println("Entro a actualizar Rol");
		PqrImp pqrImp = new PqrImp();
		pqrImp.actualizar(pqr);
		return "/faces/Admin/PQRS.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		PqrImp pqrImp = new PqrImp();
		pqrImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/PQRS.xhtml?faces-redirect=true";
	}
	
	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaPqr " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		PqrImp pqrImp = new PqrImp();
		this.listPqr = pqrImp.encontrarTodo();
		
		ExportarExcelPqr excelExportar = new ExportarExcelPqr(this.listPqr);
		excelExportar.export(response);

	}

}
