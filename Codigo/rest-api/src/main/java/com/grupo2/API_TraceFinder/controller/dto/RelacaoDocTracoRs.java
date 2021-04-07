package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.RelacaoDocTraco;

public class RelacaoDocTracoRs {

	private Long relacao_documento_traco_id;
	private String traco_id;
	private String doc_id;
	
	public static RelacaoDocTracoRs converter(RelacaoDocTraco rDocTraco) {
		var relacaoDocTraco = new RelacaoDocTracoRs();
		relacaoDocTraco.setRelacao_documento_traco_id(rDocTraco.getRelacao_documento_traco_id());
		relacaoDocTraco.setDoc_id(rDocTraco.getDoc_id());
		relacaoDocTraco.setTraco_id(rDocTraco.getTraco_id());
		return relacaoDocTraco;
	}

	public Long getRelacao_documento_traco_id() {
		return relacao_documento_traco_id;
	}

	public void setRelacao_documento_traco_id(Long relacao_documento_traco_id) {
		this.relacao_documento_traco_id = relacao_documento_traco_id;
	}

	public String getTraco_id() {
		return traco_id;
	}

	public void setTraco_id(String traco_id) {
		this.traco_id = traco_id;
	}

	public String getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	
	
	
	
}
