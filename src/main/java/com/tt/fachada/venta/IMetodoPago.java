package com.tt.fachada.venta;

import java.util.List;

import com.tt.modelo.venta.MetodoPago;

public interface IMetodoPago {
	public List<MetodoPago> encontrarTodo();

	public MetodoPago econtrarId(int id);

	public void agregar(MetodoPago metodoPago);

	public void actualizar(MetodoPago metodoPago);

	public void eliminar(int id);
}
