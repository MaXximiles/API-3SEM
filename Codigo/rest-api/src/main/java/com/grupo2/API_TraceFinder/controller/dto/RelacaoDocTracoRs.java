package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.RelacaoDocTraco;

public class RelacaoDocTracoRs {

	private Long relacaodocumentotracoid;
	private String tracoid;
	private String docid;
	
	public static RelacaoDocTracoRs converter(RelacaoDocTraco rDocTraco) {
		var relacaoDocTraco = new RelacaoDocTracoRs();
		relacaoDocTraco.setRelacaodocumentotracoid(rDocTraco.getRelacaodocumentotracoid());
		relacaoDocTraco.setDocid(rDocTraco.getDocid());
		relacaoDocTraco.setTracoid(rDocTraco.getTracoid());
		return relacaoDocTraco;
	}

	public Long getRelacaodocumentotracoid() {
		return relacaodocumentotracoid;
	}

	public void setRelacaodocumentotracoid(Long relacaodocumentotracoid) {
		this.relacaodocumentotracoid = relacaodocumentotracoid;
	}

	public String getTracoid() {
		return tracoid;
	}

	public void setTracoid(String tracoid) {
		this.tracoid = tracoid;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	
}
