package com.tt.controlador.envio;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.tt.implementacion.envio.DetallePedidoProductoImp;
import com.tt.implementacion.envio.PedidoImp;
import com.tt.implementacion.venta.ProductoImp;
import com.tt.modelo.envio.DetallePedidoProducto;
import com.tt.modelo.envio.Pedido;
import com.tt.modelo.venta.Producto;
import com.tt.utilidades.envio.ExportarExcelDetallePedidoProducto;

@ManagedBean(name = "dppBean")
@RequestScoped
public class DetallePedidoProductoBean {
	DetallePedidoProducto dpp = new DetallePedidoProducto();
	List<DetallePedidoProducto> listaDetallePedidoProducto = new ArrayList<DetallePedidoProducto>();
	List<Pedido> listaPedido = new ArrayList<Pedido>();
	List<Producto> listaProducto = new ArrayList<Producto>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idPedido;
	private int idProducto;
	private int idValor;

	private void LlenarDetallePedidoProducto() {
		DetallePedidoProductoImp pImp = new DetallePedidoProductoImp();
		this.listaDetallePedidoProducto = pImp.encontrarTodo();
	}

	private void LlenarPedido() {
		PedidoImp pImp = new PedidoImp();
		this.listaPedido = pImp.encontrarTodo();
	}

	private void LlenarProducto() {
		ProductoImp pImp = new ProductoImp();
		this.listaProducto = pImp.encontrarTodo();
	}

	public DetallePedidoProducto getDpp() {
		return dpp;
	}

	public void setDpp(DetallePedidoProducto dpp) {
		this.dpp = dpp;
	}

	public List<DetallePedidoProducto> getListaDetallePedidoProducto() {
		return listaDetallePedidoProducto;
	}

	public void setListaDetallePedidoProducto(List<DetallePedidoProducto> listaDetallePedidoProducto) {
		this.listaDetallePedidoProducto = listaDetallePedidoProducto;
	}

	public List<Pedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdValor() {
		return idValor;
	}

	public void setIdValor(int idValor) {
		this.idValor = idValor;
	}

	public DetallePedidoProductoBean() {
		this.LlenarDetallePedidoProducto();
		this.LlenarPedido();
		this.LlenarProducto();
	}

	public List<DetallePedidoProducto> encontrarTodo() {
		DetallePedidoProductoImp dtImp = new DetallePedidoProductoImp();
		this.listaDetallePedidoProducto = dtImp.encontrarTodo();
		return this.listaDetallePedidoProducto;
	}

	public String agregar() {
		DetallePedidoProductoImp dtImp = new DetallePedidoProductoImp();
		PedidoImp pImp = new PedidoImp();
		ProductoImp proImp = new ProductoImp();
		Pedido p = new Pedido();
		Producto pro = new Producto();
		p = pImp.econtrarId(idPedido);
		pro = proImp.econtrarId(idProducto);
		dpp.setIdPedido(p);
		dpp.setIdProducto(pro);
		dtImp.agregar(dpp);
		return "/faces/Admin/envios/detallepedidoproducto.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		DetallePedidoProductoImp dtImp = new DetallePedidoProductoImp();
		dpp = dtImp.econtrarId(id);
		this.sessionMap.put("dpp", dpp);
		return "/faces/Admin/editar/envios/detallepedidoproducto.xhtml?faces-redirect=true";
	}

	public String actualizar(DetallePedidoProducto dpp) {
		System.out.println("Entro a actualizar DetallePedidoProducto");
		DetallePedidoProductoImp dtImp = new DetallePedidoProductoImp();
		PedidoImp pImp = new PedidoImp();
		ProductoImp proImp = new ProductoImp();
		Pedido p = new Pedido();
		Producto pro = new Producto();
		p = pImp.econtrarId(dpp.getIdPedido().getId());
		pro = proImp.econtrarId(dpp.getIdProducto().getId());
		System.out.println("Pedido" + p.toString());
		System.out.println("Producto" + pro.toString());
		dpp.setIdPedido(p);
		dpp.setIdProducto(pro);
		dtImp.actualizar(dpp);
		return "/faces/Admin/envios/detallepedidoproducto.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		DetallePedidoProductoImp dtImp = new DetallePedidoProductoImp();
		dtImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/detallepedidop.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaDetallePP " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		DetallePedidoProductoImp dopImp = new DetallePedidoProductoImp();
		if (idPedido != 0 && idProducto != 0 && idValor != 0) {
			this.listaDetallePedidoProducto = dopImp.exportarMulticriterio(idPedido, idValor, idProducto);
		} else if (idPedido != 0 && idValor != 0) {
			this.listaDetallePedidoProducto = dopImp.exportarMulticriterio1(idPedido, idValor);
		} else if (idValor != 0 && idProducto != 0) {
			this.listaDetallePedidoProducto = dopImp.exportarMulticriterio2(idValor, idProducto);
		} else if (idPedido != 0 && idProducto != 0) {
			this.listaDetallePedidoProducto = dopImp.exportarMulticriterio3(idPedido, idProducto);
		} else if (idPedido != 0) {
			this.listaDetallePedidoProducto = dopImp.exportarPedido(idPedido);
		} else if (idValor != 0) {
			this.listaDetallePedidoProducto = dopImp.exportarValorTotal(idValor);
		} else if (idProducto != 0) {
			this.listaDetallePedidoProducto = dopImp.exportarProducto(idProducto);
		} else {
			this.listaDetallePedidoProducto = dopImp.encontrarTodo();
		}

		ExportarExcelDetallePedidoProducto excelExportar = new ExportarExcelDetallePedidoProducto(
				this.listaDetallePedidoProducto);
		excelExportar.export(response);

	}
}
