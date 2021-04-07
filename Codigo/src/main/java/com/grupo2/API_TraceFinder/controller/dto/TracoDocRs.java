package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.TracoDoc;

public class TracoDocRs {
	
	private Long traco_doc_id;
	private String traco_doc_nome;
	private String traco_doc_descricao;
	private String traco_doc_codigo;
	
	public static TracoDocRs converter(TracoDoc tracoDoc) {
		var tracoDocumento = new TracoDocRs();
		tracoDocumento.setTraco_doc_id(tracoDoc.getTraco_doc_id());
		tracoDocumento.setTraco_doc_nome(tracoDoc.getTraco_doc_nome());
		tracoDocumento.setTraco_doc_descricao(tracoDoc.getTraco_doc_descricao());
		tracoDocumento.setTraco_doc_codigo(tracoDoc.getTraco_doc_codigo());
		return tracoDocumento;
		
	}

	public Long getTraco_doc_id() {
		return traco_doc_id;
	}

	public void setTraco_doc_id(Long traco_doc_id) {
		this.traco_doc_id = traco_doc_id;
	}

	public String getTraco_doc_nome() {
		return traco_doc_nome;
	}

	public void setTraco_doc_nome(String traco_doc_nome) {
		this.traco_doc_nome = traco_doc_nome;
	}

	public String getTraco_doc_descricao() {
		return traco_doc_descricao;
	}

	public void setTraco_doc_descricao(String traco_doc_descricao) {
		this.traco_doc_descricao = traco_doc_descricao;
	}

	public String getTraco_doc_codigo() {
		return traco_doc_codigo;
	}

	public void setTraco_doc_codigo(String traco_doc_codigo) {
		this.traco_doc_codigo = traco_doc_codigo;
	}	
	
}
