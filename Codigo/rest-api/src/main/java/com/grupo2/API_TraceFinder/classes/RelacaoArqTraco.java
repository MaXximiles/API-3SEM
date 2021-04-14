package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relacao_arquivo_traco")
public class RelacaoArqTraco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="relacao_arquivo_traco_id")
	private Long relacaoarquivotracoid;
	
	@Column(name="traco_id")
	private String tracoid;
	
	@Column(name="arquivo_id")
	private String arquivoid;

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
