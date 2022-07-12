package com.tt.implementacion.inventario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.inventario.IReferenciaProducto;
import com.tt.modelo.inventario.ReferenciaProducto;
import com.tt.modelo.usuario.Rol;
import com.tt.utilidades.JPAUtil;

public class ReferenciaProductoImp implements IReferenciaProducto {

	EntityManager entity;

	public ReferenciaProductoImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<ReferenciaProducto> listaReferenciaProducto = new ArrayList<ReferenciaProducto>();
	Query q;

	@Override
	public List<ReferenciaProducto> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT refp FROM ReferenciaProducto refp");
			listaReferenciaProducto = q.getResultList();
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
		return listaReferenciaProducto;
	}

	@Override
	public ReferenciaProducto econtrarId(int id) {
		ReferenciaProducto refPro = new ReferenciaProducto();
		try {
			this.entity.getTransaction().begin();
			refPro = this.entity.find(ReferenciaProducto.class, id);
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
		return refPro;
	}

	@Override
	public void agregar(ReferenciaProducto referenciaProducto) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(referenciaProducto);
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
	public void actualizar(ReferenciaProducto referenciaProducto) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(referenciaProducto);
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
			ReferenciaProducto refPro = new ReferenciaProducto();
			refPro = this.entity.find(ReferenciaProducto.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(refPro);
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
