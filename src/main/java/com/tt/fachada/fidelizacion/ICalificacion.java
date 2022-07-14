package com.tt.fachada.fidelizacion;

import java.util.List;

import com.tt.modelo.fidelizacion.Calificacion;

public interface ICalificacion {
	public List<Calificacion> encontrarTodo();

	public Calificacion econtrarId(int id);

	public void agregar(Calificacion calificacion);

	public void actualizar(Calificacion calificacion);

	public void eliminar(int id);
}
