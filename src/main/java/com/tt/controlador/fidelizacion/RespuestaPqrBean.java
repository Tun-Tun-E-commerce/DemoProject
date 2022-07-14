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
import com.tt.implementacion.fidelizacion.RespuestaPqrImp;
import com.tt.modelo.fidelizacion.Pqr;
import com.tt.modelo.fidelizacion.RespuestaPqr;
import com.tt.utilidades.fidelizacion.ExportarExcelRespuestaPqr;

@ManagedBean(name = "rPqrBean")
@RequestScoped
public class RespuestaPqrBean {

	RespuestaPqr respuestaPqr = new RespuestaPqr();
	List<RespuestaPqr> listaRespuestaPqr = new ArrayList<RespuestaPqr>();
	List<Pqr> listaPqr = new ArrayList<Pqr>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idPqr;

	private void LlenarPqr() {
		PqrImp pqrImp = new PqrImp();
		this.listaPqr = pqrImp.encontrarTodo();
	}

	public RespuestaPqr getRespuestaPqr() {
		return respuestaPqr;
	}

	public void setRespuestaPqr(RespuestaPqr respuestaPqr) {
		this.respuestaPqr = respuestaPqr;
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

	public List<RespuestaPqr> getListaRespuestaPqr() {
		return listaRespuestaPqr;
	}

	public void setListaRespuestaPqr(List<RespuestaPqr> listaRespuestaPqr) {
		this.listaRespuestaPqr = listaRespuestaPqr;
	}

	public RespuestaPqrBean() {
		this.LlenarPqr();
	}

	public List<RespuestaPqr> encontrarTodo() {
		RespuestaPqrImp rpqrImp = new RespuestaPqrImp();
		this.listaRespuestaPqr = rpqrImp.encontrarTodo();
		return this.listaRespuestaPqr;
	}

	public String agregar() {
		RespuestaPqrImp rPqrImp = new RespuestaPqrImp();
		PqrImp pqrImp = new PqrImp();
		Pqr pqr = new Pqr();
		pqr = pqrImp.econtrarId(idPqr);
		respuestaPqr.setIdPqr(pqr);
		rPqrImp.agregar(respuestaPqr);
		return "/faces/Admin/RespuestaPQR.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		RespuestaPqrImp rPqrImp = new RespuestaPqrImp();
		respuestaPqr = rPqrImp.econtrarId(id);
		this.sessionMap.put("respuestaPqr", respuestaPqr);
		return "/faces/Admin/editar/editarRESPUESTA.xhtml?faces-redirect=true";
	}

	public String actualizar(RespuestaPqr respuestaPqr) {
		System.out.println("Entro a actualizar Respuesta Pqr");
		RespuestaPqrImp rPqrImp = new RespuestaPqrImp();
		PqrImp pqrImp = new PqrImp();
		Pqr pqr = new Pqr();
		pqr = pqrImp.econtrarId(respuestaPqr.getIdPqr().getId());
		System.out.println("Pqr" + pqr.toString());
		respuestaPqr.setIdPqr(pqr);
		rPqrImp.actualizar(respuestaPqr);
		return "/faces/Admin/RespuestaPQR.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		RespuestaPqrImp rPqrImp = new RespuestaPqrImp();
		rPqrImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/RespuestaPQR.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaRespuestaPqr " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		RespuestaPqrImp rImp = new RespuestaPqrImp();
		if (idPqr != 0) {
			this.listaRespuestaPqr = rImp.exportarPqr(idPqr);
		} else {
			this.listaRespuestaPqr = rImp.encontrarTodo();
		}

		ExportarExcelRespuestaPqr excelExportar = new ExportarExcelRespuestaPqr(this.listaRespuestaPqr);
		excelExportar.export(response);
	}

}
