package com.tt.fachada.venta;

import java.util.List;

import com.tt.modelo.venta.CarritoCompra;

public interface ICarritoCompra {
	public List<CarritoCompra> encontrarTodo();

	public CarritoCompra econtrarId(int id);

	public void agregar(CarritoCompra carritoCompra);

	public void actualizar(CarritoCompra carritoCompra);

	public void eliminar(int id);
}
