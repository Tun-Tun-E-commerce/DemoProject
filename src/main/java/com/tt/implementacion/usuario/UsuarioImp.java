package com.tt.implementacion.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.usuario.IUsuario;
import com.tt.modelo.usuario.Rol;
import com.tt.modelo.usuario.TipoDocumento;
import com.tt.modelo.usuario.Usuario;
import com.tt.utilidades.JPAUtil;

public class UsuarioImp implements IUsuario {

	EntityManager entity;

	public UsuarioImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	private Query q;

	@Override
	public List<Usuario> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT u FROM Usuario u");
			listaUsuario = q.getResultList();
			this.entity.getTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			JPAUtil.shutdown();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaUsuario;
	}

	@Override
	public Usuario econtrarId(int id) {
		Usuario u = new Usuario();
		try {
			this.entity.getTransaction().begin();
			u = this.entity.find(Usuario.class, id);
			this.entity.close();
		} catch (Exception e) {
			e.printStackTrace();
			JPAUtil.shutdown();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return u;
	}

	@Override
	public void agregar(Usuario usuario) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(usuario);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			JPAUtil.shutdown();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}

	}

	@Override
	public void actualizar(Usuario usuario) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(usuario);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			JPAUtil.shutdown();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}

	}

	@Override
	public void eliminar(int id) {
		try {
			Usuario u = new Usuario();
			u = this.entity.find(Usuario.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(u);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
	}

	public List<Usuario> exportarRolId(int idRol) {
		RolImp rImp = new RolImp();
		Rol r = new Rol();
		try {
			r = rImp.econtrarId(idRol);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT u FROM Usuario u WHERE u.idRol.id=" + idRol + "");
			this.listaUsuario = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaUsuario;
	}

	public List<Usuario> exportarTipoDId(int idTipoDocumento) {
		TipoDocumentoImp tImp = new TipoDocumentoImp();
		TipoDocumento t = new TipoDocumento();
		try {
			t = tImp.econtrarId(idTipoDocumento);
			this.entity.getTransaction().begin();
			Query q = this.entity
					.createQuery("SELECT u FROM Usuario u WHERE u.idTipoDocumento.id=" + idTipoDocumento + "");
			this.listaUsuario = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaUsuario;
	}

	public List<Usuario> exportarNombre(int idUsuarioNombre) {
		UsuarioImp uImp = new UsuarioImp();
		Usuario u = new Usuario();
		try {
			u = uImp.econtrarId(idUsuarioNombre);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT u FROM Usuario u WHERE u.nombre.id=" + idUsuarioNombre + "");
			this.listaUsuario = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaUsuario;
	}

	public List<Usuario> exportarApellido(int idUsuarioApellido) {
		UsuarioImp uImp = new UsuarioImp();
		Usuario u = new Usuario();
		try {
			u = uImp.econtrarId(idUsuarioApellido);
			this.entity.getTransaction().begin();
			Query q = this.entity
					.createQuery("SELECT u FROM Usuario u WHERE usu.apellido.id=" + idUsuarioApellido + "");
			this.listaUsuario = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaUsuario;
	}

	public List<Usuario> exportarMulticriterioU(int idRol, int idTipoDocumento) {
		RolImp rImp = new RolImp();
		Rol r = new Rol();
		TipoDocumentoImp tImp = new TipoDocumentoImp();
		TipoDocumento t = new TipoDocumento();
		try {
			r = rImp.econtrarId(idRol);
			t = tImp.econtrarId(idTipoDocumento);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT u FROM Usuario u WHERE  u.idRol.id=" + idRol
					+ "AND u.idTipoDocumento.id=" + idTipoDocumento + "");
			this.listaUsuario = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaUsuario;
	}

	public List<Usuario> exportarMulticriterioNt(int idRol, int idTipoDocumento, int idUsuarioNombre) {
		RolImp rImp = new RolImp();
		Rol r = new Rol();
		TipoDocumentoImp tpImp = new TipoDocumentoImp();
		TipoDocumento tp = new TipoDocumento();
		Usuario u = new Usuario();
		UsuarioImp uImp = new UsuarioImp();
		try {
			r = rImp.econtrarId(idRol);
			tp = tpImp.econtrarId(idTipoDocumento);
			u = uImp.econtrarId(idUsuarioNombre);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT u FROM Usuario u WHERE  u.idRol.id=" + idRol
					+ "AND u.idTipoDocumento.id=" + idTipoDocumento + "" + "AND u.nombre.id=" + idUsuarioNombre + "");
			this.listaUsuario = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaUsuario;
	}

	public List<Usuario> exportarMulticriterioAt(int idRol, int idTipoDocumento, int idUsuarioApellido) {
		RolImp rImp = new RolImp();
		Rol r = new Rol();
		TipoDocumentoImp tpImp = new TipoDocumentoImp();
		TipoDocumento tp = new TipoDocumento();
		Usuario u = new Usuario();
		UsuarioImp uImp = new UsuarioImp();
		try {
			r = rImp.econtrarId(idRol);
			tp = tpImp.econtrarId(idTipoDocumento);
			u = uImp.econtrarId(idUsuarioApellido);
			this.entity.getTransaction().begin();
			Query q = this.entity
					.createQuery("SELECT u FROM Usuario u WHERE  u.idRol.id=" + idRol + "AND u.idTipoDocumento.id="
							+ idTipoDocumento + "" + "AND u.apellido.id=" + idUsuarioApellido + "");
			this.listaUsuario = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaUsuario;
	}

	public List<Usuario> exportarMulticriterioTu(int idRol, int idTipoDocumento, int idUsuarioNombre,
			int idUsuarioApellido) {
		RolImp rImp = new RolImp();
		Rol r = new Rol();
		TipoDocumentoImp tpImp = new TipoDocumentoImp();
		TipoDocumento tp = new TipoDocumento();
		Usuario u = new Usuario();
		UsuarioImp uImp = new UsuarioImp();

		try {
			r = rImp.econtrarId(idRol);
			tp = tpImp.econtrarId(idTipoDocumento);
			u = uImp.econtrarId(idUsuarioNombre);
			u = uImp.econtrarId(idUsuarioApellido);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT u FROM Usuario u WHERE  u.idRol.id=" + idRol
					+ "AND u.idTipoDocumento.id=" + idTipoDocumento + "" + "AND u.nombre.id=" + idUsuarioNombre + ""
					+ "AND u.apellido.id=" + idUsuarioApellido + "");
			this.listaUsuario = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaUsuario;
	}

	public String validarUsuario(Usuario u) {
		String roles = "none";

		try {
			this.entity.getTransaction().begin();
			q = this.entity.createQuery("SELECT u FROM Usuario u WHERE u.contrasena='" + u.getContrasena()
					+ "' And u.correo='" + u.getCorreo() + "'");
			this.listaUsuario = q.getResultList();
			for (Usuario us : this.listaUsuario) {
				System.out.println("us" + us.toString());
				RolImp rolImp = new RolImp();
				Rol rol = new Rol();
				rol = rolImp.econtrarId(us.getIdRol().getId());
				System.out.println("nombre rol" + rol.getNombre());
				roles = rol.getNombre();
			}
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {

			}
		}

		return roles;

	}
}
