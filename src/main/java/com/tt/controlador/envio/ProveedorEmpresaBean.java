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

import com.tt.implementacion.envio.ProveedorEmpresaImp;
import com.tt.modelo.envio.ProveedorEmpresa;
import com.tt.utilidades.envio.ExportarExcelProveedorEmpresa;

@ManagedBean(name = "provBean")
@RequestScoped
public class ProveedorEmpresaBean {
	ProveedorEmpresa pe = new ProveedorEmpresa();
	List<ProveedorEmpresa> listaProveedorE = new ArrayList<ProveedorEmpresa>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public ProveedorEmpresa getPe() {
		return pe;
	}

	public void setPe(ProveedorEmpresa pe) {
		this.pe = pe;
	}

	public List<ProveedorEmpresa> getListaProveedorE() {
		return listaProveedorE;
	}

	public void setListaProveedorE(List<ProveedorEmpresa> listaProveedorE) {
		this.listaProveedorE = listaProveedorE;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<ProveedorEmpresa> encontrarTodo() {
		ProveedorEmpresaImp peImp = new ProveedorEmpresaImp();
		this.listaProveedorE = peImp.encontrarTodo();
		return this.listaProveedorE;
	}

	public String agregar() {
		ProveedorEmpresaImp peImp = new ProveedorEmpresaImp();
		peImp.agregar(pe);
		return "/faces/Admin/envio/proveedorempresa.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		ProveedorEmpresaImp peImp = new ProveedorEmpresaImp();
		pe = peImp.econtrarId(id);
		this.sessionMap.put("pe", pe);
		return "/faces/Admin/editar/proveedorempresa.xhtml?faces-redirect=true";
	}

	public String actualizar(ProveedorEmpresa pe) {
		System.out.println("Entro a actualizar Rol");
		ProveedorEmpresaImp peImp = new ProveedorEmpresaImp();
		peImp.actualizar(pe);
		return "/faces/Admin/envio/proveedorempresa.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		ProveedorEmpresaImp peImp = new ProveedorEmpresaImp();
		peImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/envio/proveedorempresa.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaProveedorEmpresa " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		ProveedorEmpresaImp pImp = new ProveedorEmpresaImp();
		this.listaProveedorE = pImp.encontrarTodo();

		ExportarExcelProveedorEmpresa excelExportar = new ExportarExcelProveedorEmpresa(this.listaProveedorE);
		excelExportar.export(response);
	}

}
