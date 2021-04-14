package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.TracoArquivo;

public class TracoArquivoRs {

	private Long tracoarquivoid;
	private String tracoarquivonome;
	private String tracoarquivodescricao;
	private String tracoarquivocodigo;
	
	public static TracoArquivoRs converter(TracoArquivo tracoArq) {
		var tracoArquivo = new TracoArquivoRs();
		tracoArquivo.setTracoarquivoid(tracoArq.getTracoarquivoid());
		tracoArquivo.setTracoarquivonome(tracoArq.getTracoarquivonome());
		tracoArquivo.setTracoarquivodescricao(tracoArq.getTracoarquivodescricao());
		tracoArquivo.setTracoarquivocodigo(tracoArq.getTracoarquivocodigo());
		return tracoArquivo;
		
	}

	public Long getTracoarquivoid() {
		return tracoarquivoid;
	}

	public void setTracoarquivoid(Long tracoarquivoid) {
		this.tracoarquivoid = tracoarquivoid;
	}

	public String getTracoarquivonome() {
		return tracoarquivonome;
	}

	public void setTracoarquivonome(String tracoarquivonome) {
		this.tracoarquivonome = tracoarquivonome;
	}

	public String getTracoarquivodescricao() {
		return tracoarquivodescricao;
	}

	public void setTracoarquivodescricao(String tracoarquivodescricao) {
		this.tracoarquivodescricao = tracoarquivodescricao;
	}

	public String getTracoarquivocodigo() {
		return tracoarquivocodigo;
	}

	public void setTracoarquivocodigo(String tracoarquivocodigo) {
		this.tracoarquivocodigo = tracoarquivocodigo;
	}	
	
	
}
