package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.ArquivoPagina;

public class ArquivoPaginaRs {
	
	private Long arquivo_pagina_id;
	private String arquivo_id;
	private String arquivo_pagina_pagina;
	private String arquivo_pagina_modificacao;
	private String arquivo_pagina_revisao;
	private String arquivo_pagina_data_modificacao;
	
	
	public static ArquivoPaginaRs converter(ArquivoPagina arqPag) {
		var arquivo = new ArquivoPaginaRs();
		arquivo.setArquivo_id(arqPag.getArquivo_id());
		arquivo.setArquivo_pagina_data_modificacao(arqPag.getArquivo_pagina_data_modificacao());
		arquivo.setArquivo_pagina_id(arqPag.getArquivo_pagina_id());
		arquivo.setArquivo_pagina_modificacao(arqPag.getArquivo_pagina_modificacao());
		arquivo.setArquivo_pagina_pagina(arqPag.getArquivo_pagina_pagina());
		arquivo.setArquivo_pagina_revisao(arqPag.getArquivo_pagina_revisao());
		return arquivo;
		
	}
	
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
