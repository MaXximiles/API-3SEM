package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Codelist;

public class CodelistRs {
	
	private Long codelistid;	
	private String codelistsecao;
	private String codelistsubsecao;
	private String codelistnbloco;
	private String codelistcodebloco;
	private String codelistcaminho;
	private String codelistdocumentoid;
	
	public static CodelistRs converter(Codelist code) {
		var codelist = new CodelistRs();
		codelist.setCodelistid(code.getCodelistid());
		codelist.setCodelistcaminho(code.getCodelistcaminho());
		codelist.setCodelistcodebloco(code.getCodelistcodebloco());
		codelist.setCodelistnbloco(code.getCodelistnbloco());
		codelist.setCodelistsecao(code.getCodelistsecao());
		codelist.setCodelistsubsecao(code.getCodelistsubsecao());
		codelist.setCodelistdocumentoid(code.getCodelistdocumentoid());
		return codelist;
	}

	
	public String getCodelistdocumentoid() {
		return codelistdocumentoid;
	}


	public void setCodelistdocumentoid(String codelistdocumentoid) {
		this.codelistdocumentoid = codelistdocumentoid;
	}


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

	public String getCodelistnbloco() {
		return codelistnbloco;
	}

	public void setCodelistnbloco(String codelistnbloco) {
		this.codelistnbloco = codelistnbloco;
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
	
	
	
	
}
