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

import com.tt.implementacion.envio.DevolucionImp;
import com.tt.implementacion.venta.FacturaImp;
import com.tt.modelo.envio.Devolucion;
import com.tt.modelo.venta.Factura;
import com.tt.utilidades.envio.ExportarExcelDevolucion;

@ManagedBean(name = "devBean")
@RequestScoped
public class DevolucionBean {
	Devolucion d = new Devolucion();
	List<Devolucion> listaDevolucion = new ArrayList<Devolucion>();
	List<Factura> listaFactura = new ArrayList<Factura>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idFactura;

	private void LlenarFactura() {
		FacturaImp fImp = new FacturaImp();
		this.listaFactura = fImp.encontrarTodo();
	}

	public Devolucion getD() {
		return d;
	}

	public void setD(Devolucion d) {
		this.d = d;
	}

	public List<Devolucion> getListaDevolucion() {
		return listaDevolucion;
	}

	public void setListaDevolucion(List<Devolucion> listaDevolucion) {
		this.listaDevolucion = listaDevolucion;
	}

	public List<Factura> getListaFactura() {
		return listaFactura;
	}

	public void setListaFactura(List<Factura> listaFactura) {
		this.listaFactura = listaFactura;
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

	public DevolucionBean() {
		this.LlenarFactura();
	}

	public List<Devolucion> encontrarTodo() {
		DevolucionImp dImp = new DevolucionImp();
		this.listaDevolucion = dImp.encontrarTodo();
		return this.listaDevolucion;
	}

	public String agregar() {
		DevolucionImp dImp = new DevolucionImp();
		FacturaImp fImp = new FacturaImp();
		Factura f = new Factura();
		f = fImp.econtrarId(idFactura);
		d.setIdFactura(f);
		dImp.agregar(d);
		return "/faces/Admin/return.xhtml?faces-redirect=true";
	}

	public String encontrarId(int id) {
		System.out.println("Entro al editar" + id);
		DevolucionImp dImp = new DevolucionImp();
		d = dImp.econtrarId(id);
		this.sessionMap.put("d", d);
		return "/faces/Admin/editar/devolucion.xhtml?faces-redirect=true";
	}

	public String actualizar(Devolucion d) {
		System.out.println("Entro a actualizar Orden Produccion");
		DevolucionImp dImp = new DevolucionImp();
		FacturaImp fImp = new FacturaImp();
		Factura f = new Factura();
		f = fImp.econtrarId(d.getIdFactura().getId());
		;
		System.out.println("Factura" + f.toString());
		d.setIdFactura(f);
		dImp.actualizar(d);
		return "/faces/Admin/return.xhtml?faces-redirect=true";
	}

	public String eliminar(int id) {
		DevolucionImp dImp = new DevolucionImp();
		dImp.eliminar(id);
		System.out.print("Se elimino el dato");
		return "/faces/Admin/return.xhtml?faces-redirect=true";
	}

	public void exportar() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=listaDevolucion " + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		DevolucionImp dImp = new DevolucionImp();
		if(idFactura !=0) {
			this.listaDevolucion = dImp.exportDevolucion(idFactura);
		}else {
			this.listaDevolucion = dImp.encontrarTodo();
		}


		ExportarExcelDevolucion excelExportar = new ExportarExcelDevolucion(this.listaDevolucion);
		excelExportar.export(response);
	}

}
