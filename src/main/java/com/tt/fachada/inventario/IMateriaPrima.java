package com.tt.fachada.inventario;

import java.util.List;

import com.tt.modelo.inventario.MateriaPrima;

public interface IMateriaPrima {
	public List<MateriaPrima> encontrarTodo();

	public MateriaPrima econtrarId(int id);

	public void agregar(MateriaPrima materiaPrima);

	public void actualizar(MateriaPrima materiaPrima);

	public void eliminar(int id);
}
