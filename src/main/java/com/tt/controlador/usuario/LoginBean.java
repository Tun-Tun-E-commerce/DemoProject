package com.tt.controlador.usuario;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.tt.implementacion.usuario.RolImp;
import com.tt.implementacion.usuario.UsuarioImp;
import com.tt.modelo.usuario.Usuario;
import com.tt.utilidades.SessionUtils;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

	UsuarioImp usuarioImp = new UsuarioImp();
	RolImp rolImp = new RolImp();
	Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String iniciarSesion() {
		String path = "";
		String usuarioRol = "";
		usuarioRol = this.usuarioImp.validarUsuario(usuario);
		System.out.println("Rol Usuario" + usuarioRol);
		HttpSession session = SessionUtils.getSession();
		switch (usuarioRol) {
		case "Administrador":
			session.setAttribute("username", usuario.getNombre());
			path = "/faces/Admin/dashboard.xhtml?faces-redirect=true";
			break;
		case "Usuario_Registrado":
			session.setAttribute("username", usuario.getNombre());
			path = "/faces/autenticacion/index-cliente.xhtml?faces-redirect=true";
			break;
		default:
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and password", "Please enter correct username and Password"));
			path = "/faces/autenticacion/login.xhtml?faces-redirect=true";
			break;
		}
		return path;
	}

}
