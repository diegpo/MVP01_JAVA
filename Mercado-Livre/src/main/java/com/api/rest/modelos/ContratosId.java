package com.api.rest.modelos;

import java.io.Serializable;

public class ContratosId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int idcliente;
	private int idplano;
     

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public int getIdplano() {
		return idplano;
	}

	public void setIdplano(int idplano) {
		this.idplano = idplano;
	}

	public ContratosId() { }
     
	public ContratosId(int id, int idcliente, int idplano) {
		this.id = id;
		this.idcliente = idcliente;
		this.idplano = idplano;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idcliente;
		result = prime * result + idplano;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContratosId other = (ContratosId) obj;
		if (id != other.id)
			return false;
		if (idcliente != other.idcliente)
			return false;
		if (idplano != other.idplano)
			return false;
		return true;
	}     
	
}
