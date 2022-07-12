package com.tt.implementacion.envio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.envio.IDetallePedidoProducto;
import com.tt.implementacion.venta.ProductoImp;
import com.tt.modelo.envio.Almacen;
import com.tt.modelo.envio.DetallePedidoProducto;
import com.tt.modelo.inventario.DetalleOrdenProduccion;
import com.tt.modelo.venta.Producto;
import com.tt.utilidades.JPAUtil;

public class DetallePedidoProductoImp implements IDetallePedidoProducto {

	EntityManager entity;

	public DetallePedidoProductoImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<DetallePedidoProducto> listaDetallePedidoProducto = new ArrayList<DetallePedidoProducto>();
	Query q;

	@Override
	public List<DetallePedidoProducto> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT dtp FROM DetallePedidoProducto dtp");
			listaDetallePedidoProducto = q.getResultList();
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
		return listaDetallePedidoProducto;
	}

	@Override
	public DetallePedidoProducto econtrarId(int id) {
		DetallePedidoProducto dtp = new DetallePedidoProducto();
		try {
			this.entity.getTransaction().begin();
			dtp = this.entity.find(DetallePedidoProducto.class, id);
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
		return dtp;
	}

	@Override
	public void agregar(DetallePedidoProducto detallePedidoProducto) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(detallePedidoProducto);
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
	public void actualizar(DetallePedidoProducto detallePedidoProducto) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(detallePedidoProducto);
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
			DetallePedidoProducto dtp = new DetallePedidoProducto();
			dtp = this.entity.find(DetallePedidoProducto.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(dtp);
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
	
	public List<DetallePedidoProducto> exportDPO(int id) {
		ProductoImp pImp = new ProductoImp();
		Producto p = new Producto();
		try {
			p = pImp.econtrarId(id);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT a FROM DetallePedidoProducto a WHERE a.idProducto.id="+id+"");
			this.listaDetallePedidoProducto = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaDetallePedidoProducto;
	}

}
