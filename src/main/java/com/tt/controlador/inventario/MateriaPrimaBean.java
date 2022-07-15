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
import com.tt.modelo.inventario.MateriaPrima;
import com.tt.utilidades.inventario.ExportarExcelMateriaPrima;

@ManagedBean(name = "mtBean")
@RequestScoped
public class MateriaPrimaBean {
	MateriaPrima materiaPrima = new MateriaPrima();
	List<MateriaPrima> listaMateriaPrima = new ArrayList<MateriaPrima>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
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

	public List<MateriaPrima> encontrarTodo() {
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		this.listaMateriaPrima = mtImp.encontrarTodo();
		return this.listaMateriaPrima;
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		materiaPrima = mtImp.econtrarId(id);
		this.sessionMap.put("materiaPrima", materiaPrima);
		return "/faces/Admin/editar/inventario/materiaprima.xhtml?faces-redirect=true";
	}

	public String agregar() {
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		mtImp.agregar(materiaPrima);
		return "/faces/Admin/inventario/materiaPrima.xhtml?faces-redirect=true";
	}

	public String actualizar(MateriaPrima materiaPrima) {
		System.out.println("Entro a actualizar Materia Prima");
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		mtImp.actualizar(materiaPrima);
		return "/faces/Admin/inventario/materiaPrima.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		MateriaPrimaImp mtImp = new MateriaPrimaImp();
		mtImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/inventario/materiaPrima.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaMateriaPrima " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		MateriaPrimaImp mImp = new MateriaPrimaImp();
		this.listaMateriaPrima = mImp.encontrarTodo();

		ExportarExcelMateriaPrima excelExportar = new ExportarExcelMateriaPrima(this.listaMateriaPrima);
		excelExportar.export(response);
	}
}
