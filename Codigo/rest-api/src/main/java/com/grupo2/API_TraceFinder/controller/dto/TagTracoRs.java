package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Tag;
import com.grupo2.API_TraceFinder.classes.TagTraco;

public class TagTracoRs {

	
	private Long tagTracoId;
	private Long tagId;
	private Long tracoId;
	
	
	
	public static TagTracoRs converter(TagTraco tagTraco) 
	{
		var taTraco = new TagTracoRs();
		taTraco.setTagTracoId(tagTraco.getTagTracoId());
		taTraco.setTagId(tagTraco.getTagId());
		taTraco.setTracoId(tagTraco.getTracoId());
		return taTraco;	
	}
	
	
	public Long getTagTracoId() {
		return tagTracoId;
	}
	public void setTagTracoId(Long tagTracoId) {
		this.tagTracoId = tagTracoId;
	}
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	public Long getTracoId() {
		return tracoId;
	}
	public void setTracoId(Long tracoId) {
		this.tracoId = tracoId;
	}
	
	
	
}
