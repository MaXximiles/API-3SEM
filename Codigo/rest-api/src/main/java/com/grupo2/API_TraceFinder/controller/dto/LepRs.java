package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Lep;

public class LepRs {

	
	private Long lepId;
	private String lepBloco;
	private String lepCode;
	private Long lepPagina;
	private String lepModificacao;
	private String lepRevisao;
	private Long arquivoId;
	private Long documentoid;
	
	public static LepRs converter(Lep lep) {
		var lep1 = new LepRs();
		lep1.setLepId(lep.getLepId());
		lep1.setLepBloco(lep.getLepBloco());
		lep1.setLepCode(lep.getLepCode());
		lep1.setLepModificacao(lep.getLepModificacao());
		lep1.setLepPagina(lep.getLepPagina());
		lep1.setLepRevisao(lep.getLepRevisao());
		lep1.setArquivoId(lep.getArquivoId());
		lep1.setDocumentoid(lep.getDocumentoid());
		return lep1;
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

	public Long getLepPagina() {
		return lepPagina;
	}

	public void setLepPagina( Long lepPagina) {
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

	public Long getDocumentoid() {
		return documentoid;
	}

	public void setDocumentoid(Long documentoid) {
		this.documentoid = documentoid;
	}
		
	
	
	
	
	
	
}
