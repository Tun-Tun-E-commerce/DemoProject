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

import com.tt.implementacion.fidelizacion.CalificacionImp;
import com.tt.implementacion.fidelizacion.PqrImp;

import com.tt.modelo.fidelizacion.Calificacion;
import com.tt.modelo.fidelizacion.Pqr;

import com.tt.utilidades.fidelizacion.ExportarExcelCalificacion;

@ManagedBean(name = "CalificacionBean")
@RequestScoped
public class CalificacionBean {
	Calificacion calificacion = new Calificacion();
	List<Calificacion> listaCalificacion = new ArrayList<Calificacion>();
	List<Pqr> listaPqr = new ArrayList<Pqr>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idPqr;

	private void LlenarPqr() {
		PqrImp pImp = new PqrImp();
		this.listaPqr = pImp.encontrarTodo();
	}

	public Calificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}

	public List<Calificacion> getListaCalificacion() {
		return listaCalificacion;
	}

	public void setListaCalificacion(List<Calificacion> listaCalificacion) {
		this.listaCalificacion = listaCalificacion;
	}

	public List<Pqr> getListaPqr() {
		return listaPqr;
	}

	public void setListaPqr(List<Pqr> listaPqr) {
		this.listaPqr = listaPqr;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public int getIdPqr() {
		return idPqr;
	}

	public void setIdPqr(int idPqr) {
		this.idPqr = idPqr;
	}

	public CalificacionBean() {
		this.LlenarPqr();

	}

	public List<Calificacion> encontrarTodo() {
		CalificacionImp cImp = new CalificacionImp();
		this.listaCalificacion = cImp.encontrarTodo();
		return this.listaCalificacion;
	}

	public String agregar() {
		CalificacionImp cImp = new CalificacionImp();
		PqrImp pImp = new PqrImp();
		Pqr pqr = new Pqr();
		pqr = pImp.econtrarId(idPqr);
		calificacion.setIdPqr(pqr);
		cImp.agregar(calificacion);
		return "/faces/Admin/calificacion.xhtml?faces-redirect=true";
	}

	public String econtrarId(int id) {
		System.out.println("Entro al editar" + id);
		CalificacionImp cImp = new CalificacionImp();
		calificacion = cImp.econtrarId(id);
		this.sessionMap.put("calificacion", calificacion);
		return "/faces/Admin/editar/calificacion.xhtml?faces-redirect=true";
	}

	public String actualizar(Calificacion calificacion) {
		System.out.println("Entro a actualizar Calificacion");
		CalificacionImp cImp = new CalificacionImp();
		PqrImp pImp = new PqrImp();
		Pqr pqr = new Pqr();
		pqr = pImp.econtrarId(calificacion.getIdPqr().getId());
		calificacion.setIdPqr(pqr);
		cImp.actualizar(calificacion);
		return "/faces/Admin/calificacion.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		CalificacionImp cImp = new CalificacionImp();
		cImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/calificacion.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaPqr " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		CalificacionImp cImp = new CalificacionImp();

		if (idPqr != 0) {
			this.listaCalificacion = cImp.exportarPqr(idPqr);
		} else {
			this.listaCalificacion = cImp.encontrarTodo();
		}

		ExportarExcelCalificacion excelExportar = new ExportarExcelCalificacion(this.listaCalificacion);
		excelExportar.export(response);

	}

}
