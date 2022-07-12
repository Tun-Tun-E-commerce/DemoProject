package com.tt.implementacion.fidelizacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.fidelizacion.IPqr;
import com.tt.modelo.fidelizacion.Pqr;
import com.tt.modelo.usuario.Rol;
import com.tt.utilidades.JPAUtil;

public class PqrImp implements IPqr {

	EntityManager entity;

	public PqrImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Pqr> listaPqr = new ArrayList<Pqr>();
	Query q;

	@Override
	public List<Pqr> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT pqr FROM Pqr pqr");
			listaPqr = q.getResultList();
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
		return listaPqr;
	}

	@Override
	public Pqr econtrarId(int id) {
		Pqr pqr = new Pqr();
		try {
			this.entity.getTransaction().begin();
			pqr = this.entity.find(Pqr.class, id);
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
		return pqr;
	}

	@Override
	public void agregar(Pqr pqr) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(pqr);
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
	public void actualizar(Pqr pqr) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(pqr);
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
			Pqr pqr = new Pqr();
			pqr = this.entity.find(Pqr.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(pqr);
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
