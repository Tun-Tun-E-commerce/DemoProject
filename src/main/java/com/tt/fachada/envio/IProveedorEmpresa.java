package com.tt.fachada.envio;

import java.util.List;

import com.tt.modelo.envio.ProveedorEmpresa;

public interface IProveedorEmpresa {
	public List<ProveedorEmpresa> encontrarTodo();

	public ProveedorEmpresa econtrarId(int id);

	public void agregar(ProveedorEmpresa proveedorE);

	public void actualizar(ProveedorEmpresa proveedorE);

	public void eliminar(int id);
}
