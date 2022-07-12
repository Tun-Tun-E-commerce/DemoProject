package com.tt.implementacion.venta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.venta.IFactura;
import com.tt.modelo.venta.Factura;
import com.tt.utilidades.JPAUtil;

public class FacturaImp implements IFactura {
	EntityManager entity;

	public FacturaImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Factura> listaFactura = new ArrayList<Factura>();
	Query q;

	@Override
	public List<Factura> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT f FROM Factura f");
			listaFactura = q.getResultList();
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
		return listaFactura;
	}

	@Override
	public Factura econtrarId(int id) {
		Factura f = new Factura();
		try {
			this.entity.getTransaction().begin();
			f = this.entity.find(Factura.class, id);
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
		return f;
	}

	@Override
	public void agregar(Factura factura) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(factura);
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
	public void actualizar(Factura factura) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(factura);
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
			Factura f = new Factura();
			f = this.entity.find(Factura.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(f);
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
	
	public List<Factura> exportFactura(int id) {
		FacturaImp fImp = new FacturaImp();
		Factura f = new Factura();
		try {
			f = fImp.econtrarId(id);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT a FROM Factura a WHERE a.idMetodoPago.id="+id+"");
			this.listaFactura = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaFactura;
	}

}
