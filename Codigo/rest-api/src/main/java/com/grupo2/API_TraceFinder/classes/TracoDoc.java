package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "traco_doc")
public class TracoDoc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="traco_doc_id")
	private Long tracodocid;
	
	@Column(name="traco_doc_nome")
	private String tracodocnome;
	
	@Column(name="traco_doc_descricao")
	private String tracodocdescricao;
	
	@Column(name="traco_doc_codigo")
	private String tracodoccodigo;

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
