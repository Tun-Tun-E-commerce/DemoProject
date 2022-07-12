package com.tt.implementacion.envio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.envio.IPedido;
import com.tt.modelo.envio.Envio;
import com.tt.modelo.envio.Pedido;
import com.tt.utilidades.JPAUtil;

public class PedidoImp implements IPedido {
	EntityManager entity;

	public PedidoImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Pedido> listaPedido = new ArrayList<Pedido>();
	Query q;

	@Override
	public List<Pedido> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT ped FROM Pedido ped");
			listaPedido = q.getResultList();
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
		return listaPedido;
	}

	@Override
	public Pedido econtrarId(int id) {
		Pedido p = new Pedido();
		try {
			this.entity.getTransaction().begin();
			p = this.entity.find(Pedido.class, id);
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
	public void agregar(Pedido pedido) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(pedido);
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
	public void actualizar(Pedido pedido) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(pedido);
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
			Pedido p = new Pedido();
			p = this.entity.find(Pedido.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(p);
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
	
	public List<Pedido> exportPedido(int id) {
		EnvioImp eImp = new EnvioImp();
		Envio en = new Envio();
		try {
			en = eImp.econtrarId(id);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT a FROM Pedido a WHERE a.idEnvio.id="+id+"");
			this.listaPedido = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaPedido;
	}

}
