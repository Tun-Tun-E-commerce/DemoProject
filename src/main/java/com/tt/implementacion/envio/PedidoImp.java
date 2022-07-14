package com.tt.implementacion.envio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.envio.IPedido;
import com.tt.implementacion.venta.FacturaImp;
import com.tt.modelo.envio.Envio;
import com.tt.modelo.envio.Pedido;
import com.tt.modelo.venta.Factura;
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
			Query q = this.entity.createQuery("SELECT p FROM Pedido p");
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

	public List<Pedido> exportarEnvio(int idEnvio) {
		EnvioImp eImp = new EnvioImp();
		Envio e = new Envio();
		try {
			e = eImp.econtrarId(idEnvio);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT p FROM Pedido p WHERE p.idEnvio.id="+idEnvio+"");
			this.listaPedido = q.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaPedido;
	}

	public List<Pedido> exportarFactura(int idFactura) {
		FacturaImp fImp = new FacturaImp();
		Factura f = new Factura();
		try {
			f = fImp.econtrarId(idFactura);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT p FROM Pedido p WHERE p.idFactura.id="+idFactura+"");
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

	public List<Pedido> exportarMulticriterio(int idFactura, int idEnvio) {
		EnvioImp eImp = new EnvioImp();
		Envio e = new Envio();
		FacturaImp fImp = new FacturaImp();
		Factura f = new Factura();
		try {
			f = fImp.econtrarId(idFactura);
			e = eImp.econtrarId(idEnvio);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT p FROM Pedido p WHERE p.idFactura.id="+idFactura+"AND p.idEnvio.id="+idEnvio+"");
			this.listaPedido = q.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
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
