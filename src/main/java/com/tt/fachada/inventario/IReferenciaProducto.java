package com.tt.fachada.inventario;

import java.util.List;

import com.tt.modelo.inventario.ReferenciaProducto;

public interface IReferenciaProducto {
	public List<ReferenciaProducto> encontrarTodo();

	public ReferenciaProducto econtrarId(int id);

	public void agregar(ReferenciaProducto referenciaProducto);

	public void actualizar(ReferenciaProducto referenciaProducto);

	public void eliminar(int id);
}
