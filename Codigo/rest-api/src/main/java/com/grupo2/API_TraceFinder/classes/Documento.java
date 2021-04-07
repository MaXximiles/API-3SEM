package com.grupo2.API_TraceFinder.classes; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "documento")
public class Documento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="documento_id")
	private Long documento_id;
	
	@Column(name="documento_nome")
	private String documento_nome;
	
	@Column(name="documento_pn")
	private String documento_pn;
	
	@Column(name="documento_caminho")
	private String documento_caminho;
	
	
	public Long getDocumento_id() {return documento_id;}
	public void setDocumento_id(Long documento_id) {	this.documento_id = documento_id;}
	public String getDocumento_nome() {	return documento_nome;}
	public void setDocumento_nome(String documento_nome) {this.documento_nome = documento_nome;	}
	public String getDocumento_pn() {return documento_pn;}
	public void setDocumento_pn(String documento_pn) {this.documento_pn = documento_pn;}
	public String getDocumento_caminho() {return documento_caminho;	}
	public void setDocumento_caminho(String documento_caminho) {this.documento_caminho = documento_caminho;	}
		
	
}
