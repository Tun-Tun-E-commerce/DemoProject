package com.tt.fachada.usuario;

import java.util.List;

import com.tt.modelo.usuario.Privilegio;

public interface IPrivilegio {
	public List<Privilegio> encontrarTodo();

	public Privilegio econtrarId(int id);

	public void agregar(Privilegio privilegio);

	public void actualizar(Privilegio privilegio);

	public void eliminar(int id);
}
