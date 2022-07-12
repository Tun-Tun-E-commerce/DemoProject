package com.tt.fachada.envio;

import java.util.List;

import com.tt.modelo.envio.CompaniaEnvio;

public interface ICompaniaEnvio {
	public List<CompaniaEnvio> encontrarTodo();

	public CompaniaEnvio econtrarId(int id);

	public void agregar(CompaniaEnvio companiaEnvio);

	public void actualizar(CompaniaEnvio companiaEnvio);

	public void eliminar(int id);
}
