package com.grupo2.API_TraceFinder.controller.dto;


public class ArquivoRq {
	
	private Long arquivoid;
	private String arquivonome;
	private Long codelistid;
	private String arquivorevisao;
	private String arquivocaminho;
	
	public Long getArquivoid() {
		return arquivoid;
	}
	public void setArquivoid(Long arquivoid) {
		this.arquivoid = arquivoid;
	}
	public String getArquivonome() {
		return arquivonome;
	}
	public void setArquivonome(String arquivonome) {
		this.arquivonome = arquivonome;
	}
	public Long getCodelistid() {
		return codelistid;
	}
	public void setCodelistid(Long codelistid) {
		this.codelistid = codelistid;
	}
	public String getArquivorevisao() {
		return arquivorevisao;
	}
	public void setArquivorevisao(String arquivorevisao) {
		this.arquivorevisao = arquivorevisao;
	}
	public String getArquivocaminho() {
		return arquivocaminho;
	}
	public void setArquivocaminho(String arquivocaminho) {
		this.arquivocaminho = arquivocaminho;
	}
	
	
	
}
