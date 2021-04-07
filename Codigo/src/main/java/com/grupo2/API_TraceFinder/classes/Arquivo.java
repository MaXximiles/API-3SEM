package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "arquivo")
public class Arquivo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="arquivo_id")
	private Long arquivo_id;
	
	@Column(name="arquivo_nome")
	private String arquivo_nome;
	
	@Column(name="codelist_id")
	private String codelist_id;
		
	
	public Long getArquivo_id() {
		return arquivo_id;
	}
	public void setArquivo_id(Long arquivo_id) {
		this.arquivo_id = arquivo_id;
	}
	public String getArquivo_nome() {
		return arquivo_nome;
	}
	public void setArquivo_nome(String arquivo_nome) {
		this.arquivo_nome = arquivo_nome;
	}
	public String getCodelist_id() {
		return codelist_id;
	}
	public void setCodelist_id(String codelist_id) {
		this.codelist_id = codelist_id;
	}
	
	
	

}
