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
	private Long arquivopaginaid;
	
	@Column(name="arquivo_id")
	private String arquivoid;
	
	@Column(name="arquivo_pagina_pagina")
	private String arquivopaginapagina;
	
	@Column(name="arquivo_pagina_modificacao")
	private String arquivopaginamodificacao;
	
	@Column(name="arquivo_pagina_revisao")
	private String arquivopaginarevisao;
	
	@Column(name="arquivo_pagina_data_modificacao")
	private String arquivopaginadatamodificacao;
	

	public Long getArquivopaginaid() {
		return arquivopaginaid;
	}

	public void setArquivopaginaid(Long arquivo_paginaid) {
		this.arquivopaginaid = arquivo_paginaid;
	}

	public String getArquivoid() {
		return arquivoid;
	}

	public void setArquivoid(String arquivoid) {
		this.arquivoid = arquivoid;
	}

	public String getArquivopaginapagina() {
		return arquivopaginapagina;
	}

	public void setArquivopaginapagina(String arquivopaginapagina) {
		this.arquivopaginapagina = arquivopaginapagina;
	}

	public String getArquivopaginamodificacao() {
		return arquivopaginamodificacao;
	}

	public void setArquivopaginamodificacao(String arquivopaginamodificacao) {
		this.arquivopaginamodificacao = arquivopaginamodificacao;
	}

	public String getArquivopaginarevisao() {
		return arquivopaginarevisao;
	}

	public void setArquivopaginarevisao(String arquivopaginarevisao) {
		this.arquivopaginarevisao = arquivopaginarevisao;
	}

	public String getArquivopaginadatamodificacao() {
		return arquivopaginadatamodificacao;
	}

	public void setArquivopaginadatamodificacao(String arquivopaginadatamodificacao) {
		this.arquivopaginadatamodificacao = arquivopaginadatamodificacao;
	}

	
	
}
