package com.grupo2.API_TraceFinder.controller.dto;


public class TagDocumentoRq {
	
	private Long tagDocId;
	private Long tagId;
	private Long documentoId;
	
	public Long getTagDocId() {
		return tagDocId;
	}
	public void setTagDocId(Long tagDocId) {
		this.tagDocId = tagDocId;
	}
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	public Long getDocumentoId() {
		return documentoId;
	}
	public void setDocumentoId(Long documentoId) {
		this.documentoId = documentoId;
	}
	
	
}
