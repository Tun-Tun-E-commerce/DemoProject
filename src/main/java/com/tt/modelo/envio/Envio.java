package com.tt.modelo.envio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tt.modelo.usuario.Usuario;

@Entity
@Table(name = "envios")
public class Envio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombreLogistico", length = 50)
	private String nombreLogistico;
	@Column(name = "descripcion")
	private String descripcion;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "idUsuario", referencedColumnName = "id")
	private Usuario idUsuario;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "idCompaniaEnvio", referencedColumnName = "id")
	private CompaniaEnvio idCompaniaEnvio;

	@OneToMany(targetEntity = Pedido.class, mappedBy = "idEnvio")
	private List<Pedido> listaPedido;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreLogistico() {
		return nombreLogistico;
	}

	public void setNombreLogistico(String nombreLogistico) {
		this.nombreLogistico = nombreLogistico;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public CompaniaEnvio getIdCompaniaEnvio() {
		return idCompaniaEnvio;
	}

	public void setIdCompaniaEnvio(CompaniaEnvio idCompaniaEnvio) {
		this.idCompaniaEnvio = idCompaniaEnvio;
	}

	public List<Pedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
