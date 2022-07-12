package com.tt.implementacion.venta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.venta.ICategoria;
import com.tt.modelo.envio.CompaniaEnvio;
import com.tt.modelo.venta.Categoria;
import com.tt.utilidades.JPAUtil;

public class CategoriaImp implements ICategoria {

	EntityManager entity;

	public CategoriaImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Categoria> listaCategoria = new ArrayList<Categoria>();
	Query q;

	@Override
	public List<Categoria> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT ca FROM Categoria ca");
			listaCategoria = q.getResultList();
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
		return listaCategoria;
	}

	@Override
	public Categoria econtrarId(int id) {
		Categoria c = new Categoria();
		try {
			this.entity.getTransaction().begin();
			c = this.entity.find(Categoria.class, id);
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
	public void agregar(Categoria categoria) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(categoria);
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
	public void actualizar(Categoria categoria) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(categoria);
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
			Categoria c = new Categoria();
			c = this.entity.find(Categoria.class, id);
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

}
