package com.grupo2.API_TraceFinder.controller.dto;

public class RelacaoDocTracoRq {
	
	private Long relacaodocumentotracoid;
	private String tracoid;
	private String docid;
	
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
