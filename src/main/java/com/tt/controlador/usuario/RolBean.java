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

import com.tt.implementacion.usuario.RolImp;
import com.tt.modelo.usuario.Rol;
import com.tt.utilidades.usuario.ExportarExcelRol;

@ManagedBean(name = "rolBean")
@RequestScoped
public class RolBean {
	Rol rol = new Rol();
	List<Rol> listaRoles = new ArrayList<Rol>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<Rol> encontrarTodo() {
		RolImp rolImp = new RolImp();
		this.listaRoles = rolImp.encontrarTodo();
		return this.listaRoles;
	}

	public String agregar() {
		RolImp rolImp = new RolImp();
		rolImp.agregar(rol);
		return "/faces/Admin/usuario/roles.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		RolImp rolImp = new RolImp();
		rol = rolImp.econtrarId(id);
		this.sessionMap.put("rol", rol);
		return "/faces/Admin/editar/usuario/roles.xhtml?faces-redirect=true";
	}

	public String actualizar(Rol rol) {
		System.out.println("Entro a actualizar Rol");
		RolImp rolImp = new RolImp();
		rolImp.actualizar(rol);
		return "/faces/Admin/usuario/roles.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		RolImp rol = new RolImp();
		rol.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/usuario/roles.xhtml?faces-redirect=true";
	}
	
	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaRoles " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		RolImp rolImp = new RolImp();
		this.listaRoles = rolImp.encontrarTodo();
		
		ExportarExcelRol excelExportar = new ExportarExcelRol(this.listaRoles);
		excelExportar.export(response);

	}

}
