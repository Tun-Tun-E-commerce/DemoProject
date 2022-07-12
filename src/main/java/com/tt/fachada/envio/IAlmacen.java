package com.tt.fachada.envio;

import java.util.List;

import com.tt.modelo.envio.Almacen;

public interface IAlmacen {
	public List<Almacen> encontrarTodo();

	public Almacen econtrarId(int id);

	public void agregar(Almacen almacen);

	public void actualizar(Almacen almacen);

	public void eliminar(int id);
}
