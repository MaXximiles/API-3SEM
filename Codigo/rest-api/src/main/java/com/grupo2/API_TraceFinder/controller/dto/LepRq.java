package com.grupo2.API_TraceFinder.controller.dto;


public class LepRq {

	private Long lepId;
	private String lepBloco;
	private String lepCode;
	private String lepPagina;
	private String lepModificacao;
	private String lepRevisao;
	private Long arquivoId;
	private Long documentoid;
	
	
	public Long getDocumentoid() {
		return documentoid;
	}
	public void setDocumentoid(Long documentoid) {
		this.documentoid = documentoid;
	}
	public Long getLepId() {
		return lepId;
	}
	public void setLepId(Long lepId) {
		this.lepId = lepId;
	}
	public String getLepBloco() {
		return lepBloco;
	}
	public void setLepBloco(String lepBloco) {
		this.lepBloco = lepBloco;
	}
	public String getLepCode() {
		return lepCode;
	}
	public void setLepCode(String lepCode) {
		this.lepCode = lepCode;
	}
	public String getLepPagina() {
		return lepPagina;
	}
	public void setLepPagina(String lepPagina) {
		this.lepPagina = lepPagina;
	}
	public String getLepModificacao() {
		return lepModificacao;
	}
	public void setLepModificacao(String lepModificacao) {
		this.lepModificacao = lepModificacao;
	}
	public String getLepRevisao() {
		return lepRevisao;
	}
	public void setLepRevisao(String lepRevisao) {
		this.lepRevisao = lepRevisao;
	}
	public Long getArquivoId() {
		return arquivoId;
	}
	public void setArquivoId(Long arquivoId) {
		this.arquivoId = arquivoId;
	}
	
	
}
