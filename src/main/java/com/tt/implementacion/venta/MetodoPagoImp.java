package com.tt.implementacion.venta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.venta.IMetodoPago;
import com.tt.modelo.venta.MetodoPago;
import com.tt.utilidades.JPAUtil;

public class MetodoPagoImp implements IMetodoPago {
	EntityManager entity;

	public MetodoPagoImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<MetodoPago> listaMetodoPago = new ArrayList<MetodoPago>();
	Query q;

	@Override
	public List<MetodoPago> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT m FROM MetodoPago m");
			listaMetodoPago = q.getResultList();
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
		return listaMetodoPago;
	}

	@Override
	public MetodoPago econtrarId(int id) {
		MetodoPago m = new MetodoPago();
		try {
			this.entity.getTransaction().begin();
			m = this.entity.find(MetodoPago.class, id);
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
		return m;
	}

	@Override
	public void agregar(MetodoPago metodoPago) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(metodoPago);
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
	public void actualizar(MetodoPago metodoPago) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(metodoPago);
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
			MetodoPago m = new MetodoPago();
			m = this.entity.find(MetodoPago.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(m);
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
