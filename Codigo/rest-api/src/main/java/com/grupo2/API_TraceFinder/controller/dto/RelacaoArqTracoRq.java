package com.grupo2.API_TraceFinder.controller.dto;

public class RelacaoArqTracoRq {
	
	
	private Long relacao_arquivo_traco_id;
	private String traco_id;
	private String arquivo_id;
	
	
	public Long getRelacao_arquivo_traco_id() {
		return relacao_arquivo_traco_id;
	}
	public void setRelacao_arquivo_traco_id(Long relacao_arquivo_traco_id) {
		this.relacao_arquivo_traco_id = relacao_arquivo_traco_id;
	}
	public String getTraco_id() {
		return traco_id;
	}
	public void setTraco_id(String traco_id) {
		this.traco_id = traco_id;
	}
	public String getArquivo_id() {
		return arquivo_id;
	}
	public void setArquivo_id(String arquivo_id) {
		this.arquivo_id = arquivo_id;
	}
	
}
