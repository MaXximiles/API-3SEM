package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Codelist;

public class CodelistRs {
	
	private Long codelist_id;
	private String codelist_secao;
	private String codelist_subsecao;
	private String codelist_nbloco;
	private String codelist_codebloco;
	private String codelist_caminho;
	
	public static CodelistRs converter(Codelist code) {
		var codelist = new CodelistRs();
		codelist.setCodelist_id(code.getCodelist_id());
		codelist.setCodelist_caminho(code.getCodelist_caminho());
		codelist.setCodelist_codebloco(code.getCodelist_codebloco());
		codelist.setCodelist_nbloco(code.getCodelist_nbloco());
		codelist.setCodelist_secao(code.getCodelist_secao());
		codelist.setCodelist_subsecao(code.getCodelist_subsecao());
		return codelist;
	}
	
	public Long getCodelist_id() {
		return codelist_id;
	}
	public void setCodelist_id(Long codelist_id) {
		this.codelist_id = codelist_id;
	}
	public String getCodelist_secao() {
		return codelist_secao;
	}
	public void setCodelist_secao(String codelist_secao) {
		this.codelist_secao = codelist_secao;
	}
	public String getCodelist_subsecao() {
		return codelist_subsecao;
	}
	public void setCodelist_subsecao(String codelist_subsecao) {
		this.codelist_subsecao = codelist_subsecao;
	}
	public String getCodelist_nbloco() {
		return codelist_nbloco;
	}
	public void setCodelist_nbloco(String codelist_nbloco) {
		this.codelist_nbloco = codelist_nbloco;
	}
	public String getCodelist_codebloco() {
		return codelist_codebloco;
	}
	public void setCodelist_codebloco(String codelist_codebloco) {
		this.codelist_codebloco = codelist_codebloco;
	}
	public String getCodelist_caminho() {
		return codelist_caminho;
	}
	public void setCodelist_caminho(String codelist_caminho) {
		this.codelist_caminho = codelist_caminho;
	}
}
