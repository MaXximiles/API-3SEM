package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lep")
public class Lep {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lep_id")
	private Long lepId;
	
	@Column(name="lep_bloco")
	private String lepBloco;
	
	@Column(name="lep_code")
	private String lepCode;
	
	@Column(name="lep_pagina")
	private String lepPagina;
	
	@Column(name="lep_modificacao")
	private String lepModificacao;
	
	@Column(name="lep_revisao")
	private String lepRevisao;
	
	@Column(name="arquivo_id")
	private Long arquivoId;
	
	@Column(name="documento_id")
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
