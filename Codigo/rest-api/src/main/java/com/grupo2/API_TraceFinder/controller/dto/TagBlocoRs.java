package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Tag;
import com.grupo2.API_TraceFinder.classes.TagBloco;

public class TagBlocoRs {
	
	private Long tagBlocoId;
	private Long tagId;
	private Long blocoId;
	
	public static TagBlocoRs converter(TagBloco tagBloco) 
	{
		var taBloco = new TagBlocoRs();
		taBloco.setTagBlocoId(tagBloco.getTagBlocoId());
		taBloco.setTagId(tagBloco.getTagId());
		taBloco.setBlocoId(tagBloco.getBlocoId());
		return taBloco;	
	}
	
	
	public Long getTagBlocoId() {
		return tagBlocoId;
	}
	public void setTagBlocoId(Long tagBlocoId) {
		this.tagBlocoId = tagBlocoId;
	}
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	public Long getBlocoId() {
		return blocoId;
	}
	public void setBlocoId(Long blocoId) {
		this.blocoId = blocoId;
	}
	
	
	

}
