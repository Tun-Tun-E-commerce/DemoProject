package com.tt.implementacion.fidelizacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.fidelizacion.ICalificacion;
import com.tt.modelo.fidelizacion.Calificacion;
import com.tt.modelo.fidelizacion.Pqr;
import com.tt.utilidades.JPAUtil;

public class CalificacionImp implements ICalificacion {
	EntityManager entity;

	public CalificacionImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Calificacion> listaCalificacion = new ArrayList<Calificacion>();
	Query q;

	@Override
	public List<Calificacion> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT c FROM Calificacion c");
			listaCalificacion = q.getResultList();
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
		return listaCalificacion;
	}

	@Override
	public Calificacion econtrarId(int id) {
		Calificacion c = new Calificacion();
		try {
			this.entity.getTransaction().begin();
			c = this.entity.find(Calificacion.class, id);
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
		return c;
	}

	@Override
	public void agregar(Calificacion calificacion) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(calificacion);
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
	public void actualizar(Calificacion calificacion) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(calificacion);
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
			Calificacion c = new Calificacion();
			c = this.entity.find(Calificacion.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(c);
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

	public List<Calificacion> exportarPqr(int idPqr) {
		PqrImp pqrImp = new PqrImp();
		Pqr pqr = new Pqr();
		try {
			pqr = pqrImp.econtrarId(idPqr);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT c FROM Calificacion r WHERE r.idPqr.id=" + idPqr + "");
			this.listaCalificacion = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaCalificacion;

	}

}
