package com.api.rest.modelos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_planos")
public class Planos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nomeplano;
	private float valorplano;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeplano() {
		return nomeplano;
	}
	public void setNomeplano(String nomeplano) {
		this.nomeplano = nomeplano;
	}
	public float getValorplano() {
		return valorplano;
	}
	public void setValorplano(float valorplano) {
		this.valorplano = valorplano;
	}
	
	
}