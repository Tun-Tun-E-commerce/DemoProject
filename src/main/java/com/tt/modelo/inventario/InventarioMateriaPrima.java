package com.tt.modelo.inventario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "inventariomateriasprimas")
public class InventarioMateriaPrima implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "saldo")
	private double saldo;
	@ManyToOne
	@JoinColumn(name = "idMateriaPrima")
	private MateriaPrima idMateriaPrima;

	@OneToMany(targetEntity = OrdenProduccion.class, mappedBy = "idInventarioMateriaPrima")
	private List<OrdenProduccion> listaOrdenProduccion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public MateriaPrima getIdMateriaPrima() {
		return idMateriaPrima;
	}

	public void setIdMateriaPrima(MateriaPrima idMateriaPrima) {
		this.idMateriaPrima = idMateriaPrima;
	}

	public List<OrdenProduccion> getListaOrdenProduccion() {
		return listaOrdenProduccion;
	}

	public void setListaOrdenProduccion(List<OrdenProduccion> listaOrdenProduccion) {
		this.listaOrdenProduccion = listaOrdenProduccion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
