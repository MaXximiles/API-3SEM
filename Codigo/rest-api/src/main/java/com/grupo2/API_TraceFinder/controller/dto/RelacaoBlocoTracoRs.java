package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.RelacaoBlocoTraco;

public class RelacaoBlocoTracoRs {

	private Long relacaotracoblocoid;
	private String tracoid;
	private String blocoid;
	
	
	
	public static RelacaoBlocoTracoRs converter(RelacaoBlocoTraco rBlocoTraco) {
		var relacaoBlocoTraco = new RelacaoBlocoTracoRs();
		relacaoBlocoTraco.setRelacaotracoblocoid(rBlocoTraco.getRelacaotracoblocoid());
		relacaoBlocoTraco.setBlocoid(rBlocoTraco.getBlocoid());
		relacaoBlocoTraco.setTracoid(rBlocoTraco.getTracoid());
		return relacaoBlocoTraco;
	}

	public Long getRelacaotracoblocoid() {
		return relacaotracoblocoid;
	}

	public void setRelacaotracoblocoid(Long relacaotracoblocoid) {
		this.relacaotracoblocoid = relacaotracoblocoid;
	}

	public String getTracoid() {
		return tracoid;
	}

	public void setTracoid(String tracoid) {
		this.tracoid = tracoid;
	}

	public String getBlocoid() {
		return blocoid;
	}

	public void setBlocoid(String blocoid) {
		this.blocoid = blocoid;
	}

	
	
}
