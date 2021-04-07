package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relacao_documento_traco")
public class RelacaoDocTraco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="relacao_documento_traco_id")
	private Long relacao_documento_traco_id;
	
	@Column(name="traco_id")
	private String traco_id;
	
	@Column(name="doc_id")
	private String doc_id;
	

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
