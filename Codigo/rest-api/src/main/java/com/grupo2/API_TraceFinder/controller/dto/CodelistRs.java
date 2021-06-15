package com.grupo2.API_TraceFinder.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo2.API_TraceFinder.classes.Arquivo;
import com.grupo2.API_TraceFinder.classes.Codelist;
import com.grupo2.API_TraceFinder.classes.TracoDoc;

public class CodelistRs {
	
	private Long codelistid;	
	private String codelistsecao;
	private String codelistsubsecao;
	private String codelistnumbloco;
	private String codelistnomebloco;
	private String codelistcodebloco;
	private String codelistcaminho;
	private Long documentoid;
	private List<TracoDocRs> tracos; 
	
	private List<ArquivoRs> arquivos;
	
	public static CodelistRs converter(Codelist code, List<TracoDoc> lstTracos, List<Arquivo> lstArquivos) {
		var codelist = new CodelistRs();
		codelist.setCodelistid(code.getCodelistid());
		codelist.setCodelistcaminho(code.getCodelistcaminho());
		codelist.setCodelistcodebloco(code.getCodelistcodebloco());
		codelist.setCodelistnumbloco(code.getCodelistnumbloco());
		codelist.setCodelistnomebloco(code.getCodelistnomebloco());
		codelist.setCodelistsecao(code.getCodelistsecao());
		codelist.setCodelistsubsecao(code.getCodelistsubsecao());
		codelist.setDocumentoid(code.getDocumentoid());
		codelist.setTracos(TracoDocRs.converter(lstTracos));
		
		codelist.setArquivos(ArquivoRs.converter(lstArquivos));
		
		return codelist;
	}
	
	
	
	public String getCodelistnumbloco() {
		return codelistnumbloco;
	}

	public void setCodelistnumbloco(String codelistnumbloco) {
		this.codelistnumbloco = codelistnumbloco;
	}

	public List<ArquivoRs> getArquivos() {
		return arquivos;
	}



	public void setArquivos(List<ArquivoRs> arquivos) {
		this.arquivos = arquivos;
	}



	public List<TracoDocRs> getTracos() {
		return tracos;
	}



	public void setTracos(List<TracoDocRs> tracos) {
		this.tracos = tracos;
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

	public Long getDocumentoid() {
		return documentoid;
	}

	public void setDocumentoid(Long documentoid) {
		this.documentoid = documentoid;
	}

	

	
	
}
