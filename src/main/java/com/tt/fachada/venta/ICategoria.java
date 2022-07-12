package com.tt.fachada.venta;

import java.util.List;

import com.tt.modelo.venta.Categoria;

public interface ICategoria {
	public List<Categoria> encontrarTodo();

	public Categoria econtrarId(int id);

	public void agregar(Categoria categoria);

	public void actualizar(Categoria categoria);

	public void eliminar(int id);
}
