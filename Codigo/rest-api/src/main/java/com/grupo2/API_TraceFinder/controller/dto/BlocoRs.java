package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Bloco;

public class BlocoRs {
	
	private Long blocoid;	
	private String blocosecao;
	private String blocosubsecao;
	private String bloconomebloco;
	private String blococodebloco;
	private String blococaminho;
	private String blocodocumentoid;
	
	public static BlocoRs converter(Bloco blc) {
		var bloco = new BlocoRs();
		bloco.setBlocoid(blc.getBlocoid());
		bloco.setBlococaminho(blc.getBlococaminho());
		bloco.setBlococodebloco(blc.getBlococodebloco());
		bloco.setBloconomebloco(blc.getBloconomebloco());
		bloco.setBlocosecao(blc.getBlocosecao());
		bloco.setBlocosubsecao(blc.getBlocosubsecao());
		return bloco;
	}

	public Long getBlocoid() {
		return blocoid;
	}

	public void setBlocoid(Long blocoid) {
		this.blocoid = blocoid;
	}

	public String getBlocosecao() {
		return blocosecao;
	}

	public void setBlocosecao(String blocosecao) {
		this.blocosecao = blocosecao;
	}

	public String getBlocosubsecao() {
		return blocosubsecao;
	}

	public void setBlocosubsecao(String blocosubsecao) {
		this.blocosubsecao = blocosubsecao;
	}

	public String getBloconomebloco() {
		return bloconomebloco;
	}

	public void setBloconomebloco(String bloconomebloco) {
		this.bloconomebloco = bloconomebloco;
	}

	public String getBlococodebloco() {
		return blococodebloco;
	}

	public void setBlococodebloco(String blococodebloco) {
		this.blococodebloco = blococodebloco;
	}

	public String getBlococaminho() {
		return blococaminho;
	}

	public void setBlococaminho(String blococaminho) {
		this.blococaminho = blococaminho;
	}

	public String getBlocodocumentoid() {
		return blocodocumentoid;
	}

	public void setBlocodocumentoid(String blocodocumentoid) {
		this.blocodocumentoid = blocodocumentoid;
	}

	
	
}