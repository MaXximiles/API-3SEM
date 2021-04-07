package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "traco_doc")
public class TracoDoc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="traco_doc_id")
	private Long traco_doc_id;
	
	@Column(name="traco_doc_nome")
	private String traco_doc_nome;
	
	@Column(name="traco_doc_descricao")
	private String traco_doc_descricao;
	
	@Column(name="traco_doc_codigo")
	private String traco_doc_codigo;
	

	public Long getTraco_doc_id() {
		return traco_doc_id;
	}

	public void setTraco_doc_id(Long traco_doc_id) {
		this.traco_doc_id = traco_doc_id;
	}

	public String getTraco_doc_nome() {
		return traco_doc_nome;
	}

	public void setTraco_doc_nome(String traco_doc_nome) {
		this.traco_doc_nome = traco_doc_nome;
	}

	public String getTraco_doc_descricao() {
		return traco_doc_descricao;
	}

	public void setTraco_doc_descricao(String traco_doc_descricao) {
		this.traco_doc_descricao = traco_doc_descricao;
	}

	public String getTraco_doc_codigo() {
		return traco_doc_codigo;
	}

	public void setTraco_doc_codigo(String traco_doc_codigo) {
		this.traco_doc_codigo = traco_doc_codigo;
	}
	
	
	
}
