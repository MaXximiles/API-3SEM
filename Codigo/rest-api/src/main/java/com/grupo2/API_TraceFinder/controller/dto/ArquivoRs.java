package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Arquivo;

public class ArquivoRs {

	private Long arquivoid;
	private String arquivonome;
	private String codelistid;
	
	public static ArquivoRs converter(Arquivo arq) {
		var arquivo = new ArquivoRs();
		arquivo.setArquivoid(arq.getArquivoid());
		arquivo.setArquivonome(arq.getArquivonome());
		arquivo.setCodelistid(arq.getCodelistid());
		return arquivo;
	}

	public Long getArquivoid() {
		return arquivoid;
	}

	public void setArquivoid(Long arquivoid) {
		this.arquivoid = arquivoid;
	}

	public String getArquivonome() {
		return arquivonome;
	}

	public void setArquivonome(String arquivonome) {
		this.arquivonome = arquivonome;
	}

	public String getCodelistid() {
		return codelistid;
	}

	public void setCodelistid(String codelistid) {
		this.codelistid = codelistid;
	}
	
	
}
