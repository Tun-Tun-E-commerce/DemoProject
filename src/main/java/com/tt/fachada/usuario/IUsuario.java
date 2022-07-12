package com.tt.fachada.usuario;

import java.util.List;

import com.tt.modelo.usuario.Usuario;

public interface IUsuario {
	public List<Usuario> encontrarTodo();

	public Usuario econtrarId(int id);

	public void agregar(Usuario usuario);

	public void actualizar(Usuario usuario);

	public void eliminar(int id);
}
