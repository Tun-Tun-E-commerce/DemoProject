package com.tt.implementacion.inventario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.inventario.IOrdenProduccion;
import com.tt.modelo.inventario.InventarioMateriaPrima;
import com.tt.modelo.inventario.OrdenProduccion;
import com.tt.utilidades.JPAUtil;

public class OrdenProduccionImp implements IOrdenProduccion {

	EntityManager entity;

	public OrdenProduccionImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<OrdenProduccion> listaOrdenProduccion = new ArrayList<OrdenProduccion>();
	Query q;

	@Override
	public List<OrdenProduccion> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT o FROM OrdenProduccion o");
			listaOrdenProduccion = q.getResultList();
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
		return listaOrdenProduccion;
	}

	@Override
	public OrdenProduccion econtrarId(int id) {
		OrdenProduccion ordenP = new OrdenProduccion();
		try {
			this.entity.getTransaction().begin();
			ordenP = this.entity.find(OrdenProduccion.class, id);
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
		return ordenP;
	}

	@Override
	public void agregar(OrdenProduccion ordenProduccion) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(ordenProduccion);
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
	public void actualizar(OrdenProduccion ordenProduccion) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(ordenProduccion);
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
			OrdenProduccion ordenP = new OrdenProduccion();
			ordenP = this.entity.find(OrdenProduccion.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(ordenP);
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
	
	public List<OrdenProduccion> exportOrdenProduccion(int id) {
		InventarioMateriaPrimaImp iIMp = new InventarioMateriaPrimaImp();
		InventarioMateriaPrima i = new InventarioMateriaPrima();
		try {
			i = iIMp.econtrarId(id);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT o FROM OrdenProduccion o WHERE o.idInventarioMateriaPrima.id="+id+"");
			this.listaOrdenProduccion = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaOrdenProduccion;
	}

}
