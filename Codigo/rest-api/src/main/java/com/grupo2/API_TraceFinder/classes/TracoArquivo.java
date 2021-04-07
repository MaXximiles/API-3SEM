package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "traco_arquivo")
public class TracoArquivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="traco_arquivo_id")
	private Long traco_arquivo_id;
	
	@Column(name="traco_arquivo_nome")
	private String traco_arquivo_nome;
	
	@Column(name="traco_arquivo_descricao")
	private String traco_arquivo_descricao;
	
	@Column(name="traco_arquivo_codigo")
	private String traco_arquivo_codigo;
	
	
	public Long getTraco_arquivo_id() {
		return traco_arquivo_id;
	}
	public void setTraco_arquivo_id(Long traco_arquivo_id) {
		this.traco_arquivo_id = traco_arquivo_id;
	}
	public String getTraco_arquivo_nome() {
		return traco_arquivo_nome;
	}
	public void setTraco_arquivo_nome(String traco_arquivo_nome) {
		this.traco_arquivo_nome = traco_arquivo_nome;
	}
	public String getTraco_arquivo_descricao() {
		return traco_arquivo_descricao;
	}
	public void setTraco_arquivo_descricao(String traco_arquivo_descricao) {
		this.traco_arquivo_descricao = traco_arquivo_descricao;
	}
	public String getTraco_arquivo_codigo() {
		return traco_arquivo_codigo;
	}
	public void setTraco_arquivo_codigo(String traco_arquivo_codigo) {
		this.traco_arquivo_codigo = traco_arquivo_codigo;
	}
	
	
	
}
