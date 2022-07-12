package com.tt.controlador.usuario;

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

import com.tt.implementacion.usuario.TipoDocumentoImp;
import com.tt.modelo.usuario.TipoDocumento;
import com.tt.utilidades.usuario.ExportarExcelTipoDocumento;

@ManagedBean(name = "tdBean")
@RequestScoped
public class TipoDocumentoBean {
	TipoDocumento tipoDocumento = new TipoDocumento();
	List<TipoDocumento> listaTipoDocumento = new ArrayList<TipoDocumento>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<TipoDocumento> getListaTipoDocumento() {
		return listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	public List<TipoDocumento> encontrarTodo() {
		TipoDocumentoImp tipoDocImp = new TipoDocumentoImp();
		this.listaTipoDocumento = tipoDocImp.encontrarTodo();
		return this.listaTipoDocumento;
	}

	public String agregar() {
		TipoDocumentoImp tipoDocImp = new TipoDocumentoImp();
		tipoDocImp.agregar(tipoDocumento);
		return "/faces/Admin/tipodoc.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {

		System.out.println("Entro al editar" + id);
		TipoDocumentoImp tipoDocumentoImp = new TipoDocumentoImp();
		tipoDocumento = tipoDocumentoImp.econtrarId(id);
		this.sessionMap.put("tipoDocumento", tipoDocumento);
		return "/faces/Admin/editar/editarTD.xhtml?faces-redirect=true";
	}

	public String actualizar(TipoDocumento tipoDocumento) {
		System.out.println("Entro a actualizar Tipo Documento");
		TipoDocumentoImp tipoDocumentoImp = new TipoDocumentoImp();
		tipoDocumentoImp.actualizar(tipoDocumento);
		return "/faces/Admin/tipodoc.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		TipoDocumentoImp tipoDocumento = new TipoDocumentoImp();
		tipoDocumento.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/tipodoc.xhtml?faces-redirect=true";

	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaTipoDocumento " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		TipoDocumentoImp tdImp = new TipoDocumentoImp();
		this.listaTipoDocumento = tdImp.encontrarTodo();

		ExportarExcelTipoDocumento excelExportar = new ExportarExcelTipoDocumento(this.listaTipoDocumento);
		excelExportar.export(response);

	}

}
