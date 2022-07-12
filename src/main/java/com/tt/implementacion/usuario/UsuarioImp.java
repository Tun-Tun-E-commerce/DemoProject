package com.tt.implementacion.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.usuario.IUsuario;
import com.tt.modelo.usuario.Rol;
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

	public List<Usuario> exportUsuario(int id) {
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
