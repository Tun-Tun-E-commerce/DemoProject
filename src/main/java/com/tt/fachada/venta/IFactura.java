package com.tt.fachada.venta;

import java.util.List;

import com.tt.modelo.venta.Factura;

public interface IFactura {
	public List<Factura> encontrarTodo();

	public Factura econtrarId(int id);

	public void agregar(Factura factura);

	public void actualizar(Factura factura);

	public void eliminar(int id);
}
