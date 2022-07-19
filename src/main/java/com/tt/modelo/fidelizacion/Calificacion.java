package com.tt.modelo.fidelizacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "calificaciones")
public class Calificacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nota")
	private short nota;
	@ManyToOne
	@JoinColumn(name = "idPqr")
	private Pqr idPqr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getNota() {
		return nota;
	}

	public void setNota(short nota) {
		this.nota = nota;
	}

	public Pqr getIdPqr() {
		return idPqr;
	}

	public void setIdPqr(Pqr idPqr) {
		this.idPqr = idPqr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
