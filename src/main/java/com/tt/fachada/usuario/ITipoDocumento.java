package com.tt.fachada.usuario;

import java.util.List;

import com.tt.modelo.usuario.TipoDocumento;

public interface ITipoDocumento {

	public List<TipoDocumento> encontrarTodo();

	public TipoDocumento econtrarId(int id);

	public void agregar(TipoDocumento tipoDocumento);

	public void actualizar(TipoDocumento tipoDocumento);

	public void eliminar(int id);
}
