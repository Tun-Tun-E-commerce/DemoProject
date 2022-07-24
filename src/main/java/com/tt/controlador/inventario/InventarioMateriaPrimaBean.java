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
import com.tt.implementacion.inventario.MateriaPrimaImp;
import com.tt.modelo.inventario.InventarioMateriaPrima;
import com.tt.modelo.inventario.MateriaPrima;
import com.tt.utilidades.inventario.ExportarExceIInventarioMateriaPrima;

@ManagedBean(name = "invBean")
@RequestScoped
public class InventarioMateriaPrimaBean {
	InventarioMateriaPrima invMT = new InventarioMateriaPrima();
	List<InventarioMateriaPrima> listaInvMT = new ArrayList<InventarioMateriaPrima>();
	List<MateriaPrima> listaMateriaPrima = new ArrayList<MateriaPrima>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idMateriaPrima;

	private void LlenarMateriaPrima() {
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		this.listaMateriaPrima = mtImp.encontrarTodo();
	}

	public InventarioMateriaPrima getInvMT() {
		return invMT;
	}

	public void setInvMT(InventarioMateriaPrima invMT) {
		this.invMT = invMT;
	}

	public List<InventarioMateriaPrima> getListaInvMT() {
		return listaInvMT;
	}

	public void setListaInvMT(List<InventarioMateriaPrima> listaInvMT) {
		this.listaInvMT = listaInvMT;
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

	public InventarioMateriaPrimaBean() {
		this.LlenarMateriaPrima();
	}

	public List<InventarioMateriaPrima> encontrarTodo() {
		InventarioMateriaPrimaImp invImp = new InventarioMateriaPrimaImp();
		this.listaInvMT = invImp.encontrarTodo();
		return this.listaInvMT;
	}

	public String agregar() {
		InventarioMateriaPrimaImp invImp = new InventarioMateriaPrimaImp();
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		MateriaPrima mt = new MateriaPrima();
		mt = mtImp.econtrarId(idMateriaPrima);
		invMT.setIdMateriaPrima(mt);
		invImp.agregar(invMT);
		return "/faces/Admin/inventario.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		InventarioMateriaPrimaImp invImp = new InventarioMateriaPrimaImp();
		invMT = invImp.econtrarId(id);
		this.sessionMap.put("invMT", invMT);
		return "/faces/Admin/editar/inventario/inventario.xhtml?faces-redirect=true";
	}

	public String actualizar(InventarioMateriaPrima invMT) {
		System.out.println("Entro a actualizar InventarioMateriaPrima");
		InventarioMateriaPrimaImp invImp = new InventarioMateriaPrimaImp();
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		MateriaPrima mt = new MateriaPrima();
		mt = mtImp.econtrarId(invMT.getIdMateriaPrima().getId());
		System.out.println("MateriaPrima" + mt.toString());
		invMT.setIdMateriaPrima(mt);
		invImp.actualizar(invMT);
		return "/faces/Admin/inventario.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		InventarioMateriaPrimaImp invImp = new InventarioMateriaPrimaImp();
		invImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/inventario.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaInventario " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		InventarioMateriaPrimaImp iIMP = new InventarioMateriaPrimaImp();
		if (idMateriaPrima != 0) {
			this.listaInvMT = iIMP.exportarMateriaPrima(idMateriaPrima);
		} else {
			this.listaInvMT = iIMP.encontrarTodo();
		}

		ExportarExceIInventarioMateriaPrima excelExportar = new ExportarExceIInventarioMateriaPrima(this.listaInvMT);
		excelExportar.export(response);
	}

}
