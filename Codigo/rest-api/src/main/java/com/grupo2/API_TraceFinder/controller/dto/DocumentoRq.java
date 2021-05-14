package com.grupo2.API_TraceFinder.controller.dto;

public class DocumentoRq {
	
	private Long documentoid;
	private String documentonome;
	private String documentopn;
	private String documentocaminho;
	private Long documentocdlistlep;
	
	
	public Long getDocumentocdlistlep() {
		return documentocdlistlep;
	}
	public void setDocumentocdlistlep(Long documentocdlistlep) {
		this.documentocdlistlep = documentocdlistlep;
	}
	public Long getDocumentoid() {
		return documentoid;
	}
	public void setDocumentoid(Long documentoid) {
		this.documentoid = documentoid;
	}
	public String getDocumentonome() {
		return documentonome;
	}
	public void setDocumentonome(String documentonome) {
		this.documentonome = documentonome;
	}
	public String getDocumentopn() {
		return documentopn;
	}
	public void setDocumentopn(String documentopn) {
		this.documentopn = documentopn;
	}
	public String getDocumentocaminho() {
		return documentocaminho;
	}
	public void setDocumentocaminho(String documentocaminho) {
		this.documentocaminho = documentocaminho;
	}
	
	
	
}
