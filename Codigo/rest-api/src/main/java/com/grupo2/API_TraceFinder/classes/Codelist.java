package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "codelist")
public class Codelist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codelist_id")
	private Long codelistid;
	
	@Column(name="codelist_secao")
	private String codelistsecao;
	
	@Column(name="codelist_subsecao")
	private String codelistsubsecao;
	
	@Column(name="codelist_nomebloco")
	private String codelistnomebloco;
	
	@Column(name="codelist_codebloco")
	private String codelistcodebloco;
	
	@Column(name="codelist_caminho")
	private String codelistcaminho;

	@Column(name="documento_id")
	private String documentoid;
	

	public Long getCodelistid() {
		return codelistid;
	}

	public void setCodelistid(Long codelistid) {
		this.codelistid = codelistid;
	}

	public String getCodelistsecao() {
		return codelistsecao;
	}

	public void setCodelistsecao(String codelistsecao) {
		this.codelistsecao = codelistsecao;
	}

	public String getCodelistsubsecao() {
		return codelistsubsecao;
	}

	public void setCodelistsubsecao(String codelistsubsecao) {
		this.codelistsubsecao = codelistsubsecao;
	}

	public String getCodelistnomebloco() {
		return codelistnomebloco;
	}

	public void setCodelistnomebloco(String codelistnomebloco) {
		this.codelistnomebloco = codelistnomebloco;
	}

	public String getCodelistcodebloco() {
		return codelistcodebloco;
	}

	public void setCodelistcodebloco(String codelistcodebloco) {
		this.codelistcodebloco = codelistcodebloco;
	}

	public String getCodelistcaminho() {
		return codelistcaminho;
	}

	public void setCodelistcaminho(String codelistcaminho) {
		this.codelistcaminho = codelistcaminho;
	}
	
	public String getDocumentoid() {
		return documentoid;
	}

	public void setDocumentoid(String documentoid) {
		this.documentoid = documentoid;
	}

	
	
}
