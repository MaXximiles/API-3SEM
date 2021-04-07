package com.grupo2.API_TraceFinder.controller.dto;


public class ArquivoRq {
	
	private Long arquivo_id;
	private String arquivo_nome;
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
