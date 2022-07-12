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

import com.tt.implementacion.inventario.DetalleOrdenProduccionImp;
import com.tt.implementacion.inventario.MateriaPrimaImp;
import com.tt.implementacion.inventario.OrdenProduccionImp;
import com.tt.modelo.inventario.DetalleOrdenProduccion;
import com.tt.modelo.inventario.MateriaPrima;
import com.tt.modelo.inventario.OrdenProduccion;
import com.tt.utilidades.inventario.ExportarExcelDetalleOrdenProduccion;

@ManagedBean(name = "dtoBean")
@RequestScoped
public class DetalleOrdenProduccionBean {
	DetalleOrdenProduccion detalleOrdenProduccion = new DetalleOrdenProduccion();
	List<DetalleOrdenProduccion> listaDetalleOrdenProduccion = new ArrayList<DetalleOrdenProduccion>();
	List<MateriaPrima> listaMateriaPrima = new ArrayList<MateriaPrima>();
	List<OrdenProduccion> listaOrdenProdccion = new ArrayList<OrdenProduccion>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idMateriaPrima;
	private int idOrdenProduccion;

	private void LlenarMateriaPrima() {
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		this.listaMateriaPrima = mtImp.encontrarTodo();
	}

	private void LlenarOrdenProduccion() {
		OrdenProduccionImp oImp = new OrdenProduccionImp();
		this.listaOrdenProdccion = oImp.encontrarTodo();
	}

	public DetalleOrdenProduccion getDetalleOrdenProduccion() {
		return detalleOrdenProduccion;
	}

	public void setDetalleOrdenProduccion(DetalleOrdenProduccion detalleOrdenProduccion) {
		this.detalleOrdenProduccion = detalleOrdenProduccion;
	}

	public List<DetalleOrdenProduccion> getListaDetalleOrdenProduccion() {
		return listaDetalleOrdenProduccion;
	}

	public void setListaDetalleOrdenProduccion(List<DetalleOrdenProduccion> listaDetalleOrdenProduccion) {
		this.listaDetalleOrdenProduccion = listaDetalleOrdenProduccion;
	}

	public List<MateriaPrima> getListaMateriaPrima() {
		return listaMateriaPrima;
	}

	public void setListaMateriaPrima(List<MateriaPrima> listaMateriaPrima) {
		this.listaMateriaPrima = listaMateriaPrima;
	}

	public List<OrdenProduccion> getListaOrdenProdccion() {
		return listaOrdenProdccion;
	}

	public void setListaOrdenProdccion(List<OrdenProduccion> listaOrdenProdccion) {
		this.listaOrdenProdccion = listaOrdenProdccion;
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

	public int getIdOrdenProduccion() {
		return idOrdenProduccion;
	}

	public void setIdOrdenProduccion(int idOrdenProduccion) {
		this.idOrdenProduccion = idOrdenProduccion;
	}

	public DetalleOrdenProduccionBean() {
		this.LlenarMateriaPrima();
		this.LlenarOrdenProduccion();
	}

	public List<DetalleOrdenProduccion> encontrarTodo() {
		DetalleOrdenProduccionImp dtImp = new DetalleOrdenProduccionImp();
		this.listaDetalleOrdenProduccion = dtImp.encontrarTodo();
		return this.listaDetalleOrdenProduccion;
	}

	public String agregar() {
		DetalleOrdenProduccionImp dtImp = new DetalleOrdenProduccionImp();
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		OrdenProduccionImp oImp = new OrdenProduccionImp();
		MateriaPrima mt = new MateriaPrima();
		OrdenProduccion o = new OrdenProduccion();
		mt = mtImp.econtrarId(idMateriaPrima);
		o = oImp.econtrarId(idOrdenProduccion);
		detalleOrdenProduccion.setIdMateriaPrima(mt);
		detalleOrdenProduccion.setIdOrdenProduccion(o);
		dtImp.agregar(detalleOrdenProduccion);
		return "/faces/Admin/detalleOP.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		DetalleOrdenProduccionImp dtImp = new DetalleOrdenProduccionImp();
		detalleOrdenProduccion = dtImp.econtrarId(id);
		this.sessionMap.put("detalleOrdenProduccion", detalleOrdenProduccion);
		return "/faces/Admin/editar/editarDOP.xhtml?faces-redirect=true";
	}

	public String actualizar(DetalleOrdenProduccion detalleOrdenProduccion) {
		System.out.println("Entro a actualizar DetalleOrdenProduccion");
		DetalleOrdenProduccionImp dtImp = new DetalleOrdenProduccionImp();
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		OrdenProduccionImp oImp = new OrdenProduccionImp();
		MateriaPrima mt = new MateriaPrima();
		OrdenProduccion o = new OrdenProduccion();
		mt = mtImp.econtrarId(detalleOrdenProduccion.getIdMateriaPrima().getId());
		o = oImp.econtrarId(detalleOrdenProduccion.getIdOrdenProduccion().getId());
		System.out.println("MateriaPrima" + mt.toString());
		detalleOrdenProduccion.setIdMateriaPrima(mt);
		detalleOrdenProduccion.setIdOrdenProduccion(o);
		dtImp.actualizar(detalleOrdenProduccion);
		return "/faces/Admin/detalleOP.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		DetalleOrdenProduccionImp dtImp = new DetalleOrdenProduccionImp();
		dtImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/detalleOP.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaDetalleOP " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		DetalleOrdenProduccionImp dopImp = new DetalleOrdenProduccionImp();
		if(idMateriaPrima !=0) {
			this.listaDetalleOrdenProduccion = dopImp.exportDOP(idMateriaPrima);
		}else {
			this.listaDetalleOrdenProduccion = dopImp.encontrarTodo();
		}

		ExportarExcelDetalleOrdenProduccion excelExportar = new ExportarExcelDetalleOrdenProduccion(this.listaDetalleOrdenProduccion);
		excelExportar.export(response);

	}

}
