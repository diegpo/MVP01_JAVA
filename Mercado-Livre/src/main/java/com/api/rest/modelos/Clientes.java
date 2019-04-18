package com.api.rest.modelos;

import java.io.Serializable;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name="tb_clientes")
public class Clientes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;	
	private String nomecli;
	private String documentocli;
	
	public String getDocumentocli() {
		return documentocli;
	}
	public void setDocumentocli(String documentocli) {
		this.documentocli = documentocli;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomecli() {
		return nomecli;
	}
	public void setNomecli(String nomecli) {
		this.nomecli = nomecli;
	}
	
}
