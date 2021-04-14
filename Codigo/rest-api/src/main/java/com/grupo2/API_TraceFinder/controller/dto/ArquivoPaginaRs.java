package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.ArquivoPagina;

public class ArquivoPaginaRs {
	
	private Long arquivopaginaid;
	private String arquivoid;
	private String arquivopaginapagina;
	private String arquivopaginamodificacao;
	private String arquivopaginarevisao;
	private String arquivopaginadatamodificacao;
	
	
	public static ArquivoPaginaRs converter(ArquivoPagina arqPag) 
	{
		var arquivo = new ArquivoPaginaRs();
		arquivo.setArquivoid(arqPag.getArquivoid());
		arquivo.setArquivopaginadatamodificacao(arqPag.getArquivopaginadatamodificacao());
		arquivo.setArquivopaginaid(arqPag.getArquivopaginaid());
		arquivo.setArquivopaginamodificacao(arqPag.getArquivopaginamodificacao());
		arquivo.setArquivopaginapagina(arqPag.getArquivopaginapagina());
		arquivo.setArquivopaginarevisao(arqPag.getArquivopaginarevisao());
		return arquivo;	
	}


	public Long getArquivopaginaid() {
		return arquivopaginaid;
	}


	public void setArquivopaginaid(Long arquivopaginaid) {
		this.arquivopaginaid = arquivopaginaid;
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
