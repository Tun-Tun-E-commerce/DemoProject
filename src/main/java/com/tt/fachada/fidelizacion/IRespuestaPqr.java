package com.tt.fachada.fidelizacion;

import java.util.List;

import com.tt.modelo.fidelizacion.RespuestaPqr;

public interface IRespuestaPqr {
	public List<RespuestaPqr> encontrarTodo();

	public RespuestaPqr econtrarId(int id);

	public void agregar(RespuestaPqr respuestPqr);

	public void actualizar(RespuestaPqr respuestPqr);

	public void eliminar(int id);
}
