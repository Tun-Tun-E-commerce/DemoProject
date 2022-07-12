package com.tt.fachada.venta;

import java.util.List;

import com.tt.modelo.venta.Producto;

public interface IProducto {
	public List<Producto> encontrarTodo();

	public Producto econtrarId(int id);

	public void agregar(Producto producto);

	public void actualizar(Producto producto);

	public void eliminar(int id);
}
