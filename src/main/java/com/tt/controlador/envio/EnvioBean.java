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

import com.tt.implementacion.envio.CompaniaEnvioImp;
import com.tt.implementacion.envio.EnvioImp;
import com.tt.implementacion.usuario.UsuarioImp;
import com.tt.modelo.envio.CompaniaEnvio;
import com.tt.modelo.envio.Envio;
import com.tt.modelo.usuario.Usuario;
import com.tt.utilidades.envio.ExportarExcelEnvio;

@ManagedBean(name = "envBean")
@RequestScoped
public class EnvioBean {
	Envio en = new Envio();
	List<Envio> listaEnvio = new ArrayList<Envio>();
	List<Usuario> listaUsuario = new ArrayList<Usuario>();
	List<CompaniaEnvio> listaCompania = new ArrayList<CompaniaEnvio>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idUsuario;
	private int idCompania;

	private void LlenarUsuario() {
		UsuarioImp uImp = new UsuarioImp();
		this.listaUsuario = uImp.encontrarTodo();
	}

	private void LlenarCompania() {
		CompaniaEnvioImp cImp = new CompaniaEnvioImp();
		this.listaCompania = cImp.encontrarTodo();
	}

	public Envio getEn() {
		return en;
	}

	public void setEn(Envio en) {
		this.en = en;
	}

	public List<Envio> getListaEnvio() {
		return listaEnvio;
	}

	public void setListaEnvio(List<Envio> listaEnvio) {
		this.listaEnvio = listaEnvio;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<CompaniaEnvio> getListaCompania() {
		return listaCompania;
	}

	public void setListaCompania(List<CompaniaEnvio> listaCompania) {
		this.listaCompania = listaCompania;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdCompania() {
		return idCompania;
	}

	public void setIdCompania(int idCompania) {
		this.idCompania = idCompania;
	}

	public EnvioBean() {
		this.LlenarUsuario();
		this.LlenarCompania();
	}

	public List<Envio> encontrarTodo() {
		EnvioImp eImp = new EnvioImp();
		this.listaEnvio = eImp.encontrarTodo();
		return this.listaEnvio;
	}

	public String agregar() {
		EnvioImp eImp = new EnvioImp();
		UsuarioImp uImp = new UsuarioImp();
		CompaniaEnvioImp ceImp = new CompaniaEnvioImp();
		Usuario u = new Usuario();
		CompaniaEnvio ce = new CompaniaEnvio();
		u = uImp.econtrarId(idUsuario);
		ce = ceImp.econtrarId(idCompania);
		en.setIdUsuario(u);
		en.setIdCompaniaEnvio(ce);
		eImp.agregar(en);
		return "/faces/Admin/envio.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		EnvioImp eImp = new EnvioImp();
		en = eImp.econtrarId(id);
		this.sessionMap.put("en", en);
		return "/faces/Admin/editar/envios/envio.xhtml?faces-redirect=true";
	}

	public String actualizar(Envio en) {
		System.out.println("Entro a actualizar Envio");
		EnvioImp eImp = new EnvioImp();
		UsuarioImp uImp = new UsuarioImp();
		CompaniaEnvioImp ceImp = new CompaniaEnvioImp();
		Usuario u = new Usuario();
		CompaniaEnvio ce = new CompaniaEnvio();
		u = uImp.econtrarId(en.getIdUsuario().getId());
		ce = ceImp.econtrarId(en.getIdCompaniaEnvio().getId());
		System.out.println("Producto" + u.toString());
		System.out.println("Proveedor" + ce.toString());
		en.setIdUsuario(u);
		en.setIdCompaniaEnvio(ce);
		eImp.actualizar(en);
		return "/faces/Admin/envio.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		EnvioImp eImp = new EnvioImp();
		eImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/envio.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaEnvio " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		EnvioImp eImp = new EnvioImp();
		if (idUsuario != 0 && idCompania != 0) {
			this.listaEnvio = eImp.exportarMulticriterio(idUsuario, idCompania);
		} else if (idUsuario != 0) {
			this.listaEnvio = eImp.exportarUsuario(idUsuario);
		} else if (idCompania != 0) {
			this.listaEnvio = eImp.exportarCompaniaEnvio(idCompania);
		} else {
			this.listaEnvio = eImp.encontrarTodo();
		}

		ExportarExcelEnvio excelExportar = new ExportarExcelEnvio(this.listaEnvio);
		excelExportar.export(response);
	}

}
