package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Documento;

public class DocumentoRs {

	private Long documento_id;
	private String documento_nome;
	private String documento_pn;
	private String documento_caminho;
	
	public static DocumentoRs converter(Documento doc) {
		var documento = new DocumentoRs();
		documento.setDocumento_id(doc.getDocumento_id());
		documento.setDocumento_nome(doc.getDocumento_nome());
		documento.setDocumento_pn(doc.getDocumento_pn());
		documento.setDocumento_caminho(doc.getDocumento_caminho());
		return documento;
		
	}
	
	
	public Long getDocumento_id() {
		return documento_id;
	}
	public void setDocumento_id(Long documento_id) {
		this.documento_id = documento_id;
	}
	public String getDocumento_nome() {
		return documento_nome;
	}
	public void setDocumento_nome(String documento_nome) {
		this.documento_nome = documento_nome;
	}
	public String getDocumento_pn() {
		return documento_pn;
	}
	public void setDocumento_pn(String documento_pn) {
		this.documento_pn = documento_pn;
	}
	public String getDocumento_caminho() {
		return documento_caminho;
	}
	public void setDocumento_caminho(String documento_caminho) {
		this.documento_caminho = documento_caminho;
	}
	
}
