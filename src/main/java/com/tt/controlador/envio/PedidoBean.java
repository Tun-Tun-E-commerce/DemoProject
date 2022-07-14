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

import com.tt.implementacion.envio.EnvioImp;
import com.tt.implementacion.envio.PedidoImp;
import com.tt.implementacion.venta.FacturaImp;
import com.tt.modelo.envio.Envio;
import com.tt.modelo.envio.Pedido;
import com.tt.modelo.venta.Factura;
import com.tt.utilidades.envio.ExportarExcelPedido;

@ManagedBean(name = "pedidoBean")
@RequestScoped
public class PedidoBean {
	Pedido p = new Pedido();
	List<Pedido> listaPedido = new ArrayList<Pedido>();
	List<Factura> listaFactura = new ArrayList<Factura>();
	List<Envio> listaEnvio = new ArrayList<Envio>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idFactura;
	private int idEnvio;

	private void LlenarFactura() {
		FacturaImp fImp = new FacturaImp();
		this.listaFactura = fImp.encontrarTodo();
	}

	private void LlenarEnvio() {
		EnvioImp eImp = new EnvioImp();
		this.listaEnvio = eImp.encontrarTodo();
	}

	public Pedido getP() {
		return p;
	}

	public void setP(Pedido p) {
		this.p = p;
	}

	public List<Pedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public List<Factura> getListaFactura() {
		return listaFactura;
	}

	public void setListaFactura(List<Factura> listaFactura) {
		this.listaFactura = listaFactura;
	}

	public List<Envio> getListaEnvio() {
		return listaEnvio;
	}

	public void setListaEnvio(List<Envio> listaEnvio) {
		this.listaEnvio = listaEnvio;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(int idEnvio) {
		this.idEnvio = idEnvio;
	}

	public PedidoBean() {
		this.LlenarFactura();
		this.LlenarEnvio();
	}

	public List<Pedido> encontrarTodo() {
		PedidoImp pImp = new PedidoImp();
		this.listaPedido = pImp.encontrarTodo();
		return this.listaPedido;
	}

	public String agregar() {
		PedidoImp pImp = new PedidoImp();
		FacturaImp fImp = new FacturaImp();
		EnvioImp enImp = new EnvioImp();
		Factura f = new Factura();
		Envio en = new Envio();
		f = fImp.econtrarId(idFactura);
		en = enImp.econtrarId(idEnvio);
		p.setIdFactura(f);
		p.setIdEnvio(en);
		pImp.agregar(p);
		return "/faces/Admin/envios/pedido.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		PedidoImp pImp = new PedidoImp();
		p = pImp.econtrarId(id);
		this.sessionMap.put("p", p);
		return "/faces/Admin/editar/envios/pedido.xhtml?faces-redirect=true";
	}

	public String actualizar(Pedido p) {
		System.out.println("Entro a actualizar Pedido");
		PedidoImp pImp = new PedidoImp();
		FacturaImp fImp = new FacturaImp();
		EnvioImp enImp = new EnvioImp();
		Factura f = new Factura();
		Envio en = new Envio();
		f = fImp.econtrarId(p.getIdFactura().getId());
		en = enImp.econtrarId(p.getIdEnvio().getId());
		p.setIdFactura(f);
		p.setIdEnvio(en);
		pImp.actualizar(p);
		return "/faces/Admin/envios/pedido.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		PedidoImp pImp = new PedidoImp();
		pImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/envios/pedido.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaPedido " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		PedidoImp pImp = new PedidoImp();
		if (idFactura != 0 && idEnvio != 0) {
			this.listaPedido = pImp.exportarMulticriterio(idFactura, idEnvio);
		} else if (idEnvio != 0) {
			this.listaPedido = pImp.exportarEnvio(idEnvio);
		} else if (idFactura != 0) {
			this.listaPedido = pImp.exportarFactura(idFactura);
		} else {
			this.listaPedido = pImp.encontrarTodo();
		}

		ExportarExcelPedido excelExportar = new ExportarExcelPedido(this.listaPedido);
		excelExportar.export(response);
	}

}
