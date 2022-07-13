package com.tt.implementacion.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.usuario.IUsuario;
import com.tt.implementacion.venta.ProductoImp;
import com.tt.modelo.usuario.Rol;
import com.tt.modelo.usuario.TipoDocumento;
import com.tt.modelo.usuario.Usuario;
import com.tt.modelo.venta.CarritoCompra;
import com.tt.modelo.venta.Producto;
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
		Usuario usuario = new Usuario();
		try {
			this.entity.getTransaction().begin();
			usuario = this.entity.find(Usuario.class, id);
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
		return usuario;
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
			Usuario usuario = new Usuario();
			usuario = this.entity.find(Usuario.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(usuario);
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

	public List<Usuario> exportarRolId(int id) {
		RolImp rolImp = new RolImp();
		Rol rol = new Rol();
		try {
			rol = rolImp.econtrarId(id);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT usu FROM Usuario usu WHERE usu.idRol.id=" + id + "");
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
	
	
	public List<Usuario> exportarTipoDId(int id) {
		TipoDocumentoImp tipImp = new TipoDocumentoImp();
		TipoDocumento tip = new TipoDocumento();
		try {
			tip = tipImp.econtrarId(id);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT usu FROM Usuario usu WHERE usu.idTipoDocumento.id=" + id + "");
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
	
	public List<Usuario> exportarNombre(int idUsuarioN) {
		UsuarioImp uImp = new UsuarioImp();
		Usuario u = new Usuario();
		try {
			u = uImp.econtrarId(idUsuarioN);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT usu FROM Usuario usu WHERE usu.nombre.id=" + idUsuarioN + "");
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
	
	
	public List<Usuario> exportarApellido(int idUsuarioA) {
		UsuarioImp uImp = new UsuarioImp();
		Usuario u = new Usuario();
		try {
			u = uImp.econtrarId(idUsuarioA);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT usu FROM Usuario usu WHERE usu.apellido.id=" + idUsuarioA + "");
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
		TipoDocumentoImp tpImp = new TipoDocumentoImp();
		TipoDocumento tp = new TipoDocumento();
		try {
			r = rImp.econtrarId(idRol);
			tp = tpImp.econtrarId(idTipoDocumento);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT usu FROM Usuario usu WHERE  usu.idRol.id="+idRol+"AND usu.idTipoDocumento.id=" + idTipoDocumento + "");
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
		return listaUsuario ;
	}
	
	

	public List<Usuario> exportarMulticriterioNt(int idRol, int idTipoDocumento , int idUsuarioN ) {
		RolImp rImp = new RolImp();
		Rol r = new Rol();
		TipoDocumentoImp tpImp = new TipoDocumentoImp();
		TipoDocumento tp = new TipoDocumento();
		Usuario u = new Usuario();
		UsuarioImp uImp = new UsuarioImp();
		try {
			r = rImp.econtrarId(idRol);
			tp = tpImp.econtrarId(idTipoDocumento);
			u = uImp.econtrarId(idUsuarioN);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT usu FROM Usuario usu WHERE  usu.idRol.id="+idRol+"AND usu.idTipoDocumento.id=" + idTipoDocumento +
					""+"AND usu.nombre.id=" + idUsuarioN + "");
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
		return listaUsuario ;
	}
	
	

	public List<Usuario> exportarMulticriterioAt(int idRol, int idTipoDocumento , int idUsuarioA ) {
		RolImp rImp = new RolImp();
		Rol r = new Rol();
		TipoDocumentoImp tpImp = new TipoDocumentoImp();
		TipoDocumento tp = new TipoDocumento();
		Usuario u = new Usuario();
		UsuarioImp uImp = new UsuarioImp();
		try {
			r = rImp.econtrarId(idRol);
			tp = tpImp.econtrarId(idTipoDocumento);
			u = uImp.econtrarId(idUsuarioA);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT usu FROM Usuario usu WHERE  usu.idRol.id="+idRol+"AND usu.idTipoDocumento.id=" + idTipoDocumento +
					""+"AND usu.apellido.id=" + idUsuarioA + "");
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
		return listaUsuario ;
	}
	
	public List<Usuario> exportarMulticriterioTu(int idRol, int idTipoDocumento , int idUsuarioN , int idUsuarioA) {
		RolImp rImp = new RolImp();
		Rol r = new Rol();
		TipoDocumentoImp tpImp = new TipoDocumentoImp();
		TipoDocumento tp = new TipoDocumento();
		Usuario u = new Usuario();
		UsuarioImp uImp = new UsuarioImp();
	
		try {
			r = rImp.econtrarId(idRol);
			tp = tpImp.econtrarId(idTipoDocumento);
			u = uImp.econtrarId(idUsuarioN);
			u = uImp.econtrarId(idUsuarioA);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT usu FROM Usuario usu WHERE  usu.idRol.id="+idRol+"AND usu.idTipoDocumento.id=" + idTipoDocumento +
					""+"AND usu.nombre.id=" + idUsuarioN + ""+"AND usu.apellido.id=" + idUsuarioA + "");
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
		return listaUsuario ;
	}
	
	

	public String validarUsuario(Usuario usu) {
		String roles = "none";

		try {
			this.entity.getTransaction().begin();
			q = this.entity.createQuery("SELECT usu FROM Usuario usu WHERE usu.contrasena='"
					+ usu.getContrasena() + "' And usu.correo='" + usu.getCorreo() + "'");
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
