package com.tt.implementacion.inventario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.inventario.IDetalleOrdenProduccion;
import com.tt.modelo.inventario.DetalleOrdenProduccion;
import com.tt.modelo.inventario.MateriaPrima;
import com.tt.modelo.inventario.OrdenProduccion;
import com.tt.utilidades.JPAUtil;

public class DetalleOrdenProduccionImp implements IDetalleOrdenProduccion {

	EntityManager entity;

	public DetalleOrdenProduccionImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<DetalleOrdenProduccion> listaDetalleOrdenProduccion = new ArrayList<DetalleOrdenProduccion>();
	Query q;

	@Override
	public List<DetalleOrdenProduccion> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT dop FROM DetalleOrdenProduccion dop");
			listaDetalleOrdenProduccion = q.getResultList();
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
		return listaDetalleOrdenProduccion;
	}

	@Override
	public DetalleOrdenProduccion econtrarId(int id) {
		DetalleOrdenProduccion dop = new DetalleOrdenProduccion();
		try {
			this.entity.getTransaction().begin();
			dop = this.entity.find(DetalleOrdenProduccion.class, id);
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
		return dop;
	}

	@Override
	public void agregar(DetalleOrdenProduccion detalleOrdenProduccion) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(detalleOrdenProduccion);
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
	public void actualizar(DetalleOrdenProduccion detalleOrdenProduccion) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(detalleOrdenProduccion);
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
			DetalleOrdenProduccion dop = new DetalleOrdenProduccion();
			dop = this.entity.find(DetalleOrdenProduccion.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(dop);
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

	public List<DetalleOrdenProduccion> exportarMateriaId(int idMateriaPrima) {
		MateriaPrimaImp mImp = new MateriaPrimaImp();
		MateriaPrima m = new MateriaPrima();
		try {
			m = mImp.econtrarId(idMateriaPrima);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery(
					"SELECT dop FROM DetalleOrdenProduccion dop WHERE dop.idMateriaPrima.id=" + idMateriaPrima + "");
			this.listaDetalleOrdenProduccion = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaDetalleOrdenProduccion;
	}

	public List<DetalleOrdenProduccion> exportarOrdenpId(int idOrdenProduccion) {
		OrdenProduccionImp opImp = new OrdenProduccionImp();
		OrdenProduccion op = new OrdenProduccion();
		try {
			op = opImp.econtrarId(idOrdenProduccion);
			this.entity.getTransaction().begin();
			Query q = this.entity
					.createQuery("SELECT dop FROM DetalleOrdenProduccion dop WHERE dop.idOrdenProduccion.id="
							+ idOrdenProduccion + "");
			this.listaDetalleOrdenProduccion = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaDetalleOrdenProduccion;
	}

	public List<DetalleOrdenProduccion> exportarMulticriterioO(int idMateriaPrima, int idOrdenProduccion) {
		OrdenProduccionImp opImp = new OrdenProduccionImp();
		OrdenProduccion op = new OrdenProduccion();
		MateriaPrimaImp mImp = new MateriaPrimaImp();
		MateriaPrima m = new MateriaPrima();
		try {
			m = mImp.econtrarId(idMateriaPrima);
			op = opImp.econtrarId(idOrdenProduccion);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT dop FROM DetalleOrdenProduccion dop WHERE dop.idMateriaPrima.id="
					+ idMateriaPrima + "AND dop.idOrdenProduccion.id=" + idOrdenProduccion + "");
			this.listaDetalleOrdenProduccion = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaDetalleOrdenProduccion;
	}

}
