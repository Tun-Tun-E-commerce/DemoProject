package com.tt.implementacion.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.usuario.IRol;
import com.tt.modelo.usuario.Rol;
import com.tt.utilidades.JPAUtil;

public class RolImp implements IRol {

	EntityManager entity;

	public RolImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Rol> listaRoles = new ArrayList<Rol>();
	Query q;

	@Override
	public List<Rol> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT r FROM Rol r");
			listaRoles = q.getResultList();
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
		return listaRoles;
	}

	@Override
	public Rol econtrarId(int id) {
		Rol r = new Rol();
		try {
			this.entity.getTransaction().begin();
			r = this.entity.find(Rol.class, id);
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
		return r;
	}

	@Override
	public void agregar(Rol rol) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(rol);
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
	public void actualizar(Rol rol) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(rol);
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
			Rol r = new Rol();
			r = this.entity.find(Rol.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(r);
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

}
