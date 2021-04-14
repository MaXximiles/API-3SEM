package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Documento;

public class DocumentoRs {

	private Long documentoid;
	private String documentonome;
	private String documentopn;
	private String documentocaminho;
	
	public static DocumentoRs converter(Documento doc) {
		var documento = new DocumentoRs();
		documento.setDocumentoid(doc.getDocumentoid());
		documento.setDocumentonome(doc.getDocumentonome());
		documento.setDocumentopn(doc.getDocumentopn());
		documento.setDocumentocaminho(doc.getDocumentocaminho());
		return documento;
		
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
