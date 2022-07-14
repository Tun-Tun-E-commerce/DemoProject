package com.tt.implementacion.envio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.envio.IProveedorEmpresa;
import com.tt.modelo.envio.ProveedorEmpresa;
import com.tt.utilidades.JPAUtil;

public class ProveedorEmpresaImp implements IProveedorEmpresa {

	EntityManager entity;

	public ProveedorEmpresaImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<ProveedorEmpresa> listaProveedorEmpresa = new ArrayList<ProveedorEmpresa>();
	Query q;

	@Override
	public List<ProveedorEmpresa> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT pe FROM ProveedorEmpresa pe");
			listaProveedorEmpresa = q.getResultList();
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
		return listaProveedorEmpresa;
	}

	@Override
	public ProveedorEmpresa econtrarId(int id) {
		ProveedorEmpresa pe = new ProveedorEmpresa();
		try {
			this.entity.getTransaction().begin();
			pe = this.entity.find(ProveedorEmpresa.class, id);
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
		return pe;
	}

	@Override
	public void agregar(ProveedorEmpresa proveedorE) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(proveedorE);
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
	public void actualizar(ProveedorEmpresa proveedorE) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(proveedorE);
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
			ProveedorEmpresa pe = new ProveedorEmpresa();
			pe = this.entity.find(ProveedorEmpresa.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(pe);
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
