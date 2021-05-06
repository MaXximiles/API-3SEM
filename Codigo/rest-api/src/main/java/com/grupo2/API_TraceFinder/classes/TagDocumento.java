package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tag_documento")
public class TagDocumento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tag_docid")
	private Long tagDocId;
	
	@Column(name="tag_id")
	private Long tagId;
	
	@Column(name="documento_id")
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
