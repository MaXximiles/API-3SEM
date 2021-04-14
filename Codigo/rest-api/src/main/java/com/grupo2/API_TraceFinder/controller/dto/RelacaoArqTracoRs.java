package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.RelacaoArqTraco;

public class RelacaoArqTracoRs {

	private Long relacaoarquivotracoid;
	private String tracoid;
	private String arquivoid;
	
	public static RelacaoArqTracoRs converter(RelacaoArqTraco rArqTraco) {
		var relacaoArqTraco = new RelacaoArqTracoRs();
		relacaoArqTraco.setRelacaoarquivotracoid(rArqTraco.getRelacaoarquivotracoid());
		relacaoArqTraco.setArquivoid(rArqTraco.getArquivoid());
		relacaoArqTraco.setTracoid(rArqTraco.getTracoid());
		return relacaoArqTraco;
	}

	public Long getRelacaoarquivotracoid() {
		return relacaoarquivotracoid;
	}

	public void setRelacaoarquivotracoid(Long relacaoarquivotracoid) {
		this.relacaoarquivotracoid = relacaoarquivotracoid;
	}

	public String getTracoid() {
		return tracoid;
	}

	public void setTracoid(String tracoid) {
		this.tracoid = tracoid;
	}

	public String getArquivoid() {
		return arquivoid;
	}

	public void setArquivoid(String arquivoid) {
		this.arquivoid = arquivoid;
	}
	
	
	
	
}
