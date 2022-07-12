package com.tt.fachada.envio;

import java.util.List;

import com.tt.modelo.envio.DetallePedidoProducto;

public interface IDetallePedidoProducto {
	public List<DetallePedidoProducto> encontrarTodo();

	public DetallePedidoProducto econtrarId(int id);

	public void agregar(DetallePedidoProducto detallePedidoProducto);

	public void actualizar(DetallePedidoProducto detallePedidoProducto);

	public void eliminar(int id);
}
