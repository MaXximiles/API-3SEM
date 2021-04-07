package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Arquivo;

public class ArquivoRs {

	private Long arquivo_id;
	private String arquivo_nome;
	private String codelist_id;
	
	public static ArquivoRs converter(Arquivo arq) {
		var arquivo = new ArquivoRs();
		arquivo.setArquivo_id(arq.getArquivo_id());
		arquivo.setArquivo_nome(arq.getArquivo_nome());
		arquivo.setCodelist_id(arq.getCodelist_id());
		return arquivo;
	}
	
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
