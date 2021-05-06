package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tag_bloco")
public class TagBloco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tag_blocoid")
	private Long tagBlocoId;
	
	@Column(name="tag_id")
	private Long tagId;
	
	@Column(name="bloco_id")
	private Long blocoId;

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
