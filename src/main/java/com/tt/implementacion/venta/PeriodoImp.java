package com.tt.implementacion.venta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.venta.IPeriodo;
import com.tt.modelo.venta.Periodo;
import com.tt.utilidades.JPAUtil;

public class PeriodoImp implements IPeriodo {

	EntityManager entity;

	public PeriodoImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Periodo> listaPeriodo = new ArrayList<Periodo>();
	Query q;

	@Override
	public List<Periodo> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT p FROM Periodo p");
			listaPeriodo = q.getResultList();
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
		return listaPeriodo;
	}

	@Override
	public Periodo econtrarId(int id) {
		Periodo p = new Periodo();
		try {
			this.entity.getTransaction().begin();
			p = this.entity.find(Periodo.class, id);
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
		return p;
	}

	@Override
	public void agregar(Periodo periodo) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(periodo);
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
	public void actualizar(Periodo periodo) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(periodo);
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
			Periodo p = new Periodo();
			p = this.entity.find(Periodo.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(p);
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
