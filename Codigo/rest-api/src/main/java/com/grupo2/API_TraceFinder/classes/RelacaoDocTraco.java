package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "relacao_documento_traco")
public class RelacaoDocTraco {
	
	@EmbeddedId
	@Column(name="traco_id")
	private String tracoid;
	@Column(name="doc_id")
	private String docid;

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
