package com.tt.implementacion.inventario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.inventario.IinventarioMateriaPrima;
import com.tt.modelo.inventario.InventarioMateriaPrima;
import com.tt.modelo.inventario.MateriaPrima;
import com.tt.utilidades.JPAUtil;

public class InventarioMateriaPrimaImp implements IinventarioMateriaPrima {

	EntityManager entity;

	public InventarioMateriaPrimaImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<InventarioMateriaPrima> listaInvMt = new ArrayList<InventarioMateriaPrima>();
	Query q;

	@Override
	public List<InventarioMateriaPrima> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT i FROM InventarioMateriaPrima i");
			listaInvMt = q.getResultList();
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
		return listaInvMt;
	}

	@Override
	public InventarioMateriaPrima econtrarId(int id) {
		InventarioMateriaPrima i = new InventarioMateriaPrima();
		try {
			this.entity.getTransaction().begin();
			i = this.entity.find(InventarioMateriaPrima.class, id);
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
		return i;
	}

	@Override
	public void agregar(InventarioMateriaPrima invMateriaPrima) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(invMateriaPrima);
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
	public void actualizar(InventarioMateriaPrima invMateriaPrima) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(invMateriaPrima);
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
			InventarioMateriaPrima invMt = new InventarioMateriaPrima();
			invMt = this.entity.find(InventarioMateriaPrima.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(invMt);
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

	public List<InventarioMateriaPrima> exportarMateriaPrima(int idMateriaPrima) {
		MateriaPrimaImp mImp = new MateriaPrimaImp();
		MateriaPrima m = new MateriaPrima();
		try {
			m = mImp.econtrarId(idMateriaPrima);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery(
					"SELECT i FROM InventarioMateriaPrima i WHERE i.idMateriaPrima.id=" + idMateriaPrima + "");
			this.listaInvMt = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaInvMt;
	}

}
