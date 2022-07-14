package com.tt.implementacion.venta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.venta.ICarritoCompra;
import com.tt.implementacion.usuario.UsuarioImp;
import com.tt.modelo.usuario.Usuario;
import com.tt.modelo.venta.CarritoCompra;
import com.tt.modelo.venta.Producto;
import com.tt.utilidades.JPAUtil;

public class CarritoCompraImp implements ICarritoCompra {
	EntityManager entity;

	public CarritoCompraImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<CarritoCompra> listaCarritoCompra = new ArrayList<CarritoCompra>();
	Query q;

	@Override
	public List<CarritoCompra> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT c FROM CarritoCompra c");
			listaCarritoCompra = q.getResultList();
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
		return listaCarritoCompra;
	}

	@Override
	public CarritoCompra econtrarId(int id) {
		CarritoCompra c = new CarritoCompra();
		try {
			this.entity.getTransaction().begin();
			c = this.entity.find(CarritoCompra.class, id);
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
		return c;
	}

	@Override
	public void agregar(CarritoCompra carritoCompra) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(carritoCompra);
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
	public void actualizar(CarritoCompra carritoCompra) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(carritoCompra);
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
			CarritoCompra c = new CarritoCompra();
			c = this.entity.find(CarritoCompra.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(c);
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

	public List<CarritoCompra> exportarProductoId(int idProducto) {
		ProductoImp pImp = new ProductoImp();
		Producto p = new Producto();
		try {
			p = pImp.econtrarId(idProducto);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT c FROM CarritoCompra c WHERE c.idProducto.id=" + idProducto + "");
			this.listaCarritoCompra = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaCarritoCompra;
	}

	public List<CarritoCompra> exportarUsuarioId(int idUsuario) {
		UsuarioImp uImp = new UsuarioImp();
		Usuario u = new Usuario();
		try {
			u = uImp.econtrarId(idUsuario);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT c FROM CarritoCompra c WHERE c.idUsuario.id=" + idUsuario + "");
			this.listaCarritoCompra = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaCarritoCompra;
	}

	public List<CarritoCompra> exportarMulticriterio(int idUsuario, int idProducto) {
		UsuarioImp uImp = new UsuarioImp();
		Usuario u = new Usuario();
		ProductoImp pImp = new ProductoImp();
		Producto p = new Producto();
		try {
			p = pImp.econtrarId(idProducto);
			u = uImp.econtrarId(idUsuario);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT c FROM CarritoCompra c WHERE c.idUsuario.id=" + idUsuario
					+ "AND c.idProducto.id=" + idProducto + "");
			// Query q1 = this.entity.createQuery("SELECT a FROM CarritoCompra a WHERE
			// a.idProducto.id="+idProducto+"");
			this.listaCarritoCompra = q.getResultList();
			// this.listaCarritoCompra = q1.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaCarritoCompra;
	}

}
