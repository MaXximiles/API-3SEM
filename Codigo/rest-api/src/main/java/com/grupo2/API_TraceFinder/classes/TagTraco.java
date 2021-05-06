package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tag_traco")
public class TagTraco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tag_tracoid")
	private Long tagTracoId;
	
	@Column(name="tag_id")
	private Long tagId;
	
	@Column(name="traco_id")
	private Long tracoId;

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
