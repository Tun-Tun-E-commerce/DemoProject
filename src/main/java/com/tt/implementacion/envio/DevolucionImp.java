package com.tt.implementacion.envio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.envio.IDevolucion;
import com.tt.implementacion.venta.FacturaImp;
import com.tt.modelo.envio.Devolucion;
import com.tt.modelo.venta.Factura;
import com.tt.utilidades.JPAUtil;

public class DevolucionImp implements IDevolucion {
	EntityManager entity;

	public DevolucionImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Devolucion> listaDevolucion = new ArrayList<Devolucion>();
	Query q;

	@Override
	public List<Devolucion> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT d FROM Devolucion d");
			listaDevolucion = q.getResultList();
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
		return listaDevolucion;
	}

	@Override
	public Devolucion econtrarId(int id) {
		Devolucion d = new Devolucion();
		try {
			this.entity.getTransaction().begin();
			d = this.entity.find(Devolucion.class, id);
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
		return d;
	}

	@Override
	public void agregar(Devolucion devolucion) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(devolucion);
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
	public void actualizar(Devolucion devolucion) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(devolucion);
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
			Devolucion d = new Devolucion();
			d = this.entity.find(Devolucion.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(d);
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

	public List<Devolucion> exportarFactura(int idFactura) {
		FacturaImp fImp = new FacturaImp();
		Factura f = new Factura();
		try {
			f = fImp.econtrarId(idFactura);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT d FROM Devolucion d WHERE d.idFactura.id=" + idFactura + "");
			this.listaDevolucion = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaDevolucion;
	}

	public List<Devolucion> exportarFecha(int idFechaDev) {
		
		try {
			econtrarId(idFechaDev);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT d FROM Devolucion d WHERE d.fecha.id=" + idFechaDev + "");
			this.listaDevolucion = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaDevolucion;
	}

	public List<Devolucion> exportarMulticriterio(int idFactura, int idFechaDev) {
		FacturaImp fImp = new FacturaImp();
		Factura f = new Factura();
		DevolucionImp dImp = new DevolucionImp();
		Devolucion d = new Devolucion();
		try {
			d = dImp.econtrarId(idFechaDev);
			f = fImp.econtrarId(idFactura);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT d FROM Devolucion d WHERE d.idFactura.id="+idFactura+"AND d.fecha.id="+idFechaDev+"");
			this.listaDevolucion = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaDevolucion;
	}

}
