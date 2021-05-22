package com.grupo2.API_TraceFinder.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupo2.API_TraceFinder.classes.TracoDoc;

public class TracoDocRs {
	
	private Long tracodocid;
	private String tracodocnome;
	private String tracodocdescricao;
	private String tracodoccodigo;
	
	public static TracoDocRs converter(TracoDoc tracoDoc) {
		var tracoDocumento = new TracoDocRs();
		tracoDocumento.setTracodocid(tracoDoc.getTracodocid());
		tracoDocumento.setTracodocnome(tracoDoc.getTracodocnome());
		tracoDocumento.setTracodocdescricao(tracoDoc.getTracodocdescricao());
		tracoDocumento.setTracodoccodigo(tracoDoc.getTracodoccodigo());
		return tracoDocumento;
		
	}
	
	
	public static List<TracoDocRs> converter(List<TracoDoc> lstTracos) {
		
		List<TracoDocRs> listTraco = new ArrayList<>();
		
		for(TracoDoc td : lstTracos)
		{
			
			listTraco.add(TracoDocRs.converter(td));
		}
		
		return listTraco;
	}

	
	

	public Long getTracodocid() {
		return tracodocid;
	}

	public void setTracodocid(Long tracodocid) {
		this.tracodocid = tracodocid;
	}

	public String getTracodocnome() {
		return tracodocnome;
	}

	public void setTracodocnome(String tracodocnome) {
		this.tracodocnome = tracodocnome;
	}

	public String getTracodocdescricao() {
		return tracodocdescricao;
	}

	public void setTracodocdescricao(String tracodocdescricao) {
		this.tracodocdescricao = tracodocdescricao;
	}

	public String getTracodoccodigo() {
		return tracodoccodigo;
	}

	public void setTracodoccodigo(String tracodoccodigo) {
		this.tracodoccodigo = tracodoccodigo;
	}



	
	
	
}
