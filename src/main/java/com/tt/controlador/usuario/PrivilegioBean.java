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

import com.tt.implementacion.usuario.PrivilegioImp;
import com.tt.implementacion.usuario.RolImp;
import com.tt.modelo.usuario.Privilegio;
import com.tt.modelo.usuario.Rol;
import com.tt.utilidades.usuario.ExportarExcelPrivilegio;

@ManagedBean(name = "privilegioBean")
@RequestScoped
public class PrivilegioBean {

	Privilegio privilegio = new Privilegio();
	List<Privilegio> listaPrivilegios = new ArrayList<Privilegio>();
	List<Rol> listaRoles = new ArrayList<Rol>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idRol;

	private void LlenarRoles() {
		RolImp rolImp = new RolImp();
		this.listaRoles = rolImp.encontrarTodo();
	}

	public Privilegio getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(Privilegio privilegio) {
		this.privilegio = privilegio;
	}

	public List<Privilegio> getListaPrivilegios() {
		return listaPrivilegios;
	}

	public void setListaPrivilegios(List<Privilegio> listaPrivilegios) {
		this.listaPrivilegios = listaPrivilegios;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public PrivilegioBean() {
		this.LlenarRoles();
	}

	public List<Privilegio> encontrarTodo() {
		PrivilegioImp privilegioImp = new PrivilegioImp();
		this.listaPrivilegios = privilegioImp.encontrarTodo();
		return this.listaPrivilegios;
	}

	public String agregar() {
		PrivilegioImp privilegioImp = new PrivilegioImp();
		RolImp rolImp = new RolImp();
		Rol rol = new Rol();
		rol = rolImp.econtrarId(idRol);
		privilegio.setIdRol(rol);
		privilegioImp.agregar(privilegio);
		return "/faces/Admin/usuario/privilegios.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		PrivilegioImp privilegioImp = new PrivilegioImp();
		privilegio = privilegioImp.econtrarId(id);
		this.sessionMap.put("privilegio", privilegio);
		return "/faces/Admin/editar/usuario/privilegios.xhtml?faces-redirect=true";
	}

	public String actualizar(Privilegio privilegio) {
		System.out.println("Entro a actualizar Privilegio");
		PrivilegioImp privilegioImp = new PrivilegioImp();
		RolImp rolImp = new RolImp();
		Rol rol = new Rol();
		rol = rolImp.econtrarId(privilegio.getIdRol().getId());
		System.out.println("Rol" + rol.toString());
		privilegio.setIdRol(rol);
		privilegioImp.actualizar(privilegio);
		return "/faces/Admin/usuario/privilegios.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		PrivilegioImp privilegio = new PrivilegioImp();
		privilegio.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/usuario/privilegios.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaPrivielgio" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		PrivilegioImp pImp = new PrivilegioImp();
		if (idRol != 0) {
			this.listaPrivilegios = pImp.exportarRol(idRol);
		} else {
			this.listaPrivilegios = pImp.encontrarTodo();
		}

		ExportarExcelPrivilegio excelExportar = new ExportarExcelPrivilegio(this.listaPrivilegios);
		excelExportar.export(response);

	}
}
