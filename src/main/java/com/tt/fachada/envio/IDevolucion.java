package com.tt.fachada.envio;

import java.util.List;

import com.tt.modelo.envio.Devolucion;

public interface IDevolucion {
	public List<Devolucion> encontrarTodo();

	public Devolucion econtrarId(int id);

	public void agregar(Devolucion devolucion);

	public void actualizar(Devolucion devolucion);

	public void eliminar(int id);
}
