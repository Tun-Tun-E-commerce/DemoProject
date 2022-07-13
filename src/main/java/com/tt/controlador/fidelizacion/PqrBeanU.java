package com.tt.controlador.fidelizacion;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.tt.implementacion.fidelizacion.PqrImp;
import com.tt.modelo.fidelizacion.Pqr;


@ManagedBean(name = "pqrBeanU")
@RequestScoped
public class PqrBeanU {
	Pqr pqr = new Pqr();
	List<Pqr> listPqr = new ArrayList<Pqr>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();


	
	public Pqr getPqr() {
		return pqr;
	}

	public void setPqr(Pqr pqr) {
		this.pqr = pqr;
	}

	public List<Pqr> getListPqr() {
		return listPqr;
	}

	public void setListPqr(List<Pqr> listPqr) {
		this.listPqr = listPqr;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	
	public String agregarU() {
		PqrImp pqrImp = new PqrImp();
		pqrImp.agregar(pqr);
		return "/faces/autenticacion/user-account.xhtml?faces-redirect=true";
	}



	public String actualizar(Pqr pqr) {
		System.out.println("Entro a actualizar Pqr");
		PqrImp pqrImp = new PqrImp();
		pqrImp.actualizar(pqr);
		return "";
	}

	public String eliminar(int id) {
		PqrImp pqrImp = new PqrImp();
		pqrImp.eliminar(id);
		System.out.print("Se elimino su Pqr");
		return "";
	}
	
	

}
