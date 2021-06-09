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
	private Long codelistid;
	
	@Column(name="arquivo_revisao")
	private String arquivorevisao;
	
	@Column(name="arquivo_caminho")
	private String arquivocaminho;
 
	public String getArquivorevisao() {
		return arquivorevisao;
	}

	public void setArquivorevisao(String arquivorevisao) {
		this.arquivorevisao = arquivorevisao;
	}

	public String getArquivocaminho() {
		return arquivocaminho;
	}

	public void setArquivocaminho(String arquivocaminho) {
		this.arquivocaminho = arquivocaminho;
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

	public Long getCodelistid() {
		return codelistid;
	}

	public void setCodelistid(Long codelistid) {
		this.codelistid = codelistid;
	}
		
	
	
	
	
	

}
