package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.RelacaoDocTraco;

public class RelacaoDocTracoRs {

	private Long tracoid;
	private Long docid;
	private Long relacaodocumentotracoid;
	
	public static RelacaoDocTracoRs converter(RelacaoDocTraco rDocTraco) {
		var relacaoDocTraco = new RelacaoDocTracoRs();
		relacaoDocTraco.setDocid(rDocTraco.getDocid());
		relacaoDocTraco.setTracoid(rDocTraco.getTracoid());
		return relacaoDocTraco;
	}

	public Long getTracoid() {
		return tracoid;
	}

	public void setTracoid(Long tracoid) {
		this.tracoid = tracoid;
	}

	public Long getDocid() {
		return docid;
	}

	public void setDocid(Long docid) {
		this.docid = docid;
	}

	public Long getRelacaodocumentotracoid() {
		return relacaodocumentotracoid;
	}

	public void setRelacaodocumentotracoid(Long relacaodocumentotracoid) {
		this.relacaodocumentotracoid = relacaodocumentotracoid;
	}

	

	
}
