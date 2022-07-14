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

import com.tt.implementacion.inventario.MateriaPrimaImp;
import com.tt.implementacion.inventario.ProveedorMateriaPrimaImp;
import com.tt.modelo.inventario.MateriaPrima;
import com.tt.modelo.inventario.ProveedorMateriaPrima;
import com.tt.utilidades.inventario.ExportarExcelProveedorMateriaPrima;

@ManagedBean(name = "pmtBean")
@RequestScoped
public class ProveedorMateriaPrimaBean {

	ProveedorMateriaPrima pmt = new ProveedorMateriaPrima();
	List<ProveedorMateriaPrima> listaPmt = new ArrayList<ProveedorMateriaPrima>();
	List<MateriaPrima> listaMateriaPrima = new ArrayList<MateriaPrima>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idMateriaPrima;

	private void LlenarMateriaPrima() {
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		this.listaMateriaPrima = mtImp.encontrarTodo();
	}

	public ProveedorMateriaPrima getPmt() {
		return pmt;
	}

	public void setPmt(ProveedorMateriaPrima pmt) {
		this.pmt = pmt;
	}

	public List<ProveedorMateriaPrima> getListaPmt() {
		return listaPmt;
	}

	public void setListaPmt(List<ProveedorMateriaPrima> listaPmt) {
		this.listaPmt = listaPmt;
	}

	public List<MateriaPrima> getListaMateriaPrima() {
		return listaMateriaPrima;
	}

	public void setListaMateriaPrima(List<MateriaPrima> listaMateriaPrima) {
		this.listaMateriaPrima = listaMateriaPrima;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public int getIdMateriaPrima() {
		return idMateriaPrima;
	}

	public void setIdMateriaPrima(int idMateriaPrima) {
		this.idMateriaPrima = idMateriaPrima;
	}

	public ProveedorMateriaPrimaBean() {
		this.LlenarMateriaPrima();
	}

	public List<ProveedorMateriaPrima> encontrarTodo() {
		ProveedorMateriaPrimaImp pmtImp = new ProveedorMateriaPrimaImp();
		this.listaPmt = pmtImp.encontrarTodo();
		return this.listaPmt;
	}

	public String agregar() {
		ProveedorMateriaPrimaImp pmtImp = new ProveedorMateriaPrimaImp();
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		MateriaPrima mt = new MateriaPrima();
		mt = mtImp.econtrarId(idMateriaPrima);
		pmt.setIdMateriaPrima(mt);
		pmtImp.agregar(pmt);
		return "/faces/Admin/proveedorM.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		ProveedorMateriaPrimaImp pmtImp = new ProveedorMateriaPrimaImp();
		pmt = pmtImp.econtrarId(id);
		this.sessionMap.put("pmt", pmt);
		return "/faces/Admin/editar/editarPMP.xhtml?faces-redirect=true";
	}

	public String actualizar(ProveedorMateriaPrima pmt) {
		System.out.println("Entro a actualizar ProveedorMateriaPrima");
		ProveedorMateriaPrimaImp pmtImp = new ProveedorMateriaPrimaImp();
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		MateriaPrima mt = new MateriaPrima();
		mt = mtImp.econtrarId(pmt.getIdMateriaPrima().getId());
		System.out.println("MateriaPrima" + mt.toString());
		pmt.setIdMateriaPrima(mt);
		pmtImp.actualizar(pmt);
		return "/faces/Admin/proveedorM.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		ProveedorMateriaPrimaImp pmtImp = new ProveedorMateriaPrimaImp();
		pmtImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/proveedorM.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaProveedorMT " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		ProveedorMateriaPrimaImp pImp = new ProveedorMateriaPrimaImp();
		if (idMateriaPrima != 0) {
			this.listaPmt = pImp.exportarMateriaPrima(idMateriaPrima);
		} else {
			this.listaPmt = pImp.encontrarTodo();
		}

		ExportarExcelProveedorMateriaPrima excelExportar = new ExportarExcelProveedorMateriaPrima(this.listaPmt);
		excelExportar.export(response);
	}

}
