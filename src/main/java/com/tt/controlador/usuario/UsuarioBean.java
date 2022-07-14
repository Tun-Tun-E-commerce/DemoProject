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
import com.tt.implementacion.usuario.TipoDocumentoImp;
import com.tt.implementacion.usuario.UsuarioImp;
import com.tt.modelo.usuario.Rol;
import com.tt.modelo.usuario.TipoDocumento;
import com.tt.modelo.usuario.Usuario;
import com.tt.utilidades.usuario.ExportarExcelUsuario;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {
	Usuario usuario = new Usuario();
	List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	List<Rol> listaRoles = new ArrayList<Rol>();
	List<TipoDocumento> listaTipoDocumento = new ArrayList<TipoDocumento>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idRol;
	private int idTipoDocumento;
	private int idUsuarioN;
	private int idUsuarioA;

	private void LlenarUsuarios() {
		UsuarioImp UsuImp = new UsuarioImp();
		this.listaUsuarios = UsuImp.encontrarTodo();
	}
	

	private void LlenarRoles() {
		RolImp rolImp = new RolImp();
		this.listaRoles = rolImp.encontrarTodo();
	}

	private void LlenarTipoDocumento() {
		TipoDocumentoImp tipodocImp = new TipoDocumentoImp();
		this.listaTipoDocumento = tipodocImp.encontrarTodo();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<TipoDocumento> getListaTipoDocumento() {
		return listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public int getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(int idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	
	public int getIdUsuarioN() {
		return idUsuarioN;
	}

	public void setIdUsuarioN(int idUsuarioN) {
		this.idUsuarioN = idUsuarioN;
	}

	public int getIdUsuarioA() {
		return idUsuarioA;
	}

	public void setIdUsuarioA(int idUsuarioA) {
		this.idUsuarioA = idUsuarioA;
	}

	public UsuarioBean() {
		this.LlenarRoles();
		this.LlenarTipoDocumento();
		this.LlenarUsuarios();	
		}

	public List<Usuario> encontrarTodo() {
		UsuarioImp usuarioImp = new UsuarioImp();
		this.listaUsuarios = usuarioImp.encontrarTodo();
		return this.listaUsuarios;
	}

	public String agregar() {
		UsuarioImp usuarioImp = new UsuarioImp();
		RolImp rolImp = new RolImp();
		TipoDocumentoImp tipodocImp = new TipoDocumentoImp();
		Rol rol = new Rol();
		TipoDocumento tipoDoc = new TipoDocumento();
		rol = rolImp.econtrarId(idRol);
		tipoDoc = tipodocImp.econtrarId(idTipoDocumento);
		usuario.setIdRol(rol);
		usuario.setIdTipoDocumento(tipoDoc);
		usuarioImp.agregar(usuario);
		return "/faces/Admin/Usuarios.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		UsuarioImp usuarioImp = new UsuarioImp();
		usuario = usuarioImp.econtrarId(id);
		this.sessionMap.put("usuario", usuario);
		return "/faces/Admin/editar/editarUSUARIO.xhtml?faces-redirect=true";
	}

	public String actualizar(Usuario usuario) {
		System.out.println("Entro a actualizar Usuario");
		UsuarioImp usuarioImp = new UsuarioImp();
		RolImp rolImp = new RolImp();
		TipoDocumentoImp tipodocImp = new TipoDocumentoImp();
		Rol rol = new Rol();
		TipoDocumento tipoDoc = new TipoDocumento();
		rol = rolImp.econtrarId(usuario.getIdRol().getId());
		tipoDoc = tipodocImp.econtrarId(usuario.getIdTipoDocumento().getId());
		usuario.setIdRol(rol);
		usuario.setIdTipoDocumento(tipoDoc);
		usuarioImp.actualizar(usuario);
		return "/faces/Admin/Usuarios.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		UsuarioImp usuarioImp = new UsuarioImp();
		usuarioImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/Usuarios.xhtml?faces-redirect=true";
	}
	
	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaUsuarios " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		UsuarioImp usuarioImp = new UsuarioImp();
		
		 if(idRol !=0 && idTipoDocumento !=0 && idUsuarioN !=0 && idUsuarioA !=0  ) {
				this.listaUsuarios = usuarioImp.exportarMulticriterioTu(idRol, idTipoDocumento, idUsuarioN, idUsuarioA);
			}
		 else if(idRol !=0 && idTipoDocumento !=0 && idUsuarioN !=0  ) {
				this.listaUsuarios = usuarioImp.exportarMulticriterioNt(idRol, idTipoDocumento, idUsuarioN);
		 }
		 else if(idRol !=0 && idTipoDocumento !=0 && idUsuarioA !=0  ) {
				this.listaUsuarios = usuarioImp.exportarMulticriterioAt(idRol, idTipoDocumento, idUsuarioA);
		 }
		else if(idRol !=0 && idTipoDocumento !=0) {
			this.listaUsuarios = usuarioImp.exportarMulticriterioU(idRol, idTipoDocumento);
		}
		else if (idRol !=0 ) {
			this.listaUsuarios = usuarioImp.exportarRolId(idRol);
		}
		else if (idTipoDocumento !=0 ) {
			this.listaUsuarios = usuarioImp.exportarTipoDId(idTipoDocumento);
		}
		else if (idUsuarioN !=0 ) {
			this.listaUsuarios = usuarioImp.exportarNombre(idUsuarioN);
		}
		else if (idUsuarioA !=0 ) {
			this.listaUsuarios = usuarioImp.exportarApellido(idUsuarioA);
		}
			
		else {
			this.listaUsuarios = usuarioImp.encontrarTodo();
		}
		
		ExportarExcelUsuario excelExportar = new ExportarExcelUsuario(this.listaUsuarios);
		excelExportar.export(response);

	}

}
