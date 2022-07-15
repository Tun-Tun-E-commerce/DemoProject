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

import com.tt.implementacion.inventario.InventarioMateriaPrimaImp;
import com.tt.implementacion.inventario.OrdenProduccionImp;
import com.tt.modelo.inventario.InventarioMateriaPrima;
import com.tt.modelo.inventario.OrdenProduccion;
import com.tt.utilidades.inventario.ExportarExcelOrdenProduccion;

@ManagedBean(name = "ordenBean")
@RequestScoped

public class OrdenProduccionBean {
	OrdenProduccion ordenP = new OrdenProduccion();
	List<InventarioMateriaPrima> listaInvMT = new ArrayList<InventarioMateriaPrima>();
	List<OrdenProduccion> listaOrdenProduccion = new ArrayList<OrdenProduccion>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idIventarioMateriaPrima;

	private void LlenarInventarioMateriaPrima() {
		InventarioMateriaPrimaImp invImp = new InventarioMateriaPrimaImp();
		this.listaInvMT = invImp.encontrarTodo();
	}

	public OrdenProduccion getOrdenP() {
		return ordenP;
	}

	public void setOrdenP(OrdenProduccion ordenP) {
		this.ordenP = ordenP;
	}

	public List<InventarioMateriaPrima> getListaInvMT() {
		return listaInvMT;
	}

	public void setListaInvMT(List<InventarioMateriaPrima> listaInvMT) {
		this.listaInvMT = listaInvMT;
	}

	public List<OrdenProduccion> getListaOrdenProduccion() {
		return listaOrdenProduccion;
	}

	public void setListaOrdenProduccion(List<OrdenProduccion> listaOrdenProduccion) {
		this.listaOrdenProduccion = listaOrdenProduccion;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public int getIdIventarioMateriaPrima() {
		return idIventarioMateriaPrima;
	}

	public void setIdIventarioMateriaPrima(int idIventarioMateriaPrima) {
		this.idIventarioMateriaPrima = idIventarioMateriaPrima;
	}

	public OrdenProduccionBean() {
		this.LlenarInventarioMateriaPrima();
	}

	public List<OrdenProduccion> encontrarTodo() {
		OrdenProduccionImp ordenImp = new OrdenProduccionImp();
		this.listaOrdenProduccion = ordenImp.encontrarTodo();
		return this.listaOrdenProduccion;
	}

	public String agregar() {
		OrdenProduccionImp ordenImp = new OrdenProduccionImp();
		InventarioMateriaPrimaImp invImp = new InventarioMateriaPrimaImp();
		InventarioMateriaPrima inv = new InventarioMateriaPrima();
		inv = invImp.econtrarId(idIventarioMateriaPrima);
		ordenP.setIdInventarioMateriaPrima(inv);
		ordenImp.agregar(ordenP);
		return "/faces/Admin/inventario/ordenproduccion.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		OrdenProduccionImp ordenImp = new OrdenProduccionImp();
		ordenP = ordenImp.econtrarId(id);
		this.sessionMap.put("ordenP", ordenP);
		return "/faces/Admin/editar/inventario/ordenproduccion.xhtml?faces-redirect=true";
	}

	public String actualizar(OrdenProduccion ordenP) {
		System.out.println("Entro a actualizar Orden Produccion");
		OrdenProduccionImp ordenImp = new OrdenProduccionImp();
		InventarioMateriaPrimaImp invImp = new InventarioMateriaPrimaImp();
		InventarioMateriaPrima inv = new InventarioMateriaPrima();
		inv = invImp.econtrarId(ordenP.getIdInventarioMateriaPrima().getId());
		System.out.println("InventarioMt" + inv.toString());
		ordenP.setIdInventarioMateriaPrima(inv);
		ordenImp.actualizar(ordenP);
		return "/faces/Admin/inventario/ordenproduccion.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		OrdenProduccionImp ordenImp = new OrdenProduccionImp();
		ordenImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/inventario/ordenproduccion.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaOrdenProduccion " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		OrdenProduccionImp oImp = new OrdenProduccionImp();
		if(idIventarioMateriaPrima !=0) {
			this.listaOrdenProduccion = oImp.exportOrdenProduccion(idIventarioMateriaPrima);
		}else {
			this.listaOrdenProduccion = oImp.encontrarTodo();
		}

		ExportarExcelOrdenProduccion excelExportar = new ExportarExcelOrdenProduccion(this.listaOrdenProduccion);
		excelExportar.export(response);
	}

}
