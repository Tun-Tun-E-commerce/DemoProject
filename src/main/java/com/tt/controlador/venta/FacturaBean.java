package com.tt.controlador.venta;

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

import com.tt.implementacion.venta.CarritoCompraImp;
import com.tt.implementacion.venta.FacturaImp;
import com.tt.implementacion.venta.MetodoPagoImp;
import com.tt.implementacion.venta.PeriodoImp;
import com.tt.modelo.venta.CarritoCompra;
import com.tt.modelo.venta.Factura;
import com.tt.modelo.venta.MetodoPago;
import com.tt.modelo.venta.Periodo;
import com.tt.utilidades.venta.ExportarExcelFactura;

@ManagedBean(name = "fBean")
@RequestScoped
public class FacturaBean {
	Factura f = new Factura();
	List<Factura> listaFactura = new ArrayList<Factura>();
	List<CarritoCompra> listaCarritoCompra = new ArrayList<CarritoCompra>();
	List<MetodoPago> listaMetodoPago = new ArrayList<MetodoPago>();
	List<Periodo> listaPeriodo = new ArrayList<Periodo>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idCarrito;

	private int idMetodoPago;

	private int idPeriodo;

	private void LlenarCarrito() {
		CarritoCompraImp cImp = new CarritoCompraImp();
		this.listaCarritoCompra = cImp.encontrarTodo();
	}

	private void LlenarMetodoPago() {
		MetodoPagoImp mImp = new MetodoPagoImp();
		this.listaMetodoPago = mImp.encontrarTodo();
	}

	private void LlenarPeriodo() {
		PeriodoImp pImp = new PeriodoImp();
		this.listaPeriodo = pImp.encontrarTodo();
	}

	public Factura getF() {
		return f;
	}

	public void setF(Factura f) {
		this.f = f;
	}

	public List<Factura> getListaFactura() {
		return listaFactura;
	}

	public void setListaFactura(List<Factura> listaFactura) {
		this.listaFactura = listaFactura;
	}

	public List<CarritoCompra> getListaCarritoCompra() {
		return listaCarritoCompra;
	}

	public void setListaCarritoCompra(List<CarritoCompra> listaCarritoCompra) {
		this.listaCarritoCompra = listaCarritoCompra;
	}

	public List<MetodoPago> getListaMetodoPago() {
		return listaMetodoPago;
	}

	public void setListaMetodoPago(List<MetodoPago> listaMetodoPago) {
		this.listaMetodoPago = listaMetodoPago;
	}

	public List<Periodo> getListaPeriodo() {
		return listaPeriodo;
	}

	public void setListaPeriodo(List<Periodo> listaPeriodo) {
		this.listaPeriodo = listaPeriodo;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public int getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public int getIdMetodoPago() {
		return idMetodoPago;
	}

	public void setIdMetodoPago(int idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}

	public int getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public FacturaBean() {
		this.LlenarCarrito();
		this.LlenarMetodoPago();
		this.LlenarPeriodo();
	}

	public List<Factura> encontrarTodo() {
		FacturaImp fImp = new FacturaImp();
		this.listaFactura = fImp.encontrarTodo();
		return this.listaFactura;
	}

	public String agregar() {
		FacturaImp fImp = new FacturaImp();
		CarritoCompraImp cImp = new CarritoCompraImp();
		MetodoPagoImp mImp = new MetodoPagoImp();
		PeriodoImp peImp = new PeriodoImp();
		CarritoCompra c = new CarritoCompra();
		MetodoPago m = new MetodoPago();
		Periodo p = new Periodo();
		m = mImp.econtrarId(idMetodoPago);
		c = cImp.econtrarId(idCarrito);
		p = peImp.econtrarId(idPeriodo);
		f.setIdMetodoPago(m);
		f.setIdCarrito(c);
		f.setIdPeriodo(p);
		fImp.agregar(f);
		return "/faces/Admin/facturas.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		FacturaImp fImp = new FacturaImp();
		f = fImp.econtrarId(id);
		this.sessionMap.put("f", f);
		return "/faces/Admin/editar/editarFACTURA.xhtml?faces-redirect=true";
	}

	public String actualizar(Factura f) {
		FacturaImp fImp = new FacturaImp();
		CarritoCompraImp cImp = new CarritoCompraImp();
		MetodoPagoImp mImp = new MetodoPagoImp();
		PeriodoImp peImp = new PeriodoImp();
		CarritoCompra c = new CarritoCompra();
		MetodoPago m = new MetodoPago();
		Periodo p = new Periodo();
		c = cImp.econtrarId(f.getIdCarrito().getId());
		m = mImp.econtrarId(f.getIdMetodoPago().getId());
		p = peImp.econtrarId(f.getIdPeriodo().getId());
		System.out.println("Carrito" + c.toString());
		System.out.println("MetodoPago" + m.toString());
		f.setIdMetodoPago(m);
		f.setIdCarrito(c);
		f.setIdPeriodo(p);
		fImp.actualizar(f);
		return "/faces/Admin/facturas.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		FacturaImp fImp = new FacturaImp();
		fImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/facturas.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaFactura " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		FacturaImp fImp = new FacturaImp();
		if (idMetodoPago != 0) {
			this.listaFactura = fImp.exportFactura(idMetodoPago);
		} else {
			this.listaFactura = fImp.encontrarTodo();

		}

		ExportarExcelFactura excelExportar = new ExportarExcelFactura(this.listaFactura);
		excelExportar.export(response);
	}

}
