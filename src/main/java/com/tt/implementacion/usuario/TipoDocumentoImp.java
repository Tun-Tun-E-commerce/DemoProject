package com.tt.implementacion.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.usuario.ITipoDocumento;
import com.tt.modelo.usuario.TipoDocumento;
import com.tt.utilidades.JPAUtil;

public class TipoDocumentoImp implements ITipoDocumento {

	EntityManager entity;

	public TipoDocumentoImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<TipoDocumento> listaTipoDocumento = new ArrayList<TipoDocumento>();
	Query q;

	@Override
	public List<TipoDocumento> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT t FROM TipoDocumento t");
			listaTipoDocumento = q.getResultList();
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
		return listaTipoDocumento;
	}

	@Override
	public TipoDocumento econtrarId(int id) {
		TipoDocumento t = new TipoDocumento();
		try {
			this.entity.getTransaction().begin();
			t = this.entity.find(TipoDocumento.class, id);
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
		return t;
	}

	@Override
	public void agregar(TipoDocumento tipoDocumento) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(tipoDocumento);
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
	public void actualizar(TipoDocumento tipoDocumento) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(tipoDocumento);
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
			TipoDocumento t = new TipoDocumento();
			t = this.entity.find(TipoDocumento.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(t);
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
