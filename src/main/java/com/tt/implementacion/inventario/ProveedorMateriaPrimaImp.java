package com.tt.implementacion.inventario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.inventario.IProveedorMateriaPrima;
import com.tt.modelo.inventario.MateriaPrima;
import com.tt.modelo.inventario.ProveedorMateriaPrima;
import com.tt.utilidades.JPAUtil;

public class ProveedorMateriaPrimaImp implements IProveedorMateriaPrima {

	EntityManager entity;

	public ProveedorMateriaPrimaImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<ProveedorMateriaPrima> listaPmt = new ArrayList<ProveedorMateriaPrima>();
	Query q;

	@Override
	public List<ProveedorMateriaPrima> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT p FROM ProveedorMateriaPrima p");
			listaPmt = q.getResultList();
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
		return listaPmt;
	}

	@Override
	public ProveedorMateriaPrima econtrarId(int id) {
		ProveedorMateriaPrima p = new ProveedorMateriaPrima();
		try {
			this.entity.getTransaction().begin();
			p = this.entity.find(ProveedorMateriaPrima.class, id);
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
	public void agregar(ProveedorMateriaPrima pmt) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(pmt);
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
	public void actualizar(ProveedorMateriaPrima pmt) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(pmt);
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
			ProveedorMateriaPrima p = new ProveedorMateriaPrima();
			p = this.entity.find(ProveedorMateriaPrima.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(p);
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

	public List<ProveedorMateriaPrima> exportarMateriaPrima(int idMateriaPrima) {
		MateriaPrimaImp mImp = new MateriaPrimaImp();
		MateriaPrima m = new MateriaPrima();
		try {
			m = mImp.econtrarId(idMateriaPrima);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery(
					"SELECT p FROM ProveedorMateriaPrima p WHERE p.idMateriaPrima.id=" + idMateriaPrima + "");
			this.listaPmt = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaPmt;
	}

}
