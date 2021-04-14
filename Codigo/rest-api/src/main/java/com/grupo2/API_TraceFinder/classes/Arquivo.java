package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "arquivo")
public class Arquivo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="arquivo_id")
	private Long arquivoid;
	
	@Column(name="arquivo_nome")
	private String arquivonome;
	
	@Column(name="codelist_id")
	private String codelistid;

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
