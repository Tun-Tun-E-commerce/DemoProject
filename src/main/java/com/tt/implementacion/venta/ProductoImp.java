package com.tt.implementacion.venta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.venta.IProducto;
import com.tt.implementacion.usuario.UsuarioImp;
import com.tt.modelo.envio.Almacen;
import com.tt.modelo.usuario.Usuario;
import com.tt.modelo.venta.Producto;
import com.tt.utilidades.JPAUtil;

public class ProductoImp implements IProducto {

	EntityManager entity;

	public ProductoImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Producto> listaProducto = new ArrayList<Producto>();
	Query q;

	@Override
	public List<Producto> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT pro FROM Producto pro");
			listaProducto = q.getResultList();
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
		return listaProducto;
	}

	@Override
	public Producto econtrarId(int id) {
		Producto p = new Producto();
		try {
			this.entity.getTransaction().begin();
			p = this.entity.find(Producto.class, id);
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
	public void agregar(Producto producto) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(producto);
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
	public void actualizar(Producto producto) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(producto);
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
			Producto p = new Producto();
			p = this.entity.find(Producto.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(p);
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
	
	public List<Producto> exportProductoValor(int idProductoValor) {
		ProductoImp pImp = new ProductoImp();
		Producto p = new Producto();
		try {
			p = pImp.econtrarId(idProductoValor);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT a FROM Producto a WHERE a.valorUnitario.id="+idProductoValor+"");
			this.listaProducto = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaProducto;
	}
	
	public List<Producto> exportProductoCantidad(int idProductoCantidad) {
		ProductoImp pImp = new ProductoImp();
		Producto p = new Producto();
		try {
			p = pImp.econtrarId(idProductoCantidad);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT a FROM Producto a WHERE a.cantidad.id="+idProductoCantidad+"");
			this.listaProducto = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaProducto;
	}
	
	public List<Producto> exportarMulticriterio(int idProductoValor, int idProductoCantidad) {
		ProductoImp pImp = new ProductoImp();
		Producto p = new Producto();
		try {
			p = pImp.econtrarId(idProductoValor);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT a FROM Producto a WHERE a.valorUnitario.id="+idProductoValor+"AND a.cantidad.id="+idProductoCantidad+"");
			this.listaProducto = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaProducto;
	}

}
