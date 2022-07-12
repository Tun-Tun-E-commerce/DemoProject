package com.tt.fachada.envio;

import java.util.List;

import com.tt.modelo.envio.Pedido;

public interface IPedido {
	public List<Pedido> encontrarTodo();

	public Pedido econtrarId(int id);

	public void agregar(Pedido pedido);

	public void actualizar(Pedido pedido);

	public void eliminar(int id);
}
