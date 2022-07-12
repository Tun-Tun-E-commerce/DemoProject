package com.tt.fachada.envio;

import java.util.List;

import com.tt.modelo.envio.Envio;

public interface IEnvio {
	public List<Envio> encontrarTodo();

	public Envio econtrarId(int id);

	public void agregar(Envio envio);

	public void actualizar(Envio envio);

	public void eliminar(int id);
}
