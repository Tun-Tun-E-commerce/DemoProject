package com.tt.implementacion.envio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.envio.IEnvio;
import com.tt.implementacion.usuario.UsuarioImp;
import com.tt.modelo.envio.Envio;
import com.tt.modelo.usuario.Usuario;
import com.tt.modelo.venta.Producto;
import com.tt.utilidades.JPAUtil;

public class EnvioImp implements IEnvio {
	EntityManager entity;

	public EnvioImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Envio> listaEnvio = new ArrayList<Envio>();
	Query q;

	@Override
	public List<Envio> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT env FROM Envio env");
			listaEnvio = q.getResultList();
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
		return listaEnvio;
	}

	@Override
	public Envio econtrarId(int id) {
		Envio en = new Envio();
		try {
			this.entity.getTransaction().begin();
			en = this.entity.find(Envio.class, id);
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
		return en;
	}

	@Override
	public void agregar(Envio envio) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(envio);
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
	public void actualizar(Envio envio) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(envio);
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
			Envio en = new Envio();
			en = this.entity.find(Envio.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(en);
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
	
	public List<Envio> exportEnvio(int id) {
		UsuarioImp uImp = new UsuarioImp();
		Usuario u = new Usuario();
		try {
			u = uImp.econtrarId(id);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT a FROM Envio a WHERE a.idUsuario.id="+id+"");
			this.listaEnvio = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaEnvio;
	}

}
