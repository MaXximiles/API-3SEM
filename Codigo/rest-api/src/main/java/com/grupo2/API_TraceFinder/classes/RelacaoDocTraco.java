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
	private Long relacaodocumentotracoid;
	
	@Column(name="traco_id")
	private String tracoid;
	
	@Column(name="doc_id")
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
