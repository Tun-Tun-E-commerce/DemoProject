package com.tt.fachada.inventario;

import java.util.List;

import com.tt.modelo.inventario.InventarioMateriaPrima;

public interface IinventarioMateriaPrima {
	public List<InventarioMateriaPrima> encontrarTodo();

	public InventarioMateriaPrima econtrarId(int id);

	public void agregar(InventarioMateriaPrima invMateriaPrima);

	public void actualizar(InventarioMateriaPrima invMateriaPrima);

	public void eliminar(int id);
}
