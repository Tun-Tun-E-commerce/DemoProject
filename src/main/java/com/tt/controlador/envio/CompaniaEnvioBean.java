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

import com.tt.implementacion.envio.CompaniaEnvioImp;
import com.tt.modelo.envio.CompaniaEnvio;
import com.tt.utilidades.envio.ExportarExcelCompaniaEnvio;

@ManagedBean(name = "ceBean")
@RequestScoped
public class CompaniaEnvioBean {
	CompaniaEnvio ce = new CompaniaEnvio();
	List<CompaniaEnvio> listaCompaniaE = new ArrayList<CompaniaEnvio>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public CompaniaEnvio getCe() {
		return ce;
	}

	public void setCe(CompaniaEnvio ce) {
		this.ce = ce;
	}

	public List<CompaniaEnvio> getListaCompaniaE() {
		return listaCompaniaE;
	}

	public void setListaCompaniaE(List<CompaniaEnvio> listaCompaniaE) {
		this.listaCompaniaE = listaCompaniaE;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<CompaniaEnvio> encontrarTodo() {
		CompaniaEnvioImp ceImp = new CompaniaEnvioImp();
		this.listaCompaniaE = ceImp.encontrarTodo();
		return this.listaCompaniaE;
	}

	public String agregar() {
		CompaniaEnvioImp ceImp = new CompaniaEnvioImp();
		ceImp.agregar(ce);
		return "/faces/Admin/envios/companiaenvio.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		CompaniaEnvioImp ceImp = new CompaniaEnvioImp();
		ce = ceImp.econtrarId(id);
		this.sessionMap.put("ce", ce);
		return "/faces/Admin/editar/envios/companiaenvio.xhtml?faces-redirect=true";
	}

	public String actualizar(CompaniaEnvio ce) {
		System.out.println("Entro a actualizar CompaniaEnvio");
		CompaniaEnvioImp ceImp = new CompaniaEnvioImp();
		ceImp.actualizar(ce);
		return "/faces/Admin/envios/companiaenvio.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		CompaniaEnvioImp ceImp = new CompaniaEnvioImp();
		ceImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/envios/companiaenvio.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaCompañiaEnvio " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		CompaniaEnvioImp ceImp = new CompaniaEnvioImp();
		this.listaCompaniaE = ceImp.encontrarTodo();

		ExportarExcelCompaniaEnvio excelExportar = new ExportarExcelCompaniaEnvio(this.listaCompaniaE);
		excelExportar.export(response);

	}

}
