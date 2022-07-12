package com.tt.implementacion.envio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.envio.ICompaniaEnvio;
import com.tt.modelo.envio.CompaniaEnvio;
import com.tt.utilidades.JPAUtil;

public class CompaniaEnvioImp implements ICompaniaEnvio {

	EntityManager entity;

	public CompaniaEnvioImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<CompaniaEnvio> listaCompaniaEnvio = new ArrayList<CompaniaEnvio>();
	Query q;

	@Override
	public List<CompaniaEnvio> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT ce FROM CompaniaEnvio ce");
			listaCompaniaEnvio = q.getResultList();
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
		return listaCompaniaEnvio;
	}

	@Override
	public CompaniaEnvio econtrarId(int id) {
		CompaniaEnvio companiaE = new CompaniaEnvio();
		try {
			this.entity.getTransaction().begin();
			companiaE = this.entity.find(CompaniaEnvio.class, id);
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
		return companiaE;
	}

	@Override
	public void agregar(CompaniaEnvio companiaEnvio) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(companiaEnvio);
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
	public void actualizar(CompaniaEnvio companiaEnvio) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(companiaEnvio);
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
			CompaniaEnvio companiaE = new CompaniaEnvio();
			companiaE = this.entity.find(CompaniaEnvio.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(companiaE);
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
