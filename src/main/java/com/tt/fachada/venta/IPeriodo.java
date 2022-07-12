package com.tt.fachada.venta;

import java.util.List;

import com.tt.modelo.venta.Periodo;

public interface IPeriodo {
	public List<Periodo> encontrarTodo();

	public Periodo econtrarId(int id);

	public void agregar(Periodo periodo);

	public void actualizar(Periodo periodo);

	public void eliminar(int id);
}
