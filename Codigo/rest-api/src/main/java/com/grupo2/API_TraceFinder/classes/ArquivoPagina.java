package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "arquivo_pagina")
public class ArquivoPagina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="arquivo_pagina_id")
	private Long arquivo_pagina_id;
	
	@Column(name="arquivo_id")
	private String arquivo_id;
	
	@Column(name="arquivo_pagina_pagina")
	private String arquivo_pagina_pagina;
	
	@Column(name="arquivo_pagina_modificacao")
	private String arquivo_pagina_modificacao;
	
	@Column(name="arquivo_pagina_revisao")
	private String arquivo_pagina_revisao;
	
	@Column(name="arquivo_pagina_data_modificacao")
	private String arquivo_pagina_data_modificacao;

	
	public Long getArquivo_pagina_id() {
		return arquivo_pagina_id;
	}
	public void setArquivo_pagina_id(Long arquivo_pagina_id) {
		this.arquivo_pagina_id = arquivo_pagina_id;
	}
	public String getArquivo_id() {
		return arquivo_id;
	}
	public void setArquivo_id(String arquivo_id) {
		this.arquivo_id = arquivo_id;
	}
	public String getArquivo_pagina_pagina() {
		return arquivo_pagina_pagina;
	}
	public void setArquivo_pagina_pagina(String arquivo_pagina_pagina) {
		this.arquivo_pagina_pagina = arquivo_pagina_pagina;
	}
	public String getArquivo_pagina_modificacao() {
		return arquivo_pagina_modificacao;
	}
	public void setArquivo_pagina_modificacao(String arquivo_pagina_modificacao) {
		this.arquivo_pagina_modificacao = arquivo_pagina_modificacao;
	}
	public String getArquivo_pagina_revisao() {
		return arquivo_pagina_revisao;
	}
	public void setArquivo_pagina_revisao(String arquivo_pagina_revisao) {
		this.arquivo_pagina_revisao = arquivo_pagina_revisao;
	}
	public String getArquivo_pagina_data_modificacao() {
		return arquivo_pagina_data_modificacao;
	}
	public void setArquivo_pagina_data_modificacao(String arquivo_pagina_data_modificacao) {
		this.arquivo_pagina_data_modificacao = arquivo_pagina_data_modificacao;
	}
}
