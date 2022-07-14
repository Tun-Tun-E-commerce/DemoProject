package com.tt.implementacion.fidelizacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.fidelizacion.IRespuestaPqr;
import com.tt.modelo.fidelizacion.Pqr;
import com.tt.modelo.fidelizacion.RespuestaPqr;
import com.tt.utilidades.JPAUtil;

public class RespuestaPqrImp implements IRespuestaPqr {

	EntityManager entity;

	public RespuestaPqrImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<RespuestaPqr> listaRespuestaPqr = new ArrayList<RespuestaPqr>();
	Query q;

	@Override
	public List<RespuestaPqr> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT r FROM RespuestaPqr r");
			listaRespuestaPqr = q.getResultList();
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
		return listaRespuestaPqr;
	}

	@Override
	public RespuestaPqr econtrarId(int id) {
		RespuestaPqr r = new RespuestaPqr();
		try {
			this.entity.getTransaction().begin();
			r = this.entity.find(RespuestaPqr.class, id);
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
	public void agregar(RespuestaPqr respuestPqr) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(respuestPqr);
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
	public void actualizar(RespuestaPqr respuestPqr) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(respuestPqr);
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
			RespuestaPqr r = new RespuestaPqr();
			r = this.entity.find(RespuestaPqr.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(r);
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

	public List<RespuestaPqr> exportarPqr(int idPqr) {
		PqrImp pqrImp = new PqrImp();
		Pqr pqr = new Pqr();
		try {
			pqr = pqrImp.econtrarId(idPqr);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT r FROM RespuestaPqr r WHERE r.idPqr.id=" + idPqr + "");
			this.listaRespuestaPqr = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaRespuestaPqr;

	}
}