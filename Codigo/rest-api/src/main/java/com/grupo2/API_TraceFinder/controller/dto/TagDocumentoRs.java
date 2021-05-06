package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.TagDocumento;

public class TagDocumentoRs {
	
	private Long tagDocId;
	private Long tagId;
	private Long documentoId;
	
	public static TagDocumentoRs converter(TagDocumento tagDoc) 
	{
		var tagdoc = new TagDocumentoRs();
		tagdoc.setDocumentoId(tagDoc.getDocumentoId());
		tagdoc.setTagDocId(tagDoc.getTagDocId());
		tagdoc.setTagId(tagDoc.getTagId());
		return tagdoc;	
	}
	
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
