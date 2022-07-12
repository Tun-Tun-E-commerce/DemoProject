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

import com.tt.implementacion.venta.MetodoPagoImp;
import com.tt.modelo.venta.MetodoPago;
import com.tt.utilidades.venta.ExportarExcelMetodoPago;

@ManagedBean(name = "metodoBean")
@RequestScoped
public class MetodoPagoBean {
	MetodoPago m = new MetodoPago();
	List<MetodoPago> listaMetodoPago = new ArrayList<MetodoPago>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public MetodoPago getM() {
		return m;
	}

	public void setM(MetodoPago m) {
		this.m = m;
	}

	public List<MetodoPago> getListaMetodoPago() {
		return listaMetodoPago;
	}

	public void setListaMetodoPago(List<MetodoPago> listaMetodoPago) {
		this.listaMetodoPago = listaMetodoPago;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<MetodoPago> encontrarTodo() {
		MetodoPagoImp mImp = new MetodoPagoImp();
		this.listaMetodoPago = mImp.encontrarTodo();
		return this.listaMetodoPago;
	}

	public String agregar() {
		MetodoPagoImp mImp = new MetodoPagoImp();
		mImp.agregar(m);
		return "/faces/Admin/metodopago.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		MetodoPagoImp mImp = new MetodoPagoImp();
		m = mImp.econtrarId(id);
		this.sessionMap.put("m", m);
		return "/faces/Admin/editar/editarMETODOP.xhtml?faces-redirect=true";
	}

	public String actualizar(MetodoPago m) {
		System.out.println("Entro a actualizar Rol");
		MetodoPagoImp mImp = new MetodoPagoImp();
		mImp.actualizar(m);
		return "/faces/Admin/metodopago.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		MetodoPagoImp m = new MetodoPagoImp();
		m.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/metodopago.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaMetodoPago " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		MetodoPagoImp mImp = new MetodoPagoImp();
		this.listaMetodoPago = mImp.encontrarTodo();

		ExportarExcelMetodoPago excelExportar = new ExportarExcelMetodoPago(this.listaMetodoPago);
		excelExportar.export(response);
	}

}
