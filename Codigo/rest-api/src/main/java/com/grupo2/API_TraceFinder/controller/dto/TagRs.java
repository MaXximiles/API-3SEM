package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Tag;

public class TagRs {
	
	private Long tagId;
	private String tagNome;
	
	
	public static TagRs converter(Tag tag) 
	{
		var ta = new TagRs();
		ta.setTagId(tag.getTagId());
		ta.setTagNome(tag.getTagNome());
		return ta;	
	}
	
	
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	public String getTagNome() {
		return tagNome;
	}
	public void setTagNome(String tagNome) {
		this.tagNome = tagNome;
	}
	
	
}
