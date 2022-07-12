package com.tt.fachada.fidelizacion;

import java.util.List;

import com.tt.modelo.fidelizacion.Pqr;

public interface IPqr {
	public List<Pqr> encontrarTodo();

	public Pqr econtrarId(int id);

	public void agregar(Pqr pqr);

	public void actualizar(Pqr pqr);

	public void eliminar(int id);
}
