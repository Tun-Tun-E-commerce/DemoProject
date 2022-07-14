package com.tt.implementacion.envio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.envio.IDetallePedidoProducto;
import com.tt.implementacion.venta.ProductoImp;
import com.tt.modelo.envio.DetallePedidoProducto;
import com.tt.modelo.envio.Pedido;
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
			Query q = this.entity.createQuery("SELECT dpp FROM DetallePedidoProducto dpp");
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
		DetallePedidoProducto dpp = new DetallePedidoProducto();
		try {
			this.entity.getTransaction().begin();
			dpp = this.entity.find(DetallePedidoProducto.class, id);
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
		return dpp;
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
			DetallePedidoProducto dpp = new DetallePedidoProducto();
			dpp = this.entity.find(DetallePedidoProducto.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(dpp);
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

	public List<DetallePedidoProducto> exportarValorTotal(int idValorTotal) {
		DetallePedidoProductoImp dppImp = new DetallePedidoProductoImp();
		DetallePedidoProducto dpp = new DetallePedidoProducto();
		try {
			dpp = dppImp.econtrarId(idValorTotal);
			this.entity.getTransaction().begin();
			Query q = this.entity
					.createQuery("SELECT dpp FROM DetallePedidoProducto dpp WHERE dpp.valorTotal.id=" + idValorTotal + "");
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

	public List<DetallePedidoProducto> exportarProducto(int idProducto) {
		ProductoImp pImp = new ProductoImp();
		Producto p = new Producto();
		try {
			p = pImp.econtrarId(idProducto);
			this.entity.getTransaction().begin();
			Query q = this.entity
					.createQuery("SELECT dpp FROM DetallePedidoProducto dpp WHERE dpp.idProducto.id=" + idProducto + "");
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

	public List<DetallePedidoProducto> exportarPedido(int idPedido) {
		PedidoImp peImp = new PedidoImp();
		Pedido pe = new Pedido();
		try {
			pe = peImp.econtrarId(idPedido);
			this.entity.getTransaction().begin();
			Query q = this.entity
					.createQuery("SELECT a FROM DetallePedidoProducto a WHERE a.idPedido.id=" + idPedido + "");
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

	public List<DetallePedidoProducto> exportarMulticriterio1(int idPedido, int idValor) {

		DetallePedidoProductoImp dppImp = new DetallePedidoProductoImp();
		DetallePedidoProducto dpp = new DetallePedidoProducto();

		PedidoImp peImp = new PedidoImp();
		Pedido pe = new Pedido();

		try {
			pe = peImp.econtrarId(idPedido);
			dpp = dppImp.econtrarId(idValor);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT dpp FROM DetallePedidoProducto dpp WHERE dpp.idPedido.id="+idPedido+"AND dpp.valorTotal.id="+idValor+"");
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
	
	public List<DetallePedidoProducto> exportarMulticriterio2(int idValor, int idProducto) {
		ProductoImp pImp = new ProductoImp();
		Producto p = new Producto();

		DetallePedidoProductoImp dppImp = new DetallePedidoProductoImp();
		DetallePedidoProducto dpp = new DetallePedidoProducto();

		try {
			dpp = dppImp.econtrarId(idValor);
			p = pImp.econtrarId(idProducto);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT dpp FROM DetallePedidoProducto dpp WHERE dpp.valorTotal.id="+idValor+"AND dpp.idProducto.id="+idProducto+"");
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
	
	public List<DetallePedidoProducto> exportarMulticriterio3(int idPedido, int idProducto) {
		ProductoImp pImp = new ProductoImp();
		Producto p = new Producto();

		PedidoImp peImp = new PedidoImp();
		Pedido pe = new Pedido();

		try {
			pe = peImp.econtrarId(idPedido);
			p = pImp.econtrarId(idProducto);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT dpp FROM DetallePedidoProducto dpp WHERE dpp.idPedido.id="+idPedido+"AND dpp.idProducto.id="+idProducto+"");
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
	
	public List<DetallePedidoProducto> exportarMulticriterio(int idPedido, int idValor, int idProducto) {
		ProductoImp pImp = new ProductoImp();
		Producto p = new Producto();

		DetallePedidoProductoImp dppImp = new DetallePedidoProductoImp();
		DetallePedidoProducto dpp = new DetallePedidoProducto();

		PedidoImp peImp = new PedidoImp();
		Pedido pe = new Pedido();

		try {
			pe = peImp.econtrarId(idPedido);
			dpp = dppImp.econtrarId(idValor);
			p = pImp.econtrarId(idProducto);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT dpp FROM DetallePedidoProducto dpp WHERE dpp.idPedido.id="+idPedido+"AND dpp.valorTotal.id="+idValor+"AND dpp.idProducto.id="+idProducto+"");
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
