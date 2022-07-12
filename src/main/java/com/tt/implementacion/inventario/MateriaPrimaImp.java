package com.tt.implementacion.inventario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.inventario.IMateriaPrima;
import com.tt.modelo.inventario.MateriaPrima;
import com.tt.modelo.inventario.ReferenciaProducto;
import com.tt.utilidades.JPAUtil;

public class MateriaPrimaImp implements IMateriaPrima {

	EntityManager entity;

	public MateriaPrimaImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}
	
	private List<MateriaPrima> listaMateriaPrima = new ArrayList<MateriaPrima>();
	Query q;

	@Override
	public List<MateriaPrima> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT mt FROM MateriaPrima mt");
			listaMateriaPrima = q.getResultList();
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
		return listaMateriaPrima;
	}

	@Override
	public MateriaPrima econtrarId(int id) {
		MateriaPrima mt = new MateriaPrima();
		try {
			this.entity.getTransaction().begin();
			mt = this.entity.find(MateriaPrima.class, id);
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
		return mt;
	}

	@Override
	public void agregar(MateriaPrima materiaPrima) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(materiaPrima);
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
	public void actualizar(MateriaPrima materiaPrima) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(materiaPrima);
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
			MateriaPrima mt = new MateriaPrima();
			mt = this.entity.find(MateriaPrima.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(mt);
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
