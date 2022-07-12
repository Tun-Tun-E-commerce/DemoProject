package com.tt.fachada.inventario;

import java.util.List;

import com.tt.modelo.inventario.ProveedorMateriaPrima;

public interface IProveedorMateriaPrima {
	public List<ProveedorMateriaPrima> encontrarTodo();

	public ProveedorMateriaPrima econtrarId(int id);

	public void agregar(ProveedorMateriaPrima pmt);

	public void actualizar(ProveedorMateriaPrima pmt);

	public void eliminar(int id);
}
