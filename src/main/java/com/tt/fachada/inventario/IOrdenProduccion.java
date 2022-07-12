package com.tt.fachada.inventario;

import java.util.List;

import com.tt.modelo.inventario.OrdenProduccion;

public interface IOrdenProduccion {
	public List<OrdenProduccion> encontrarTodo();

	public OrdenProduccion econtrarId(int id);

	public void agregar(OrdenProduccion ordenProduccion);

	public void actualizar(OrdenProduccion ordenProduccion);

	public void eliminar(int id);
}
