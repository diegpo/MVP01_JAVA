package com.api.rest.modelos;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="tb_contratos")
@IdClass(ContratosId.class)
public class Contratos implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    private int id;    
	@Id
	private int idcliente;	
	@Id
	private int idplano;    
	private String dtvenc, obs;
	private float valor;
	
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
	public String getDtvenc() {
		return dtvenc;
	}
	public void setDtvenc(String dtvenc) {
		this.dtvenc = dtvenc;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
}
