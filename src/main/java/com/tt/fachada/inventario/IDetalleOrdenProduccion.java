package com.tt.fachada.inventario;

import java.util.List;

import com.tt.modelo.inventario.DetalleOrdenProduccion;

public interface IDetalleOrdenProduccion {
	public List<DetalleOrdenProduccion> encontrarTodo();

	public DetalleOrdenProduccion econtrarId(int id);

	public void agregar(DetalleOrdenProduccion detalleOrdenProduccion);

	public void actualizar(DetalleOrdenProduccion detalleOrdenProduccion);

	public void eliminar(int id);
}
