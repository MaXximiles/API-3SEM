package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "codelist")
public class Codelist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codelist_id")
	private Long codelist_id;
	
	@Column(name="codelist_secao")
	private String codelist_secao;
	
	@Column(name="codelist_subsecao")
	private String codelist_subsecao;
	
	@Column(name="codelist_nbloco")
	private String codelist_nbloco;
	
	@Column(name="codelist_codebloco")
	private String codelist_codebloco;
	
	@Column(name="codelist_caminho")
	private String codelist_caminho;
	
	
	
	public Long getCodelist_id() {
		return codelist_id;
	}
	public void setCodelist_id(Long codelist_id) {
		this.codelist_id = codelist_id;
	}
	public String getCodelist_secao() {
		return codelist_secao;
	}
	public void setCodelist_secao(String codelist_secao) {
		this.codelist_secao = codelist_secao;
	}
	public String getCodelist_subsecao() {
		return codelist_subsecao;
	}
	public void setCodelist_subsecao(String codelist_subsecao) {
		this.codelist_subsecao = codelist_subsecao;
	}
	public String getCodelist_nbloco() {
		return codelist_nbloco;
	}
	public void setCodelist_nbloco(String codelist_nbloco) {
		this.codelist_nbloco = codelist_nbloco;
	}
	public String getCodelist_codebloco() {
		return codelist_codebloco;
	}
	public void setCodelist_codebloco(String codelist_codebloco) {
		this.codelist_codebloco = codelist_codebloco;
	}
	public String getCodelist_caminho() {
		return codelist_caminho;
	}
	public void setCodelist_caminho(String codelist_caminho) {
		this.codelist_caminho = codelist_caminho;
	}
	
	
}
