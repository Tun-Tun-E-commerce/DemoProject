package com.tt.implementacion.envio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.envio.IAlmacen;
import com.tt.implementacion.venta.ProductoImp;
import com.tt.modelo.envio.Almacen;
import com.tt.modelo.venta.Producto;
import com.tt.utilidades.JPAUtil;

public class AlmacenImp implements IAlmacen {
	EntityManager entity;

	public AlmacenImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Almacen> listaAlmacen = new ArrayList<Almacen>();
	Query q;

	@Override
	public List<Almacen> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT a FROM Almacen a");
			listaAlmacen = q.getResultList();
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
		return listaAlmacen;
	}

	@Override
	public Almacen econtrarId(int id) {
		Almacen a = new Almacen();
		try {
			this.entity.getTransaction().begin();
			a = this.entity.find(Almacen.class, id);
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
		return a;
	}

	@Override
	public void agregar(Almacen almacen) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(almacen);
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
	public void actualizar(Almacen almacen) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(almacen);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			JPAUtil.shutdown();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				System.out.println("Cerrando la entity");
			}
		}
	}

	@Override
	public void eliminar(int id) {
		try {
			Almacen a = new Almacen();
			a = this.entity.find(Almacen.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(a);
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
	
	public List<Almacen> exportarCantidad(int idAlmacenCantidad) {
		AlmacenImp aImp = new AlmacenImp();
		Almacen a = new Almacen();
		try {
			a = aImp.econtrarId(idAlmacenCantidad);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT a FROM Almacen a WHERE a.cantidad.id="+idAlmacenCantidad+"");
			this.listaAlmacen = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaAlmacen;
	}
	
	public List<Almacen> exportarProducto(int id) {
		ProductoImp pImp = new ProductoImp();
		Producto p = new Producto();
		try {
			p = pImp.econtrarId(id);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT a FROM Almacen a WHERE a.idProducto.id="+id+"");
			this.listaAlmacen = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaAlmacen;
	}
	
	public List<Almacen> exportarMultiCriterio(int id, int idAlmacenCantidad) {
		ProductoImp pImp = new ProductoImp();
		Producto p = new Producto();
		AlmacenImp aImp = new AlmacenImp();
		Almacen a = new Almacen();
		try {
			a= aImp.econtrarId(idAlmacenCantidad);
			p = pImp.econtrarId(id);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT a FROM Almacen a WHERE a.idProducto.id="+id+"AND a.cantidad.id="+idAlmacenCantidad+"");
			this.listaAlmacen = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaAlmacen;
	}

}
