package com.tt.fachada.usuario;

import java.util.List;

import com.tt.modelo.usuario.Rol;

public interface IRol {
	public List<Rol> encontrarTodo();

	public Rol econtrarId(int id);

	public void agregar(Rol rol);

	public void actualizar(Rol rol);

	public void eliminar(int id);
}
